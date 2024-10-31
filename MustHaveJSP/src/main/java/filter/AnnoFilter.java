package filter;
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(filterName = "AnnoFilter", urlPatterns = "/15FilterListener/AnnoFilter.jsp")
public class AnnoFilter implements Filter{
	
	FilterConfig config;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String searchField=request.getParameter("searchField");
		String searchWord=request.getParameter("searchWord");
		System.out.println("검색 필드 : "+ searchField);
		System.out.println("검색어 : "+ searchWord);
		
		chain.doFilter(request, response);
		
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
config=filterConfig;
		
		String filterName=filterConfig.getFilterName();
		
		System.out.println("BasicFilter - > init() 호출됨:"+ filterName);
	}
	
	

}
