package event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.model.service.EventService;
import event.model.vo.EventPageData;

/**
 * Servlet implementation class EventListServlet
 */
@WebServlet(name = "eventList", urlPatterns = { "/eventList" })
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
				request.setCharacterEncoding("utf-8");
				//2. view에서 보낸 데이터 추출
				int reqPage = Integer.parseInt(request.getParameter("reqPage"));
				//3. 비즈니스 로직
				EventPageData epd = new EventService().selectEventList(reqPage);
				//4. 결과처리
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/readMore/event/eventList.jsp");
				request.setAttribute("list", epd.getList());
				request.setAttribute("pageNavi", epd.getPageNavi());
				request.setAttribute("start", epd.getStart());
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
