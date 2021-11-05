package mypageFunderFunding.model.vo;

import table.model.vo.MakerBoard;

public class LikedFunder extends Like{
	MakerBoard makerboard;

	public LikedFunder(int likeNo, MakerBoard makerboard) {
		super(likeNo);
		this.makerboard = makerboard;
	}

	public MakerBoard getMakerboard() {
		return makerboard;
	}

	public void setMakerboard(MakerBoard makerboard) {
		this.makerboard = makerboard;
	}

	
}
