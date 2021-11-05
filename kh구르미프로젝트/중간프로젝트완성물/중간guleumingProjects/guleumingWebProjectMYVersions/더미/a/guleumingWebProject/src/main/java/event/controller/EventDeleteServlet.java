package event.controller;

import java.io.File;
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
 * Servlet implementation class EventDeleteServlet
 */
@WebServlet(name = "eventDelete", urlPatterns = { "/eventDelete" })
public class EventDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventDeleteServlet() {
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
		//3. 비즈니스 로직
		EventService service = new EventService();
		Event e = service.getEvent(eventNo);	//삭제 성공 시 파일을 삭제하기 위해 filepath를 알아오기 위함
		int result = service.deleteEvent(eventNo);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(e.getFilepath() != null) {	//게시글 삭제를 성공하고, 해당 게시글에 첨부파일이 있는 경우
				String root = getServletContext().getRealPath("/");
				String file = root+ "/upload/event/" +e.getFilepath();
				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "삭제완료");
			request.setAttribute("loc", "/eventList?reqPage=1");
		}else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("loc", "/eventView?eventNo="+eventNo);			
		}
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
