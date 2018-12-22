package br.com.gympass.gpresult;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
			public void setHeader(String name, String value) {
				if(!name.equals("Content-Disposition")) {
					super.setHeader(name, value);
				}
			}
		});
		System.out.println("Got Here! ");
		
	}
	@Override
	public void destroy() {	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}