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
 * Servlet implementation class ChkChangeLevelServlet
 */
@WebServlet(name = "chkChangeLevel", urlPatterns = { "/chkChangeLevel" })
public class ChkChangeLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChkChangeLevelServlet() {
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
		String num = request.getParameter("num");
		String level = request.getParameter("level");
		System.out.println("num : "+num);
		System.out.println("level : "+level);
		//3. 비즈니스로직
		boolean result = new AdminPageService().chkChangeLevel(num, level);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");
		if(result) {
			request.setAttribute("msg", "변경완료");
		}else {
			request.setAttribute("msg", "에러");
		}
		request.setAttribute("loc", "/adminPageMemberList?reqPage=1");
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
