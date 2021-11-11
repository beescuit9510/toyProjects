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
	
	public Controller getContoller(String uri) {
		return mappings.get(uri);
	}
	

}
