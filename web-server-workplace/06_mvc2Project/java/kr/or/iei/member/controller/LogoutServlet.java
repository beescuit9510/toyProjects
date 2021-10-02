package kr.or.iei.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(name = "Logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// request.getSession(false) 매개변수로 false
		//기존에 세션이 존재하면 기존세션을 가져오고
		//기존에 세션이 존재하지 않으면 null 리턴;
		
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			session.invalidate();
		}
		
		response.sendRedirect("/");
		//세션객체 생성
		// request.getSession();
		// -> 매개변수가 없는 경우 세션이 이미 존재하면 있는 세션을 가져오고
		// -> 매개변수가 있는 경우 세션이
		// 매개변수가 없는 경우 자동으로 true로 봄.
		
		// request.getSession(false) 매개변수로 false
		//기존에 세션이 존재하면 기존세션을 가져오고
		//기존에 세션이 존재하지 않으면 null 리턴;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
