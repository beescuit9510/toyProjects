package funder.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import funder.model.service.FunderService;
import funding.model.vo.FundingListRecent;

/**
 * Servlet implementation class FunderViewMoreServlet
 */
@WebServlet(name = "FunderViewMore", urlPatterns = { "/funderViewMore" })
public class FunderViewMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FunderViewMoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int start = Integer.parseInt(request.getParameter("start"));
		int writerNo = Integer.parseInt(request.getParameter("writerNo"));
		ArrayList<FundingListRecent> pro = new FunderService().selectProject(start,writerNo);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(pro,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
