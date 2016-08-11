package com.apps.question.service.impl;

import org.springframework.stereotype.Service;

import com.apps.question.domain.MQuestionVO;
import com.apps.question.service.IMQuestionService;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.service.impl.BaseService;
import com.framework.core.utils.GuidUtils;
import com.framework.core.utils.StringUtils;

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
public class MQuestionService extends BaseService implements IMQuestionService
{
    /**
     * 分页查询
     * 
     * @param page
     *            分页参数
     * @param mQuestion
     * @return
     */
    public PageBeanVO<MQuestionVO> selectMQuestionVOPage(PageBeanVO<MQuestionVO> page, MQuestionVO mQuestion)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" MQuestion.title ,");
        sql.append(" MQuestion.content ,");
        sql.append(" MQuestion.user_id ,");
        sql.append(" g.truename  userName,");
        sql.append(" date_format(MQuestion.question_time,'%Y-%m-%d %H:%i:%s') question_time , ");
        sql.append(" MQuestion.question_type ,");
        sql.append(" MQuestion.state ,");
        sql.append(" MQuestion.approval_status ,");
        sql.append(" MQuestion.approval_id ,");
        sql.append(" date_format(MQuestion.approval_time,'%Y-%m-%d %H:%i:%s') approval_time , ");
        sql.append(" MQuestion.role_id ,");
        sql.append(" MQuestion.app_id ,");
        sql.append(" MQuestion.device_id ,");
        sql.append(" MQuestion.Spread1 ,");
        sql.append(" MQuestion.Spread2 ,");
        sql.append(" MQuestion.Spread3 ,");
        sql.append(" MQuestion.Spread4 ,");
        sql.append(" MQuestion.Spread5 ,");
        sql.append(" MQuestion.question_id ");
        sql.append(" from m_question  MQuestion ");
        sql.append("LEFT JOIN vo_userinfor g on  MQuestion.user_id = g.shortname");
        sql.append(" where 1=1 ");
        if (!StringUtils.isEmpty(mQuestion.getQuestion_id()))
        {
            sql.append(" and  MQuestion.question_id = :question_id ");
        }
        if (!StringUtils.isEmpty(mQuestion.getTitle()))
        {
            mQuestion.setTitle(StringUtils.encodeLike(mQuestion.getTitle(), 3));
            sql.append(" and  MQuestion.title like  :title ");
        }
        if (!StringUtils.isEmpty(mQuestion.getContent()))
        {
            mQuestion.setContent(StringUtils.encodeLike(mQuestion.getContent(), 3));
            sql.append(" and  MQuestion.content like  :content ");
        }
        if (!StringUtils.isEmpty(mQuestion.getUser_id()))
        {
            sql.append(" and  MQuestion.user_id = :user_id ");
        }
        if (!StringUtils.isEmpty(mQuestion.getQuestion_time()))
        {
            sql.append(" and  MQuestion.question_time = :question_time ");
        }
        if (!StringUtils.isEmpty(mQuestion.getQuestion_type()))
        {
            sql.append(" and  MQuestion.question_type = :question_type ");
        }
        if (!StringUtils.isEmpty(mQuestion.getState()))
        {
            sql.append(" and  MQuestion.state = :state ");
        }
        if (!StringUtils.isEmpty(mQuestion.getApproval_id()))
        {
            sql.append(" and  MQuestion.approval_id = :approval_id ");
        }
        if (!StringUtils.isEmpty(mQuestion.getApproval_time()))
        {
            sql.append(" and  MQuestion.approval_time = :approval_time ");
        }
        if (!StringUtils.isEmpty(mQuestion.getRole_id()))
        {
            sql.append(" and  MQuestion.role_id in( '" + mQuestion.getRole_id() + "') ");
        }
        if (!StringUtils.isEmpty(mQuestion.getApp_id()))
        {
            sql.append(" and  MQuestion.app_id = :app_id ");
        }
        if (!StringUtils.isEmpty(mQuestion.getDevice_id()))
        {
            mQuestion.setDevice_id(StringUtils.encodeLike(mQuestion.getDevice_id(), 3));
            sql.append(" and  MQuestion.device_id like  :device_id ");
        }

        if (!StringUtils.isEmpty(mQuestion.getApproval_status()))
        {
            sql.append(" and  MQuestion.approval_status = :approval_status ");
        }
        if(!StringUtils.isEmpty(mQuestion.getSdate())){
        	sql.append(" and  MQuestion.question_time >=:sdate ");
        }
        if(!StringUtils.isEmpty(mQuestion.getEdate())){
        	sql.append(" and  MQuestion.question_time <=:edate ");
        }
        return this.query4PageExt(page, sql.toString(), mQuestion);
    }

    /**
     * 根据ID查看
     */
    public MQuestionVO selectMQuestionVOById(MQuestionVO mQuestion)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append(" MQuestion.question_id , ");
        sql.append(" MQuestion.title , ");
        sql.append(" MQuestion.content , ");
        sql.append(" MQuestion.user_id , ");
        sql.append(" date_format(MQuestion.question_time,'%Y-%m-%d %H:%i:%s') question_time , ");
        sql.append(" MQuestion.question_type , ");
        sql.append(" MQuestion.state , ");
        sql.append(" MQuestion.approval_id , ");
        sql.append(" date_format(MQuestion.approval_time,'%Y-%m-%d %H:%i:%s') approval_time , ");
        sql.append(" MQuestion.role_id , ");
        sql.append(" MQuestion.app_id , ");
        sql.append(" MQuestion.device_id , ");
        sql.append(" MQuestion.Spread1 , ");
        sql.append(" MQuestion.Spread2 , ");
        sql.append(" MQuestion.Spread3 , ");
        sql.append(" MQuestion.Spread4 , ");
        sql.append(" MQuestion.Spread5  ");
        sql.append(" from m_question MQuestion ");
        sql.append(" where 1=1 ");
        if (!StringUtils.isEmpty(mQuestion.getQuestion_id()))
        {
            sql.append(" and  MQuestion.question_id = :question_id ");
        }
        return (MQuestionVO) this.queryForClass(sql.toString(), MQuestionVO.class, mQuestion);
    }

    /**
     * 新增单条数据
     */
    public boolean saveMQuestionVO(MQuestionVO mQuestion)
    {
        mQuestion.setQuestion_id(GuidUtils.getRandomGUID(true));
        StringBuffer sql = new StringBuffer();
        sql.append("insert into  m_question ( ");
        sql.append(" question_id , ");
        sql.append(" title , ");
        sql.append(" content , ");
        sql.append(" user_id , ");
        sql.append(" question_time , ");
        sql.append(" question_type , ");
        sql.append(" state , ");
        sql.append(" approval_id , ");
        sql.append(" approval_time , ");
        sql.append(" role_id , ");
        sql.append(" app_id , ");
        sql.append(" approval_status , ");
        sql.append(" device_id , ");
        sql.append(" Spread1 , ");
        sql.append(" Spread2 , ");
        sql.append(" Spread3 , ");
        sql.append(" Spread4 , ");
        sql.append(" Spread5  ");
        sql.append(" ) values ( ");
        sql.append(" :question_id , ");
        sql.append(" :title , ");
        sql.append(" :content , ");
        sql.append(" :user_id , ");
        sql.append(" now() , ");
        sql.append(" :question_type , ");
        sql.append(" :state , ");
        sql.append(" :approval_id , ");
        sql.append(" :approval_time , ");
        sql.append(" :role_id , ");
        sql.append(" :app_id , ");
        sql.append(" :approval_status , ");
        sql.append(" :device_id , ");
        sql.append(" :Spread1 , ");
        sql.append(" :Spread2 , ");
        sql.append(" :Spread3 , ");
        sql.append(" :Spread4 , ");
        sql.append(" :Spread5  ");
        sql.append(" )   ");
        return this.execSql(sql.toString(), mQuestion) > 0;
    }

    /**
     * 修改单条数据
     */
    public boolean updateApprovalStatus(MQuestionVO mQuestion)
    {
        // 数据校验
        if (StringUtils.isEmpty(mQuestion))
        {
            return false;
        }
        if (StringUtils.isEmpty(mQuestion.getApproval_status()) || StringUtils.isEmpty(mQuestion.getQuestion_id()))
        {
            return false;
        }
        StringBuffer sql = new StringBuffer();
        sql.append(" update m_question set  ");
        sql.append(" approval_status = '").append(mQuestion.getApproval_status()).append("'");
        sql.append(" where question_id = :question_id ");
        return this.batchExecSql4List(sql.toString(),
                extractToList("question_id", mQuestion.getQuestion_id(), MQuestionVO.class)).length > 0;
    }

    /**
     * 修改单条数据
     */
    public boolean updateMQuestionVO(MQuestionVO mQuestion)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" update m_question set  ");
        sql.append(" state = :state , ");
        sql.append(" approval_id = :approval_id , ");
        sql.append(" approval_time = now(), ");
        sql.append(" Spread1 = :Spread1 , ");
        sql.append(" Spread2 = :Spread2 , ");
        sql.append(" Spread3 = :Spread3 , ");
        sql.append(" Spread4 = :Spread4 , ");
        sql.append(" Spread5 = :Spread5  ");
        sql.append(" where question_id = :question_id ");
        return this.execSql(sql.toString(), mQuestion) > 0;
    }

    /**
     * 批量删除多条数据
     */
    public boolean delMQuestionVO(MQuestionVO mQuestion)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" delete from  m_question where  question_id = :question_id  ");
        return this.batchExecSql4List(sql.toString(),
                extractToList("question_id", mQuestion.getQuestion_id(), MQuestionVO.class)).length > 0;

    }
}
