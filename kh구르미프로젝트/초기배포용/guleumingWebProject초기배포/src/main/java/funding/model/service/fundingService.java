package funding.model.service;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import common.JDBCTemplate;
import funding.model.dao.fundingDao;
import funding.model.vo.FundingCommentTotal;
import funding.model.vo.FundingListRecent;
import funding.model.vo.FundingViewTotal;
import table.model.vo.MakerInfo;
import table.model.vo.PaymentInfo;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;



public class fundingService {

	public Reward selectReward(int projectNo) { // reward 객체에 담아서 가져오기
		Connection conn = JDBCTemplate.getConnection();
		Reward r = new fundingDao().selectReward(projectNo,conn);
		JDBCTemplate.close(conn);
		return r;
	}

	public ProjectBasicInfo selectProjectBasicInfo(int projectNo) { // 기본정보 담아서 가져오기
		Connection conn = JDBCTemplate.getConnection();
		ProjectBasicInfo pbi = new fundingDao().selectProjectBasicInfo(projectNo, conn);
		JDBCTemplate.close(conn);
		return pbi;
	}

	public MakerInfo selectMakerInfo(int projectNo) {
		Connection conn = JDBCTemplate.getConnection();
		MakerInfo mi = new fundingDao().selectMakerInfo(projectNo,conn);
		JDBCTemplate.close(conn);
		return mi;
	}
	public int totalQuantity(int projectNo) {
		//int projectNo = 1;
		Connection conn= JDBCTemplate.getConnection();
		int totalQua = new fundingDao().totalQuantity(projectNo,conn);
		JDBCTemplate.close(conn);
		System.out.println(totalQua);
		return totalQua;
	}

	public static ArrayList<FundingCommentTotal> selectFundingCommentList(int projectNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<FundingCommentTotal> list = new fundingDao().selectFundingCommentList(projectNo,conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<FundingListRecent> selectFundingListRecent(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<FundingListRecent> list = new fundingDao().selectFundingListRecent(reqPage,conn);
		for(FundingListRecent flr : list) {
			//날짜계산
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			//String formatedNow = now.format(formatter);
			String endDate = flr.getEndDate();
		    LocalDate date = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
		    System.out.println(date);
		    System.out.println(now);
			//System.out.println(formatedNow);
		    long period = ChronoUnit.DAYS.between(now, date);
		    flr.setPeriod(period);
		    //전체금액계산
		    int totalPrice =flr.getTotal()*flr.getRewardPrice();
		    flr.setTotalPrice(totalPrice);
		    int percent = flr.getTotalPrice()/flr.getTargetPrice()*100;
		    flr.setPercent(percent);
			System.out.println(period);
		}
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<FundingListRecent> selectFundingListCategory(String category) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<FundingListRecent> list = new fundingDao().selectFundingListCategory(category,conn);
		for(FundingListRecent flr : list) {
			//날짜계산
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			//String formatedNow = now.format(formatter);
			String endDate = flr.getEndDate();
		    LocalDate date = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
		    System.out.println(date);
		    System.out.println(now);
			//System.out.println(formatedNow);
		    long period = ChronoUnit.DAYS.between(now, date);
		    flr.setPeriod(period);
		    //전체금액계산
		    int totalPrice =flr.getTotal()*flr.getRewardPrice();
		    flr.setTotalPrice(totalPrice);
		    int percent = flr.getTotalPrice()/flr.getTargetPrice()*100;
		    flr.setPercent(percent);
			System.out.println(period);
		}
		JDBCTemplate.close(conn);
		return list;
	}

	public int countFunding() {
		Connection conn = JDBCTemplate.getConnection();
		int count = new fundingDao().countFunding(conn);
		JDBCTemplate.close(conn);
		return count;
	}

	public FundingViewTotal selectFundingViewTotal(int projectNo) {
		Connection conn = JDBCTemplate.getConnection();
		FundingViewTotal fvt = new fundingDao().selectFundingViewTotal(projectNo,conn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		//String formatedNow = now.format(formatter);
		String endDate = fvt.getEndDate();
	    LocalDate date = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
	    LocalDate payDate = date.plusDays(1);
	    String payDateS =payDate.format(formatter);
	    System.out.println(date);
	    System.out.println(now);
		//System.out.println(formatedNow);
	    fvt.setPayDateS(payDateS);
	    long period = ChronoUnit.DAYS.between(now, date);
	    fvt.setPeriod(period);
	    int totalPrice = fvt.getTotal()*fvt.getRewardPrice();
	    fvt.setTotalPrice(totalPrice);
	    int percent = fvt.getTotalPrice()/fvt.getTargetPrice()*100;
	    fvt.setPercent(percent);
		return fvt;
	}

	public int insertPaymentInfo(PaymentInfo pi) {
		Connection conn =JDBCTemplate.getConnection();
		int result = new fundingDao().insertPaymentInfo(conn,pi);
		if(result == 0) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
