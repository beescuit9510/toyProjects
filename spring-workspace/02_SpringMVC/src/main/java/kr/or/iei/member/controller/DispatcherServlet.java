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
		
		//사용자가 요청한 uri 추출
		String uri = request.getRequestURI();
		
		System.out.println("사용자가 요청한 uri :"+uri);
		
		//사용자가 요청한 uri를 처리할 컨트룰러 주소를 handlerMapping에게 질의
		Controller controller = new HandlerMapping().getContoller(uri);
		
		//handlerMapping이 리턴한 주소로 요청
		String result = controller.handleRequest(request, response);
		System.out.println(result);
	
		String viewName = new ViewResolver().getView(result);
		
		request.getRequestDispatcher(viewName).forward(request, response);;
		
		System.out.println(viewName);

		/*
		//로그인 성공
		request.getRequestDispatcher("/view/loginSucess.jsp");
		//로그인  실패
		request.getRequestDispatcher("/view/loginFailed.jsp");
		
		//조회 성공
		request.getRequestDispatcher("/view/searchSucess.jsp");
		//조회 실패
		request.getRequestDispatcher("/view/searchFailed.jsp");
		*/
		
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
