package mypageFunderFunding.model.vo;

import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;
import table.model.vo.MakerInfo;
import table.model.vo.PaymentInfo;

public class Funding {
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

	public Funding(ProjectBasicInfo projectBasicInfo, Reward reward, MakerInfo makerInfo) {
		super();
		this.projectBasicInfo = projectBasicInfo;
		this.reward = reward;
		this.makerInfo = makerInfo;
	}

	@Override
	public String toString() {
		return "Funding [projectBasicInfo=" + projectBasicInfo.toString() + ", reward=" + reward + ", makerInfo="
				+ makerInfo.toString();
	}

}
