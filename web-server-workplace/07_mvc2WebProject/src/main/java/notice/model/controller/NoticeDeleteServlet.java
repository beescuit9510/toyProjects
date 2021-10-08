package notice.model.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet(name = "NoticeDelete", urlPatterns = { "/noticeDelete" })
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//인코딩
		request.setCharacterEncoding("UTF-8");

		//데이터 추출
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

		
		//비지니스 로직
		NoticeService service = new NoticeService();
		
		Notice notice = service.getNotice(noticeNo);

		int r = service.deleteNotice(noticeNo);
		
		
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if (r > 0 ) {
			//만약 첨부파일이 있다면
			if(notice.getFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String savedDirectory = root+"/upload/notice/";
				String file = savedDirectory+notice.getFilepath(); 
				//fileName은 오리지널 이름 
				//path는 DefaultFileRenamePolicy()이용해서 저장한 진짜 upload/notice 안 파일 이름;
				
				File delfile = new File(file);
				delfile.delete();
				
			}
			request.setAttribute("msg", "삭제성공");
			request.setAttribute("loc", "/noticeList?reqPage=1");
			
		} else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("msg", "/noticeView?noticeNo=" + noticeNo);
			
		}
		
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
