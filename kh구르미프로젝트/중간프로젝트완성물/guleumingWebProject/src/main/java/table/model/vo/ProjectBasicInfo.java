package table.model.vo;

public class ProjectBasicInfo {

	private int projectNo;
	private String projectTitle;
	private int targetPrice;
	private String filepath;
	private String endDate;
	private String projectStory;
	private int businessNo;
	private String fundingCategory;
	private String startDate;

//	기본정보 table (
//			프로젝트No number primary key number
//			프로젝트 제목 varchar2(120) not null
//			목표금액 number not null
//			프로젝트 대표 이미지 varchar2(1500)
//			프로젝트 마감일 char(10) not null
//			프로젝트 스토리 varchar(4000) not null
//			프로젝트 펀더 number not null reference 사업자 등록(멤버No) on delete cascade
//			카테고리 varchar2(30) not null reference 카테고리(펀딩_상품)
//			start_date char(10) not null
//			)

	public ProjectBasicInfo() {
		super();
	}

	public ProjectBasicInfo(int projectNo, String projectTitle, int targetPrice, String filepath, String endDate,
			String projectStory, int businessNo, String fundingCategory, String startDate) {
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

	@Override
	public String toString() {
		return "ProjectBasicInfo [projectNo=" + projectNo + ", projectTitle=" + projectTitle + ", targetPrice="
				+ targetPrice + ", filepath=" + filepath + ", endDate=" + endDate + ", projectStory=" + projectStory
				+ ", businessNo=" + businessNo + ", fundingCategory=" + fundingCategory + ", startDate=" + startDate
				+ "]";
	}

}
