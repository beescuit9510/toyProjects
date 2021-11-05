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
 * Servlet implementation class FundingCommentUpdateServlet
 */
@WebServlet(name = "FundingCommentUpdate", urlPatterns = { "/fundingCommentUpdate" })
public class FundingCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String updateCommentContent = request.getParameter("updateCommentContent");
		//int cMemberNo = Integer.parseInt(request.getParameter("cMemberNo"));
		int result = new fundingService().fundingCommentUpdate(commentNo,updateCommentContent);
		RequestDispatcher view =
				request.getRequestDispatcher("/WEB-INF/views/funding/fundingMsg.jsp");
		if(result == 1) {
			request.setAttribute("msg", "댓글 수정 완료 ");
		}else {
			request.setAttribute("msg", "댓글 수정 실패");
		}
		request.setAttribute("loc", "/fundingView?projectNo="+projectNo+"&showComment=true#comment"+commentNo);
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
