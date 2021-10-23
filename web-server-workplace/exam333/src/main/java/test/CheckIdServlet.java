package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CheckId", urlPatterns = { "/checkId" })

public class CheckIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CheckIdServlet() {

		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter();
		
		System.out.println(id);

//Dao의 checkId()메소드는 매개변수로 받은 아이디를 DB에서 조회하여 아이디가 있는 경우 Member객체를, 없는경우 null을 리턴함

		Member m = new Dao().checkId(id);
		

		if (m == null) {

			response.getWriter().print("ng");

		} else {

			response.getWriter().print("ok");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

// TODO Auto-generated method stub

		doGet(request, response);

	}

}