package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateMember
 */
@WebServlet(name = "UpdateMember", urlPatterns = { "/updateMember" })
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Member temptMember = new Member();
		Member sessionMember = (Member)request.getSession().getAttribute("member");
		temptMember.setMemberId(sessionMember.getMemberId());
		temptMember.setMemberPw(request.getParameter("memberPw"));
		temptMember.setPhone(request.getParameter("phone"));
		temptMember.setAddress(request.getParameter("address"));
		
		int r = new MemberService().updateMember(temptMember);
		
		System.out.println(temptMember);
		System.out.println(r);
		
		if( r > 0 ) {
			sessionMember.setMemberPw(temptMember.getMemberPw());
			sessionMember.setPhone(temptMember.getPhone());
			sessionMember.setAddress(temptMember.getAddress());			
			response.sendRedirect("/");
			
		}else {
			response.sendRedirect("/mypage");
		}		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
