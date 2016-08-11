package com.apps.question.service;

import com.apps.question.domain.MQuestionReplyVO;
import com.framework.core.domain.PageBeanVO;
import java.util.List;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company:易象
 * </p>
 * 
 * @author: yanxw
 * @version 1.0
 */
public interface IMQuestionReplyService
{
	/**
	 * 分页查询
	 */
	public PageBeanVO<MQuestionReplyVO> selectMQuestionReplyVOPage(PageBeanVO<MQuestionReplyVO> page,
			MQuestionReplyVO mQuestionReply);

	/**
	 * 根据ID查看
	 */
	public MQuestionReplyVO selectMQuestionReplyVOById(MQuestionReplyVO mQuestionReply);

	/**
	 * 根据ID查看
	 */
	public List<MQuestionReplyVO> selectMQuestionReplyVOByQid(MQuestionReplyVO mQuestionReply);

	/**
	 * 新增单条数据
	 */
	public boolean saveMQuestionReplyVO(MQuestionReplyVO mQuestionReply);

	/**
	 * 修改单条数据
	 */
	public boolean updateMQuestionReplyVO(MQuestionReplyVO mQuestionReply);

	/**
	 * 批量删除多条数据
	 */
	public boolean delMQuestionReplyVO(MQuestionReplyVO mQuestionReply);

	/**
	 * 批量删除多条数据
	 */
	public boolean delMQuestionReplyVOByQuestionId(MQuestionReplyVO mQuestionReply);
}
