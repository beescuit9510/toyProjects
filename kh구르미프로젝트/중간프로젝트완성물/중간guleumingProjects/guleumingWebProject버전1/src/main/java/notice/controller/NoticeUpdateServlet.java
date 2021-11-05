package notice.controller;

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
import table.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "noticeUpdate", urlPatterns = { "/noticeUpdate" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "enctype오류");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}
		//1) 파일업로드 경로 설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/notice";
		//2) 파일업로드 최대 크기 지정(10MB)
		int maxSize = 10*1024*1024;
		//3) request -> MultipartRequest로 변환(파일이 업로드되는 시점)
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		Notice n = new Notice();
		n.setNoticeNo(Integer.parseInt(mRequest.getParameter("noticeNo")));
		n.setNoticeTitle(mRequest.getParameter("noticeTitle"));
		n.setNoticeContent(mRequest.getParameter("noticeContent"));
		//파일명 저장(새로운 파일이 업로드되면 새로운 파일정보, 새로운 파일이 업로드되지 않으면 null)
		n.setFilepath(mRequest.getFilesystemName("upfile"));
		//기존 파일명/파일경로
		String oldFilepath = mRequest.getParameter("oldFilepath");
		//삭제여부 판단용 값
		int status = Integer.parseInt(mRequest.getParameter("status"));
		if(status == 2) {	//기존파일이 삭제되었을 때
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilepath != null) {
			n.setFilepath(oldFilepath);
		}
		//3. 비즈니스 로직
		int result = new NoticeService().updateNotice(n);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정성공");
		}else {
			request.setAttribute("msg", "수정실패");
		}
		request.setAttribute("loc", "/noticeView?noticeNo="+n.getNoticeNo());
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
