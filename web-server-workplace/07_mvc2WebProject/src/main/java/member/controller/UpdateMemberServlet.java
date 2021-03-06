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
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet(name = "UpdateMember", urlPatterns = { "/updateMember" })
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("utf-8");

		Member member = new Member(request.getParameter("memberId"), 
				request.getParameter("memberPw"),
				request.getParameter("memberName"), 
				request.getParameter("phone"), 
				request.getParameter("address"));

		int result = new MemberService().updateMember(member);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		
		String msg = null;
		
		
		if (result > 0 ) {
//			
			HttpSession session = request.getSession(false);
			
			Member myMember = new MemberService().selectOneMember(member.getMemberId(), member.getMemberPw());
			
			session.setAttribute("member", myMember);
			
//			Member myMember = (Member) session.getAttribute("member");
//			myMember.setMemberPw(request.getParameter("memberPW"));
//			myMember.setMemberName(request.getParameter("memberName"));
//			myMember.setPhone(request.getParameter("phone"));
//			myMember.setAddress(request.getParameter("address"));
//			
			
			
			msg = "???????????? ??????!";
			
		} else {
			msg = "???????????? ??????!";

		}
		
		request.setAttribute("msg", msg);
		
		request.setAttribute("loc", "mypage");
		
		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
