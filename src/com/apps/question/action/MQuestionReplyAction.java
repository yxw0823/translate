package com.apps.question.action;

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
import com.apps.question.service.IMQuestionReplyService;
import com.apps.question.domain.MQuestionReplyVO;

/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company:易象</p>
* @author: yanxw
* @version 1.0
*/
@Controller
@RequestMapping("/apps/question/MQuestionReplyAction.do")
public class MQuestionReplyAction extends BaseToolAction {
	@Autowired
	private IMQuestionReplyService mQuestionReplyService;
	
	@RequestMapping(params = "method=gotoView")
	public String gotoView(String type){
		if (type.equals("grid")) {
			return "/jsp/backmng/question/mQuestionReply_grid";
		}
	
		return "";
	}
	/**
	 *  分页查询
	 * @param page 分页参数
	 * @param mQuestionReply
	 * @return
	 */
	@RequestMapping(params = "method=selectMQuestionReplyVOPage")
	public void selectMQuestionReplyVOPage(PageBeanVO<MQuestionReplyVO> page,MQuestionReplyVO mQuestionReply,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 this.pagerToJsonString(response, this.mQuestionReplyService.selectMQuestionReplyVOPage(page, mQuestionReply));
	}
	
	
	/**
	 * 根据ID查询
	 */
	@RequestMapping(params = "method=selectMQuestionReplyVOById")
	public void selectMQuestionReplyVOById(MQuestionReplyVO mQuestionReply,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		mQuestionReply=this.mQuestionReplyService.selectMQuestionReplyVOById(mQuestionReply);
		this.responseJsonExtForm(response,mQuestionReply);
	}
	
	/**
	 *  修改单条数据
	 */
	@RequestMapping(params = "method=updateMQuestionReplyVO")
	public void updateMQuestionReplyVO(MQuestionReplyVO mQuestionReply , HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.mQuestionReplyService.updateMQuestionReplyVO(mQuestionReply)));
	}
	
	/**
	 *  新增单条数据
	 */
	@RequestMapping(params = "method=saveMQuestionReplyVO")
	public void saveMQuestionReplyVO(MQuestionReplyVO mQuestionReply,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.mQuestionReplyService.saveMQuestionReplyVO(mQuestionReply)));
	}
	
	/**
	 *  批量删除多条数据
	 */
	@RequestMapping(params = "method=delMQuestionReplyVO")
	public void delMQuestionReplyVO(MQuestionReplyVO mQuestionReply,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		this.responseHtml(response, String.valueOf(this.mQuestionReplyService.delMQuestionReplyVO(mQuestionReply)));
	}
	
}
