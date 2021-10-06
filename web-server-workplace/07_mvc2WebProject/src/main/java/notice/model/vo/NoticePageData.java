package notice.model.vo;

import java.util.ArrayList;

public class NoticePageData {
	private ArrayList<Notice> notices;
	private String pageNavi;
	private int start;

	public ArrayList<Notice> getNotices() {
		return notices;
	}

	public void setNotices(ArrayList<Notice> notices) {
		this.notices = notices;
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

	public NoticePageData(ArrayList<Notice> notices, String pageNavi, int start) {
		super();
		this.notices = notices;
		this.pageNavi = pageNavi;
		this.start = start;
	}

}
