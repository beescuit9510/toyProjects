package adminPage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adminPage.model.service.AdminPageService;

/**
 * Servlet implementation class ChangeLevelServlet
 */
@WebServlet(name = "changeLevel", urlPatterns = { "/changeLevel" })
public class ChangeLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeLevelServlet() {
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
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		//3. 비즈니스로직
		int result = new AdminPageService().changeLevel(memberNo, memberLevel);
		//4. 결과처리
		if(result>0) {
			/*response.sendRedirect("/adminPageMemberList?reqPage=1");*/
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "등급변경 완료");
			request.setAttribute("loc", "/adminPageMemberList?reqPage=1&type=1");
			view.forward(request, response);
			
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "등급변경 오류");
			request.setAttribute("loc", "/adminPageMemberList?reqPage=1&type=1");
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
