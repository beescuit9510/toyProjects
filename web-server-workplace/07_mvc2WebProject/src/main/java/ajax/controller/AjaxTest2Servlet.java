package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxTest2Servlet
 */
@WebServlet(name = "AjaxTest2", urlPatterns = { "/ajaxTest2" })
public class AjaxTest2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.인코딩
		// 2.값추출
		// 3.비지니스 로직
		// 4.결과처리
		
		String str = "서버에서 보내는 데이터 입니다!!!!";
		//보내주는 데이터 인코딩;
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		out.print(str);//str이 ajax function(data) 매개변수로 들어감		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
