package table.model.vo;

public class FunderCategory {
	private String funderCategory;

//	펀더 카테고리 테이블(
//			펀더 업종 varchar2(50)
//			)

	
	public FunderCategory() {
		super();
	}

	public FunderCategory(String funderCategory) {
		super();
		this.funderCategory = funderCategory;
	}

	public String getFunderCategory() {
		return funderCategory;
	}

	public void setFunderCategory(String funderCategory) {
		this.funderCategory = funderCategory;
	}

	@Override
	public String toString() {
		return "funderCategory [funderCategory=" + funderCategory + "]";
	}

	
	
}
