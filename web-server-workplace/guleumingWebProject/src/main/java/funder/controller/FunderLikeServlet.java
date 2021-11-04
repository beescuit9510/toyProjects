package funder.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import funder.model.service.FunderService;

/**
 * Servlet implementation class FunderLikeServlet
 */
@WebServlet(name = "FunderLike", urlPatterns = { "/funderLike" })
public class FunderLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FunderLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int likeMemberNo = Integer.parseInt(request.getParameter("user"));
		int likedBusinessNo = Integer.parseInt(request.getParameter("funder"));
		FunderService service = new FunderService();
		int like_check = service.selectLikeCheck(likeMemberNo, likedBusinessNo);
		int like_cnt = service.selectLike(likedBusinessNo);
		//상태 전송용
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if(like_check == 1) {
			service.deleteLike(likeMemberNo,likedBusinessNo);
			like_cnt --;
			like_check --;
		}else {
			service.updateLike(likeMemberNo,likedBusinessNo);
			like_check ++;
			like_cnt ++;
		}
		hashMap.put("like_cnt", like_cnt);
		hashMap.put("like_check", like_check);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(hashMap,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
