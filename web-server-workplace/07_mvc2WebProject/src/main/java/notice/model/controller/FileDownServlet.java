package notice.model.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.BufferedInputFilter;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		request.setCharacterEncoding("UTF-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		Notice notice = new NoticeService().getNotice(noticeNo);
		
		
		// 파일 위치 지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/notice/";
		String file = saveDirectory+notice.getFilepath();
		
		System.out.println("다운로드 파일 전체 경로 : "+file);
		
		
		//서버의 물리공간에서 서블릿으로 파일을 읽어오는 객체
		FileInputStream fis = new FileInputStream(file);
		//파일을 읽어오는 속도를 개선하기위한 보조스트림
		BufferedInputStream bis = new BufferedInputStream(fis);
		

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
