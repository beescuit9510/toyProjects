package table.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private int memberLevel;
	private String memberName;
	private String memberEmail;
	private String memberPhone;
	private Date memberEnrollDate;
	
	

	public Member() {
		super();
	}

	public Member(int memberNo, int memberLevel, String memberName, String memberEmail, String memberPhone,
			Date memberEnrollDate) {
		super();
		this.memberNo = memberNo;
		this.memberLevel = memberLevel;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberEnrollDate = memberEnrollDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Date getMemberEnrollDate() {
		return memberEnrollDate;
	}

	public void setMemberEnrollDate(Date memberEnrollDate) {
		this.memberEnrollDate = memberEnrollDate;
	}

	@Override
	public String toString() {
		return "member [memberNo=" + memberNo + ", memberLevel=" + memberLevel + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberPhone=" + memberPhone + ", memberEnrollDate="
				+ memberEnrollDate + "]";
	}
	
	

}
