package com.apps.question.service.impl;

import com.apps.question.service.IMQuestionReplyService;
import com.apps.question.domain.MQuestionReplyVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.utils.GuidUtils;
import org.springframework.stereotype.Service;
import com.framework.core.utils.StringUtils;
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
@Service
public class MQuestionReplyService extends BaseService implements IMQuestionReplyService
{
	/**
	 * 分页查询
	 * 
	 * @param page
	 *            分页参数
	 * @param mQuestionReply
	 * @return
	 */
	public PageBeanVO<MQuestionReplyVO> selectMQuestionReplyVOPage(PageBeanVO<MQuestionReplyVO> page,
			MQuestionReplyVO mQuestionReply)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(" MQuestionReply.question_id ,");
		sql.append(" MQuestionReply.reply_content ,");
		sql.append(" MQuestionReply.user_id ,");
		sql.append(" MQuestionReply.reply_user_id ,");
		sql.append(" date_format(MQuestionReply.reply_time,'%Y-%m-%d') reply_time , ");
		sql.append(" MQuestionReply.reply_state ,");
		sql.append(" MQuestionReply.Spread1 ,");
		sql.append(" MQuestionReply.Spread2 ,");
		sql.append(" MQuestionReply.Spread3 ,");
		sql.append(" MQuestionReply.Spread4 ,");
		sql.append(" MQuestionReply.Spread5 ,");
		sql.append(" MQuestionReply.reply_id ");
		sql.append(" from m_question_reply  MQuestionReply ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(mQuestionReply.getReply_id()))
		{
			sql.append(" and  MQuestionReply.reply_id = :reply_id ");
		}
		if (!StringUtils.isEmpty(mQuestionReply.getQuestion_id()))
		{
			sql.append(" and  MQuestionReply.question_id = :question_id ");
		}
		if (!StringUtils.isEmpty(mQuestionReply.getReply_content()))
		{
			mQuestionReply.setReply_content(StringUtils.encodeLike(mQuestionReply.getReply_content(), 3));
			sql.append(" and  MQuestionReply.reply_content like  :reply_content ");
		}
		if (!StringUtils.isEmpty(mQuestionReply.getUser_id()))
		{
			sql.append(" and  MQuestionReply.user_id = :user_id ");
		}
		if (!StringUtils.isEmpty(mQuestionReply.getReply_user_id()))
		{
			sql.append(" and  MQuestionReply.reply_user_id = :reply_user_id ");
		}
		if (!StringUtils.isEmpty(mQuestionReply.getReply_time()))
		{
			sql.append(" and  MQuestionReply.reply_time = :reply_time ");
		}
		if (!StringUtils.isEmpty(mQuestionReply.getReply_state()))
		{
			sql.append(" and  MQuestionReply.reply_state = :reply_state ");
		}
		return this.query4PageExt(page, sql.toString(), mQuestionReply);
	}

	/**
	 * 根据ID查看
	 */
	public MQuestionReplyVO selectMQuestionReplyVOById(MQuestionReplyVO mQuestionReply)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(" MQuestionReply.reply_id , ");
		sql.append(" MQuestionReply.question_id , ");
		sql.append(" MQuestionReply.reply_content , ");
		sql.append(" MQuestionReply.user_id , ");
		sql.append(" MQuestionReply.reply_user_id , ");
		sql.append(" date_format(MQuestionReply.reply_time,'%Y-%m-%d') reply_time , ");
		sql.append(" MQuestionReply.reply_state , ");
		sql.append(" MQuestionReply.Spread1 , ");
		sql.append(" MQuestionReply.Spread2 , ");
		sql.append(" MQuestionReply.Spread3 , ");
		sql.append(" MQuestionReply.Spread4 , ");
		sql.append(" MQuestionReply.Spread5  ");
		sql.append(" from m_question_reply MQuestionReply ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(mQuestionReply.getReply_id()))
		{
			sql.append(" and  MQuestionReply.reply_id = :reply_id ");
		}
		return (MQuestionReplyVO) this.queryForClass(sql.toString(), MQuestionReplyVO.class, mQuestionReply);
	}

	/**
	 * 新增单条数据
	 */
	public boolean saveMQuestionReplyVO(MQuestionReplyVO mQuestionReply)
	{
		mQuestionReply.setReply_id(GuidUtils.getRandomGUID(true));
		StringBuffer sql = new StringBuffer();
		sql.append("insert into  m_question_reply ( ");
		sql.append(" reply_id , ");
		sql.append(" question_id , ");
		sql.append(" reply_content , ");
		sql.append(" user_id , ");
		sql.append(" reply_user_id , ");
		sql.append(" reply_time , ");
		sql.append(" reply_state , ");
		sql.append(" Spread1 , ");
		sql.append(" Spread2 , ");
		sql.append(" Spread3 , ");
		sql.append(" Spread4 , ");
		sql.append(" Spread5  ");
		sql.append(" ) values ( ");
		sql.append(" :reply_id , ");
		sql.append(" :question_id , ");
		sql.append(" :reply_content , ");
		sql.append(" :user_id , ");
		sql.append(" :reply_user_id , ");
		sql.append(" now() , ");
		sql.append(" 1 , ");
		sql.append(" :Spread1 , ");
		sql.append(" :Spread2 , ");
		sql.append(" :Spread3 , ");
		sql.append(" :Spread4 , ");
		sql.append(" :Spread5  ");
		sql.append(" )   ");
		return this.execSql(sql.toString(), mQuestionReply) > 0;
	}

	/**
	 * 修改单条数据
	 */
	public boolean updateMQuestionReplyVO(MQuestionReplyVO mQuestionReply)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" update m_question_reply set  ");
		sql.append(" question_id = :question_id , ");
		sql.append(" reply_content = :reply_content , ");
		sql.append(" user_id = :user_id , ");
		sql.append(" reply_user_id = :reply_user_id , ");
		sql.append(" reply_time = :reply_time , ");
		sql.append(" reply_state = :reply_state , ");
		sql.append(" Spread1 = :Spread1 , ");
		sql.append(" Spread2 = :Spread2 , ");
		sql.append(" Spread3 = :Spread3 , ");
		sql.append(" Spread4 = :Spread4 , ");
		sql.append(" Spread5 = :Spread5  ");
		sql.append(" where reply_id = :reply_id ");
		return this.execSql(sql.toString(), mQuestionReply) > 0;
	}

	/**
	 * 批量删除多条数据
	 */
	public boolean delMQuestionReplyVO(MQuestionReplyVO mQuestionReply)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from  m_question_reply where  reply_id = :reply_id  ");
		return this.batchExecSql4List(sql.toString(), extractToList("reply_id", mQuestionReply.getReply_id(),
				MQuestionReplyVO.class)).length > 0;

	}

	public List<MQuestionReplyVO> selectMQuestionReplyVOByQid(MQuestionReplyVO questionReply)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(" MQuestionReply.reply_id , ");
		sql.append(" MQuestionReply.question_id , ");
		sql.append(" MQuestionReply.reply_content , ");
		sql.append(" MQuestionReply.user_id , ");
	//	sql.append(" case  MQuestionReply.Spread1  WHEN 'Admin' THEN a.user_name  ELSE  g.guest_name END userName,");
		sql.append(" MQuestionReply.reply_user_id , ");
		sql.append(" date_format(MQuestionReply.reply_time,'%Y-%m-%d %H:%i:%s') reply_time , ");
		sql.append(" MQuestionReply.reply_state , ");
		sql.append(" MQuestionReply.Spread1 , ");
		sql.append(" MQuestionReply.Spread2 , ");
		sql.append(" MQuestionReply.Spread3 , ");
		sql.append(" MQuestionReply.Spread4 , ");
		sql.append(" MQuestionReply.Spread5  ");
		sql.append(" from m_question_reply MQuestionReply ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(questionReply.getQuestion_id()))
		{
			sql.append(" and  MQuestionReply.question_id = :question_id ");
		}
		if (!StringUtils.isEmpty(questionReply.getReply_state()))
		{
			sql.append(" and  MQuestionReply.reply_state = :reply_state ");
		}

		sql.append(" ORDER BY reply_time ");

		return this.query(sql.toString(), MQuestionReplyVO.class, questionReply);
	}

	public boolean delMQuestionReplyVOByQuestionId(MQuestionReplyVO questionReply)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from  m_question_reply where  question_id = :question_id  ");
		if (!StringUtils.isEmpty(questionReply.getReply_id()))
		{
			sql.append(" AND reply_id=:reply_id");
		}
		return this.execSql(sql.toString(), questionReply) > 0;
	}
}
