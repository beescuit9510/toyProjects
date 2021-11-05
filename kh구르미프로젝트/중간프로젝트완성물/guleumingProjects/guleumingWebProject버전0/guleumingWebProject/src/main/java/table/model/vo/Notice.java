package table.model.vo;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String writeDate;
	private String filepath;
	private int noticeWriter;

//	공지사항 table(
//			공지사항No number primary key
//			제목 varchar2(500) not null
//			내용 varchar2(2000) not null
//			작성일자 char(10) not null
//			filepath varchar2(1500)
//			작성자 number not null reference 회원(회원No) delete on cascade
//			)
	
	public Notice() {
		super();
	}
	
	public Notice(int noticeNo, String noticeTitle, String noticeContent, String writeDate, String filepath,
			int noticeWriter) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.writeDate = writeDate;
		this.filepath = filepath;
		this.noticeWriter = noticeWriter;
	}

	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(int noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", writeDate=" + writeDate + ", filepath=" + filepath + ", noticeWriter=" + noticeWriter + "]";
	}
	
	
	
	

}
