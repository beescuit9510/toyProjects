package join.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import join.service.JoinService;
import table.model.vo.Business;
import table.model.vo.Member;

/**
 * Servlet implementation class JoinFrm2Servlet
 */
@WebServlet(name = "JoinFrm2", urlPatterns = { "/joinFrm2" })
public class JoinFrm2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinFrm2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Member m = new Member();
		m.setcEmail(request.getParameter("joinId"));
		m.setcPassword(request.getParameter("joinPw"));
		m.setcName(request.getParameter("joinName"));
		m.setcPhone(request.getParameter("joinPhone"));
		m.setcLevel(Integer.parseInt(request.getParameter("joinLevel")));
		int joinLevel = Integer.parseInt(request.getParameter("joinLevel"));
		Business b = new Business();
		b.setBusinessName(request.getParameter("busiName"));
		b.setBusinessCode(request.getParameter("busiCode"));
		b.setManagerName(request.getParameter("managerName"));
		int result = new JoinService().insertBusiness(m,b);
		if(result>0) {
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
