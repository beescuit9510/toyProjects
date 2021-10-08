package notice.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.NoticePageData;

/**
 * Servlet implementation class SearchNoticeServlet
 */
@WebServlet(name = "SearchNotice", urlPatterns = { "/searchNotice" })
public class SearchNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");
		
		String keyword = request.getParameter("keyword");
		
		int reqPage = -1;
		
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));			
		}catch(NumberFormatException e) {
			reqPage = 1;
		}
		
		//reqPage는 매개변수로 전달된경우 -> 전달된 값
		//reqPage가 매개변수로 전달되지 않은 경우 ->  1;
		
		
		//페이징처리 -> ArrayList notices, String navi, int start;
		
		NoticePageData noticePageDate = new NoticeService().searchNotice(reqPage,type,keyword);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/noticeList.jsp");
		
		request.setAttribute("notices", noticePageDate.getNotices());
		request.setAttribute("pageNavi", noticePageDate.getPageNavi());
		request.setAttribute("start", noticePageDate.getStart());
		request.setAttribute("keyword", keyword);
		request.setAttribute("type", type);
		
		
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
