package kr.or.iei.controller;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendData3Servlet
 */
@WebServlet(name = "SendData3", urlPatterns = { "/SendData3" })
public class SendData3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendData3Servlet() {
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
		request.setCharacterEncoding("utf-8");
//		int age = Integer.parseInt(request.getParameter("age"));
//		String name = request.getParameter("name");
//		String addres = request.getParameter("addres");
//		System.out.println(age);
//		System.out.println(name);
		
//		System.out.println(request.getParameterValues("name")[0]);
//		System.out.println(request.getParameterValues("name")[1]);

		System.out.println(request.getParameter("str"));
		System.out.println(request.getParameter("num"));
		System.out.println(request.getParameter("gender"));
		System.out.println(request.getParameterValues("hobby"));
		System.out.println(request.getParameter("age"));
		
		String[] hobby = request.getParameterValues("hobby");
		Stream.of(hobby).forEach(System.out::println);
//		for(String str : hobby) {
//			System.out.println(str);
//		}
		System.out.println();
		System.out.println(request.getParameter("hiddenData"));
		System.out.println("input1 readonly :"+ request.getParameter("input1"));
		System.out.println("input2 disabled :"+ request.getParameter("input2"));
		System.out.println("input3 :"+ request.getParameter("input3"));
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
