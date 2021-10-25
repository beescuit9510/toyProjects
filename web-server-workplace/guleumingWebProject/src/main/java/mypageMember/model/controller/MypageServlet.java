package mypageMember.model.controller;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import table.model.vo.Member;

/**
 * Servlet implementation class MyPage
 */
@WebServlet(name = "Mypage", urlPatterns = { "/mypage" })
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session2 = request.getSession(true);
		
		Member m = new Member();
		m.setcMemberNo(3);
		m.setcName("펀더변덕");
		m.setcPassword("1234");
		m.setcPhone("010-9999-9999");
		m.setcEmail("penpeong2312@");
		m.setBusinessNo(123123);
		m.setBusinessCode("123123-312312-312312");
		m.setManagerName("매니저 메닝");
		
		session2.setAttribute("member", m);
		


		request.getRequestDispatcher("/WEB-INF/views/mypage/funder/mypage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
