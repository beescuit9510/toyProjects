
package table.model.vo;

public class Reward {

	private int rewardNo;
	private int rewardPrice;
	private String rewardTitle;
	private String rewardContent;
	private String shippingDate;
	private String cancelPolicy;
	private String qEmail;
	private String qPhone;

//	리워드 table( 
//			리워드No number primary key reference 기본정보(프로젝트No)
//			리워드금액 number not null
//			리워드 제목  varchar2(500) not null
//			리워드 내용 varchar2(4000) not null
//			예상 배송일 char(10) not null  //insert때 default 설정하기함
//			진행자 환불 및 교환 정책 varchar2(3000) not null
//			문의이메일  varchar2(50) rnot null
//			문의 가능한 번호 char(13) not null
//			)
//
	public Reward() {
		super();
	}

	public Reward(int rewardNo, int rewardPrice, String rewardTitle, String rewardContent, String shippingDate,
			String cancelPolicy, String qEmail, String qPhone) {
		super();
		this.rewardNo = rewardNo;
		this.rewardPrice = rewardPrice;
		this.rewardTitle = rewardTitle;
		this.rewardContent = rewardContent;
		this.shippingDate = shippingDate;
		this.cancelPolicy = cancelPolicy;
		this.qEmail = qEmail;
		this.qPhone = qPhone;
	}

	public int getRewardNo() {
		return rewardNo;
	}

	public void setRewardNo(int rewardNo) {
		this.rewardNo = rewardNo;
	}

	public int getRewardPrice() {
		return rewardPrice;
	}

	public void setRewardPrice(int rewardPrice) {
		this.rewardPrice = rewardPrice;
	}

	public String getRewardTitle() {
		return rewardTitle;
	}

	public void setRewardTitle(String rewardTitle) {
		this.rewardTitle = rewardTitle;
	}

	public String getRewardContent() {
		return rewardContent;
	}

	public void setRewardContent(String rewardContent) {
		this.rewardContent = rewardContent;
	}

	public String getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getCancelPolicyBr() {
		return cancelPolicy.replaceAll("\r\n", "<br>");
	}
	public String getCancelPolicy() {
		return cancelPolicy;
	}

	public void setCancelPolicy(String cancelPolicy) {
		this.cancelPolicy = cancelPolicy;
	}

	public String getqEmail() {
		return qEmail;
	}

	public void setqEmail(String qEmail) {
		this.qEmail = qEmail;
	}

	public String getqPhone() {
		return qPhone;
	}

	public void setqPhone(String qPhone) {
		this.qPhone = qPhone;
	}

	@Override
	public String toString() {
		return "Reward [rewardNo=" + rewardNo + ", rewardPrice=" + rewardPrice + ", rewardTitle=" + rewardTitle
				+ ", rewardContent=" + rewardContent + ", shippingDate=" + shippingDate + ", cancelPolicy="
				+ cancelPolicy + ", qEmail=" + qEmail + ", qPhone=" + qPhone + "]";
	}

	
}
