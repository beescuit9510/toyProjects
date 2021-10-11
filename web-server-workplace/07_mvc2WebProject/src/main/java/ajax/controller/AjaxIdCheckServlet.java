package ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxIdCheckServlet
 */
@WebServlet(name = "AjaxIdCheck", urlPatterns = { "/ajaxIdCheck" })
public class AjaxIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxIdCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member member = new MemberService().selectOneMember(request.getParameter("memberId"));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

//		int r = 0;
		if(member != null) {
//			r = 1;
			new Gson().toJson(1,response.getWriter()); 
		}else {		
//			r = 2;
			new Gson().toJson(2,response.getWriter()); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
