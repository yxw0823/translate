package com.apps.question.service;

import com.apps.question.domain.MQuestionVO;
import com.framework.core.domain.PageBeanVO;

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
public interface IMQuestionService
{
    /**
     * 分页查询
     */
    public PageBeanVO<MQuestionVO> selectMQuestionVOPage(PageBeanVO<MQuestionVO> page, MQuestionVO mQuestion);

    /**
     * 根据ID查看
     */
    public MQuestionVO selectMQuestionVOById(MQuestionVO mQuestion);

    /**
     * 新增单条数据
     */
    public boolean saveMQuestionVO(MQuestionVO mQuestion);

    /**
     * 修改单条数据
     */
    public boolean updateMQuestionVO(MQuestionVO mQuestion);

    /**
     * 修改审批状态
     */
    public boolean updateApprovalStatus(MQuestionVO mQuestion);

    /**
     * 批量删除多条数据
     */
    public boolean delMQuestionVO(MQuestionVO mQuestion);
}
