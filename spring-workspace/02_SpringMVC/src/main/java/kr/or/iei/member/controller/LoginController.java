package kr.or.iei.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");

		if(memberId.equals("user01") && memberPw.equals("1234")) {
			return "loginSuccess";
		} 
		else {
			return "loginFailed";
		}
		
	}

}
