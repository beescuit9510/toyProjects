package adminPage.model.vo;

public class AdminPageFundingRewardPayment {
	private int projectNo;
	private String projectTitle;
	private int targetPrice;
	private String filepath;
	private String startDate;
	private String endDate;
	private int businessNo;
	private int total;			//펀딩참가 수 * 수량
	private int rewardPrice;
	private double percent;
	public AdminPageFundingRewardPayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminPageFundingRewardPayment(int projectNo, String projectTitle, int targetPrice, String filepath,
			String startDate, String endDate, int businessNo, int quantity, int rewardPrice, int total, double percent) {
		super();
		this.projectNo = projectNo;
		this.projectTitle = projectTitle;
		this.targetPrice = targetPrice;
		this.filepath = filepath;
		this.startDate = startDate;
		this.endDate = endDate;
		this.businessNo = businessNo;
		this.total = total;
		this.rewardPrice = rewardPrice;
		this.percent = percent;
		/*this.total = total;*/
	}
	public int getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public int getTargetPrice() {
		return targetPrice;
	}
	public void setTargetPrice(int targetPrice) {
		this.targetPrice = targetPrice;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(int businessNo) {
		this.businessNo = businessNo;
	}
	public int getRewardPrice() {
		return rewardPrice;
	}
	public void setRewardPrice(int rewardPrice) {
		this.rewardPrice = rewardPrice;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	
}
