package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//인코딩
		request.setCharacterEncoding("UTF-8");
		
		//사용자가 요청한 url 추출
		String url = request.getRequestURI();
		
		System.out.println("사용자가 요청한 url :"+url);
		
		//사용자가 요청한 url를 처리할 컨트룰러 주소를 handlerMapping에게 질의
		Controller controller = new HandlerMapping().getContoller(url);
		
		//handlerMapping이 리턴한 주소로 요청
		String result = controller.handleRequest(request, response);
		System.out.println(result);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
