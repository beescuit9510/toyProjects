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

import mypageFunderFunding.model.vo.MyOwnProject;
import mypageMember.model.service.MypageMemberService;
import table.model.vo.FundingComment;
import table.model.vo.Member;

/**
 * Servlet implementation class InsertCommentServlet
 */
@WebServlet(name = "InsertComment", urlPatterns = { "/insertComment" })
public class InsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession(false);
		
		Member member = (Member) session.getAttribute("member");
		
		int cMemberNo = member.getcMemberNo();
		
		String comment = request.getParameter("comment");
		int projcetNo = Integer.parseInt(request.getParameter("projectNo"));
		int commentRefNo = Integer.parseInt(request.getParameter("commentRefNo"));
		int currCount = Integer.parseInt(request.getParameter("currCount"));
		

		FundingComment fc = new FundingComment();
		
		fc.setCommentContent(comment);
		fc.setProjectRefNo(projcetNo);
		fc.setCommentRefNo(commentRefNo);
		fc.setCommentWriter(cMemberNo);
		
		int r = new MypageMemberService().replyComment(fc);
		
		if(r > 0) {
			
			
			ArrayList<MyOwnProject> myOwnProjects = new MypageMemberService().selectMyOwnProject(cMemberNo, 1, currCount);

			
			response.setCharacterEncoding("UTF-8");
		    response.setContentType("application/json");

			PrintWriter out = response.getWriter();

			System.out.println(myOwnProjects);
			new Gson().toJson(myOwnProjects, out);

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
