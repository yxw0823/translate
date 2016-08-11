package com.apps.qrlogin.moblie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import com.framework.core.utils.ExtUtils;
import com.framework.core.utils.GuidUtils;
import com.framework.core.utils.UUIDUtils;
import com.framework.core.action.BaseToolAction;
import com.framework.core.domain.PageBeanVO;
import com.alibaba.fastjson.JSON;
import com.framework.core.utils.StringUtils;
import com.apps.qrlogin.service.IQrcodeloginService;
import com.apps.qrlogin.domain.QrcodeloginVO;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
@Controller
@RequestMapping("/app/QrcodeMobileloginAction.do")
public class QrcodeMobileloginAction extends BaseToolAction {
	@Autowired
	private IQrcodeloginService qrcodeloginService;
	
	
	
	/**
	 * 根据ID查询
	 */
	@RequestMapping(params = "method=selectQrcodeloginVOById")
	public void selectQrcodeloginVOById(QrcodeloginVO qrcodelogin,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		qrcodelogin=this.qrcodeloginService.selectQrcodeloginVOById(qrcodelogin);
		this.responseJsonExtForm(response,qrcodelogin);
	}
	
	/**
	 *  修改单条数据
	 */
	@RequestMapping(params = "method=updateQrcodeloginVO")
	public void updateQrcodeloginVO(QrcodeloginVO qrcodelogin , HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.qrcodeloginService.updateQrcodeloginVO(qrcodelogin)));
	}
}
