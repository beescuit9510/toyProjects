package notice.model.controller;

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
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet(name = "NoticeWrite", urlPatterns = { "/noticeWrite" })
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		cos 라이브러리는 multiple 이 불가능 
//		<input type="file" name="upfile" mutitple>
		
		request.setCharacterEncoding("utf-8");
		
		// 파일업로드를 수행할 예정으로 enctype이 multipart/form-data 인지 확인하는 코드;
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 작성 오류 [enctype확인] !");
			request.setAttribute("loc", "/");
	
			view.forward(request, response);
			return; // forward 후 다른 코드가 오기때문에 리턴해줌!
		}
		
		
		//파일 업로드 준비
		// 1) 파일업로드 경로 지정
		
		String root = getServletContext().getRealPath("");
		// WebContent 폴더의 파일의 경로를 가져옴.
		
		String saveDirectory = root+"upload/notice";
		
		System.out.println("root : "+root);
		System.out.println(saveDirectory);
		
		// 2) 업로드 파일의 최대크기 지정(일반적으로 웹의 경우 10MB 정도 사용)
		
		int maxSize = 10*1024*1024;
		//(10MB를 byte단위로 변환)
//		10 byte * 1024 -> 10 killo byte * 1024 -> 10 mega byte
		
		
		// 3) request 객체를 MultipartRequest객체로 변환(변환하면서 파일이 서버에 업로드 함)
		//MultipartRequest 객체 생성 시 매개변수 총 5개;
		//request 객체, 파일저장 경로, 파일최대 크기, 인코딩 타입, 파일중복처리 객체;
		
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		//cos에서 지원해주는 객체 -> DefaultFileRenamePolicy
	// multipartRequest 는 cos 라이브러리에서 오는 객체!
		
		
		//데이터 추출! request가 아닌 multipartRequst에서 꺼냄! request에서 꺼낼시 null나옴.
		//데이터 추출시 request로 추출하면 모두 null로 처리 -> mRequest로 추출해야함;
		//한번 포장된거라고 할 수 있음.
		Notice notice = new Notice(mRequest.getParameter("noticeTitle"),
				mRequest.getParameter("noticeContent"),
				mRequest.getParameter("noticeWriter"),
				mRequest.getOriginalFileName("upfile"), //file name 사용자가 올린 파일 이름;
				mRequest.getFilesystemName("upfile") //file path 서버에 업로드된 파일 이름;
				);
		//getOriginalFileName(), getFilesystemName()의 매개변수는
		//<input type="file" name="upfile"> -> name 속성값을 매개변수로.
		
		
		System.out.println("original : "+notice.getFilename());
		System.out.println("filesystem : "+notice.getFilepath());
		
		System.out.println(notice);
		
		int result = new NoticeService().insertNotice(notice);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result > 0) {
			request.setAttribute("msg", "공지사항 등록 성공!");
		}else {
			request.setAttribute("msg", "에러가 났어요! 찾아요 빨리!");			
		}
		
		request.setAttribute("loc", "/noticeList?reqPage=1");
		
		view.forward(request, response);
		

		
		
		request.getParameter("");

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
