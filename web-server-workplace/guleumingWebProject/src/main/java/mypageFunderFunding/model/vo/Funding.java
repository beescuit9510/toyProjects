package mypageFunderFunding.model.vo;

import table.model.vo.MakerInfo;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;

public class Funding {
	// 펀딩 판매갯수
	int total;
	// project_basic_info
	ProjectBasicInfo projectBasicInfo;
	// reward
	Reward reward;
	// maker_info
	MakerInfo makerInfo;

	public ProjectBasicInfo getProjectBasicInfo() {
		return projectBasicInfo;
	}

	public void setProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
		this.projectBasicInfo = projectBasicInfo;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public MakerInfo getMakerInfo() {
		return makerInfo;
	}

	public void setMakerInfo(MakerInfo makerInfo) {
		this.makerInfo = makerInfo;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Funding(int total, ProjectBasicInfo projectBasicInfo, Reward reward, MakerInfo makerInfo) {
		super();
		this.total = total;
		this.projectBasicInfo = projectBasicInfo;
		this.reward = reward;
		this.makerInfo = makerInfo;
	}

}
