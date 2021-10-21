package mypageFunderFunding.model.vo;

import table.model.vo.MakerInfo;
import table.model.vo.PaymentInfo;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;

public class FundedFunding {
	// project_basic_info
	ProjectBasicInfo projectBasicInfo;
	// reward
	Reward reward;
	// maker_info
	MakerInfo makerInfo;
	// payment_info
	PaymentInfo paymentInfo;

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

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public FundedFunding(ProjectBasicInfo projectBasicInfo, Reward reward, MakerInfo makerInfo,
			PaymentInfo paymentInfo) {
		super();
		this.projectBasicInfo = projectBasicInfo;
		this.reward = reward;
		this.makerInfo = makerInfo;
		this.paymentInfo = paymentInfo;
	}

	@Override
	public String toString() {
		return "FundedFunding [projectBasicInfo=" + projectBasicInfo + ", reward=" + reward + ", makerInfo=" + makerInfo
				+ ", paymentInfo=" + paymentInfo + "]";
	}

}
