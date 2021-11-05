package table.model.vo;

public class Event {
	private int evnetNo;
	private String eventTitle;
	private String evnetContent;
	private String writeDate;
	private String filepath;
	private int evnetWriter;

	
	
//	이벤트 table(
//			이벤트No number primary key
//			제목 varchar2(500) not null
//			내용 varchar2(4000) not null
//			작성일자 char(10) not null
//			filepath varchar2(1500)
//			작성자 number not null reference 회원(회원No) delete on cascade
//			)

	public Event() {
		super();
	}

	public Event(int evnetNo, String eventTitle, String evnetContent, String writeDate, String filepath,
			int evnetWriter) {
		super();
		this.evnetNo = evnetNo;
		this.eventTitle = eventTitle;
		this.evnetContent = evnetContent;
		this.writeDate = writeDate;
		this.filepath = filepath;
		this.evnetWriter = evnetWriter;
	}

	public int getEvnetNo() {
		return evnetNo;
	}

	public void setEvnetNo(int evnetNo) {
		this.evnetNo = evnetNo;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEvnetContent() {
		return evnetContent;
	}

	public void setEvnetContent(String evnetContent) {
		this.evnetContent = evnetContent;
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

	public int getEvnetWriter() {
		return evnetWriter;
	}

	public void setEvnetWriter(int evnetWriter) {
		this.evnetWriter = evnetWriter;
	}

	@Override
	public String toString() {
		return "Event [evnetNo=" + evnetNo + ", eventTitle=" + eventTitle + ", evnetContent=" + evnetContent
				+ ", writeDate=" + writeDate + ", filepath=" + filepath + ", evnetWriter=" + evnetWriter + "]";
	}

	
}
