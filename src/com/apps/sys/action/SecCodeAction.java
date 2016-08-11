package com.apps.sys.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import message.sms.SUBMIT;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.framework.core.action.BaseAction;
import com.framework.core.utils.Const;
import common.ComFun;
import common.SmsClient;

/**
 * @author Administrator 验证码生成类 /SecCodeAction.do?method=generate
 */
@Controller
@RequestMapping("/apps/sys/SecCodeAction.do")
public class SecCodeAction extends BaseAction
{
    @RequestMapping(params = "method=generate")
    public void generate(HttpSession session, HttpServletResponse response)
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = drawImg(output);
        session.setAttribute(Const.SESSION_SECURITY_CODE, code); // 放入session
        // System.out.println(session.getAttribute(Const.SESSION_SECURITY_CODE));
        try
        {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    SmsClient smsClient = new SmsClient();

    @RequestMapping(params = "method=sendSecCode")
    public void sendSecCode(HttpSession session, HttpServletResponse response, String phoneNumber)
        throws InterruptedException
    {
        smsClient.stop();
        smsClient.start();
        SUBMIT mt = new SUBMIT();
        mt.setDestID(phoneNumber); // 用户号码
        mt.setServiceID("10");// 下行通道。（或下行短信业务）
        String code = this.getRandomCode(6);// 6位验证码
        String mailContent = "您本次注册的验证码是：" + code + "，请尽快输入并完成注册";
        mt.setMsgContent(mailContent);// 短信内容
        String retVal = "1";
        String responseMsg = "";
        boolean isSendSuccess = false;
        int sendCount = 0;
        while ("1".equals(retVal) || "2".equals(retVal) || "3".equals(retVal))
        {
            if (sendCount >= 10)// 如果连续尝试10次，都没有发送成功，直接失败
            {
                break;
            }
            sendCount++;
            Thread.sleep(1000);
            retVal = ComFun.sendSMS(mt);
            if ("1".equals(retVal))
            {
                responseMsg = "the SubmitPool was full.";
            }
            else if ("2".equals(retVal))
            {
                responseMsg = "没有建立连接";
            }
            else if ("3".equals(retVal))
            {
                responseMsg = "流量控制错误";
                Thread.sleep(1000);
            }
            else
            {
                responseMsg = "send_Success";
                isSendSuccess = true;
            }
        }
        if (!isSendSuccess)// 如果10次机会都用完了也没发送成功，清空验证码
        {
            code = "";
        }
        // smsClient.pause();
        System.out.println(responseMsg);
        session.setAttribute(Const.SESSION_SECURITY_CODE, code);// 将验证码放入到Session中
        String jsonStr =
                "{\"Success\":\"" + String.valueOf(isSendSuccess) + "\",\"Message\":\"" + responseMsg
                        + "\",\"VerifyCode\":\"" + code + "\"}";
        this.responseJson(response, jsonStr);
    }

    @RequestMapping(params = "method=getCurrentSecCode")
    public void getCurrentSecCode(HttpSession session, HttpServletResponse response)
    {
        String code = (String) session.getAttribute(Const.SESSION_SECURITY_CODE);
        this.responseText(response, code);
    }

    private String drawImg(ByteArrayOutputStream output)
    {
        String code = "";
        for (int i = 0; i < 4; i++)
        {
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66, 2, 82);
        g.setColor(color);
        g.setBackground(new Color(226, 226, 240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        g.dispose();
        try
        {
            ImageIO.write(bi, "jpg", output);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return code;
    }

    private char randomChar()
    {
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }

    private String getRandomCode(int length)
    {
        Random r = new Random();
        String s = "0123456789";
        String code = "";
        for (int i = 0; i < length; i++)
        {
            code += s.charAt(r.nextInt(s.length()));
        }
        return code;
    }
}
