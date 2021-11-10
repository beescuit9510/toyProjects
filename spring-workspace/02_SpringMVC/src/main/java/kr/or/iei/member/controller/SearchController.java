package kr.or.iei.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String memberId = request.getParameter("memberId");
		
		if(memberId != null && memberId.equals("user01")) {
			return "searchSuccess";			
		}else {			
			return "searchFailed";			
		}
	
	}
	
	

}
