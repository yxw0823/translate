package com.framework.core.dao.impl;

/**
 * Title: JDBC方法集合
 * Description: JDBC方法集合数据交互实现类
 * Copyright: Copyright (c) 2012
 * @author 钱嘉
 * @version 1.0 2012-05-29
 */
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.framework.core.dao.IBaseDao;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.domain.SortVO;
import com.framework.core.utils.Const;

@SuppressWarnings("unchecked")
@Component
@Repository
public class BaseDao extends NamedParameterJdbcDaoSupport implements IBaseDao
{
    protected static Logger logger = null;
    protected static String className = null;

    public BaseDao()
    {
        super();
        className = getClass().getName();
        logger = LoggerFactory.getLogger(className);
    }

    @Autowired
    private SimpleJdbcCall sjdbcCall;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate nameJdbcTemplate;

    protected BeanPropertyRowMapper resultBeanMapper(Class clazz)
    {
        return new BeanPropertyRowMapper(clazz);
    }

    protected SqlParameterSource paramBeanMapper(Object object)
    {
        return new BeanPropertySqlParameterSource(object);
    }

    public PageBeanVO query4PageExt(PageBeanVO pager, String sql, Object obj)
    {

        int start = Integer.valueOf(pager.getStart());
        int limit = Integer.valueOf(pager.getLimit());
        int offset = pager.getOffset();
        String sort = pager.getSort();// string
        String dir = pager.getDir();
        List<SortVO> sv = JSONArray.parseArray(sort, SortVO.class);// &sort=[{property=MFeedbackCategory.create_time,direction=DESC}]
        if (("oracle".equals(Const.DIALECT)))
        {
            List resultList = null;
            int start_num = (start - 1) * limit - offset;
            int end_num = start * limit - offset;
            String v_table = "(" + sql + " order by " + sort + " " + dir + ") vtb";
            String countsql = "SELECT COUNT(*) FROM " + v_table;
            logger.debug("开始执行sql=" + countsql);
            int resTotal = nameJdbcTemplate.queryForInt(countsql, paramBeanMapper(obj));
            resTotal = resTotal + offset;
            String selSqlend = "";
            selSqlend = " SELECT * FROM (SELECT vtb.*, rownum rn FROM  " + v_table + " WHERE rownum <= to_char("
                    + end_num + "))  WHERE rn > to_char(" + start_num + ")";
            resultList = this.query(selSqlend, obj.getClass(), obj);
            PageBeanVO pageBean = new PageBeanVO();
            pageBean.setPageNo(start);
            pageBean.setPageSize(limit);
            pageBean.setResList(resultList);
            pageBean.setResTotal(resTotal);
            return pageBean;
        }
        else if ("mysql".equalsIgnoreCase(Const.DIALECT))
        {
            if (sv != null)
            {
                sql = sql + " order by ";
            }

            String order = "";
            if (sv != null)
            {

                for (SortVO s : sv)
                {
                    order += s.getProperty() + " " + s.getDirection() + ",";
                }
                if (order.endsWith(","))
                {
                    order = order.substring(0, order.length() - 1);
                }
            }
            sql += order;
            String limitSql = " limit " + start + "," + limit;
            String totalSql = "select count(0) from (" + sql + ") vtb";
            sql += limitSql;
            List resList = this.query(sql, obj.getClass(), obj);
            logger.debug("开始执行sql=" + totalSql);
            int resTotal = nameJdbcTemplate.queryForInt(totalSql, paramBeanMapper(obj));
            PageBeanVO pageBean = new PageBeanVO();
            pageBean.setPageNo(start);
            pageBean.setPageSize(limit);
            pageBean.setResList(resList);
            pageBean.setResTotal(resTotal);
            pageBean.setCurrpage((start / limit) + 1);
            pageBean.setCountpage(resTotal % limit == 0 ? resTotal / limit : (resTotal / limit) + 1);
            return pageBean;
        }
        else
        {
            return null;
        }
    }

    /**
     * JqueryEasyui Grid分页
     * 
     * @param map
     *            存放page：当前页、rows：每页显示条数、sort：排序字段、order：排序规则
     * @param sql
     *            执行SQL
     * @param obj
     * @return PageBeanVO
     */
    public PageBeanVO query4PageEasyUI(PageBeanVO pager, String sql, Object obj)
    {
        int page = Integer.valueOf(pager.getPage());
        int rows = Integer.valueOf(pager.getRows());
        int offset = pager.getOffset();
        String sort = pager.getSort();
        String order = pager.getOrder();
        if (("oracle".equals(Const.DIALECT)))
        {
            List resultList = null;
            int start_num = (page - 1) * rows - offset;
            int end_num = page * rows - offset;
            String v_table = "(" + sql + " order by " + sort + " " + order + ") vtb";
            String countsql = "SELECT COUNT(*) FROM " + v_table;
            logger.debug("开始执行sql=" + countsql);
            int resTotal = nameJdbcTemplate.queryForInt(countsql, paramBeanMapper(obj));
            resTotal = resTotal + offset;
            String selSqlend = "";
            selSqlend = " SELECT * FROM (SELECT vtb.*, rownum rn FROM  " + v_table + " WHERE rownum <= to_char("
                    + end_num + "))  WHERE rn > to_char(" + start_num + ")";
            resultList = this.query(selSqlend, obj.getClass(), obj);
            PageBeanVO pageBean = new PageBeanVO();
            pageBean.setPageNo(page);
            pageBean.setPageSize(rows);
            pageBean.setResList(resultList);
            pageBean.setResTotal(resTotal);
            return pageBean;
        }
        else if ("mysql".equalsIgnoreCase(Const.DIALECT))
        {
            sql = sql + " order by " + sort + " " + order;
            String limitSql = " limit " + (page - 1) * rows + "," + rows;
            String totalSql = "select count(0) from (" + sql + ") vtb";
            sql += limitSql;
            logger.debug("开始执行sql=" + sql);
            List resList = this.query(sql, obj.getClass(), obj);
            logger.debug("开始执行sql=" + totalSql);
            int resTotal = nameJdbcTemplate.queryForInt(totalSql, paramBeanMapper(obj));
            PageBeanVO pageBean = new PageBeanVO();
            pageBean.setPageNo(page);
            pageBean.setPageSize(rows);
            pageBean.setResList(resList);
            pageBean.setResTotal(resTotal);
            return pageBean;
        }
        else
        {
            return null;
        }
    }

    public List query(String sql, Class clazz)
    {
        logger.debug("开始执行sql=" + sql);
        List retList = jdbcTemplate.query(sql, resultBeanMapper(clazz));
        return retList;
    }

    public List query(String sql, Class clazz, Object object)
    {
        sql = this.tranSql(sql, object);
        List retList = null;
        logger.debug("开始执行sql=" + sql);
        if (null == clazz && null == object)
        {
            retList = jdbcTemplate.queryForList(sql);
        }
        else if (null != clazz && null == object)
        {
            retList = jdbcTemplate.query(sql, resultBeanMapper(clazz));
        }
        else if (null == clazz && null != object)
        {
            retList = nameJdbcTemplate.query(sql, paramBeanMapper(object), resultBeanMapper(clazz));
        }
        else if (null != clazz && null != object)
        {
            retList = nameJdbcTemplate.query(sql, paramBeanMapper(object), resultBeanMapper(clazz));
        }
        else
        {
            retList = null;
        }
        return retList;
    }

    public int execSql(String sql)
    {
        logger.debug("开始执行sql=" + sql);
        return jdbcTemplate.update(sql);
    }

    public int execSql(String sql, Object object)
    {
        logger.debug("开始执行sql=" + sql);
        int resultValue = nameJdbcTemplate.update(sql, paramBeanMapper(object));
        return resultValue;
    }

    public int execSql(String sql, Object[] objs, Object object)
    {
        sql = this.tranSql(sql, object);
        logger.debug("开始执行sql=" + sql);
        if (null == object)
        {
            return jdbcTemplate.update(sql, objs);
        }
        else if (null == objs && null != object)
        {
            return nameJdbcTemplate.update(sql, paramBeanMapper(object));
        }
        else
        {
            return 0;
        }
    }

    public int execSqlByMap(String sql, Map map)
    {
        logger.debug("开始执行sql=" + sql);
        int resultValue = nameJdbcTemplate.update(sql, new MapSqlParameterSource(map));
        return resultValue;
    }

    public int[] batchExecSql(String sql, List<Object[]> objs)
    {
        SqlParameterSource[] sqlParams = new SqlParameterSource[objs.size()];
        int k = objs.size();
        for (int i = 0; i < k; i++)
        {
            sqlParams[i] = paramBeanMapper(objs.get(i));
        }
        sql = this.tranSql(sql, objs.get(0));
        logger.debug("开始执行sql=" + sql);
        return nameJdbcTemplate.batchUpdate(sql, sqlParams);
    }

    public int[] batchExecSql4List(String sql, List objs)
    {
        SqlParameterSource[] sqlParams = new SqlParameterSource[objs.size()];
        int k = objs.size();
        for (int i = 0; i < k; i++)
        {
            sqlParams[i] = paramBeanMapper(objs.get(i));
        }
        sql = this.tranSql(sql, objs.get(0));
        logger.debug("开始执行sql=" + sql);
        return nameJdbcTemplate.batchUpdate(sql, sqlParams);
    }

    public Object queryForColumn(String sql, Class columnType)
    {
        logger.debug("开始执行sql=" + sql);
        return jdbcTemplate.queryForObject(sql, columnType);
    }

    public Object queryForClass(String sql, Class clazz, Object object)
    {
        sql = this.tranSql(sql, object);
        logger.debug("开始执行sql=" + sql);
        List retList = this.query(sql, clazz, object);
        if (retList.size() > 0)
        {
            Object resClass = retList.get(0);
            return resClass;
        }
        else
        {
            return new Object();
        }
    }

    public Object queryForObject(String sql, Class clazz, Object object)
    {
        sql = this.tranSql(sql, object);
        List retList = this.query(sql, clazz, object);
        if (retList.size() > 0)
        {
            Object resClass = retList.get(0);
            return resClass;
        }
        else
        {
            return null;
        }
    }

    public static String builderSql(String xsql, Object obj)
    {
        String sql = null;
        if (null != obj)
        {
            sql = xsql;
        }
        else
        {
            sql = xsql;
        }
        return sql;
    }

    public String tranSql(String sql, Object obj)
    {
        String retSql = null;
        if (StringUtils.contains(sql, "/~") || StringUtils.contains(sql, "~/"))
        {
            retSql = builderSql(sql, obj);
        }
        else
        {
            retSql = sql;
        }
        return retSql;
    };

    public SimpleJdbcCall getSjdbcCall()
    {
        return sjdbcCall;
    }

    public void setSjdbcCall(SimpleJdbcCall sjdbcCall)
    {
        this.sjdbcCall = sjdbcCall;
    }

    public NamedParameterJdbcTemplate getNameJdbcTemplate()
    {
        return this.getNamedParameterJdbcTemplate();
    }

    public void setNameJdbcTemplate(NamedParameterJdbcTemplate nameJdbcTemplate)
    {
        this.nameJdbcTemplate = nameJdbcTemplate;
    }
}
