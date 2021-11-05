package table.model.vo;

public class MakerInfo {

	private int makerInfoNo;
	private String tradeBank;
	private int accountNumber;
	private String depositName;

//	제작자/부가정보(
//			제작자/부가정보No number primary key reference 기본정보(프로젝트No)
//			거래은행 varchar2(50) not null 
//			계좌번호 number not null
//			예금주 varchar2(20) not null
//			)

	public MakerInfo() {
		super();
	}

	public MakerInfo(int makerInfoNo, String tradeBank, int accountNumber, String depositName) {
		super();
		this.makerInfoNo = makerInfoNo;
		this.tradeBank = tradeBank;
		this.accountNumber = accountNumber;
		this.depositName = depositName;
	}

	public int getMakerInfoNo() {
		return makerInfoNo;
	}

	public void setMakerInfoNo(int makerInfoNo) {
		this.makerInfoNo = makerInfoNo;
	}

	public String getTradeBank() {
		return tradeBank;
	}

	public void setTradeBank(String tradeBank) {
		this.tradeBank = tradeBank;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDepositName() {
		return depositName;
	}

	public void setDepositName(String depositName) {
		this.depositName = depositName;
	}

	@Override
	public String toString() {
		return "MakerInfo [makerInfoNo=" + makerInfoNo + ", tradeBank=" + tradeBank + ", accountNumber=" + accountNumber
				+ ", depositName=" + depositName + "]";
	}
	
	

}
