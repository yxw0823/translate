package com.apps.qrlogin.action;

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
@RequestMapping("/apps/qrlogin/QrcodeloginAction.do")
public class QrcodeloginAction extends BaseToolAction {
	@Autowired
	private IQrcodeloginService qrcodeloginService;
	
	@RequestMapping(params = "method=gotoView")
	public String gotoView(String type){
		if (type.equals("grid")) {
			return "/jsp/backmng/qrlogin/qrcodelogin_grid";
		}
	
		return "";
	}
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param qrcodelogin
	 * @return
	 */
	@RequestMapping(params = "method=selectQrcodeloginVOPage")
	public void selectQrcodeloginVOPage(PageBeanVO<QrcodeloginVO> page,QrcodeloginVO qrcodelogin,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 this.pagerToJsonString(response, this.qrcodeloginService.selectQrcodeloginVOPage(page, qrcodelogin));
	}
	
	
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
	
	/**
	 *  新增单条数据
	 */
	@RequestMapping(params = "method=saveQrcodeloginVO")
	public void saveQrcodeloginVO(QrcodeloginVO qrcodelogin,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		qrcodelogin.setQrcode(GuidUtils.getRandomGUID(true));
		this.qrcodeloginService.saveQrcodeloginVO(qrcodelogin);
		this.responseJson(response, qrcodelogin);
	}
	
	/**
	 *  批量删除多条数据
	 */
	@RequestMapping(params = "method=delQrcodeloginVO")
	public void delQrcodeloginVO(QrcodeloginVO qrcodelogin,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.qrcodeloginService.delQrcodeloginVO(qrcodelogin)));
	}
	
}
