package adminPage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import adminPage.model.vo.AdminPageFundingRewardPayment;
import common.JDBCTemplate;
import table.model.vo.MakerBoard;
import table.model.vo.Member;
import table.model.vo.ProjectBasicInfo;

public class AdminPageDao {

	
	public ArrayList<Member> selectAdminPageMemberList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select nn.* from (select rownum as rnum, n.* from ( select * from member order by c_member_no desc)n)nn where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setcMemberNo(rset.getInt("c_member_no"));
				m.setcName(rset.getString("c_name"));
				m.setcPassword(rset.getString("c_Password"));
				m.setcPhone(rset.getString("c_phone"));
				m.setcEnrollDate(rset.getString("c_enroll_date"));
				m.setcEmail(rset.getString("c_email"));
				m.setcLevel(rset.getInt("c_level"));
				list.add(m);
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
		String query = "select count(*) as cnt from member";
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
	
	public int selectFundingTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from member where c_level=2";
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
	
	public int selectFunderTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from member where c_level=3";
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
	
	public int changeLevel(Connection conn, int memberNo, int memberLevel) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query="update member set c_level=? where c_member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberLevel);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Member> selectSearchMember(Connection conn, int start, int end, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "";
		if(type.equals("email")) {
			query = "select * from (SELECT ROWNUM AS RNUM, n.* from (SELECT * FROM member where c_email like ? ORDER BY C_MEMBER_NO DESC) n) where rnum BETWEEN ? and ?";
		}else {
			query = "select * from (SELECT ROWNUM AS RNUM, n.* from (SELECT * FROM member where c_member_no like ? ORDER BY C_MEMBER_NO DESC) n) where rnum BETWEEN ? and ?";			
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setcMemberNo(rset.getInt("c_member_no"));
				m.setcName(rset.getString("c_name"));
				m.setcPassword(rset.getString("c_Password"));
				m.setcPhone(rset.getString("c_phone"));
				m.setcEnrollDate(rset.getString("c_enroll_date"));
				m.setcEmail(rset.getString("c_email"));
				m.setcLevel(rset.getInt("c_level"));
				list.add(m);
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
		if(type.equals("email")) {
			query = "select count(*) as cnt from member where c_email like ?";
		} else {			
			query = "select count(*) as cnt from member where c_member_no like ?";
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
	
	public ArrayList<ProjectBasicInfo> selectAdminPageFundingList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProjectBasicInfo> list = new ArrayList<ProjectBasicInfo>();
		/*String query = "select nn.* from (select rownum as rnum, n.* from ( select * from Project_Basic_Info order by project_no desc)n)nn where rnum BETWEEN ? and ?";*/
		String query = "select nn.*, (select count(*) from payment_info where project_no=?) quantity from (select rownum as rnum, n.* from ( select * from Project_Basic_Info order by project_no desc)n)nn where rnum BETWEEN ? and ?";
		/*String query = "select * from(select rownum, t.* from (select (select count(*) from payment_info pi where pi.project_no = p.project_no) as total, p.payment_no,p.quantity,p.receive_addr,p.order_date,p.receive_name,p.receive_phone,p.c_member_no, pbi.*,r.*,m.* from payment_info p join project_basic_info pbi on p.project_no = pbi.project_no join reward r on p.project_no = r.reward_no join maker_info m on p.project_no = m.maker_info_no where p.c_member_no = 5 order by order_date desc) t) where rownum between ? and ?";*/
		/*String query = "select * from (select t.* from (select rownum as rnum, (select count(*) from payment_info pi where pi.project_no = pbi.project_no) as total, r.reward_price, pbi.* from project_basic_info pbi join reward r on pbi.project_no = r.reward_no order by pbi.project_no desc)t)t2 where t2.rnum between ? and ?";*/
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 1);							/////////////////////이거머야!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProjectBasicInfo pbi = new ProjectBasicInfo();
				pbi.setProjectNo(rset.getInt("project_no"));
				pbi.setBusinessNo(rset.getInt("business_no"));
				pbi.setProjectTitle(rset.getString("project_title"));
				pbi.setTargetPrice(rset.getInt("target_price"));
				pbi.setFilepath(rset.getString("filepath"));
				pbi.setEndDate(rset.getString("end_date"));
				/*pbi.setQuantity(rset.getInt("quantity"));*/
				/*frp.setTotal(rset.getInt("total"));*/
				list.add(pbi);
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
	
	
	
	public ArrayList<Member> selectAdminPageFundingMemberList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select nn.* from (select rownum as rnum, n.* from ( select * from member where c_level = 2 order by c_member_no desc)n)nn where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setcMemberNo(rset.getInt("c_member_no"));
				m.setcName(rset.getString("c_name"));
				m.setcPassword(rset.getString("c_Password"));
				m.setcPhone(rset.getString("c_phone"));
				m.setcEnrollDate(rset.getString("c_enroll_date"));
				m.setcEmail(rset.getString("c_email"));
				m.setcLevel(rset.getInt("c_level"));
				list.add(m);
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

	public ArrayList<Member> selectAdminPageMemberList(Connection conn, int start, int end, String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ArrayList<Member> selectAdminPageFunderMemberList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select nn.* from (select rownum as rnum, n.* from ( select * from member where c_level = 3 order by c_member_no desc)n)nn where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setcMemberNo(rset.getInt("c_member_no"));
				m.setcName(rset.getString("c_name"));
				m.setcPassword(rset.getString("c_Password"));
				m.setcPhone(rset.getString("c_phone"));
				m.setcEnrollDate(rset.getString("c_enroll_date"));
				m.setcEmail(rset.getString("c_email"));
				m.setcLevel(rset.getInt("c_level"));
				list.add(m);
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
	
	public int selectFundingViewTotalCount(Connection conn) {
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
	
	public int selectFunderViewTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from maker_board";
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
	
	public ArrayList<MakerBoard> selectAdminPageFunderList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MakerBoard> list = new ArrayList<MakerBoard>();
		String query = "select nn.*, (select count(*) from maker_board) as cnt from (select rownum as rnum, n.* from ( select * from maker_board order by maker_board_no desc)n)nn where rnum BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MakerBoard mb = new MakerBoard();
				mb.setMakerBoardNo(rset.getInt("maker_board_no"));
				mb.setWriterNo(rset.getInt("writer_no"));
				mb.setBusinessName(rset.getString("business_name"));
				mb.setProfileFilepath(rset.getString("profile_filepath"));
				mb.setBoardEmail(rset.getString("board_email"));
				mb.setOpenDate(rset.getString("open_date"));
				mb.setCompanyAddr(rset.getString("company_addr"));
				list.add(mb);
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
