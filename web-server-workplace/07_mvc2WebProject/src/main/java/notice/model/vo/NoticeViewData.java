package notice.model.vo;

import java.util.ArrayList;

public class NoticeViewData {
	private ArrayList<NoticeComment> noticeComments;
	private Notice notice;

	public NoticeViewData(ArrayList<NoticeComment> noticeComments, Notice notice) {
		super();
		this.noticeComments = noticeComments;
		this.notice = notice;
	}

	public ArrayList<NoticeComment> getNoticeComments() {
		return noticeComments;
	}

	public void setNoticeComments(ArrayList<NoticeComment> noticeComments) {
		this.noticeComments = noticeComments;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

}
