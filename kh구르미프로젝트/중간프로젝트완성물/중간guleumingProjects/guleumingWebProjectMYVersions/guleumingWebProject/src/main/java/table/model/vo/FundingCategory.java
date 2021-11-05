package table.model.vo;

public class FundingCategory {
	private String fundingCategory;


//	펀딩 카테고리 테이블(
//		펀딩 상품 varchar2(50)
//		)

	
	public FundingCategory() {
		super();
	}

	public FundingCategory(String fundingCategory) {
		super();
		this.fundingCategory = fundingCategory;
	}

	public String getFundingCategory() {
		return fundingCategory;
	}

	public void setFundingCategory(String fundingCategory) {
		this.fundingCategory = fundingCategory;
	}

	@Override
	public String toString() {
		return "fundingCategory [fundingCategory=" + fundingCategory + "]";
	}

	
	
}
