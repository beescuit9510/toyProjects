package funding.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funding.model.service.fundingService;
import table.model.vo.FundingComment;

/**
 * Servlet implementation class FundingCommentWriteServlet
 */
@WebServlet(name = "FundingCommentWrite", urlPatterns = { "/fundingCommentWrite" })
public class FundingCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FundingComment fc = new FundingComment();
		fc.setCommentLevel(Integer.parseInt(request.getParameter("commentLevel")));
		fc.setCommentRefNo(Integer.parseInt(request.getParameter("commentRefNo")));
		fc.setCommentWriter(Integer.parseInt(request.getParameter("commentWriter")));
		fc.setCommentContent(request.getParameter("commentContent"));
		fc.setProjectRefNo(Integer.parseInt(request.getParameter("projectRefNo")));
		System.out.println(fc.getCommentLevel());
		System.out.println(fc.getCommentNo());
		System.out.println(fc.getCommentRefNo());
		System.out.println(fc.getCommentWriter());
		System.out.println(fc.getProjectRefNo());
		int result = new fundingService().insertFundingComment(fc);
		RequestDispatcher view =
				request.getRequestDispatcher("/WEB-INF/views/funding/fundingMsg.jsp");
		if(result>0) {
			request.setAttribute("msg", "댓글 등록 ");
		}else {
			request.setAttribute("msg", "본문이 비어있습니다");
		}
		request.setAttribute("loc", "/fundingView?projectNo="+fc.getProjectRefNo()+"&showComment=true");
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
