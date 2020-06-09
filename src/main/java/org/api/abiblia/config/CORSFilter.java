package org.api.abiblia.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "false");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-requested-with, X-AUTH-TOKEN, access_token, client_id, device_id, senha");
		response.setHeader("Access-Control-Max-Age", "3600");

		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
			response.setStatus(200);
		} else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {

	}
}