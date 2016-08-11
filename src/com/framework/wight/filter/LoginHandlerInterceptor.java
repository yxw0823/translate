package com.framework.wight.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if(path.matches("")){
			return true;
		}else{
			//HttpSession session = request.getSession();
			//User user = (User)session.getAttribute(Const.SESSION_USER);
//			if(user!=null){
				return true;
//			}else{
//				response.sendRedirect(request.getContextPath()+"/jsp/sys/login.jsp");
//				return false;
//			}
		}
	}
	
}
