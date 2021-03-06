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
 * Servlet implementation class ChkChangeLevelServlet
 */
@WebServlet(name = "ChkChangeLevel", urlPatterns = { "/chkChangeLevel" })
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
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");
//		
//		StringTokenizer num = new StringTokenizer(request.getParameter("num"), "/");
//		StringTokenizer level = new StringTokenizer(request.getParameter("level"), "/");
//		
//		while(num.hasMoreTokens()) {
//			int memberNo = Integer.parseInt(num.nextToken());
//			int memberLevel = Integer.parseInt(level.nextToken());
//			int r = new MemberService().changeLevel(memberNo, memberLevel);
//		}
		
		String num = request.getParameter("num");
		String level = request.getParameter("level");
		
		boolean r = new MemberService().chkChangeLevel(num, level);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		String msg = null;

		if(r) {
			
			msg = "변경완료";
			
		}else {
			
			msg = "에러 발생";
			
		}

		request.setAttribute("msg", msg);
		
		request.setAttribute("loc", "/adminPage");
		
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
