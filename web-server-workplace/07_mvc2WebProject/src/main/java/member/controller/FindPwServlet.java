package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class FindPwServlet
 */
@WebServlet(name = "FindPw", urlPatterns = { "/findPw" })
public class FindPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("utf-8");
		
		String memberId= request.getParameter("memberId");
		
		String phone = request.getParameter("phone");
		
		Member member = new MemberService().findPw(memberId, phone);
		
//		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/foundIdPw.jsp");

		
		String msg = null;

		if(member != null) {
			msg = "찾으시는 비밀번호는 : "+member.getMemberPw();
		
		}else {
			msg = "해당하는 회원이 없습니다. 아이디 혹은 전화번호를 확인해주세요";
			
		}
		
		request.setAttribute("msg", msg);
//		request.setAttribute("loc", "/findIdPwFrm");
		view.forward(request, response);
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
