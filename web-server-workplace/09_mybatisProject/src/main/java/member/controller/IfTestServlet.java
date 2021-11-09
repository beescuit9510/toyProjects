package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class IfTestServlet
 */
@WebServlet(name = "IfTest", urlPatterns = { "/ifTest" })
public class IfTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IfTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String ckName = request.getParameter("ckName");
		String ckPhone = request.getParameter("ckPhone");
		String ckAddress = request.getParameter("ckAddress");
		
		ArrayList<Member> list = new MemberService().ifTest(ckName, ckPhone, ckAddress);
		
		request.setAttribute("list", list);
		request.setAttribute("ckName", ckName);
		request.setAttribute("ckPhone", ckPhone);
		request.setAttribute("ckAddress", ckAddress);

		request.getRequestDispatcher("/WEB-INF/views/ifTest.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
