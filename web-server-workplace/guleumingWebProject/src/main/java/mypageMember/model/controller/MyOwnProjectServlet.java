package mypageMember.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypageMember.model.service.MypageMemberService;
import table.model.vo.Member;

/**
 * Servlet implementation class MyOwnProjectServlet
 */
@WebServlet(name = "MyOwnProject", urlPatterns = { "/myOwnProject" })
public class MyOwnProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyOwnProjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	

		
		HttpSession session = request.getSession(false);

		Member member = (Member) session.getAttribute("member");
		
		int cMemberNo = member.getcMemberNo();
		
		int totalCount = new MypageMemberService().getTotalMyOwnProjcet(cMemberNo);
		
		request.setAttribute("totalCount", totalCount);
		System.out.println(member);
		
		System.out.println(totalCount);
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/funder/myFundingProjectList.jsp").forward(request, response);
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
