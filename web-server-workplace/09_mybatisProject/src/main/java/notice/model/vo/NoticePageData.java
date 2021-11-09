package notice.model.vo;

import java.util.ArrayList;

public class NoticePageData {
	private ArrayList<Notice> list;
	private String pageNavi;
	private int start;

	public NoticePageData(ArrayList<Notice> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}

	public NoticePageData() {
		super();
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
