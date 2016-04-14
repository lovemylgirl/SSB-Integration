package org.dream.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class FirstFileter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		/** 如果非要获取spring管理的bean，可以通过此种方式获取spring上下文 */
		// WebApplicationContext context = WebApplicationContextUtils
		// .getWebApplicationContext(request.getSession().getServletContext());
		// ObjectMapper jacksonObjectMapper = (ObjectMapper)
		// context.getBean("jacksonObjectMapper");
		// System.out.println(jacksonObjectMapper.writeValueAsString(request.getParameterMap()));
		
		System.out.println("FirstFileter pre has excuted !");
		filterChain.doFilter(request, response);
		System.out.println("FirstFileter has excuted !");
	}

}
