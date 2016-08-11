package com.apps.sys.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.apps.userinfo.domain.VoUserinforVO;
import com.framework.core.utils.Const;

public class LoginInterceptor extends HandlerInterceptorAdapter
{
	private static List<String> PermitURI = new ArrayList<String>();
	static
	{
		PermitURI.add("SecCodeAction.do");
		PermitURI.add("apps/qrlogin/QrcodeloginAction.do");
		PermitURI.add("/app/QrcodeloginAction.do");
	}

	private static boolean ValidateURI(String URI)
	{
		boolean flag = false;
		for (String uri : PermitURI)
		{
			if (URI.indexOf(uri) != -1)
			{
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (ValidateURI(request.getRequestURI()))
			return super.preHandle(request, response, handler);

		VoUserinforVO ac = (VoUserinforVO) request.getSession().getAttribute(Const.SESSION_USER);
		if (ac == null)
		{
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
