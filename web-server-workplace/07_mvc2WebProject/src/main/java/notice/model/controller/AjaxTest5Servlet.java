package notice.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxTest5Servlet
 */
@WebServlet(name = "AjaxTest5", urlPatterns = { "/ajaxTest5" })
public class AjaxTest5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxTest5Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 비지니스 로직
		String memberId =request.getParameter("memberId");
		
		Member member = new MemberService().selectOneMember(memberId);
		
		JSONObject result = null;
		
		if(member != null) {
			// 자바스크립트 객체로 변환!
			result = new JSONObject();
			result.put("memberId", member.getMemberId());
			result.put("memberPw", member.getMemberPw());
			result.put("memberName", member.getMemberName());
		}		 
		
		
		// 결과 처리
		response.setContentType("application/json");
		//되돌려주는 데이터 타입이 json임을 명시

		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		System.out.println(member);
		System.out.println(result);
		
		out.print(result);
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
