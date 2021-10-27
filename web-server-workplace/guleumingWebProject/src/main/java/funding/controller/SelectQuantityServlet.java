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
import table.model.vo.Reward;

/**
 * Servlet implementation class SelectQuantityServlet
 */
@WebServlet(name = "SelectQuantity", urlPatterns = { "/selectQuantity" })
public class SelectQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectQuantityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		//ArrayList<FundingCommentTotal> commentList = fundingService.selectFundingCommentList(projectNo);
		Reward r = new fundingService().selectReward(projectNo);// reward 잘받아옴
		//ProjectBasicInfo pbi = new fundingService().selectProjectBasicInfo(projectNo);//pbi 잘받아옴
		FundingViewTotal fvt = new fundingService().selectFundingViewTotal(projectNo);
		//MakerInfo mi = new fundingService().selectMakerInfo(projectNo);//잘받아 옴
		//int total = new fundingService().totalQuantity(projectNo);// 합 잘받아오
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//LocalDate now = LocalDate.now();
		//String formatedNow = now.format(formatter);
		//String endDate = fvt.getEndDate();
	    //LocalDate date = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
	    //LocalDate payDate = date.plusDays(1);
	    //String payDateS =payDate.format(formatter);
	    //System.out.println(date);
	    //System.out.println(now);
		//System.out.println(formatedNow);
	    //long period = ChronoUnit.DAYS.between(now, date);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/funding/selectQuantity.jsp");
		request.setAttribute("r", r);
		//request.setAttribute("pbi", pbi);
		//request.setAttribute("total", total);
		//request.setAttribute("mi", mi);
		//request.setAttribute("period", period);
		//request.setAttribute("payDateS", payDateS);
		request.setAttribute("fvt", fvt);
		//request.setAttribute("commentList", commentList);
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
