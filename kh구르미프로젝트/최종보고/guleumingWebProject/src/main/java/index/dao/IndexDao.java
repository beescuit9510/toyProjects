package index.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import funding.model.vo.FundingListRecent;
import table.model.vo.Event;
import table.model.vo.Notice;
import table.model.vo.ProjectBasicInfo;

public class IndexDao {


	public ArrayList<FundingListRecent> productList(Connection conn, String selected) {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<FundingListRecent>list = new ArrayList<FundingListRecent>();
		String query= "";
		if(selected.equals("new")) {
			query = "select nn.*,(select sum(quantity)as total from PAYMENT_INFO where project_no=nn.project_no)as total \r\n" + 
					"				from (select rownum as rnum, n.* from(select a.*, b.reward_price from PROJECT_BASIC_INFO a \r\n" + 
					"				join reward b on a.project_no = b.reward_no order by project_no desc)n)nn where rnum between 1 and 6";
		}else {
			query="select  nn.*, (select sum(quantity)as total from PAYMENT_INFO where project_no=nn.project_no)as total,\r\n" + 
					"        (select count(*) from funding_like where liked_project_no=nn.project_no) as liked_count\r\n" + 
					"        from\r\n" + 
					"            (select rownum as rnum, n.* from(select a.*, b.reward_price from PROJECT_BASIC_INFO a  \r\n" + 
					"            join reward b on a.project_no = b.reward_no order by project_no desc)n)nn where rnum between 1 and 6 order by liked_count desc";
		}
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FundingListRecent flr = new FundingListRecent();
				flr.setProjectNo(rset.getInt("project_no"));
				flr.setBusinessNo(rset.getInt("business_no"));
				flr.setProjectTitle(rset.getString("project_title"));
				flr.setTargetPrice(rset.getInt("target_price"));
				flr.setFilepath(rset.getString("filepath"));
				flr.setEndDate(rset.getString("end_date"));
				flr.setProjectStory(rset.getString("project_story"));
				flr.setFundingCategory(rset.getString("funding_category"));
				flr.setRewardPrice(rset.getInt("reward_price"));
				flr.setTotal(rset.getInt("total"));
				list.add(flr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
	}

	public ArrayList<FundingListRecent> moreProduct(Connection conn, String selected, int start, int end) {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<FundingListRecent>list = new ArrayList<FundingListRecent>();
		String query= "";
		if(selected.equals("new")) {
			query = "select nn.*,(select sum(quantity)as total from PAYMENT_INFO where project_no=nn.project_no)as total \r\n" + 
					"				from (select rownum as rnum, n.* from(select a.*, b.reward_price from PROJECT_BASIC_INFO a \r\n" + 
					"				join reward b on a.project_no = b.reward_no order by project_no desc)n)nn where rnum between ? and ?";
		}else {
			query="select  nn.*, (select sum(quantity)as total from PAYMENT_INFO where project_no=nn.project_no)as total,\r\n" + 
					"        (select count(*) from funding_like where liked_project_no=nn.project_no) as liked_count\r\n" + 
					"        from\r\n" + 
					"            (select rownum as rnum, n.* from(select a.*, b.reward_price from PROJECT_BASIC_INFO a  \r\n" + 
					"            join reward b on a.project_no = b.reward_no order by project_no desc)n)nn where rnum between ? and ? order by liked_count desc";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FundingListRecent flr = new FundingListRecent();
				flr.setProjectNo(rset.getInt("project_no"));
				flr.setBusinessNo(rset.getInt("business_no"));
				flr.setProjectTitle(rset.getString("project_title"));
				flr.setTargetPrice(rset.getInt("target_price"));
				flr.setFilepath(rset.getString("filepath"));
				flr.setEndDate(rset.getString("end_date"));
				flr.setProjectStory(rset.getString("project_story"));
				flr.setFundingCategory(rset.getString("funding_category"));
				flr.setRewardPrice(rset.getInt("reward_price"));
				flr.setTotal(rset.getInt("total"));
				list.add(flr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return list;
	}

	public static ArrayList<Notice> selectNoticeList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		String query = "select nn.* from (select rownum as rnum, n.* from ( select * from notice order by notice_no desc)n)nn where rnum BETWEEN 1 and 4";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice(); 
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setWriteDate(rset.getString("write_date"));
				n.setFilepath(rset.getString("filepath"));
				n.setNoticeWriter(rset.getInt("notice_writer"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectNoticeTotal(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from notice";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectEventTotal(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from event";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectRewardTotal(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from project_basic_info";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Notice> moreNotice(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		String query = "select nn.* from (select rownum as rnum, n.* from ( select * from notice order by notice_no desc)n)nn where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2,end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice(); 
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setWriteDate(rset.getString("write_date"));
				n.setFilepath(rset.getString("filepath"));
				n.setNoticeWriter(rset.getInt("notice_writer"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public  ArrayList<Event> selectEventList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Event> list = new ArrayList<Event>();
		String query = "select nn.* from (select rownum as rnum, n.* from ( select * from event order by event_no desc)n)nn where rnum BETWEEN 1 and 2";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Event e = new Event(); 
				e.setEventNo(rset.getInt("event_no"));
				e.setEventTitle(rset.getString("event_title"));
				e.setEventContent(rset.getString("event_content"));
				e.setWriteDate(rset.getString("write_date"));
				e.setFilepath(rset.getString("filepath"));
				e.setEventWriter(rset.getInt("event_writer"));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Event> moreEvent(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Event> list = new ArrayList<Event>();
		String query = "select nn.* from (select rownum as rnum, n.* from ( select * from event order by event_no desc)n)nn where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2,end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Event e = new Event(); 
				e.setEventNo(rset.getInt("event_no"));
				e.setEventTitle(rset.getString("event_title"));
				e.setEventContent(rset.getString("event_content"));
				e.setWriteDate(rset.getString("write_date"));
				e.setFilepath(rset.getString("filepath"));
				e.setEventWriter(rset.getInt("event_writer"));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	
	}
}
