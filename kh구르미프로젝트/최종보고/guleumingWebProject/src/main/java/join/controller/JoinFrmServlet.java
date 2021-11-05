package join.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import join.service.JoinService;
import table.model.vo.Member;

/**
 * Servlet implementation class JoinFrmServlet
 */
@WebServlet(name = "JoinFrm", urlPatterns = { "/joinFrm" })
public class JoinFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Member m = new Member();
		m.setcEmail(request.getParameter("joinId"));
		m.setcPassword(request.getParameter("joinPw"));
		m.setcName(request.getParameter("joinName"));
		m.setcPhone(request.getParameter("joinPhone"));
		m.setcLevel(Integer.parseInt(request.getParameter("joinLevel")));
		int joinLevel = Integer.parseInt(request.getParameter("joinLevel"));
		int memNo = new JoinService().insertMember(m);
		request.setAttribute("memNo", memNo);
		if(memNo > 0) {
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/signUp/msg.jsp");
				request.setAttribute("msg", "가입성공!");
				request.setAttribute("loc", "/");
				view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/signUp/msg.jsp");
			request.setAttribute("msg", "회원가입에 실패했습니다");
			request.setAttribute("loc", "/join");
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
