package api.controller;

public class Movie {
	private String rank;
	private String movieNm;
	private String openDt;
	private String salesAcc;
	private String audiAcc;

	public Movie() {
		super();
	}

	public Movie(String rank, String movieNm, String openDt, String salesAcc, String audiAcc) {
		super();
		this.rank = rank;
		this.movieNm = movieNm;
		this.openDt = openDt;
		this.salesAcc = salesAcc;
		this.audiAcc = audiAcc;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getMovieNm() {
		return movieNm;
	}

	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}

	public String getOpenDt() {
		return openDt;
	}

	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}

	public String getSalesAcc() {
		return salesAcc;
	}

	public void setSalesAcc(String salesAcc) {
		this.salesAcc = salesAcc;
	}

	public String getAudiAcc() {
		return audiAcc;
	}

	public void setAudiAcc(String audiAcc) {
		this.audiAcc = audiAcc;
	}

}
