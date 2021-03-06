package notice.model.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "NoticeUpdate", urlPatterns = { "/noticeUpdate" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeUpdateServlet() {
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

		request.setCharacterEncoding("UTF-8");

		
		
		// multiparat인지 확인!
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "enctype 오류!!");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}

		//1) 파일업로드 경로지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "/upload/notice";

		//2) 파일업로드 최대 크기 지정(10MB)
		int maxSize = 10 * 1024 * 1024;

		//3) request -? MultipartRequest로 변환(파일이 업로드되는 시점)
		MultipartRequest multipartReq = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		Notice notice = new Notice();		
		notice.setNoticeNo(Integer.parseInt(multipartReq.getParameter("noticeNo")));
		notice.setNoticeTitle(multipartReq.getParameter("noticeTitle"));
		notice.setNoticeContent(multipartReq.getParameter("noticeContent"));
		notice.setNoticeWriter(multipartReq.getParameter("noticeWriter"));

		
		//파일명 저장(새로운 파일이 업로드되면 새로운 파일 정보, 새로운 파일이 업로드되지 않으면 null;
		notice.setFilename(multipartReq.getOriginalFileName("upfile"));
		notice.setFilepath(multipartReq.getFilesystemName("upfile"));
		
		//기존 파일명/파일경로
		String oldFilename = multipartReq.getParameter("oldFilename");
		String oldFilepath = multipartReq.getParameter("oldFilepath");
		
		//삭제여부 판단용 값
		int status = Integer.parseInt(multipartReq.getParameter("status"));
		
		if(status==2) {
			//기존파일을 지운 경우
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
			
		}else if(oldFilename != null){
			notice.setFilename(oldFilename);
			notice.setFilepath(oldFilepath);
			
		}
		
		int result = new NoticeService().updateNotice(notice);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result > 0) {
			request.setAttribute("msg", "정보 수정 완료");
		}else {
			request.setAttribute("msg", "정보 수정 실패");
						
		}
		
		request.setAttribute("loc", "/noticeView?noticeNo="+notice.getNoticeNo());
		
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
