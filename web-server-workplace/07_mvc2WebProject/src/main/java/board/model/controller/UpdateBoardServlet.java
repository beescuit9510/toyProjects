package board.model.controller;

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

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class UpdateBoardServlet
 */
@WebServlet(name = "UpdateBoard", urlPatterns = { "/updateBoard" })
public class UpdateBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "enctype 오류!!");
			request.setAttribute("loc", "/");
			view.forward(request, response);
			return;
		}

		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"/upload/board";
		
		int maxSize = 10 * 1024 * 1024;
		
		MultipartRequest multipartRe = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		
		Board board = new Board();
		board.setBoardTitle(multipartRe.getParameter("boardTitle"));
		board.setBoardContent(multipartRe.getParameter("boardContent"));
		board.setBoardNo(Integer.parseInt(multipartRe.getParameter("boardNo"))); 
		
		
		board.setFilename(multipartRe.getOriginalFileName("upfile"));
		board.setFilepath(multipartRe.getFilesystemName("upfile"));

		
		String oldFilename = multipartRe.getParameter("oldFilename");
		String oldFilepath = multipartRe.getParameter("oldFilepath");
		
		System.out.println(board);
		
		//삭제여부 판단용 값
		int status = Integer.parseInt(multipartRe.getParameter("status"));
		
		if(status==2) {
			//기존파일을 지운 경우
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
			
		}else if(oldFilename != null){
			board.setFilename(oldFilename);
			board.setFilepath(oldFilepath);
			
		}
		
		
		int result = new BoardService().updateBoard(board);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result > 0) {
			request.setAttribute("msg", "정보 수정 완료");
		}else {
			request.setAttribute("msg", "정보 수정 실패");
						
		}
		
		request.setAttribute("loc", "/boardView?boardNo="+board.getBoardNo());
		
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
