package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class ChooseTestServlet
 */
@WebServlet(name = "ChooseTest", urlPatterns = { "/chooseTest" })
public class ChooseTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		ArrayList<Member> list = new MemberService().chooseTest(type, keyword);
		
		request.setAttribute("members", list);
		Stream.of(list).forEach(System.out::println);
		System.out.println(type);
		System.out.println(keyword);
		
		request.getRequestDispatcher("/WEB-INF/views/allMember.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
