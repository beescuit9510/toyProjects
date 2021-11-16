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
import funding.model.vo.LikeCount;
import table.model.vo.FundingCategory;
import table.model.vo.FundingComment;
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
		    //System.out.println(date);
		    //System.out.println(now);
			//System.out.println(formatedNow);
		    long period = ChronoUnit.DAYS.between(now, date);
		    flr.setPeriod(period);
		    //전체금액계산
		    int totalPrice =flr.getTotal()*flr.getRewardPrice();
		    double root = (double)totalPrice/flr.getTargetPrice();
		    System.out.println("퍼센트전"+root);
		    double percent = root*100;
		    percent = Math.round(percent*100)/100.0;
		    flr.setTotalPrice(totalPrice);
		    flr.setPercent(percent);
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
		    //System.out.println(date);
		    //System.out.println(now);
			//System.out.println(formatedNow);
		    long period = ChronoUnit.DAYS.between(now, date);
		    flr.setPeriod(period);
		    //전체금액계산
		    int totalPrice =flr.getTotal()*flr.getRewardPrice();
		    double root = (double)totalPrice/flr.getTargetPrice();
		    System.out.println("퍼센트전"+root);
		    double percent = root*100;
		    percent = Math.round(percent*100)/100.0;
		    flr.setTotalPrice(totalPrice);
		    flr.setPercent(percent);
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

	public FundingViewTotal selectFundingViewTotal(int projectNo, int cMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		FundingViewTotal fvt = new fundingDao().selectFundingViewTotal(projectNo,cMemberNo,conn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();
		//String formatedNow = now.format(formatter);
		//System.out.println("startDate"+fvt.getLikeCheck());
		//System.out.println("enddate"+fvt.getEndDate());
		String endDate = fvt.getEndDate();
	    LocalDate date = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
	    LocalDate payDate = date.plusDays(1);
	    String payDateS =payDate.format(formatter);
	    //System.out.println(date);
	    //System.out.println(now);
		//System.out.println(formatedNow);
	    fvt.setPayDateS(payDateS);
	    long period = ChronoUnit.DAYS.between(now, date);
	    fvt.setPeriod(period);
	    int totalPrice = fvt.getTotal()*fvt.getRewardPrice();
	    fvt.setTotalPrice(totalPrice);
	    double root = (double)totalPrice/fvt.getTargetPrice();
	    System.out.println("형변환 한거");
	    System.out.println("퍼센트전"+root);
	    double percent = root*100;
	    System.out.println(percent);
	    percent = Math.round(percent*100)/100.0;
	    System.out.println(percent);
	    System.out.println(totalPrice*100/fvt.getTargetPrice());
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

	public int insertFundingComment(FundingComment fc) {
		Connection conn =JDBCTemplate.getConnection();
		int result = new fundingDao().insertFundingComment(conn,fc);
		if (result == 0) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int fundingCommentUpdate(int commentNo, String updateCommentContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new fundingDao().fundingCommentUpdate(conn,commentNo,updateCommentContent);
		if(result == 0) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int fundingCommentDelete(int commentNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new fundingDao().fundingCommentDelete(conn,commentNo);
		if(result == 0) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<FundingCategory> selectFundingCategory() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<FundingCategory> fcList = new fundingDao().selectFundingCategory(conn);
		JDBCTemplate.close(conn);
		return fcList;
	}

	public int checkFundingLike(int cMemberNo, int projectNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new fundingDao().checkFundingLike(conn,cMemberNo,projectNo);
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateFundingLike(int projectNo, int cMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new fundingDao().updateFundingLike(conn, cMemberNo, projectNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteFundingLike(int projectNo, int cMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new fundingDao().deleteFundingLike(conn,projectNo,cMemberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public static ArrayList<LikeCount> selectLikedMember(int projectNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<LikeCount> lcList = new fundingDao().selectLikedMember(conn,projectNo);
		JDBCTemplate.close(conn);
		return lcList;
	}

	public ArrayList<FundingListRecent> selectFundingListSearch(String keyWord) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<FundingListRecent> list = new fundingDao().selectFundingListSearch(keyWord,conn);
		for(FundingListRecent flr : list) {
			//날짜계산
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			//String formatedNow = now.format(formatter);
			String endDate = flr.getEndDate();
		    LocalDate date = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
		    //System.out.println(date);
		    //System.out.println(now);
			//System.out.println(formatedNow);
		    long period = ChronoUnit.DAYS.between(now, date);
		    flr.setPeriod(period);
		    //전체금액계산
		    int totalPrice =flr.getTotal()*flr.getRewardPrice();
		    double root = (double)totalPrice/flr.getTargetPrice();
		    
		    double percent = root*100;
		    percent = Math.round(percent*100)/100.0;
		    flr.setTotalPrice(totalPrice);
		    flr.setPercent(percent);
		}
		JDBCTemplate.close(conn);
		return list;
	}
	

}
