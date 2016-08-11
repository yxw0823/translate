package com.framework.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import sun.misc.BASE64Encoder;

@Component
@SuppressWarnings("unchecked")
public class HttpClientMapUtilsTest
{
	public static final String METHOD_POST_STRING = "POST";
	public static final String METHOD_GET_STRING = "GET";
	public static final String METHOD_PUT_STRING = "PUT";
	public static final String METHOD_DELETE_STRING = "DELETE";
	public static final String CONTENT_TYPE_TEXT_XML = "text/xml;charset=UTF-8";
	public static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=UTF-8";
	public static final String CONTENT_TYPE_TEXT_FORM = "application/x-www-form-urlencoded";

	/***************************************************************************
	 * @param requestURL
	 *            http请求地址
	 * @param parameters
	 *            发送的参数
	 * @param contentType
	 *            消息内容类型
	 * @param method
	 *            请求方式
	 * @throws Exception
	 **************************************************************************/
	public static void sendRequest(final String requestURL, final String parameters, final String contentType,
			final String method) throws Exception
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					URL url = new URL(requestURL);
					HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
					httpConn.setRequestProperty("Origin", "http://192.168.10.173:8080");
					httpConn
							.setRequestProperty("Referer",
									"http: // 192.168.10.173:8080/xhw/apps/informationMessage/MInformationMessageAction.do?method=getXHSFB");
					httpConn.setRequestProperty("User-Agent",
							"Dalvik/1.6.0 (Linux; U; Android 4.4.2; MI 3C MIUI/KXDCNBF24.0)");
					httpConn.setRequestMethod(method);
					httpConn.setDoOutput(true);
					httpConn.setDoInput(true);
					OutputStream o = httpConn.getOutputStream();
					o.write(parameters.getBytes("utf-8"));
					o.close();
					InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "utf-8");
					BufferedReader bufr = new BufferedReader(isr);// 缓冲
					// BufferedReader bufr = new BufferedReader(new
					// InputStreamReader(new
					// FileInputStream("D:\\demo.txt")));可以综合到一句。
					/*
					 * int ch =0; ch = bufr.read();
					 * System.out.println((char)ch);
					 */
					String line = null;
					StringBuffer content = new StringBuffer();
					while ((line = bufr.readLine()) != null)
					{
						content.append(line);
					}
					System.out.println(content.toString());
				}
				catch (Exception e)
				{
					// throw new BaseException(e.getMessage());
				}
			}
		}).start();
		// HttpServletResponse response = getResponse();
		// content.toString();
	}

	public static String getMapUrl(String str)
	{
		if (StringUtils.isEmpty(str))
		{
			return "";
		}
		String[] strs = str.split(",");
		String lat = "";
		String lng = "";
		if (strs.length == 2)
		{
			lat = strs[0];
			lng = strs[1];
			return "http://www.gpsspg.com/ajax/maps_get.aspx?lat=" + lat + "&lng=" + lng + "&type=0";
		}

		return "";

	}

	/*
	 * public static String send(String str) {
	 * 
	 * try { return sendRequest(getMapUrl(str), "",
	 * "application/x-www-form-urlencoded",
	 * HttpClientMapUtilsTest.METHOD_POST_STRING); } catch (Exception e) { //
	 * TODO Auto-generated catch block } return ""; }
	 */

	public static void main(String[] args) throws Exception
	{
		String url = "http://xhpfm.mobile.zhongguowangshi.com:8091/ajax/userdown";
		// url = "http://xhpfm.mobile.zhongguowangshi.com:8091/ajax/userlike";

		StringBuffer parameters = new StringBuffer();

		// parameters.append("itemid=140&r=0.38927789288572967");
		// parameters.append("itemid=62&r=0.38927789288572967");
		/*
		 * for (int i = 1; i < 20000; i++) { if (i % 300 == 0) {
		 * Thread.sleep(2000); } sendRequest(url, parameters.toString(), "",
		 * HttpClientMapUtilsTest.METHOD_POST_STRING); }
		 */

		parameters
				.append("obj=.itemlist&action=http://xhpfm.open.zhongguowangshi.com/open/wapnewslist?pn=1&channel=d0029&pageno=1&rand=0.6030962856020778");
		sendRequest("http://xhpfm.open.zhongguowangshi.com/open/wapnewslist?pn=1&channel=d0029", parameters.toString(),
				"", HttpClientMapUtilsTest.METHOD_POST_STRING);

	}

	public static String Encrypt(String strSrc, String encName)
	{
		// parameter strSrc is a string will be encrypted,
		// parameter encName is the algorithm name will be used.
		// encName dafault to "MD5"
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try
		{
			if (encName == null || encName.equals(""))
			{
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts)
	{
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++)
		{
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1)
			{
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static String getSHA(String vars) throws Exception
	{
		MessageDigest md5 = MessageDigest.getInstance("SHA-1");
		md5.update(vars.getBytes("UTF-8"));
		byte[] digesta = md5.digest();
		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(digesta);
	}

	public static String bytetoString(byte[] digest)
	{
		String str = "";
		String tempStr = "";

		for (int i = 1; i < digest.length; i++)
		{
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1)
			{
				str = str + "0" + tempStr;
			}
			else
			{
				str = str + tempStr;
			}
		}
		return str.toLowerCase();
	}

}
