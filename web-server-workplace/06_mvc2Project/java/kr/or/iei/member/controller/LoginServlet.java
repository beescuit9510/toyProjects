package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new MemberService().selectOneMember(memberId, memberPw);
		
		if(member!=null) {
			
			
			//세션객체 생성
			// request.getSession();
			// -> 매개변수가 없는 경우 세션이 이미 존재하면 있는 세션을 가져오고
			// -> 매개변수가 있는 경우 세션이
			// 매개변수가 없는 경우 자동으로 true로 봄.
			
			// request.getSession(false) 매개변수로 false
			//기존에 세션이 존재하면 기존세션을 가져오고
			//기존에 세션이 존재하지 않으면 null 리턴;
			

			
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			
			response.sendRedirect("/");
			
		}else {
			
			response.sendRedirect("/views/loginFail.jsp");			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
