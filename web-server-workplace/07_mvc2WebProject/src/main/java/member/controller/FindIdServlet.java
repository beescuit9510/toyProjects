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
 * Servlet implementation class FindIdServlet
 */
@WebServlet(name = "FindId", urlPatterns = { "/findId" })
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String memberName= request.getParameter("memberName");
		
		String phone = request.getParameter("phone");
		
		Member member = new MemberService().findId(memberName, phone);
		
//		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/foundIdPw.jsp");
		
		String msg = null;

		if(member != null) {
			msg = "찾으시는 아이디는 : "+member.getMemberId();
		
		}else {
			msg = "해당하는 회원이 없습니다. 이름 혹은 비밀번호를 확인해주세요";
			
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
