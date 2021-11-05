package mypageMember.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import mypageFunderFunding.model.vo.FundedFunding;
import mypageMember.model.service.MypageMemberService;
import table.model.vo.Member;

/**
 * Servlet implementation class GetMoreFundedListServlet
 */
@WebServlet(name = "GetMoreFundedList", urlPatterns = { "/getMoreFundedList" })
public class GetMoreFundedListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMoreFundedListServlet() {
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

		Member member= (Member)session.getAttribute("member");
		
		int cMemberNo =member.getcMemberNo();
	
		int currCount = Integer.parseInt(request.getParameter("currCount"));

		int perPost = Integer.parseInt(request.getParameter("perPost"));
		
		
		ArrayList<FundedFunding> fundedFundings = new MypageMemberService().selectFundedFunding(cMemberNo, currCount+1, currCount+perPost);

		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		new Gson().toJson(fundedFundings, out);
		
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
