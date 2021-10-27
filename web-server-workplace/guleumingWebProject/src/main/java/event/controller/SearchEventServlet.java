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
 * Servlet implementation class SearchEventServlet
 */
@WebServlet(name = "searchEvent", urlPatterns = { "/searchEvent" })
public class SearchEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEventServlet() {
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
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		int reqPage = 0;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));			
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		//reqPage는 매개변수로 전달된 경우 -> 전달된 값
		//reqPage가 매개변수로 전달되지 않은경우 -> 1
		//3. 비즈니스로직
		//페이징처리 -> ArrayList, String
		EventPageData epd = new EventService().searchEvent(reqPage, type, keyword);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/readMore/event/eventList.jsp");
		request.setAttribute("list", epd.getList());
		request.setAttribute("pageNavi", epd.getPageNavi());
		request.setAttribute("start", epd.getStart());
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
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
