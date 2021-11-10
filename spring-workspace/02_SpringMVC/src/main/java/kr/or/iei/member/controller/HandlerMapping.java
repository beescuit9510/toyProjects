package kr.or.iei.member.controller;

import java.util.HashMap;

public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		super();
		this.mappings = new HashMap<String, Controller>();
		mappings.put("/search.do", new SearchController());
		mappings.put("/login.do", new LoginController());
	}
	
	public Controller getContoller(String url) {
		return mappings.get(url);
	}
	

}
