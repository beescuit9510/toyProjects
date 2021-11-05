package funding.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funding.model.service.fundingService;
import funding.model.vo.FundingListRecent;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;


/**
 * Servlet implementation class FundingListServlet
 */
@WebServlet(name = "FundingList", urlPatterns = { "/fundingList" })
public class FundingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int reqPage = 1;
		//ArrayList<FundingListRecent> list = new fundingService().selectFundingListRecent(reqPage);
		int count = new fundingService().countFunding();
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/funding/fundingList.jsp");
		//request.setAttribute("list", list);
		request.setAttribute("count", count);
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
