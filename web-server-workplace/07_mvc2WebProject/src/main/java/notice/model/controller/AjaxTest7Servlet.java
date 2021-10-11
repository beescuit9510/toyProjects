package notice.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AjaxTest7Servlet
 */
@WebServlet(name = "AjaxTest7", urlPatterns = { "/ajaxTest7" })
public class AjaxTest7Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest7Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		ArrayList<Member> list = new MemberService().selectAllMember();
		
		JSONObject map = new JSONObject();
//		HashMap<String, Member> map2 = new HashMap<>();
		
		for(Member m: list) {
//			map2.put(m.getMemberId(), m);

			JSONObject obj = new JSONObject();
			obj.put("memberNo", m.getMemberNo());
			obj.put("memberName", m.getMemberName());
			obj.put("phone", m.getPhone());
			map.put(m.getMemberId(),obj);
		}
		response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		out.print(map);
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
