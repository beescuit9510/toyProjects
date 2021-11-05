package funder.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funder.model.service.FunderService;
import table.model.vo.FunderCategory;
import table.model.vo.FunderPageData;
import table.model.vo.Member;

/**
 * Servlet implementation class SearchFunderServlet
 */
@WebServlet(name = "searchFunder", urlPatterns = { "/searchFunder" })
public class SearchFunderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFunderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션 정보가 있으면 회원값을 가져오기
		HttpSession session = request.getSession(false);
		if(session != null){
			Member member = (Member)session.getAttribute("member");
			int memberNo = 0;
			if(member != null) {
				 memberNo = member.getcMemberNo();
			}			
		String keyword = request.getParameter("keyword");
		int reqPage = 0;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		ArrayList<FunderCategory> catelist = new FunderService().selectCategoryList();
		FunderPageData fundpd = new FunderService().searchFunder(reqPage,keyword,memberNo);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/funder/funderList.jsp");
		request.setAttribute("list", fundpd.getMblist());
		request.setAttribute("pageNavi", fundpd.getPageNavi());
		request.setAttribute("start", fundpd.getStart());
		request.setAttribute("catelist", catelist);
		request.setAttribute("keyword", keyword);
		view.forward(request, response);
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
