package event.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import table.model.vo.Event;

public class EventDao {

	public ArrayList<Event> selectEventList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Event> list = new ArrayList<Event>();
		String query = "select nn.* from (select rownum as rnum, n.* from ( select * from event order by event_no desc)n)nn where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
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

	public int selectTotalCount(Connection conn) {
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

	public int insertEvent(Connection conn, Event e) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into event values(event_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, e.getEventTitle());
			pstmt.setString(2, e.getEventContent());
			pstmt.setString(3, e.getFilepath());
			pstmt.setInt(4, e.getEventWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Event selectOneEvent(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Event e = new Event();
		String query = "select * from event where event_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				 e.setEventNo(rset.getInt("event_no"));
				 e.setEventTitle(rset.getString("event_title"));
				 e.setEventContent(rset.getString("event_content"));
				 e.setEventWriter(rset.getInt("event_writer"));
				 e.setFilepath(rset.getString("filepath"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return e;
	}
	
	public ArrayList<Event> selectSearchEvent(Connection conn, int start, int end, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Event> list = new ArrayList<Event>();
		String query = "";
		if(type.equals("title")) {
			query = "select * from (SELECT ROWNUM AS RNUM, n.* from (SELECT * FROM event where event_title like ? ORDER BY event_NO DESC) n) where rnum BETWEEN ? and ?";
		}else {
			query = "select * from (SELECT ROWNUM AS RNUM, n.* from (SELECT * FROM event where event_content like ? ORDER BY event_NO DESC) n) where rnum BETWEEN ? and ?";			
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Event e = new Event(); 
				e.setEventNo(rset.getInt("event_no"));
				e.setEventTitle(rset.getString("event_title"));
				e.setEventContent(rset.getString("event_content"));
				e.setEventWriter(rset.getInt("event_writer"));
				e.setFilepath(rset.getString("filepath"));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectTotalCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "";
		if(type.equals("title")) {
			query = "select count(*) as cnt from event where event_title like ?";
		} else {			
			query = "select count(*) as cnt from event where event_content like ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
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
	
	public int deleteEvent(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from event where event_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateEvent(Connection conn, Event e) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update event set event_title=?, event_content=?, filepath=? where event_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, e.getEventTitle());
			pstmt.setString(2, e.getEventContent());
			pstmt.setString(3, e.getFilepath());
			pstmt.setInt(4, e.getEventNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
