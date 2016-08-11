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
import com.apps.userinfo.service.IVoUserinforExtensionService;
import com.apps.userinfo.domain.VoUserinforExtensionVO;

/**
* <p>Title: gocom用户扩展表</p>
* <p>Description: gocom用户扩展表</p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:中盈优创</p>
* @author: yanxuewen
* @version 1.0
*/
@Controller
@RequestMapping("/apps/userinfo/VoUserinforExtensionAction.do")
public class VoUserinforExtensionAction extends BaseToolAction {
	@Autowired
	private IVoUserinforExtensionService voUserinforExtensionService;
	
	@RequestMapping(params = "method=gotoView")
	public String gotoView(String type){
		if (type.equals("grid")) {
			return "/jsp/backmng/userinfo/voUserinforExtension_grid";
		}
	
		return "";
	}
	/**
	 * gocom用户扩展表 分页查询
	 * @param page 分页参数
	 * @param voUserinforExtension
	 * @return
	 */
	@RequestMapping(params = "method=selectVoUserinforExtensionVOPage")
	public void selectVoUserinforExtensionVOPage(PageBeanVO<VoUserinforExtensionVO> page,VoUserinforExtensionVO voUserinforExtension,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 this.pagerToJsonString(response, this.voUserinforExtensionService.selectVoUserinforExtensionVOPage(page, voUserinforExtension));
	}
	
	
	/**
	 * gocom用户扩展表根据ID查询
	 */
	@RequestMapping(params = "method=selectVoUserinforExtensionVOById")
	public void selectVoUserinforExtensionVOById(VoUserinforExtensionVO voUserinforExtension,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		voUserinforExtension=this.voUserinforExtensionService.selectVoUserinforExtensionVOById(voUserinforExtension);
		this.responseJsonExtForm(response,voUserinforExtension);
	}
	
	/**
	 * gocom用户扩展表 修改单条数据
	 */
	@RequestMapping(params = "method=updateVoUserinforExtensionVO")
	public void updateVoUserinforExtensionVO(VoUserinforExtensionVO voUserinforExtension , HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voUserinforExtensionService.updateVoUserinforExtensionVO(voUserinforExtension)));
	}
	
	/**
	 * gocom用户扩展表 新增单条数据
	 */
	@RequestMapping(params = "method=saveVoUserinforExtensionVO")
	public void saveVoUserinforExtensionVO(VoUserinforExtensionVO voUserinforExtension,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voUserinforExtensionService.saveVoUserinforExtensionVO(voUserinforExtension)));
	}
	
	/**
	 * gocom用户扩展表 批量删除多条数据
	 */
	@RequestMapping(params = "method=delVoUserinforExtensionVO")
	public void delVoUserinforExtensionVO(VoUserinforExtensionVO voUserinforExtension,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.voUserinforExtensionService.delVoUserinforExtensionVO(voUserinforExtension)));
	}
	
}
