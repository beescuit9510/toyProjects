package notice.model.vo;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeCount;
	private String noticeWriter;
	private int readCount;
	private String regDate;
	private String filename;
	private String filepath;

	public Notice(int noticeNo, String noticeTitle, String noticeCount, String noticeWriter, int readCount,
			String regDate, String filename, String filepath) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeCount = noticeCount;
		this.noticeWriter = noticeWriter;
		this.readCount = readCount;
		this.regDate = regDate;
		this.filename = filename;
		this.filepath = filepath;
	}

	public Notice() {
		super();
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

	public String getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(String noticeCount) {
		this.noticeCount = noticeCount;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
