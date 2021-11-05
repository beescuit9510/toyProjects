package login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import table.model.vo.Member;

public class LoginDao {

	public Member selectOneMember(Connection conn, String loginEmail, String pw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = "select * from member where c_Email=? and c_Password=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginEmail);
			pstmt.setString(2, pw);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				member.setcMemberNo(rset.getInt("c_member_no"));
				member.setcEmail(rset.getString("c_email"));
				member.setcPassword(rset.getString("c_password"));
				member.setcName(rset.getString("c_name"));
				member.setcPhone(rset.getString("c_phone"));
				member.setcLevel(rset.getInt("c_level"));
				member.setcEnrollDate(rset.getString("c_enroll_date"));
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
			
		return member;
	}

}
