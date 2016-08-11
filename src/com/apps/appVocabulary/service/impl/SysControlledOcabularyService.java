package com.apps.appVocabulary.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.apps.appVocabulary.domain.SysControlledOcabularyVO;
import com.apps.appVocabulary.service.ISysControlledOcabularyService;
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
 * @author: yanxuewen
 * @version 1.0
 */
@Service
public class SysControlledOcabularyService extends BaseService implements ISysControlledOcabularyService
{
	/**
	 * 分页查询
	 * 
	 * @param page
	 *            分页参数
	 * @param sysControlledOcabulary
	 * @return
	 */
	public PageBeanVO<SysControlledOcabularyVO> selectSysControlledOcabularyVOPage(
			PageBeanVO<SysControlledOcabularyVO> page, SysControlledOcabularyVO sysControlledOcabulary)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(" SysControlledOcabulary.operatetype_id ,");
		sql.append(" SysControlledOcabulary.content ,");
		sql.append(" SysControlledOcabulary.coding ,");
		sql.append(" SysControlledOcabulary.state ,");
		sql.append(" SysControlledOcabulary.pid ,");
		sql.append(" SysControlledOcabulary.Spread1 ,");
		sql.append(" SysControlledOcabulary.Spread2 ,");
		sql.append(" SysControlledOcabulary.Spread3 ,");
		sql.append(" SysControlledOcabulary.Spread4 ,");
		sql.append(" SysControlledOcabulary.Spread5 ,");
		sql.append(" SysControlledOcabulary.ocabulary_id ");
		sql.append(" from sys_controlled_ocabulary  SysControlledOcabulary ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(sysControlledOcabulary.getOcabulary_id()))
		{
			sql.append(" and  SysControlledOcabulary.ocabulary_id = :ocabulary_id ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getOperatetype_id()))
		{
			sql.append(" and  SysControlledOcabulary.operatetype_id = :operatetype_id ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getContent()))
		{
			sysControlledOcabulary.setContent(StringUtils.encodeLike(sysControlledOcabulary.getContent(), 3));
			sql.append(" and  SysControlledOcabulary.content like  :content ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getCoding()))
		{
			sql.append(" and ( SysControlledOcabulary.coding like '%" + sysControlledOcabulary.getCoding() + "%' ");
			sql.append(" or  SysControlledOcabulary.content like '%" + sysControlledOcabulary.getCoding() + "%') ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getState()))
		{
			sql.append(" and  SysControlledOcabulary.state = :state ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getPid()))
		{
			sql.append(" and  SysControlledOcabulary.pid = :pid ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getSpread1()))
		{
			sql.append(" and  SysControlledOcabulary.Spread1 = :Spread1 ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getSpread2()))
		{
			sql.append(" and  SysControlledOcabulary.Spread2 = :Spread2 ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getSpread3()))
		{
			sql.append(" and  SysControlledOcabulary.Spread3 = :Spread3 ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getSpread4()))
		{
			sysControlledOcabulary.setSpread4(StringUtils.encodeLike(sysControlledOcabulary.getSpread4(), 3));
			sql.append(" and  SysControlledOcabulary.Spread4 like  :Spread4 ");
		}
		if (!StringUtils.isEmpty(sysControlledOcabulary.getSpread5()))
		{
			sysControlledOcabulary.setSpread5(StringUtils.encodeLike(sysControlledOcabulary.getSpread5(), 3));
			sql.append(" and  SysControlledOcabulary.Spread5 like  :Spread5 ");
		}
		return this.query4PageExt(page, sql.toString(), sysControlledOcabulary);
	}

	/**
	 * 根据ID查看
	 */
	public SysControlledOcabularyVO selectSysControlledOcabularyVOById(SysControlledOcabularyVO sysControlledOcabulary)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(" SysControlledOcabulary.ocabulary_id , ");
		sql.append(" SysControlledOcabulary.operatetype_id , ");
		sql.append(" SysControlledOcabulary.content , ");
		sql.append(" SysControlledOcabulary.coding , ");
		sql.append(" SysControlledOcabulary.state , ");
		sql.append(" SysControlledOcabulary.pid , ");
		sql.append(" SysControlledOcabulary.Spread1 , ");
		sql.append(" SysControlledOcabulary.Spread2 , ");
		sql.append(" SysControlledOcabulary.Spread3 , ");
		sql.append(" SysControlledOcabulary.Spread4 , ");
		sql.append(" SysControlledOcabulary.Spread5  ");
		sql.append(" from sys_controlled_ocabulary SysControlledOcabulary ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(sysControlledOcabulary.getOcabulary_id()))
		{
			sql.append(" and  SysControlledOcabulary.ocabulary_id = :ocabulary_id ");
		}
		return (SysControlledOcabularyVO) this.queryForClass(sql.toString(), SysControlledOcabularyVO.class,
				sysControlledOcabulary);
	}

	/**
	 * 新增单条数据
	 */
	public boolean saveSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary)
	{
		sysControlledOcabulary.setOcabulary_id(GuidUtils.getRandomGUID(true));
		StringBuffer sql = new StringBuffer();
		sql.append("insert into  sys_controlled_ocabulary ( ");
		sql.append(" ocabulary_id , ");
		sql.append(" operatetype_id , ");
		sql.append(" content , ");
		sql.append(" coding , ");
		sql.append(" state , ");
		sql.append(" pid , ");
		sql.append(" Spread1 , ");
		sql.append(" Spread2 , ");
		sql.append(" Spread3 , ");
		sql.append(" Spread4 , ");
		sql.append(" Spread5  ");
		sql.append(" ) values ( ");
		sql.append(" :ocabulary_id , ");
		sql.append(" :operatetype_id , ");
		sql.append(" :content , ");
		sql.append(" :coding , ");
		sql.append(" :state , ");
		sql.append(" :pid , ");
		sql.append(" :Spread1 , ");
		sql.append(" :Spread2 , ");
		sql.append(" :Spread3 , ");
		sql.append(" :Spread4 , ");
		sql.append(" :Spread5  ");
		sql.append(" )   ");
		return this.execSql(sql.toString(), sysControlledOcabulary) > 0;
	}

	/**
	 * 修改单条数据
	 */
	public boolean updateSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_controlled_ocabulary set  ");
		sql.append(" operatetype_id = :operatetype_id , ");
		sql.append(" content = :content , ");
		sql.append(" coding = :coding , ");
		sql.append(" state = :state , ");
		sql.append(" pid = :pid , ");
		sql.append(" Spread1 = :Spread1 , ");
		sql.append(" Spread2 = :Spread2 , ");
		sql.append(" Spread3 = :Spread3 , ");
		sql.append(" Spread4 = :Spread4 , ");
		sql.append(" Spread5 = :Spread5  ");
		sql.append(" where ocabulary_id = :ocabulary_id ");
		return this.execSql(sql.toString(), sysControlledOcabulary) > 0;
	}

	/**
	 * 批量删除多条数据
	 */
	public boolean delSysControlledOcabularyVO(SysControlledOcabularyVO sysControlledOcabulary)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from  sys_controlled_ocabulary where  ocabulary_id = :ocabulary_id  ");
		return this.batchExecSql4List(sql.toString(), extractToList("ocabulary_id", sysControlledOcabulary
				.getOcabulary_id(), SysControlledOcabularyVO.class)).length > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.apps.appVocabulary.service.ISysControlledOcabularyService#saveSysControlledOcabularyBatch(java.util.List)
	 */
	public void saveSysControlledOcabularyBatch(List<SysControlledOcabularyVO> list)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("insert into  sys_controlled_ocabulary ( ");
		sql.append(" ocabulary_id , ");
		sql.append(" operatetype_id , ");
		sql.append(" content , ");
		sql.append(" coding , ");
		sql.append(" state , ");
		sql.append(" pid , ");
		sql.append(" Spread1 , ");
		sql.append(" Spread2 , ");
		sql.append(" Spread3 , ");
		sql.append(" Spread4 , ");
		sql.append(" Spread5  ");
		sql.append(" ) values ( ");
		sql.append(" :ocabulary_id , ");
		sql.append(" :operatetype_id , ");
		sql.append(" :content , ");
		sql.append(" :coding , ");
		sql.append(" :state , ");
		sql.append(" :pid , ");
		sql.append(" :Spread1 , ");
		sql.append(" :Spread2 , ");
		sql.append(" :Spread3 , ");
		sql.append(" :Spread4 , ");
		sql.append(" :Spread5  ");
		sql.append(" )   ");
		this.batchExecSql4List(sql.toString(), list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.apps.appVocabulary.service.ISysControlledOcabularyService#selectSysControlledOcabularyVOBycoding(com.apps.appVocabulary.domain.SysControlledOcabularyVO)
	 */
	public List<SysControlledOcabularyVO> selectSysControlledOcabularyVOBycoding(
			SysControlledOcabularyVO sysControlledOcabulary)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(" SysControlledOcabulary.ocabulary_id , ");
		sql.append(" SysControlledOcabulary.operatetype_id , ");
		sql.append(" SysControlledOcabulary.content , ");
		sql.append(" SysControlledOcabulary.coding , ");
		sql.append(" SysControlledOcabulary.state , ");
		sql.append(" SysControlledOcabulary.pid , ");
		sql.append(" SysControlledOcabulary.Spread1 , ");
		sql.append(" SysControlledOcabulary.Spread2 , ");
		sql.append(" SysControlledOcabulary.Spread3 , ");
		sql.append(" SysControlledOcabulary.Spread4 , ");
		sql.append(" SysControlledOcabulary.Spread5  ");
		sql.append(" from sys_controlled_ocabulary SysControlledOcabulary ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(sysControlledOcabulary.getCoding()))
		{
			sql.append(" and  SysControlledOcabulary.coding = :coding ");
		}
		return this.query(sql.toString(), SysControlledOcabularyVO.class, sysControlledOcabulary);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.apps.appVocabulary.service.ISysControlledOcabularyService#selectSysControlledOcabularyVOBycoding(com.apps.appVocabulary.domain.SysControlledOcabularyVO)
	 */
	public List<SysControlledOcabularyVO> selectSysControlledOcabularyVOBycoding(String sysControlledOcabulary)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(" SysControlledOcabulary.ocabulary_id , ");
		sql.append(" SysControlledOcabulary.operatetype_id , ");
		sql.append(" SysControlledOcabulary.content , ");
		sql.append(" SysControlledOcabulary.coding , ");
		sql.append(" SysControlledOcabulary.state , ");
		sql.append(" SysControlledOcabulary.pid , ");
		sql.append(" SysControlledOcabulary.Spread1 , ");
		sql.append(" SysControlledOcabulary.Spread2 , ");
		sql.append(" SysControlledOcabulary.Spread3 , ");
		sql.append(" SysControlledOcabulary.Spread4 , ");
		sql.append(" SysControlledOcabulary.Spread5  ");
		sql.append(" from sys_controlled_ocabulary SysControlledOcabulary ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(sysControlledOcabulary))
		{
			sql.append(" and  SysControlledOcabulary.coding = '" + sysControlledOcabulary + "'");
		}
		sql.append(" order by operatetype_id  ");
		return this.query(sql.toString(), SysControlledOcabularyVO.class, null);
	}

	/**
	 * 根据ID查看
	 */
	public SysControlledOcabularyVO selectSysControlledOcabularyVOByMap(Map<String, String> map)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(" SysControlledOcabulary.ocabulary_id , ");
		sql.append(" SysControlledOcabulary.operatetype_id , ");
		sql.append(" SysControlledOcabulary.content , ");
		sql.append(" SysControlledOcabulary.coding , ");
		sql.append(" SysControlledOcabulary.state , ");
		sql.append(" SysControlledOcabulary.pid , ");
		sql.append(" SysControlledOcabulary.Spread1 , ");
		sql.append(" SysControlledOcabulary.Spread2 , ");
		sql.append(" SysControlledOcabulary.Spread3 , ");
		sql.append(" SysControlledOcabulary.Spread4 , ");
		sql.append(" SysControlledOcabulary.Spread5  ");
		sql.append(" from sys_controlled_ocabulary SysControlledOcabulary ");
		sql.append(" where 1=1 ");
		if (!StringUtils.isEmpty(map.get("operatetype_id")))
		{
			sql.append(" and  SysControlledOcabulary.operatetype_id = '" + map.get("operatetype_id") + "'");
		}
		if (!StringUtils.isEmpty(map.get("coding")))
		{
			sql.append(" and  SysControlledOcabulary.coding = '" + map.get("coding") + "'");
		}
		if (!StringUtils.isEmpty(map.get("ocabulary_id")))
		{
			sql.append(" and  SysControlledOcabulary.ocabulary_id = '" + map.get("ocabulary_id") + "'");
		}
		if (!StringUtils.isEmpty(map.get("content")))
		{
			sql.append(" and  SysControlledOcabulary.content = '" + map.get("content") + "'");
		}
		if (!StringUtils.isEmpty(map.get("state")))
		{
			sql.append(" and  SysControlledOcabulary.state = '" + map.get("state") + "'");
		}
		Object o = this.queryForClass(sql.toString(), SysControlledOcabularyVO.class, null);
		if (o instanceof SysControlledOcabularyVO)
		{
			return (SysControlledOcabularyVO) o;
		}
		return new SysControlledOcabularyVO();
	}
}
