package com.framework.core.service;

/**
 * Title: JDBC方法集合
 * Description: JDBC方法集合数据交互业务接口
 * Copyright: Copyright (c) 2012
 * @author 钱嘉
 * @version 1.0 2012-05-29
 */

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.framework.core.domain.PageBeanVO;

@SuppressWarnings("unchecked")
public interface IBaseService {
	
	public PageBeanVO query4PageExt(PageBeanVO pager, String sql, Object obj);
	
	/**
	 * JqueryEasyui Grid分页
	 * @param map 存放page：当前页、rows：每页显示条数、sort：排序字段、order：排序规则
	 * @param sql 执行SQL
	 * @param obj
	 * @return PageBeanVO
	 */
	public PageBeanVO query4PageEasyUI(PageBeanVO pager, String sql, Object obj);
	
	public List query(String sql, Class clazz);
	
	public List query(String sql, Class clazz, Object object);
	
	public int execSql(String sql);

	public int execSql(String sql, Object object);
	
	public int execSql(String sql, Object[] objs, Object object);

	public int execSqlByMap(String sql, Map map);

	public int[] batchExecSql(String sql, List<Object[]> objs);

	public int[] batchExecSql4List(String sql, List objs);
	
	public Object queryForClass(String sql, Class clazz, Object object);
	
	public Object queryForClassDefault(String sql, Object object);
	
	public Object queryForColumn(String sql, Class columnType);
	
	public NamedParameterJdbcTemplate getNameJdbcTemplate();
	
}
