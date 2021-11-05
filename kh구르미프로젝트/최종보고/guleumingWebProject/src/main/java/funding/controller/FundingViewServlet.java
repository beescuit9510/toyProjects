package funding.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import funding.model.service.fundingService;
import funding.model.vo.FundingCommentTotal;
import funding.model.vo.FundingViewTotal;
import funding.model.vo.LikeCount;
import table.model.vo.MakerInfo;
import table.model.vo.Member;
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
		HttpSession session = request.getSession(false);
		int cMemberNo = 0;
		if(session != null) {
			Member member = (Member)session.getAttribute("member");
			if(member != null) {
				cMemberNo = member.getcMemberNo();
			}
		}
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		ArrayList<FundingCommentTotal> commentList = fundingService.selectFundingCommentList(projectNo);
		//ArrayList<LikeCount> lcList = fundingService.selectLikedMember(projectNo);
		//Reward r = new fundingService().selectReward(projectNo);
		boolean showComment = Boolean.parseBoolean(request.getParameter("showComment"));
		FundingViewTotal fvt = new fundingService().selectFundingViewTotal(projectNo,cMemberNo);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/funding/fundingView.jsp");
		//request.setAttribute("r", r);
		//request.setAttribute("lcList", lcList);
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
