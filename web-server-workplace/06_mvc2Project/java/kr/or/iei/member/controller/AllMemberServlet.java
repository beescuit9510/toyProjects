package kr.or.iei.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class AllMemberServlet
 */
@WebServlet(name = "AllMember", urlPatterns = { "/allMember" })
public class AllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//1.인코딩;
//		request.setCharacterEncoding("utf-8");
		
		//2.view에서 보낸 값 추출;
		
		//3.비지니스 로직;
		ArrayList<Member> members = new MemberService().allMember();
		
		//4.화면처리 (동적인 페이지인 경우 -> servlet의 데이터가 화면에 필요한 경우);
		//4-1.처리할 화면 경로 지정;
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/allMember.jsp");
		//4-2.화면 구성에 필요한 데이터 등록;
		request.setAttribute("members", members);
		//4-3.페이지 이동;
		view.forward(request, response);
		
		//리퀘스트는 세션과 다르게 딱 다음페이지까지만 사용가능;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
