package kr.or.iei.member.controller;

public class ViewResolver {
	
	public String getView(String result) {
		return "/view/"+result+".jsp";
	}

}
