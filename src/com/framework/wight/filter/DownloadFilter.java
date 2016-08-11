/**  
 * Project Name:sdyd  
 * File Name:DownloadFilter.java  
 * Package Name:com.framework.wight.filter  
 * Date:2015年1月14日上午8:46:50  
 * Copyright (c) 2015, 南京烽火星空通信发展有限公司 All Rights Reserved.  
 *  
 */

package com.framework.wight.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:DownloadFilter <br/> Function: 下载过滤器. <br/> Reason: 下载过滤器. <br/>
 * Date: 2015年1月14日 上午8:46:50 <br/>
 * 
 * @author ZhuQiang
 * @version
 * @since JDK 1.6
 * @see
 */
public class DownloadFilter implements Filter
{
	/**
	 * UNKNOW_RESOURCES:不明资源.
	 * 
	 * @since JDK 1.6
	 */
	private static final int UNKNOW_RESOURCES = -1;
	/**
	 * STATIC_RESOURCES:静态资源.
	 * 
	 * @since JDK 1.6
	 */
	private static final int STATIC_RESOURCES = 1;
	/**
	 * NOT_ACCESS_RESOURCES:不允许访问的资源.
	 * 
	 * @since JDK 1.6
	 */
	private static final int NOT_ACCESS_RESOURCES = 2;
	/**
	 * NOT_ACCESS_RESOURCES:下载资源.
	 * 
	 * @since JDK 1.6
	 */
	private static final int DOWNLOAD_RESOURCES = 3;
	/**
	 * NOT_ACCESS_RESOURCES:HTTP请求资源.
	 * 
	 * @since JDK 1.6
	 */
	private static final int HTTP_REQUEST = 4;
	/**
	 * staticURI:静态资源定义.
	 * 
	 * @since JDK 1.6
	 */
	private static Map<String, String> staticResourcesMap = new HashMap<String, String>();
	/**
	 * unaccessResourcesMap:不可访问资源.
	 * 
	 * @since JDK 1.6
	 */
	private static Map<String, String> notAccessResourcesMap = new HashMap<String, String>();
	/**
	 * unaccessResourcesMap:http请求.
	 * 
	 * @since JDK 1.6
	 */
	private static Map<String, String> httpRequestMap = new HashMap<String, String>();
	/**
	 * config:过滤器配置.
	 * 
	 * @since JDK 1.6
	 */
	FilterConfig config = null;
	HttpServletRequest request = null;

	static
	{
		staticResourcesMap.put("common", "/common");
		staticResourcesMap.put("css", "/css");
		staticResourcesMap.put("data", "/data");
		staticResourcesMap.put("images", "/images");
		staticResourcesMap.put("js", "/js");

		notAccessResourcesMap.put("jsp", "/jsp");

		httpRequestMap.put("apps", "/apps");
	}

	/**
	 * ValidateURI:判断是否是下载. <br/>
	 * 
	 * @author ZhuQiang
	 * @param URI
	 * @return
	 * @since JDK 1.6
	 */
	private static int ValidateURI(HttpServletRequest request, HttpServletResponse response, String uri)
	{
		uri = uri.replaceFirst(request.getContextPath(), "");
		String[] s = uri.split("/");
		if (s.length < 1 && !"/".equals(uri))
		{
			return UNKNOW_RESOURCES;
		}
		else if ("/".equals(uri) || s.length == 2 || null != httpRequestMap.get(s[1]))
		{
			return HTTP_REQUEST;
		}
		else if (null != staticResourcesMap.get(s[1]))
		{
			return STATIC_RESOURCES;
		}
		else if (null != notAccessResourcesMap.get(s[1]))
		{
			return NOT_ACCESS_RESOURCES;
		}
		else
		{
			return DOWNLOAD_RESOURCES;
		}
	}

	public void destroy()
	{
	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException,
		ServletException
	{
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		int rKey = ValidateURI(request, response, request.getRequestURI());
		if (rKey == STATIC_RESOURCES)
		{// 静态资源
			// 自动添加content-type
			sResponse.setContentType("multipart/form-data");
			chain.doFilter(sRequest, sResponse);
		}
		else if (rKey == NOT_ACCESS_RESOURCES)
		{// 非法资源（不允许直接访问）
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
		else if (rKey == DOWNLOAD_RESOURCES)
		{// 下载资源
			// 自动添加content-type
			sResponse.setContentType("multipart/form-data");
			chain.doFilter(sRequest, sResponse);
		}
		else if (rKey == HTTP_REQUEST)
		{// http请求
			chain.doFilter(sRequest, sResponse);
		}
		else
		{// 未知资源
			chain.doFilter(sRequest, sResponse);
		}
	}

	public void init(FilterConfig config) throws ServletException
	{
		this.config = config;
	}

}
