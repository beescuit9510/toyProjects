package table.model.vo;

import java.util.ArrayList;

public class FunderPageData {
	private ArrayList<MakerBoard> mblist;
	private String pageNavi;
	private int start;
	public FunderPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FunderPageData(ArrayList<MakerBoard> mblist, String pageNavi, int start) {
		super();
		this.mblist = mblist;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	public ArrayList<MakerBoard> getMblist() {
		return mblist;
	}
	public void setMblist(ArrayList<MakerBoard> mblist) {
		this.mblist = mblist;
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
