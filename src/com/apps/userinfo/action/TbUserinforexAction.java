package com.apps.userinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import com.framework.core.utils.ExtUtils;
import com.framework.core.action.BaseToolAction;
import com.framework.core.domain.PageBeanVO;
import com.alibaba.fastjson.JSON;
import com.framework.core.utils.StringUtils;
import com.apps.userinfo.service.ITbUserinforexService;
import com.apps.userinfo.domain.TbUserinforexVO;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
@Controller
@RequestMapping("/apps/userinfo/TbUserinforexAction.do")
public class TbUserinforexAction extends BaseToolAction {
	@Autowired
	private ITbUserinforexService tbUserinforexService;
	
	@RequestMapping(params = "method=gotoView")
	public String gotoView(String type){
		if (type.equals("grid")) {
			return "/jsp/backmng/userinfo/tbUserinforex_grid";
		}
	
		return "";
	}
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param tbUserinforex
	 * @return
	 */
	@RequestMapping(params = "method=selectTbUserinforexVOPage")
	public void selectTbUserinforexVOPage(PageBeanVO<TbUserinforexVO> page,TbUserinforexVO tbUserinforex,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 this.pagerToJsonString(response, this.tbUserinforexService.selectTbUserinforexVOPage(page, tbUserinforex));
	}
	
	
	/**
	 * 根据ID查询
	 */
	@RequestMapping(params = "method=selectTbUserinforexVOById")
	public void selectTbUserinforexVOById(TbUserinforexVO tbUserinforex,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		tbUserinforex=this.tbUserinforexService.selectTbUserinforexVOById(tbUserinforex);
		this.responseJsonExtForm(response,tbUserinforex);
	}
	
	/**
	 *  修改单条数据
	 */
	@RequestMapping(params = "method=updateTbUserinforexVO")
	public void updateTbUserinforexVO(TbUserinforexVO tbUserinforex , HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.tbUserinforexService.updateTbUserinforexVO(tbUserinforex)));
	}
	
	/**
	 *  新增单条数据
	 */
	@RequestMapping(params = "method=saveTbUserinforexVO")
	public void saveTbUserinforexVO(TbUserinforexVO tbUserinforex,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.tbUserinforexService.saveTbUserinforexVO(tbUserinforex)));
	}
	
	/**
	 *  批量删除多条数据
	 */
	@RequestMapping(params = "method=delTbUserinforexVO")
	public void delTbUserinforexVO(TbUserinforexVO tbUserinforex,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.tbUserinforexService.delTbUserinforexVO(tbUserinforex)));
	}
	
}
