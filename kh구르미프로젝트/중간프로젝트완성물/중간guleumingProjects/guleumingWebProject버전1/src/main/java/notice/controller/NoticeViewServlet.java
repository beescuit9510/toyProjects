package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import table.model.vo.Notice;

/**
 * Servlet implementation class NoticeViewServlet
 */
@WebServlet(name = "noticeView", urlPatterns = { "/noticeView" })
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeViewServlet() {
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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		//.3 비즈니스 로직
		Notice n = new NoticeService().selectOneNotice(noticeNo);
		//4. 결과처리
		if(n != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/readMore/notice/noticeView.jsp");
			request.setAttribute("n", n);
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "조회 오류");
			request.setAttribute("loc", "/noticeList?reqPage=1");
			view.forward(request, response);
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
