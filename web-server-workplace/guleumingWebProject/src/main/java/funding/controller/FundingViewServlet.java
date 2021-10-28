package funding.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funding.model.service.fundingService;
import funding.model.vo.FundingCommentTotal;
import funding.model.vo.FundingViewTotal;
import table.model.vo.MakerInfo;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;


/**
 * Servlet implementation class FundingViewServlet
 */
@WebServlet(name = "FundingView", urlPatterns = { "/fundingView" })
public class FundingViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		ArrayList<FundingCommentTotal> commentList = fundingService.selectFundingCommentList(projectNo);
		Reward r = new fundingService().selectReward(projectNo);
		boolean showComment = Boolean.parseBoolean(request.getParameter("showComment"));
		FundingViewTotal fvt = new fundingService().selectFundingViewTotal(projectNo);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/funding/fundingView.jsp");
		request.setAttribute("r", r);
		request.setAttribute("fvt", fvt);
		request.setAttribute("commentList", commentList);
		request.setAttribute("showComment", showComment);
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
