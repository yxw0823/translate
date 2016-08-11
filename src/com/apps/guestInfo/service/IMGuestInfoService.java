package com.apps.guestInfo.service;

import com.apps.guestInfo.domain.MGuestInfoVO;
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
 * @author: yanxuewen
 * @version 1.0
 */
public interface IMGuestInfoService
{
    /**
     * 分页查询
     */
    public PageBeanVO<MGuestInfoVO> selectMGuestInfoVOPage(PageBeanVO<MGuestInfoVO> page, MGuestInfoVO mGuestInfo);

    /**
     * 根据ID查看
     */
    public MGuestInfoVO selectMGuestInfoVOById(MGuestInfoVO mGuestInfo);

    /**
     * 验证登陆账号是否存在
     */
    public int checkLongName(MGuestInfoVO mGuestInfo);

    /**
     * 验证外宾登陆账号是否正确
     */
    public List<MGuestInfoVO> checkPassword(MGuestInfoVO mGuestInfo);

    /**
     * 新增单条数据
     */
    public boolean saveMGuestInfoVO(MGuestInfoVO mGuestInfo);

    /**
     * 修改单条数据
     */
    public boolean updateMGuestInfoVO(MGuestInfoVO mGuestInfo);

    /**
     * 批量删除多条数据
     */
    public boolean delMGuestInfoVO(MGuestInfoVO mGuestInfo);
}
