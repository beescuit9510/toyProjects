package mypageMember.model.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypageFunderFunding.model.vo.FundedFunding;
import mypageMember.model.service.MypageMemberService;
import table.model.vo.Member;

/**
 * Servlet implementation class FundedFundingListServlet
 */
@WebServlet(name = "FundedFundingList", urlPatterns = { "/fundedFundingList" })
public class FundedFundingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FundedFundingListServlet() {
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
		
		Member m = new Member();
		m.setcMemberNo(5);
		session.setAttribute("member", m);

		Member member = (Member) session.getAttribute("member");
		
		int cMemberNo = member.getcMemberNo();
		
		int totalCount = new MypageMemberService().getTotalFundedFunding(cMemberNo);
		
		request.setAttribute("totalCount", totalCount);

		
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/funder/fundingList.jsp").forward(request, response);
		

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
