package kr.or.iei.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseTestServlet
 */
@WebServlet(name = "ResponseTest", urlPatterns = { "/responseTest" })
public class ResponseTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseTestServlet() {
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
		//1.인코딩
		//2.화면에서 전송한 데이터 추출
		//3.비지니스로직처리(서버에서 데이터처리하는 단계)
		//처리할  비지니스 로직 없음
		//4.사용자 화면 출력 (사용자가 볼  HTML)을 작성
		response.setContentType("text/html;charset=UTF-8");
		//응답을 HTML타입 문서로 하고, 문자셋은 UTF-8로 함
		PrintWriter out = response.getWriter();
		// 사용자가 볼 HTML을 작성하는 객체 생성
		//HTML 작성
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>응답페이지</title></head><body>");
		out.println("<h1>서블릿 응답페이지</h1>");
		out.println("</body></html>");
		out.println("<style>h1{color:blue}</style>");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
