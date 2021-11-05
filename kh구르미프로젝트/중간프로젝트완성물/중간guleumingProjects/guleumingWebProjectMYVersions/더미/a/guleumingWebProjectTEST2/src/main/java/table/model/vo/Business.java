package table.model.vo;

public class Business {
	private int businessNo;
	private String businessName;
	private String businessCode;
	private String managerName;
	
	
//	사업자 등록 table(
//			멤버No number primary key reference 회원(회원No)
//			법인명 varchar2(50) not null unique
//			사업자등록번호 char(12) not null
//			담당자명 varchar2(20) not null
//			)

	public Business() {
		super();
	}

	public Business(int businessNo, String businessName, String businessCode, String managerName) {
		super();
		this.businessNo = businessNo;
		this.businessName = businessName;
		this.businessCode = businessCode;
		this.managerName = managerName;
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
		return "Business [businessNo=" + businessNo + ", businessName=" + businessName + ", businessCode="
				+ businessCode + ", managerName=" + managerName + "]";
	}

	
	
}
