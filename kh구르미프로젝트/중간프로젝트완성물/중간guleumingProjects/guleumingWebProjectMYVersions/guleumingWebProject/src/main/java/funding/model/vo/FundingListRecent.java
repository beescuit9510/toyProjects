package funding.model.vo;

public class FundingListRecent {
	private int projectNo;
	private String projectTitle;
	private int targetPrice;
	private String filepath;
	private String endDate;
	private String projectStory;
	private int businessNo;
	private String fundingCategory;
	private String startDate;
	private int total;
	private int rewardPrice;
	private long period;
	private int totalPrice;
	private int percent;
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public long getPeriod() {
		return period;
	}
	public void setPeriod(long period) {
		this.period = period;
	}
	public int getRewardPrice() {
		return rewardPrice;
	}
	public void setRewardPrice(int rewardPrice) {
		this.rewardPrice = rewardPrice;
	}
	public FundingListRecent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FundingListRecent(int projectNo, String projectTitle, int targetPrice, String filepath, String endDate,
			String projectStory, int businessNo, String fundingCategory, String startDate, int total, int rewardPrice, long period, int totalPrice, int percent) {
		super();
		this.projectNo = projectNo;
		this.projectTitle = projectTitle;
		this.targetPrice = targetPrice;
		this.filepath = filepath;
		this.endDate = endDate;
		this.projectStory = projectStory;
		this.businessNo = businessNo;
		this.fundingCategory = fundingCategory;
		this.startDate = startDate;
		this.total = total;
		this.rewardPrice = rewardPrice;
		this.period = period;
		this.totalPrice = totalPrice;
		this.percent = percent;
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
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getProjectStory() {
		return projectStory;
	}
	public void setProjectStory(String projectStory) {
		this.projectStory = projectStory;
	}
	public int getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(int businessNo) {
		this.businessNo = businessNo;
	}
	public String getFundingCategory() {
		return fundingCategory;
	}
	public void setFundingCategory(String fundingCategory) {
		this.fundingCategory = fundingCategory;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
