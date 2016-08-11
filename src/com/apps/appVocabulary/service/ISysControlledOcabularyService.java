package com.apps.appVocabulary.service;

import java.util.List;
import java.util.Map;

import com.apps.appVocabulary.domain.SysControlledOcabularyVO;
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
 * @author: yanxuewen
 * @version 1.0
 */
public interface ISysControlledOcabularyService
{
    /**
     * 分页查询
     */
    public PageBeanVO<SysControlledOcabularyVO> selectSysControlledOcabularyVOPage(
            PageBeanVO<SysControlledOcabularyVO> page, SysControlledOcabularyVO sysControlledOcabulary);

    /**
     * 根据ID查看
     */
    public SysControlledOcabularyVO selectSysControlledOcabularyVOById(SysControlledOcabularyVO sysControlledOcabulary);

    /**
     * 根据根据传入的Map获取对象查看
     */
    public SysControlledOcabularyVO selectSysControlledOcabularyVOByMap(Map<String, String> map);

    /**
     * 根据code返回List<SysControlledOcabularyVO>
     */
    public List<SysControlledOcabularyVO> selectSysControlledOcabularyVOBycoding(
            SysControlledOcabularyVO sysControlledOcabulary);

    /**
     * 根据code返回List<SysControlledOcabularyVO>
     */
    public List<SysControlledOcabularyVO> selectSysControlledOcabularyVOBycoding(String sysControlledOcabulary);

    /**
     * 新增单条数据
     */
    public boolean saveSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary);

    /**
     * 批量导入数据
     */
    public void saveSysControlledOcabularyBatch(List<SysControlledOcabularyVO> list);

    /**
     * 修改单条数据
     */
    public boolean updateSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary);

    /**
     * 批量删除多条数据
     */
    public boolean delSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary);
}
