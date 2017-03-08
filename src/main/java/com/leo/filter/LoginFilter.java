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
		System.out.println("loginfilter-->init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String userName = (String)session.getAttribute("userName");
		String loginuri = ((HttpServletRequest)request).getRequestURI();
		if(StringUtil.isEmpty(userName)&&!matchesUrl(loginuri)){
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/login");
			return;
		}
		chain.doFilter(request, response);
	}
	public boolean matchesUrl(String url){
		boolean flag = false;
		for(String str:Constants.assetsUrl){
			if(url.contains(str)){
				flag = true;
			}
		}
		return flag;
	}
	@Override
	public void destroy() {
		System.out.println("loginfilter-->destroy");
	}

}
