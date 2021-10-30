package funder.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funder.model.service.FunderService;
import table.model.vo.FunderCategory;
import table.model.vo.FunderPageData;

/**
 * Servlet implementation class FunderListServlet
 */
@WebServlet(name = "FunderList", urlPatterns = { "/funder/funderList" })
public class FunderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FunderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("funderCategory");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		FunderPageData fundpd = new FunderService().selectFunderList(reqPage,category);
		ArrayList<FunderCategory> catelist = new FunderService().selectCategoryList();
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/funder/funderList.jsp");
		request.setAttribute("list", fundpd.getMblist());
		request.setAttribute("pageNavi", fundpd.getPageNavi());
		request.setAttribute("start", fundpd.getStart());
		request.setAttribute("catelist", catelist);
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
