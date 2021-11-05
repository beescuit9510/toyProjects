package funding.model.vo;

public class FundingViewTotal {
	private int projectNo;
	private String projectTitle;
	private int targetPrice;
	private String filepath;
	private String endDate;
	private String projectStory;
	private int businessNo;
	private String fundingCategory;
	private String startDate;
	private String businessName;
	private int rewardPrice;
	private long period;
	private int total;
	private String payDateS;
	private int totalPrice;
	private double percent;
	private String rewardTitle;
	private String rewardContent;
	private String shippingDate;
	private String cancelPolicy;
	private String qEmail;
	private String qPhone;
	private int cnt;
	private int likeCount;
	private String likeCheck;
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
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public int getRewardPrice() {
		return rewardPrice;
	}
	public void setRewardPrice(int rewardPrice) {
		this.rewardPrice = rewardPrice;
	}
	public long getPeriod() {
		return period;
	}
	public void setPeriod(long period) {
		this.period = period;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getPayDateS() {
		return payDateS;
	}
	public void setPayDateS(String payDateS) {
		this.payDateS = payDateS;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getLikeCheck() {
		return likeCheck;
	}
	public void setLikeCheck(String likeCheck) {
		this.likeCheck = likeCheck;
	}
	public FundingViewTotal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FundingViewTotal(int projectNo, String projectTitle, int targetPrice, String filepath, String endDate,
			String projectStory, int businessNo, String fundingCategory, String startDate, String businessName,
			int rewardPrice, long period, int total, String payDateS, int totalPrice, double percent, String rewardTitle,
			String rewardContent, String shippingDate, String cancelPolicy, String qEmail, String qPhone, int cnt,
			int likeCount, String likeCheck) {
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
		this.businessName = businessName;
		this.rewardPrice = rewardPrice;
		this.period = period;
		this.total = total;
		this.payDateS = payDateS;
		this.totalPrice = totalPrice;
		this.percent = percent;
		this.rewardTitle = rewardTitle;
		this.rewardContent = rewardContent;
		this.shippingDate = shippingDate;
		this.cancelPolicy = cancelPolicy;
		this.qEmail = qEmail;
		this.qPhone = qPhone;
		this.cnt = cnt;
		this.likeCount = likeCount;
		this.likeCheck = likeCheck;
	}
	
}	
