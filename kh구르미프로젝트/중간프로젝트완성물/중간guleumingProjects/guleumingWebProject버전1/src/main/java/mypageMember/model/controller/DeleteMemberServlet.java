package mypageMember.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import mypageMember.model.service.MypageMemberService;
import table.model.vo.Member;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet(name = "DeleteMember", urlPatterns = { "/deleteMember" })
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

		Member member= (Member)session.getAttribute("member");

		int cMemberNo =member.getcMemberNo();
		
		int r = new MypageMemberService().deleteMember(cMemberNo);
		
		
		String data = r>0? "회원 탈퇴 되었습니다.":"펀더로 활동 및 프로젝트 제작 혹은 펀딩 참여중입니다.";
		
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		new Gson().toJson(data, out);



	}
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
