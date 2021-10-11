package photo.model.controller;

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

import photo.model.service.PhotoService;
import photo.model.vo.Photo;

/**
 * Servlet implementation class PhotoWriteServlet
 */
@WebServlet(name = "PhotoWrite", urlPatterns = { "/photoWrite" })
public class PhotoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 작성 오류 [enctype확인] !");
			request.setAttribute("loc", "/");
	
			view.forward(request, response);
			return; // forward 후 다른 코드가 오기때문에 리턴해줌!
		}
		

		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"/upload/photo";
		String encoding = "UTF-8";
		int maxSize = 10* 1024 * 1024;
		
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxSize, encoding, new DefaultFileRenamePolicy());
		
		Photo photo = new Photo();
		photo.setPhotoWriter(multiReq.getParameter("photoWriter"));
		photo.setPhotoComment(multiReq.getParameter("photoComment"));
		photo.setFilepath(multiReq.getFilesystemName("img"));
		
		int r = new PhotoService().insertPhoto(photo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(r > 0) {
			request.setAttribute("msg", "게시글 등록 성공!");
		}else {
			request.setAttribute("msg", "게시글 등록 실패!");			
		}
		
		request.setAttribute("loc", "/photoList");
		
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
