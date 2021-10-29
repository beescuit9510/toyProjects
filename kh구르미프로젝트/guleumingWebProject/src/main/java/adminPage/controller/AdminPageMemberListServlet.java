package adminPage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adminPage.model.service.AdminPageService;
import adminPage.model.vo.AdminPageMemberData;

/**
 * Servlet implementation class AdminPageMemberListServlet
 */
@WebServlet(name = "adminPageMemberList", urlPatterns = { "/adminPageMemberList" })
public class AdminPageMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPageMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. view에서 보낸 데이터 추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String type = request.getParameter("type");
		//3. 비즈니스 로직
		AdminPageMemberData amd = new AdminPageService().selectAdminPageMemberList(reqPage, type);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/adminPage/memberList.jsp");
		request.setAttribute("list", amd.getList());
		request.setAttribute("type", type);
		request.setAttribute("pageNavi", amd.getPageNavi());
		request.setAttribute("start", amd.getStart());
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
