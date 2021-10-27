package table.model.vo;

public class Event {
	private int eventNo;			//
	private String eventTitle;
	private String eventContent;	//
	private String writeDate;
	private String filepath;
	private int eventWriter;		//

	
	
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

	public Event(int eventNo, String eventTitle, String eventContent, String writeDate, String filepath,
			int eventWriter) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.eventContent = eventContent;
		this.writeDate = writeDate;
		this.filepath = filepath;
		this.eventWriter = eventWriter;
	}

	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
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

	public int getEventWriter() {
		return eventWriter;
	}

	public void setEventWriter(int eventWriter) {
		this.eventWriter = eventWriter;
	}

	@Override
	public String toString() {
		return "Event [eventNo=" + eventNo + ", eventTitle=" + eventTitle + ", eventContent=" + eventContent
				+ ", writeDate=" + writeDate + ", filepath=" + filepath + ", eventWriter=" + eventWriter + "]";
	}

	
}
