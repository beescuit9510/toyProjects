package adminPage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adminPage.model.service.AdminPageService;
import adminPage.model.vo.AdminPageFunderData;

/**
 * Servlet implementation class AdminPageFunderListServlet
 */
@WebServlet(name = "adminPageFunderList", urlPatterns = { "/adminPageFunderList" })
public class AdminPageFunderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPageFunderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		
		//2. 값 추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		//3. 비즈니스 로직
		AdminPageFunderData afd = new AdminPageService().selectAdminPageFunderList(reqPage);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/adminPage/funderList.jsp");
		request.setAttribute("list", afd.getList());
		request.setAttribute("funderPageNavi", afd.getPageNavi());
		request.setAttribute("start", afd.getStart());
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
