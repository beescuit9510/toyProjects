package funding.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funding.model.service.fundingService;

/**
 * Servlet implementation class FundingCommentDeleteServlet
 */
@WebServlet(name = "FundingCommentDelete", urlPatterns = { "/fundingCommentDelete" })
public class FundingCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectRefNo = Integer.parseInt(request.getParameter("projectRefNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		//int cMemberNo = Integer.parseInt(request.getParameter("cMemberNo"));
		int result = new fundingService().fundingCommentDelete(commentNo);
		RequestDispatcher view =
				request.getRequestDispatcher("/WEB-INF/views/funding/fundingMsg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글 삭제 ");
		}else {
			request.setAttribute("msg", "댓글 삭제 실패");
		}
		request.setAttribute("loc", "/fundingView?projectNo="+projectRefNo+"&showComment=true#comment"+commentNo);
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
