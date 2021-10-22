package kh.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kh.model.dao.Dao;
import kh.model.vo.Kh;

/**
 * Servlet implementation class SearchKhServlet
 */
@WebServlet(name = "SearchKH", urlPatterns = { "/searchKH" })
public class SearchKHServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchKHServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		String khName = request.getParameter("khName");
		
		Dao dao = new Dao();
		
		Kh kh = dao.selectOneKh(khName);
		
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("application/json");
				
		new Gson().toJson(kh, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
