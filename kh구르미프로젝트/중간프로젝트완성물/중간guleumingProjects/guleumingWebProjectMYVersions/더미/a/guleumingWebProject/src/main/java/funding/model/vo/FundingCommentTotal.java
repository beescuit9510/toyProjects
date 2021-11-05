package funding.model.vo;

public class FundingCommentTotal {
	private int commentNo;
	private String commentContent;
	private String writeDate;
	private int commentLevel;
	private int projectRefNo;
	private int commentRefNo;
	private int commentWriter;
	private String cName;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getCommentLevel() {
		return commentLevel;
	}
	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}
	public int getProjectRefNo() {
		return projectRefNo;
	}
	public void setProjectRefNo(int projectRefNo) {
		this.projectRefNo = projectRefNo;
	}
	public int getCommentRefNo() {
		return commentRefNo;
	}
	public void setCommentRefNo(int commentRefNo) {
		this.commentRefNo = commentRefNo;
	}
	public int getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(int commentWriter) {
		this.commentWriter = commentWriter;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public FundingCommentTotal(int commentNo, String commentContent, String writeDate, int commentLevel,
			int projectRefNo, int commentRefNo, int commentWriter, String cName) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.writeDate = writeDate;
		this.commentLevel = commentLevel;
		this.projectRefNo = projectRefNo;
		this.commentRefNo = commentRefNo;
		this.commentWriter = commentWriter;
		this.cName = cName;
	}
	public FundingCommentTotal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
