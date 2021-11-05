package table.model.vo;

public class Member {
	//일반
	private int cMemberNo;
	private String cName;
	private String cPassword;
	private String cPhone;
	private String cEnrollDate;
	private String cEmail;
	private int cLevel;
	//펀더
	private int businessNo;
	private String businessName;
	private String businessCode;
	private String managerName;

//	멤버 테이블(
//			멤버No number primary key
//			이름  varchar2(20) not null
//			비밀번호  varchar2(50) not null
//			전화번호  char(13) not null
//			가입일자  char(10) not null 
//			이메일   varchar2(100) not null unique
//			회원등급 number //(관리자1/일반회원2/일반펀더3)
//			)

//	+++

//	
//	사업자 등록 table(
//			멤버No number primary key reference 회원(회원No)
//			법인명 varchar2(50) not null unique
//			사업자등록번호 char(12) not null
//			담당자명 varchar2(20) not null
//			)

	public Member() {
		super();
	}

	public Member(int cMemberNo, String cName, String cPassword, String cPhone, String cEnrollDate, String cEmail,
			int cLevel) {
		super();
		this.cMemberNo = cMemberNo;
		this.cName = cName;
		this.cPassword = cPassword;
		this.cPhone = cPhone;
		this.cEnrollDate = cEnrollDate;
		this.cEmail = cEmail;
		this.cLevel = cLevel;
	}

	public Member(int cMemberNo, String cName, String cPassword, String cPhone, String cEnrollDate, String cEmail,
			int cLevel, int businessNo, String businessName, String businessCode, String managerName) {
		super();
		this.cMemberNo = cMemberNo;
		this.cName = cName;
		this.cPassword = cPassword;
		this.cPhone = cPhone;
		this.cEnrollDate = cEnrollDate;
		this.cEmail = cEmail;
		this.cLevel = cLevel;
		this.businessNo = businessNo;
		this.businessName = businessName;
		this.businessCode = businessCode;
		this.managerName = managerName;
	}


	public int getcMemberNo() {
		return cMemberNo;
	}

	public void setcMemberNo(int cMemberNo) {
		this.cMemberNo = cMemberNo;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public String getcEnrollDate() {
		return cEnrollDate;
	}

	public void setcEnrollDate(String cEnrollDate) {
		this.cEnrollDate = cEnrollDate;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public int getcLevel() {
		return cLevel;
	}

	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}

	public int getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(int businessNo) {
		this.businessNo = businessNo;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Override
	public String toString() {
		return "Member [cMemberNo=" + cMemberNo + ", cName=" + cName + ", cPassword=" + cPassword + ", cPhone=" + cPhone
				+ ", cEnrollDate=" + cEnrollDate + ", cEmail=" + cEmail + ", cLevel=" + cLevel + ", businessNo="
				+ businessNo + ", businessName=" + businessName + ", businessCode=" + businessCode + ", managerName="
				+ managerName + "]";
	}
	
	
	

}
