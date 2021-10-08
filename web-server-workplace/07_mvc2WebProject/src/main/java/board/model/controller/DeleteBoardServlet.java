package board.model.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class DeleteNoticeServlet
 */
@WebServlet(name = "DeleteBoard", urlPatterns = { "/deleteBoard" })
public class DeleteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBoardServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");

		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		BoardService service = new BoardService();

		Board board = service.getBoard(boardNo);

		int r = service.deleteBoard(boardNo);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common//msg.jsp");

		if (r > 0) {

			if (board.getFilename() != null) {
				String root = getServletContext().getRealPath("/");
				String savedDirectory = root + "/upload/board/";
				String file = savedDirectory + board.getFilepath();
				File delFile = new File(file);
				delFile.delete();
			}

			request.setAttribute("msg", "삭제성공");
			request.setAttribute("loc", "/boardList?reqPage=1");

		} else {
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("msg", "/boardView?noticeNo=" + boardNo);

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
