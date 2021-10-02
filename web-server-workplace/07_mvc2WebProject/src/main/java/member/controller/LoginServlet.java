package member.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

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
		//1. 인코딩
		//2. view에서 보낸 데이터 추출
		//3. 비지니스 로직
		//4. 결과처리
		
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new MemberService().selectOneMember(memberId, memberPw);
		
		if(member!=null) {
			
			if(member.getMemberLevel() == 3) {
				//레벨 3이면 ID/PW가 맞더라도 로그인안됨.
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/msg.jsp");
				request.setAttribute("msg", "로그인 권한이 없습니다. 관리자에게 문의하세요");
				request.setAttribute("loc", "/");
				view.forward(request, response);
				
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				
				response.sendRedirect("/");				
				
			}
		}else {
			//msg.jsp 는 alert을 화면에 띄운 후 페이지를 이동하는 기능을 가진 jsp
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/msg.jsp");
			//alert에 띄울 메세지를 msg에 등록
			request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
			//이동할 페이지를 loc에 등록
			request.setAttribute("loc", "/");
			
			view.forward(request, response);
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
