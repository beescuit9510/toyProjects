package table.model.vo;

public class MakerBoard {

	private int writerNo;
	private String openDate;
	private String boardEmail;
	private String searchTag;
	private String skillName;
	private String skillLevel;
	private String companyAddr;
	private String companyIntro;
	private String profileFilepath;
	private String funderCategory;
	private String businessName;
	private int makerBoardNo;
	private String writeDate;

//	펀더 게시판 테이블 (
//			작성자 number primary key reference 사업자 등록(사업자 등록No) delete on cascade
//			설립입자 char(10)
//			이메일 varchar2(50) 
//			검색 태그 varchar2(500)
//			기술 정보 varchar2(500)
//			스킬 레벨 varchar2(500)
//			회사주소 varchar2(500)
//			회사 소개글 varchar2(4000) not null
//			프로필 이미지filepath varchar2(1500) not null
//			업종 varchar2(50) reference not null 펀더_카테고리(펀더 업종)
//			법인명 varchar(50) reference 사업자 등록(법인명)
//			maker_board_no number not null unique
//			write_date char(10) not null 
//			)

	public MakerBoard() {
		super();
	}

	public MakerBoard(int writerNo, String openDate, String boardEmail, String searchTag, String skillName,
			String skillLevel, String companyAddr, String companyIntro, String profileFilepath, String funderCategory,
			String businessName, int makerBoardNo, String writeDate) {
		super();
		this.writerNo = writerNo;
		this.openDate = openDate;
		this.boardEmail = boardEmail;
		this.searchTag = searchTag;
		this.skillName = skillName;
		this.skillLevel = skillLevel;
		this.companyAddr = companyAddr;
		this.companyIntro = companyIntro;
		this.profileFilepath = profileFilepath;
		this.funderCategory = funderCategory;
		this.businessName = businessName;
		this.makerBoardNo = makerBoardNo;
		this.writeDate = writeDate;
	}

	public int getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getBoardEmail() {
		return boardEmail;
	}

	public void setBoardEmail(String boardEmail) {
		this.boardEmail = boardEmail;
	}

	public String getSearchTag() {
		return searchTag;
	}

	public void setSearchTag(String searchTag) {
		this.searchTag = searchTag;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	public String getCompanyIntro() {
		return companyIntro;
	}

	public void setCompanyIntro(String companyIntro) {
		this.companyIntro = companyIntro;
	}

	public String getProfileFilepath() {
		return profileFilepath;
	}

	public void setProfileFilepath(String profileFilepath) {
		this.profileFilepath = profileFilepath;
	}

	public String getFunderCategory() {
		return funderCategory;
	}

	public void setFunderCategory(String funderCategory) {
		this.funderCategory = funderCategory;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public int getMakerBoardNo() {
		return makerBoardNo;
	}

	public void setMakerBoardNo(int makerBoardNo) {
		this.makerBoardNo = makerBoardNo;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "MakerBoard [writerNo=" + writerNo + ", openDate=" + openDate + ", boardEmail=" + boardEmail
				+ ", searchTag=" + searchTag + ", skillName=" + skillName + ", skillLevel=" + skillLevel
				+ ", companyAddr=" + companyAddr + ", companyIntro=" + companyIntro + ", profileFilepath="
				+ profileFilepath + ", funderCategory=" + funderCategory + ", businessName=" + businessName
				+ ", makerBoardNo=" + makerBoardNo + ", writeDate=" + writeDate + "]";
	}
	
	

}
