package kr.or.iei.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet(name = "Calc", urlPatterns = { "/calc" })
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalcServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		1.인코딩
		request.setCharacterEncoding("utf-8");
//		2.화면에서 전송한 값 추출
		int a = Integer.parseInt(request.getParameter("su1"));
		int b = Integer.parseInt(request.getParameter("su2"));
		char oper = request.getParameter("oper").charAt(0);
//		3.비즈니스 로직 처리
		int r = oper=='+'? a+b:oper=='-'? a-b:oper=='*'? a*b:a/b;
//		4.사용자 화면 출력(HTML 작성)
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =  response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>더하기 결과</title></html></head><body>");
		out.println("<h1>계산 결과</h1>");
		out.println("<br>");
		out.println("<h2>"+a+oper+b+" = "+r+"</h2>");
		out.println("</body></html>");
		System.out.println(r);

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
