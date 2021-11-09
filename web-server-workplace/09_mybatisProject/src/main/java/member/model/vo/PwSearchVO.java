package member.model.vo;

public class PwSearchVO {
	private String memberId;
	private String phone;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PwSearchVO(String memberId, String phone) {
		super();
		this.memberId = memberId;
		this.phone = phone;
	}

	public PwSearchVO() {
		super();
	}

}
