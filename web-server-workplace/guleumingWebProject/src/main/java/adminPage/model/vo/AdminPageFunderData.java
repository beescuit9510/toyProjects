package adminPage.model.vo;

import java.util.ArrayList;

import table.model.vo.MakerBoard;

public class AdminPageFunderData {

	private ArrayList<MakerBoard> list;
	private String pageNavi;
	private int start;
	public AdminPageFunderData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminPageFunderData(ArrayList<MakerBoard> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<MakerBoard> getList() {
		return list;
	}
	public void setList(ArrayList<MakerBoard> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	
}
