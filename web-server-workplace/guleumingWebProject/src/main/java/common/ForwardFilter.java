package common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class ForwardFilter
 */
@WebFilter(filterName = "ForwardFilter", urlPatterns = { "/ForwardFilter" })
public class ForwardFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ForwardFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
//		System.out.println("filter 시작");

		
		String href = request.getParameter("href");
		String path = "/WEB-INF/views"+href+".jsp";
		
//		System.out.println(path);
		
		HttpServletRequest req = (HttpServletRequest)request;
		req.getRequestDispatcher(path).forward(req, response);
		
		chain.doFilter(req, response);
		
//		System.out.println("filter 끝");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
