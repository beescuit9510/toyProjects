package index.service;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import common.JDBCTemplate;
import funding.model.vo.FundingListRecent;
import index.dao.IndexDao;
import notice.model.dao.NoticeDao;
import notice.model.vo.NoticePageData;
import table.model.vo.Event;
import table.model.vo.Notice;
import table.model.vo.ProjectBasicInfo;

public class IndexService {

	public ArrayList<FundingListRecent> productList(String selected) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<FundingListRecent>list = new IndexDao().productList(conn,selected);
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
		    flr.setTotalPrice(totalPrice);
		    int percent = flr.getTotalPrice()/flr.getTargetPrice()*100;
		    flr.setPercent(percent);
			System.out.println(period);
		}
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<FundingListRecent> moreProduct(String selected, int start) {
		Connection conn = JDBCTemplate.getConnection();
		int length = 3;
		int end = start+length-1;
		ArrayList<FundingListRecent>list = new IndexDao().moreProduct(conn,selected,start,end);
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
		    flr.setTotalPrice(totalPrice);
		    int percent = flr.getTotalPrice()/flr.getTargetPrice()*100;
		    flr.setPercent(percent);
			System.out.println(period);
		}
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Notice> selectNoticeList() {
		Connection conn = JDBCTemplate.getConnection();
		IndexDao dao = new IndexDao();
		ArrayList<Notice> list = IndexDao.selectNoticeList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	  public int noticeTotal() {
		Connection conn = JDBCTemplate.getConnection();
		int totalCount = new IndexDao().selectNoticeTotal(conn);
		JDBCTemplate.close(conn);
		return totalCount;
	}

	public int eventTotal() {
		Connection conn = JDBCTemplate.getConnection();
		int eventTotal = new IndexDao().selectEventTotal(conn);
		JDBCTemplate.close(conn);
		return eventTotal;
	}

	public int rewardTotal() {
		Connection conn = JDBCTemplate.getConnection();
		int rewardTotal = new IndexDao().selectRewardTotal(conn);
		JDBCTemplate.close(conn);
		return rewardTotal;
	}

	public ArrayList<Notice> moreNotice(int start) {
		Connection conn = JDBCTemplate.getConnection();
		int length = 1;
		int end = start+length-1;
		ArrayList<Notice> list = new IndexDao().moreNotice(conn,start,end);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Event> selectEventList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Event> list = new IndexDao().selectEventList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Event> moreEvent(int start) {
		Connection conn = JDBCTemplate.getConnection();
		int length = 2;
		int end = start+length-1;
		ArrayList<Event> list = new IndexDao().moreEvent(conn,start,end);
		JDBCTemplate.close(conn);
		return list;
	}
}
