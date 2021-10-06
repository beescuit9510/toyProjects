package board.model.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "fileDown", urlPatterns = { "/fileDown" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		Board board = new BoardService().getBoard(boardNo);

		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "/upload/board";
		String file = saveDirectory + board.getFilepath();

		
		
		FileInputStream fileInputSream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputSream);

		
		ServletOutputStream servletOutputStream = response.getOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(servletOutputStream);

		String resultFileName = null;
		

		boolean isInternetExplorer = request.getHeader("user-agent").indexOf("MSIE") != -1
				|| request.getHeader("user-agent").indexOf("Trident") != -1;

		System.out.println("IE 여부 : "+isInternetExplorer);
		
		
		
		if(isInternetExplorer) {
			resultFileName = URLEncoder.encode(board.getFilename(), "UTF-8");
			resultFileName = resultFileName.replaceAll("\\\\", "%20");
		}else {
			resultFileName = new String(board.getFilename().getBytes("UTF-8"), "ISO-8859-1");
		}
		
		response.setContentType("application/octet-stream");

		response.setHeader("Content-Disposition", "attachment;filename="+resultFileName);

		
		while(true) {
			
			int read =bufferedInputStream.read();
			
			if(read != -1 ) {
				bufferedOutputStream.write(read);
			}else {
				break;
			}
		}
		
		bufferedInputStream.close();
		bufferedOutputStream.close();
		
		

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
