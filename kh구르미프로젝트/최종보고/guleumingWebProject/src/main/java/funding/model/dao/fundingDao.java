package funding.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
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


public class fundingDao {

	public Reward selectReward(int projectNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reward r = null;
		String query="select*from reward where reward_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				r = new Reward();
				r.setRewardNo(rset.getInt("reward_no"));
				r.setRewardPrice(rset.getInt("reward_price"));
				r.setRewardTitle(rset.getString("reward_title"));
				r.setRewardContent(rset.getString("reward_content"));
				r.setShippingDate(rset.getString("shipping_date"));
				r.setCancelPolicy(rset.getString("cancel_policy"));
				r.setqEmail(rset.getString("q_email"));
				r.setqPhone(rset.getString("q_phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return r;
	}

	public ProjectBasicInfo selectProjectBasicInfo(int projectNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProjectBasicInfo pbi = null;
		String query ="select*from PROJECT_BASIC_INFO where project_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				pbi = new ProjectBasicInfo();
				pbi.setProjectNo(rset.getInt("project_no"));
				pbi.setBusinessNo(rset.getInt("business_no"));
				pbi.setProjectTitle(rset.getString("project_title"));
				pbi.setTargetPrice(rset.getInt("target_price"));
				pbi.setFilepath(rset.getString("filepath"));
				pbi.setEndDate(rset.getString("end_date"));
				pbi.setProjectStory(rset.getString("project_story"));
				pbi.setFundingCategory(rset.getString("funding_category"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pbi;
	}

	public MakerInfo selectMakerInfo(int projectNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MakerInfo mi = null;
		String query ="select*from MAKER_INFO where maker_info_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mi = new MakerInfo();
				mi.setAccountNumber(rset.getInt("account_number"));
				mi.setDepositName(rset.getString("deposit_name"));
				mi.setMakerInfoNo(rset.getInt("maker_info_no"));
				mi.setTradeBank(rset.getString("trade_bank"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mi;
	}

	public int totalQuantity(int projectNo ,Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select sum(quantity)as total from PAYMENT_INFO where project_no=?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<FundingCommentTotal> selectFundingCommentList(int projectNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FundingCommentTotal> list = new ArrayList<FundingCommentTotal>();
		String query ="select a.*,b.c_name from FUNDING_COMMENT a join member b on a.COMMENT_WRITER = b.c_member_no where project_ref_no= ? order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FundingCommentTotal fct = new FundingCommentTotal();
				fct.setcName(rset.getString("c_name"));
				fct.setCommentContent(rset.getString("comment_content"));
				fct.setCommentLevel(rset.getInt("comment_level"));
				fct.setCommentNo(rset.getInt("comment_no"));
				fct.setCommentRefNo(rset.getInt("comment_ref_no"));
				fct.setCommentWriter(rset.getInt("comment_writer"));
				fct.setProjectRefNo(rset.getInt("project_ref_no"));
				fct.setWriteDate(rset.getNString("write_date"));
				list.add(fct);
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

	public ArrayList<FundingListRecent> selectFundingListRecent(int reqPage, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FundingListRecent> list = new ArrayList<FundingListRecent>();
		String query ="select nn.*,(select sum(quantity)as total from PAYMENT_INFO where project_no=nn.project_no)as total  \r\n" + 
				"from (select rownum as rnum, n.* from(select a.*, b.reward_price from PROJECT_BASIC_INFO a \r\n" + 
				"join reward b on a.project_no = b.reward_no order by project_no desc)n)nn where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reqPage);
			pstmt.setInt(2, reqPage+2);
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
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	public ArrayList<FundingListRecent> selectFundingListCategory(String category, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FundingListRecent> list = new ArrayList<FundingListRecent>();
		String query ="select nn.*,(select sum(quantity)as total from PAYMENT_INFO where project_no=nn.project_no)as total  from (select rownum as rnum, n.* from(select a.*, b.reward_price from PROJECT_BASIC_INFO a \r\n" + 
				"join reward b on a.project_no = b.reward_no order by project_no desc)n)nn where funding_category= ? order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
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
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	public int countFunding(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String query = "select count(*) as cnt from PROJECT_BASIC_INFO";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}

	public FundingViewTotal selectFundingViewTotal(int projectNo,int cMemberNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FundingViewTotal fvt = null;
		String query ="select * from (select rownum as rnum, pb.*, (select count(*) from funding_like \r\n" + 
				"where liked_project_no=pb.project_no) as liked_count, \r\n" + 
				"decode((select count(*) from funding_like \r\n" + 
				"where c_member_no=? and liked_project_no= pb.project_no),'1','like','no') as liked_check \r\n" + 
				"from (select a.*,b.*,c.business_name,\r\n" + 
				"(select sum(quantity)as total from PAYMENT_INFO where project_no = a.project_no)as total,\r\n" + 
				"(select count(*) from PAYMENT_INFO where project_no = a.project_no)as cnt\r\n" + 
				"from PROJECT_BASIC_INFO a join reward b on a.project_no = b.reward_no join business c on a.business_no=c.business_no)pb) where project_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			pstmt.setInt(2, projectNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				fvt = new FundingViewTotal();
				fvt.setProjectNo(rset.getInt("project_no"));
				fvt.setBusinessNo(rset.getInt("business_no"));
				fvt.setProjectTitle(rset.getString("project_title"));
				fvt.setTargetPrice(rset.getInt("target_price"));
				fvt.setFilepath(rset.getString("filepath"));
				fvt.setEndDate(rset.getString("end_date"));
				fvt.setProjectStory(rset.getString("project_story"));
				fvt.setFundingCategory(rset.getString("funding_category"));
				fvt.setBusinessName(rset.getString("business_name"));
				fvt.setTotal(rset.getInt("total"));
				fvt.setRewardPrice(rset.getInt("reward_price"));
				fvt.setRewardTitle(rset.getString("reward_title"));
				fvt.setRewardContent(rset.getString("reward_content"));
				fvt.setShippingDate(rset.getString("shipping_date"));
				fvt.setCancelPolicy(rset.getString("cancel_policy"));
				fvt.setqEmail(rset.getString("q_email"));
				fvt.setqPhone(rset.getString("q_phone"));
				fvt.setCnt(rset.getInt("cnt"));
				fvt.setLikeCount(rset.getInt("liked_count"));
				fvt.setLikeCheck(rset.getString("liked_check"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return fvt;
	}

	public int insertPaymentInfo(Connection conn, PaymentInfo pi) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into PAYMENT_INFO values(?,?,?,to_char(sysdate,'YYYYMMDDHH24MISS'),?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, pi.getPaymentNo());
			pstmt.setInt(2, pi.getQuantity());
			pstmt.setString(3, pi.getReceiveAddr());
			pstmt.setString(4, pi.getReceiveName());
			pstmt.setString(5, pi.getReceivePhone());
			pstmt.setInt(6, pi.getProjectNo());
			pstmt.setInt(7, pi.getcMemberNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertFundingComment(Connection conn, FundingComment fc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into funding_comment values(fundcomm_SEQ.nextval,?,to_char(sysdate,'yyyy-mm-dd'),?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fc.getCommentContent());
			pstmt.setInt(2, fc.getCommentLevel());
			pstmt.setInt(3, fc.getProjectRefNo());
			pstmt.setString(4, (fc.getCommentRefNo()==0) ? null : String.valueOf(fc.getCommentRefNo()));
			pstmt.setInt(5, fc.getCommentWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int fundingCommentUpdate(Connection conn, int commentNo, String updateCommentContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update funding_comment set comment_content=? where comment_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updateCommentContent);
			pstmt.setInt(2, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int fundingCommentDelete(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from funding_comment where comment_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<FundingCategory> selectFundingCategory(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FundingCategory> fcList = new ArrayList<FundingCategory>();
		String query = "select*from FUNDING_CATEGORY";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FundingCategory fc = new FundingCategory();
				fc.setFundingCategory(rset.getString("funding_category"));
				fcList.add(fc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return fcList;
	}

	public int checkFundingLike(Connection conn, int cMemberNo, int projectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select COUNT(*)as cnt from funding_like where c_member_no=? and liked_project_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			pstmt.setInt(2, projectNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return result;
	}

	public int updateFundingLike(Connection conn, int cMemberNo, int projectNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="insert into funding_like values(?,?,like_seq.nextval)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			pstmt.setInt(2, projectNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteFundingLike(Connection conn, int projectNo, int cMemberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from funding_like where c_member_no = ? and Liked_project_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			pstmt.setInt(2, projectNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<LikeCount> selectLikedMember(Connection conn, int projectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<LikeCount> lcList = new ArrayList<LikeCount>();
		String query ="select a.c_member_no from funding_like a where liked_project_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				LikeCount lc = new LikeCount();
				lc.setLikedMemberNo(rset.getInt("c_member_no"));
				lcList.add(lc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return lcList;
	}

	public ArrayList<FundingListRecent> selectFundingListSearch(String keyWord, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FundingListRecent> list = new ArrayList<FundingListRecent>();
		String query ="select nn.*,(select sum(quantity)as total from PAYMENT_INFO where project_no=nn.project_no)as total  \r\n" + 
				"from (select rownum as rnum, n.* from(select a.*, b.reward_price,b.reward_title,b.reward_content from PROJECT_BASIC_INFO a  \r\n" + 
				"join reward b on a.project_no = b.reward_no  order by project_no desc)n)nn where \r\n" + 
				"project_title like ? or project_story like ? or reward_title like ? or reward_content like ?  order by 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyWord+"%");
			pstmt.setString(2, "%"+keyWord+"%");
			pstmt.setString(3, "%"+keyWord+"%");
			pstmt.setString(4, "%"+keyWord+"%");
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
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}


	

}
