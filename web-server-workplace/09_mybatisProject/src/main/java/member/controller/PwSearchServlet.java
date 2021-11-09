package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class PwSearchServlet
 */
@WebServlet(name = "PwSearch", urlPatterns = { "/pwSearch" })
public class PwSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String phone = request.getParameter("phone");
		
		String memberPw = new MemberService().searchPw(memberId, phone);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/searchResult.jsp");

		if(memberPw!=null) {
			request.setAttribute("msg", "비밀번호는 ["+memberPw+"] 입니다.");
		}else {
			request.setAttribute("msg", "정보를 조회할 수 없습니다.");
		}

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
