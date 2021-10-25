package mypageMember.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import mypageMember.model.dao.MypageMemberDao;
import mypageMember.model.service.MypageMemberService;
import table.model.vo.Member;

/**
 * Servlet implementation class UnlikeFunderServlet
 */
@WebServlet(name = "UnlikeFunding", urlPatterns = { "/unlikeFunding" })
public class UnlikeFundingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnlikeFundingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

		Member member= (Member)session.getAttribute("member");

		int cMemberNo =member.getcMemberNo();		

		
		int likedProjectNo = Integer.parseInt(request.getParameter("likedProjectNo"));
		
		int r = new MypageMemberService().unLikeFunding(cMemberNo, likedProjectNo);
		
		if(r > 0) {
			response.setCharacterEncoding("UTF-8");
		    response.setContentType("application/json");

			PrintWriter out = response.getWriter();

			new Gson().toJson("좋아요 취소 성공", out);

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
