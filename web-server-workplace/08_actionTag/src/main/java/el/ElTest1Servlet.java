package el;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ElTest1Servlet
 */
@WebServlet(name = "ElTest1", urlPatterns = { "/elTest1" })
public class ElTest1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElTest1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/elTest1.jsp");
		
		request.setAttribute("name", "byunduck");
		request.setAttribute("age", "22");
		request.setAttribute("addr", "서울");
		request.setAttribute("str1", "테스트1");
		
		
		HttpSession session = request.getSession();
		session.setAttribute("name", "byunduck222");
		session.setAttribute("age", "22222");
		session.setAttribute("addr", "서울222"); 
		request.setAttribute("str2", "테스트2");
		
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
