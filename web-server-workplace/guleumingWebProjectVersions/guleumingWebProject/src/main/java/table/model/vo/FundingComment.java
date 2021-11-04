package table.model.vo;

public class FundingComment {

	private int commentNo;
	private String commentContent;
	private String writeDate;
	private int commentLevel;
	private int projectRefNo;
	private int commentRefNo;
	private int commentWriter;

// 	펀딩 댓글 테이블(
//			댓글No number primary key
//			내용 varchar(1500) not null
//			작성일자 char(10)
//			댓글 등급 number not null //(일반 댓글1/ 펀딩게시물 작성자의 대댓글은2)
//			펀딩No number not null reference 펀딩(펀딩No) delete on cascade
//			댓글_참조 number reference 댓글(댓글No) delete on cascade
//			작성자 varchar(50) not null reference 멤버(멤버No) delete on cascade
//			)

	public FundingComment(int commentNo, String commentContent, String writeDate, int commentLevel, int projectRefNo,
			int commentRefNo, int commentWriter) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.writeDate = writeDate;
		this.commentLevel = commentLevel;
		this.projectRefNo = projectRefNo;
		this.commentRefNo = commentRefNo;
		this.commentWriter = commentWriter;
	}

	public FundingComment() {
		super();
	}

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

	@Override
	public String toString() {
		return "FundingComment [commentNo=" + commentNo + ", commentContent=" + commentContent + ", writeDate="
				+ writeDate + ", commentLevel=" + commentLevel + ", projectRefNo=" + projectRefNo + ", commentRefNo="
				+ commentRefNo + ", commentWriter=" + commentWriter + "]";
	}

}
