package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import table.model.vo.MakerInfo;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;

public class ProjectDao {

	public int insertProjInfo(Connection conn, ProjectBasicInfo pjInfo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into project_basic_info values(project_seq.nextval,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pjInfo.getBusinessNo());
			pstmt.setString(2, pjInfo.getProjectTitle());
			pstmt.setInt(3, pjInfo.getTargetPrice());
			pstmt.setString(4 , pjInfo.getFilepath());
			pstmt.setString(5, pjInfo.getEndDate());
			pstmt.setString(6, pjInfo.getProjectStory());
			pstmt.setString(7, pjInfo.getFundingCategory());
			pstmt.setString(8, pjInfo.getStartDate());
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

	public int selectProjNo(Connection conn, ProjectBasicInfo pjInfo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int projNo = 0;
		String query = "select max(project_no) as project_no from project_basic_info";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				projNo = rset.getInt("project_no");
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
		return projNo;
	}

	public int insertReward(Connection conn, Reward re,int projNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into reward values(?,?,?,?,?,?,?,?)"; 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,projNo);
			pstmt.setInt(2, re.getRewardPrice());
			pstmt.setString(3, re.getRewardTitle());
			pstmt.setString(4, re.getRewardContent());
			pstmt.setString(5, re.getShippingDate());
			pstmt.setString(6, re.getCancelPolicy());
			pstmt.setString(7, re.getqEmail());
			pstmt.setString(8, re.getqPhone());
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

	public int insertMakerInfo(Connection conn, MakerInfo mInfo,int projNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query= "insert into maker_info values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projNo);
			pstmt.setString(2, mInfo.getTradeBank());
			pstmt.setInt(3, mInfo.getAccountNumber());
			pstmt.setString(4, mInfo.getDepositName());
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
