package event.model.vo;

import java.util.ArrayList;

import table.model.vo.Event;

public class EventPageData {
	private ArrayList<Event> list;
	private String pageNavi;
	private int start;
	public EventPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventPageData(ArrayList<Event> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public ArrayList<Event> getList() {
		return list;
	}
	public void setList(ArrayList<Event> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
