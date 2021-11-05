package join.dao;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import common.JDBCTemplate;
import table.model.vo.Business;
import table.model.vo.Member;

public class JoinDao {

	public int selectOneId(Connection conn, String joinId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where c_email=?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, joinId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return result;
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(member_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getcName());
			pstmt.setString(2, m.getcPassword());
			pstmt.setString(3, m.getcPhone());
			pstmt.setString(4, m.getcEmail());
			pstmt.setInt(5, m.getcLevel());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int selectOneNo(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int memNo = 0;
		String query = "select max(c_member_no) as c_member_no from member";
		try {
			pstmt  = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				memNo = rset.getInt("c_member_no" );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return memNo;
	}

	public int insertBusiness(Connection conn, Business b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="insert into business values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,b.getBusinessNo());
			pstmt.setString(2, b.getBusinessName());
			pstmt.setString(3, b.getBusinessCode());
			pstmt.setString(4, b.getManagerName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int insertBusiness(Connection conn, Business b, int memNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="insert into business values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,memNo);
			pstmt.setString(2, b.getBusinessName());
			pstmt.setString(3, b.getBusinessCode());
			pstmt.setString(4, b.getManagerName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
