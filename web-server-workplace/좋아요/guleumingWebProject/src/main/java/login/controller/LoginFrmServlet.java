package login.controller;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.gson.Gson;

import login.service.LoginService;
import table.model.vo.Business;
import table.model.vo.Member;

/**
 * Servlet implementation class LoginFrmServlet
 */
@WebServlet(name = "LoginFrm", urlPatterns = { "/loginFrm" })
public class LoginFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String loginEmail = request.getParameter("loginEmail");
		String pw = request.getParameter("pw");
		Member member = new LoginService().selectOneMember(loginEmail,pw);
		if(member!= null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			response.sendRedirect("/");
		}else {
			response.sendRedirect("/login");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
