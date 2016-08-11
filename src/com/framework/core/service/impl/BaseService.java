package com.framework.core.service.impl;

/**
 * Title: JDBC方法集合
 * Description: JDBC方法集合数据交互业务实现类
 * Copyright: Copyright (c) 2012
 * @author 钱嘉
 * @version 1.0 2012-05-29
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.framework.core.dao.IBaseDao;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.service.IBaseService;
import com.framework.core.utils.BeanUtils;

@Component
@SuppressWarnings("unchecked")
public class BaseService implements IBaseService
{
    protected static Logger logger = null;
    protected static String className = null;

    public BaseService()
    {
        super();
        className = getClass().getName();
        logger = LoggerFactory.getLogger(className);
    }

    @Autowired
    @Qualifier("baseDao")
    private IBaseDao baseDao;

    /**
     * JqueryEasyui Grid分页
     * 
     * @param map 存放page：当前页、rows：每页显示条数、sort：排序字段、order：排序规则
     * @param sql 执行SQL
     * @param obj
     * @return PageBeanVO
     */
    public PageBeanVO query4PageEasyUI(PageBeanVO pager, String sql, Object obj)
    {
        return this.baseDao.query4PageEasyUI(pager, sql, obj);
    }

    public PageBeanVO query4PageExt(PageBeanVO pager, String sql, Object obj)
    {
        return this.baseDao.query4PageExt(pager, sql, obj);
    }

    public List query(String sql, Class clazz)
    {
        return this.baseDao.query(sql, clazz);
    }

    public List query(String sql, Class clazz, Object object)
    {
        return this.baseDao.query(sql, clazz, object);
    }

    public int execSql(String sql)
    {
        return this.baseDao.execSql(sql);
    }

    public int execSql(String sql, Object object)
    {
        return this.baseDao.execSql(sql, object);
    }

    public int execSql(String sql, Object[] objs, Object object)
    {
        return this.baseDao.execSql(sql, objs, object);
    }

    public int execSqlByMap(String sql, Map map)
    {
        return this.baseDao.execSqlByMap(sql, map);
    }

    public int[] batchExecSql(String sql, List<Object[]> objs)
    {
        return this.baseDao.batchExecSql(sql, objs);
    }

    public int[] batchExecSql4List(String sql, List objs)
    {
        return baseDao.batchExecSql4List(sql, objs);
    }

    public Object queryForColumn(String sql, Class columnType)
    {
        return this.baseDao.queryForColumn(sql, columnType);
    }

    public Object queryForClass(String sql, Class clazz, Object object)
    {
        return this.baseDao.queryForClass(sql, clazz, object);
    }

    public Object queryForObject(String sql, Class clazz, Object object)
    {
        return this.baseDao.queryForObject(sql, clazz, object);
    }

    public Object queryForClassDefault(String sql, Object object)
    {
        return this.baseDao.queryForClass(sql, object.getClass(), object);
    }

    public <T> T queryForClassDefault1(String sql, T object)
    {
        return object;
    }

    public NamedParameterJdbcTemplate getNameJdbcTemplate()
    {
        return this.baseDao.getNameJdbcTemplate();
    }

    public List extractToList(String fieldname, String values, Class cls)
    {
        List<Object> list = new ArrayList<Object>();
        String[] v = values.split(",");
        for (int i = 0; i < v.length; i++)
        {
            Object object = null;
            try
            {
                object = cls.newInstance();
                BeanUtils.setFieldValue(object, fieldname, v[i]);
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (NoSuchFieldException e)
            {
                e.printStackTrace();
            }
            if (v[i] != null && !v[i].equals(""))
                list.add(object);
        }
        return list;
    }

    /**
     * @return the baseDao
     */
    public IBaseDao getBaseDao()
    {
        return baseDao;
    }

    /**
     * @param baseDao the baseDao to set
     */
    public void setBaseDao(IBaseDao baseDao)
    {
        this.baseDao = baseDao;
    }
}