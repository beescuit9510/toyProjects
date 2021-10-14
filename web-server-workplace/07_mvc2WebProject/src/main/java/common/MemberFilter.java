package common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class MemberFilter
 */
@WebFilter("/MemberFilter")
public class MemberFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public MemberFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		Member member = new Member();
		
		
//		int memberNo = -1;
//		
//		int memberLevel = -1;
//		
		try {
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			member.setMemberNo(memberNo);
		
		} catch (NumberFormatException e) {
			
		}
		
		try {
			int memberLevel  = Integer.parseInt(request.getParameter("memberLevel"));			
			member.setMemberLevel(memberLevel);

		} catch (NumberFormatException e) {
			
		}

		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String enrollDate = request.getParameter("enrollDate");
		
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setPhone(phone);
		member.setAddress(address);
		member.setEnrollDate(enrollDate);
		
		request.setAttribute("memberFilter", member);
		
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
