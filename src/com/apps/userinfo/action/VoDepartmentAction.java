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
import com.apps.userinfo.service.IVoDepartmentService;
import com.apps.userinfo.domain.VoDepartmentVO;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
@Controller
@RequestMapping("/apps/userinfo/VoDepartmentAction.do")
public class VoDepartmentAction extends BaseToolAction {
	@Autowired
	private IVoDepartmentService voDepartmentService;
	
	@RequestMapping(params = "method=gotoView")
	public String gotoView(String type){
		if (type.equals("grid")) {
			return "/jsp/backmng/userinfo/voDepartment_grid";
		}
	
		return "";
	}
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param voDepartment
	 * @return
	 */
	@RequestMapping(params = "method=selectVoDepartmentVOPage")
	public void selectVoDepartmentVOPage(PageBeanVO<VoDepartmentVO> page,VoDepartmentVO voDepartment,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 this.pagerToJsonString(response, this.voDepartmentService.selectVoDepartmentVOPage(page, voDepartment));
	}
	
	
	/**
	 * 根据ID查询
	 */
	@RequestMapping(params = "method=selectVoDepartmentVOById")
	public void selectVoDepartmentVOById(VoDepartmentVO voDepartment,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		voDepartment=this.voDepartmentService.selectVoDepartmentVOById(voDepartment);
		this.responseJsonExtForm(response,voDepartment);
	}
	
	/**
	 *  修改单条数据
	 */
	@RequestMapping(params = "method=updateVoDepartmentVO")
	public void updateVoDepartmentVO(VoDepartmentVO voDepartment , HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voDepartmentService.updateVoDepartmentVO(voDepartment)));
	}
	
	/**
	 *  新增单条数据
	 */
	@RequestMapping(params = "method=saveVoDepartmentVO")
	public void saveVoDepartmentVO(VoDepartmentVO voDepartment,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voDepartmentService.saveVoDepartmentVO(voDepartment)));
	}
	
	/**
	 *  批量删除多条数据
	 */
	@RequestMapping(params = "method=delVoDepartmentVO")
	public void delVoDepartmentVO(VoDepartmentVO voDepartment,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voDepartmentService.delVoDepartmentVO(voDepartment)));
	}
	
}
