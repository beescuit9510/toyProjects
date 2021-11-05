package notice.model.vo;

import java.util.ArrayList;

import table.model.vo.Notice;

public class NoticePageData {
	private ArrayList<Notice> list;
	private String pageNavi;
	private int start;
	public NoticePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticePageData(ArrayList<Notice> list, String pageNavi, int start) {
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
	public ArrayList<Notice> getList() {
		return list;
	}
	public void setList(ArrayList<Notice> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
