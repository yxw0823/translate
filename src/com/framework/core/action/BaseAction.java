package com.framework.core.action;

/**
 * Title: Action层基类
 * Description: Action层基类
 * Copyright: Copyright (c) 2012
 * @author 钱嘉
 * @version 1.0 2012-05-29
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.framework.core.domain.PageBeanVO;
import com.framework.core.service.IBaseService;
import com.framework.core.utils.BeanUtils;
import com.framework.core.utils.JsonUtils;

@Component
@SuppressWarnings("unchecked")
public class BaseAction
{
	protected static String SUCCESS = "success";
	protected static String ERROR = "common/defaultError";
	protected static String INPUT = "input";
	private static final String ACTION_MESSAGE = "ACTION_MESSAGE";
	protected static Logger logger = null;
	protected static String className = null;
	public static final String COMMON_FAIL_PAGE = "fail";
	public static final String COMMON_FAIL_ALERT_KEY = "fail_key";
	public static final String USER_SESSION_KEY = "_user_session_key_";
	@Autowired
	@Qualifier("baseService")
	// 因此接口有多个实现类，在此要指定一个实现
	private IBaseService baseService;

	public IBaseService getBaseService()
	{
		return baseService;
	}

	public void setBaseService(IBaseService baseService)
	{
		this.baseService = baseService;
	}

	public BaseAction()
	{
		super();
		className = getClass().getName();
		logger = LoggerFactory.getLogger(className);
	}

	protected void saveInfoForRequest(String messages, HttpServletRequest request)
	{
		saveMessages(request, messages);
	}

	protected void saveMessages(HttpServletRequest request, String messages)
	{
		if (messages == null || messages.equals(""))
		{
			request.removeAttribute(ACTION_MESSAGE);
			return;
		}
		else
		{
			request.setAttribute(ACTION_MESSAGE, messages);
			return;
		}
	}

	protected void saveObjForRequest(Object obj, HttpServletRequest request, String saveName)
	{
		String className = saveName;
		if (obj == null)
		{
			request.removeAttribute(className);
			return;
		}
		else
		{
			request.setAttribute(className, obj);
			return;
		}
	}

	protected void saveObjForSession(Object obj, HttpServletRequest request, String saveName)
	{
		String className = saveName;
		HttpSession session = request.getSession();
		if (obj == null)
		{
			session.removeAttribute(className);
			return;
		}
		else
		{
			session.setAttribute(className, obj);
			return;
		}
	}

	public String listToJsonString(List listvo)
	{
		/*
		 * 注意: JSONArray.fromObject(listvo):对listvo中的vo中如果有日期格式要特别注意
		 */
		JSONArray jsonObject = JSONArray.fromObject(listvo);
		String jsonString = jsonObject.toString();
		return jsonString;
	}

	/**
	 * 向客户端输出分页JSON
	 * 
	 * @param response
	 * @param pageBean
	 */
	public void pagerToJsonString(HttpServletResponse response, PageBeanVO pageBean)
	{
		Map resMap = new HashMap();
		resMap.put("total", pageBean.getResTotal());
		resMap.put("currpage", pageBean.getCurrpage());
		resMap.put("countpage", pageBean.getCountpage());
		resMap.put("resTotal", pageBean.getResTotal());
		resMap.put("pageString", pageBean.toString());
		resMap.put("rows", pageBean.getResList());
		String jsonString = JSONObject.fromObject(resMap).toString();
		try
		{
			response.setContentType("text/json; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonString);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 向客户端输出分页JSON
	 * 
	 * @param response
	 * @param pageBean
	 */
	public void pagerToDataTablesJsonString(HttpServletResponse response, PageBeanVO pageBean)
	{
		Map resMap = new HashMap();
		resMap.put("sEcho", pageBean.getResTotal());
		resMap.put("iTotalRecords", pageBean.getResTotal());
		resMap.put("iTotalDisplayRecords", pageBean.getResTotal());
		resMap.put("aaData", pageBean.getResList());
		String jsonString = JSONObject.fromObject(resMap).toString();
		try
		{
			response.setContentType("text/json; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonString);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 向客户端输出日期格式化后的分页json
	 * 
	 * @param response
	 * @param object
	 *            被转化的JAVA对象
	 * @param dataFormat
	 *            日期格式
	 */
	public void pagerToJsonDateFormate(HttpServletResponse response, PageBeanVO pageBean, String dataFormat)
	{
		JSONArray jsonObject = JsonUtils.getJsonArray4JavaPOJO(pageBean.getResList(), dataFormat);
		String jsonString = "{'total':" + pageBean.getResTotal() + ",'rows':" + jsonObject.toString() + "}";
		try
		{
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(jsonString);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端输出JSONObject
	 * 
	 * @param response
	 * @param object
	 */
	public void responseJson(HttpServletResponse response, Object object)
	{
		String jsonStr = JSONObject.fromObject(object).toString();
		try
		{
			response.setContentType("text/json; charset=UTF-8");
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端输出JSONArray
	 * 
	 * @param response
	 * @param object
	 */
	public void responseJsonArray(HttpServletResponse response, Object object)
	{
		String jsonStr = JSONArray.fromObject(object).toString();
		try
		{
			response.setContentType("text/json; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端输出日期格式化后的JSONObject
	 * 
	 * @param response
	 * @param object
	 *            被转化的JAVA对象
	 * @param dataFormat
	 *            日期格式
	 */
	public void responseJsonDateFormate(HttpServletResponse response, Object object, String dataFormat)
	{
		String jsonStr = JsonUtils.getJsonObject4JavaPOJO(object, dataFormat).toString();
		try
		{
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端输出日期格式化后的JSONArray
	 * 
	 * @param response
	 * @param object
	 *            被转化的JAVA对象
	 * @param dataFormat
	 *            日期格式
	 */
	public void responseJsonArrayDateFormate(HttpServletResponse response, Object object, String dataFormat)
	{
		String jsonStr = JsonUtils.getJsonArray4JavaPOJO(object, dataFormat).toString();
		try
		{
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端输出普通文本
	 * 
	 * @param response
	 * @param object
	 */
	public void responseText(HttpServletResponse response, String text)
	{
		try
		{
			response.setContentType("text/json; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(text);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端输出xml格式文本
	 * 
	 * @param response
	 * @param object
	 */
	public void responseXml(HttpServletResponse response, String xml)
	{
		try
		{
			response.setContentType("text/xml");
			response.getWriter().write(xml);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端输出Html格式文本
	 * 
	 * @param response
	 * @param object
	 */
	public void responseHtml(HttpServletResponse response, String html)
	{
		try
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(html);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前时间 格式为
	 * 
	 */
	public String getDateForYYYYMM(String geshi)
	{
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(geshi);
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY));
		return sdf.format(calendar.getTime());
	}

	/**
	 * 向客户端输出JSONObject
	 * 
	 * @param response
	 * @param object
	 */
	public void responseJsonExtForm(HttpServletResponse response, Object object)
	{
		// Map resMap = new HashMap();
		// resMap.put("success",object!=null);
		// resMap.put("data",object);
		String jsonString = JSON.toJSONString(object);
		try
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().write(jsonString);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 解析Ext proxy使用api时的传值 注意writer的root : ''
	 * 
	 * @param response
	 * @param object
	 */
	public <T> List<T> extraExtProxyAPIRecords(HttpServletRequest request, String root, Class<T> cls)
	{
		String jsonStr = request.getParameter(root);
		if (jsonStr == null)
			System.out.println("JSON字符串未找到");
		return JSON.parseArray(jsonStr, cls);
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
}
