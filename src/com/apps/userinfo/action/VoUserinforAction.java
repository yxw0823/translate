package com.apps.userinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import com.framework.core.utils.Const;
import com.framework.core.utils.ExtUtils;
import com.framework.core.action.BaseToolAction;
import com.framework.core.domain.PageBeanVO;
import com.alibaba.fastjson.JSON;
import com.framework.core.utils.StringUtils;
import com.apps.userinfo.service.IVoUserinforService;
import com.apps.userinfo.domain.VoUserinforVO;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
@Controller
@RequestMapping("/apps/userinfo/VoUserinforAction.do")
public class VoUserinforAction extends BaseToolAction {
	@Autowired
	private IVoUserinforService voUserinforService;
	
	@RequestMapping(params = "method=gotoView")
	public String gotoView(String type){
		if (type.equals("grid")) {
			return "/jsp/backmng/userinfo/voUserinfor_grid";
		}
	
		return "";
	}
	/**
	 * 扫描登陆
	 * @param voUserinfor
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(params = "method=login")
	public String  login(VoUserinforVO voUserinfor,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		voUserinfor = this.voUserinforService.login(voUserinfor);
		if(StringUtils.isEmpty(voUserinfor)){
			return "/";
		}
		//登陆成功，写入会话
		session.setAttribute(Const.SESSION_USER, voUserinfor);
		return "/jsp/mian";
	}
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param voUserinfor
	 * @return
	 */
	@RequestMapping(params = "method=selectVoUserinforVOPage")
	public void selectVoUserinforVOPage(PageBeanVO<VoUserinforVO> page,VoUserinforVO voUserinfor,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 this.pagerToJsonString(response, this.voUserinforService.selectVoUserinforVOPage(page, voUserinfor));
	}
	
	
	/**
	 * 根据ID查询
	 */
	@RequestMapping(params = "method=selectVoUserinforVOById")
	public void selectVoUserinforVOById(VoUserinforVO voUserinfor,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		voUserinfor=this.voUserinforService.selectVoUserinforVOById(voUserinfor);
		this.responseJsonExtForm(response,voUserinfor);
	}
	
	/**
	 *  修改单条数据
	 */
	@RequestMapping(params = "method=updateVoUserinforVO")
	public void updateVoUserinforVO(VoUserinforVO voUserinfor , HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voUserinforService.updateVoUserinforVO(voUserinfor)));
	}
	
	/**
	 *  新增单条数据
	 */
	@RequestMapping(params = "method=saveVoUserinforVO")
	public void saveVoUserinforVO(VoUserinforVO voUserinfor,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voUserinforService.saveVoUserinforVO(voUserinfor)));
	}
	
	/**
	 *  批量删除多条数据
	 */
	@RequestMapping(params = "method=delVoUserinforVO")
	public void delVoUserinforVO(VoUserinforVO voUserinfor,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voUserinforService.delVoUserinforVO(voUserinfor)));
	}
	
}
