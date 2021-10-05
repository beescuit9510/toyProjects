package board.model.controller;

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

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet(name = "BoardWrite", urlPatterns = { "/boardWrite" })
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String encoding = "UTF-8";
		
		request.setCharacterEncoding(encoding);

		
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 작성 오류 [enctype확인] !");
			request.setAttribute("loc", "/");

			view.forward(request, response);
			return;
		}
		
		

		String root = getServletContext().getRealPath("");

		String saveDirectory = root + "upload/board";

		int maxSize = 10 * 1024 * 1024;


		
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, encoding,
				new DefaultFileRenamePolicy());

		
		
		Board board = new Board(mRequest.getParameter("boardTitle"), mRequest.getParameter("boardContent"),
				mRequest.getParameter("boardWriter"), mRequest.getOriginalFileName("upfile"),
				mRequest.getFilesystemName("upfile"));		
		
		
		
		int result = new BoardService().insertBoard(board);
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result > 0) {
			request.setAttribute("msg", "공지사항 등록 성공!");
		}else {
			request.setAttribute("msg", "에러가 났어요! 찾아요 빨리!");			
		}
		
		request.setAttribute("loc", "/boardWriteFrm");
		
		
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
