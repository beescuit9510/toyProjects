package funder.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funder.model.service.FunderService;
import table.model.vo.MakerBoard;
import table.model.vo.Member;

/**
 * Servlet implementation class FunderViewServlet
 */
@WebServlet(name = "FunderView", urlPatterns = { "/funderView" })
public class FunderViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FunderViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null){
		Member member = (Member)session.getAttribute("member");
		int memberNo = 0;
		if(member != null) {
			 memberNo = member.getcMemberNo();
		}
		int writerNo = Integer.parseInt(request.getParameter("writerNo"));
		MakerBoard mb = new FunderService().selectOneFunderView(writerNo,memberNo);
		if(mb != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/funder/funderView.jsp");
			request.setAttribute("mb", mb);
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/funder/funderList.jsp");
			view.forward(request, response);
		}
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
