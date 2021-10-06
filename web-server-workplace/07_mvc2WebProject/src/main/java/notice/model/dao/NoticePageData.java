package notice.model.dao;

import java.util.ArrayList;

import notice.model.vo.Notice;

public class NoticePageData {
	private ArrayList<Notice> notices;
	private String pageNavi;

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

	public NoticePageData(ArrayList<Notice> notices, String pageNavi) {
		super();
		this.notices = notices;
		this.pageNavi = pageNavi;
	}

}
