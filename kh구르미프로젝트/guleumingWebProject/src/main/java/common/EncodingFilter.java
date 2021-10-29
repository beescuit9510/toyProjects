package common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter2
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = { "/EncodingFilter" })
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		//필터가 소멸될때 호출되는 메소드;
		
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		

		request.setCharacterEncoding("UTF-8");
	
		
		
		// pass the request along the filter chain		
		//chain.doFilter() 이전에 작성하면 서버 도착 전에 할일;
		chain.doFilter(request, response);
		//chain.doFilter() 이후에 작성하면 클라이언트 응답 가기전에 할일;
		//chain.doFilter() - > 다음필터를 적용, 다음필터가 없으면 servlet 호출;
		
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//필터가 생성될때 호출되는 메소드;
		
	}

}
