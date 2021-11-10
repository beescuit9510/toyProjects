package kr.or.tv.model.vo;

public class TvFactory {

	public TV getTv(String brand) {
		if(brand.equals("Samsung")) {
			return new SamsungTV();
		}
		if(brand.equals("Lg")) {
			return new LgTV();
		}
		return null;
	}
}
