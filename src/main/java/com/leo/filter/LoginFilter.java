package com.leo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leo.util.Constants;
import com.leo.util.StringUtil;

public class LoginFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String userName = (String)session.getAttribute("adminUser");
		String uri = ((HttpServletRequest)request).getRequestURI();
		String tab = ((HttpServletRequest)request).getParameter("tab");
		if(!uri.endsWith(".do")&&StringUtil.isEmpty(userName)&&!"login_in".equals(tab)){
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/admin?tab=login_in");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
