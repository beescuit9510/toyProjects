package adminPage.model.vo;

import java.util.ArrayList;

import table.model.vo.ProjectBasicInfo;

public class AdminPageFundingData {
	private ArrayList<ProjectBasicInfo> list;
	private String pageNavi;
	private int start;
	
	public AdminPageFundingData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminPageFundingData(ArrayList<ProjectBasicInfo> list, String pageNavi, int start) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.start = start;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public ArrayList<ProjectBasicInfo> getList() {
		return list;
	}
	public void setList(ArrayList<ProjectBasicInfo> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

}
