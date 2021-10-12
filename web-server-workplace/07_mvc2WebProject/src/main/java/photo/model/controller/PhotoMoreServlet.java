package photo.model.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import photo.model.service.PhotoService;
import photo.model.vo.Photo;

/**
 * Servlet implementation class PhotoMoreServlet
 */
@WebServlet(name = "PhotoMore", urlPatterns = { "/photoMore" })
public class PhotoMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoMoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.인코딩
		request.setCharacterEncoding("UTF-8");
		//2.값 추출
		int start = Integer.parseInt(request.getParameter("start"));
		//3.비지니스 로직
		
		ArrayList<Photo> photos = new PhotoService().morePhoto(start);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		System.out.println(photos.size());
		for(Photo photo : photos) {
			System.out.println(photo);			
		}
		
		new Gson().toJson(photos, response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
