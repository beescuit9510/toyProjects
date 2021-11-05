package funder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import funder.model.service.FunderService;
import table.model.vo.MakerBoard;
import table.model.vo.Member;

/**
 * Servlet implementation class FunderWriteFrmServlet
 */
@WebServlet(name = "FunderWriteFrm", urlPatterns = { "/funderWriteFrm" })
public class FunderWriteFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FunderWriteFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션 정보가 있으면 회원값을 가져오기
		HttpSession session = request.getSession(false);
		if(session != null){
			Member member = (Member)session.getAttribute("member");
			int memberNo = 0;
			if(member != null) {
				 memberNo = member.getcMemberNo();
			}
			MakerBoard mb = new FunderService().selectOneFunderView(memberNo);
			request.setAttribute("mb", mb);
			request.getRequestDispatcher("/WEB-INF/views/funder/funderWriteFrm.jsp").forward(request, response);
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
