package event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.service.EventService;
import table.model.vo.Event;

/**
 * Servlet implementation class EventUpdateFrmServlet
 */
@WebServlet(name = "eventUpdateFrm", urlPatterns = { "/eventUpdateFrm" })
public class EventUpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventUpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		int eventNo = Integer.parseInt(request.getParameter("eventNo"));
		//3. 비즈니스로직
		Event e = new EventService().getEvent(eventNo);
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/readMore/event/eventUpdateFrm.jsp");
		request.setAttribute("e", e);
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
