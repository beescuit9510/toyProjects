package funder.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import table.model.vo.FunderCategory;
import table.model.vo.MakerBoard;

public class FunderDao {
	//1. 글 목록 
	public ArrayList<MakerBoard> selectFunderList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MakerBoard> mblist = new ArrayList<MakerBoard>();
		String query = "select mb.*,(select count(*) from funder_like where liked_business_no=mb.writer_no) as \"makerCount\" from (select rownum as rnum, m.* from(select * from MAKER_BOARD order by 1 desc)m)mb  where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MakerBoard fb = new MakerBoard();
				fb.setWriterNo(rset.getInt("writer_no"));
				fb.setBusinessName(rset.getString("business_name"));
				fb.setOpenDate(rset.getString("open_date"));
				fb.setBoardEmail(rset.getString("board_email"));
				fb.setSearchTag(rset.getString("search_tag"));
				fb.setSkillName(rset.getString("skill_name"));
				fb.setSkillLevel(rset.getString("skill_level"));
				fb.setCompanyAddr(rset.getString("company_addr"));
				fb.setCompanyIntro(rset.getString("company_intro"));
				fb.setProfileFilepath(rset.getString("profile_filepath"));
				fb.setFunderCategory(rset.getString("funder_category"));
				fb.setMakerBoardNo(rset.getInt("maker_board_no"));
				fb.setWriteDate(rset.getString("write_date"));
				fb.setFunderCount(rset.getInt("makerCount"));
				mblist.add(fb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mblist;
	}
	//2. 카테고리 조회
	public ArrayList<FunderCategory> selectCategoryList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FunderCategory> catelist = new ArrayList<FunderCategory>();
		String query = "select * from funder_category";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				FunderCategory fc = new FunderCategory();
				fc.setFunderCategory(rset.getString("funder_category"));
				catelist.add(fc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return catelist;
	}
	//3. 펀더 등록
	public int insertFunder(Connection conn, MakerBoard mb) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into maker_board values(?,?,?,?,?,?,?,?,?,?,?,maker_board_seq.NEXTVAL,to_char(sysdate,'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mb.getWriterNo());
			pstmt.setString(2, mb.getBusinessName());
			pstmt.setString(3, mb.getOpenDate());
			pstmt.setString(4, mb.getBoardEmail());
			pstmt.setString(5, mb.getSearchTag());
			pstmt.setString(6, mb.getSkillName());
			pstmt.setString(7, mb.getSkillLevel());
			pstmt.setString(8, mb.getCompanyAddr());
			pstmt.setString(9, mb.getCompanyIntro());
			pstmt.setString(10, mb.getProfileFilepath());
			pstmt.setString(11, mb.getFunderCategory());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//4. 카운트 조회
	public int selectLike(Connection conn, int likedBusinessNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int like_cnt = 0;
		String query = "select count(*) as\"makerCount\" from funder_like where liked_business_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, likedBusinessNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				like_cnt = rset.getInt("makerCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return like_cnt;
	}
	//5. 좋아요 누른사람인지 체크 
	public int selectLikeCheck(Connection conn, int likeMemberNo, int likedBusinessNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int like_check = 0;
		String query = "select * from funder_like where c_member_no=? and liked_business_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, likeMemberNo);
			pstmt.setInt(2, likedBusinessNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				like_check = 1;
			}else {
				like_check = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return like_check;
	}
	//6. 좋아요 취소
	public int deleteLike(Connection conn, int likeMemberNo, int likedBusinessNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from funder_like where c_member_no=? and liked_business_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, likeMemberNo);
			pstmt.setInt(2, likedBusinessNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//7. 좋아요
	public int updateLike(Connection conn, int likeMemberNo, int likedBusinessNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into funder_like values(?,?,like_seq.nextval)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, likeMemberNo);
			pstmt.setInt(2, likedBusinessNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//8. 펀더 view
	public MakerBoard selectOneFunderView(Connection conn, int writerNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MakerBoard mb = null;
		String query = "select nn.*,(select count(*) from funder_like where liked_business_no=nn.writer_no) as \"makerCount\" from (select * from maker_board where writer_no=?)nn";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, writerNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mb = new MakerBoard();
				mb.setWriterNo(rset.getInt("writer_no"));
				mb.setBusinessName(rset.getString("business_name"));
				mb.setOpenDate(rset.getString("open_date"));
				mb.setBoardEmail(rset.getString("board_email"));
				mb.setSearchTag(rset.getString("search_tag"));
				mb.setSkillName(rset.getString("skill_name"));
				mb.setSkillLevel(rset.getString("skill_level"));
				mb.setCompanyAddr(rset.getString("company_addr"));
				mb.setCompanyIntro(rset.getString("company_intro"));
				mb.setProfileFilepath(rset.getString("profile_filepath"));
				mb.setFunderCategory(rset.getString("funder_category"));
				mb.setMakerBoardNo(rset.getInt("maker_board_no"));
				mb.setWriteDate(rset.getString("write_date"));
				mb.setFunderCount(rset.getInt("makerCount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mb;
	}	
	//09. list_페이징
	public int selectTotalCount(Connection conn) {
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
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//10. funder_list_search
	public ArrayList<MakerBoard> selectSearchFunder(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MakerBoard> mblist = new ArrayList<MakerBoard>();
		String query = "select mb.*,(select count(*) from funder_like where liked_business_no=mb.writer_no) as \"makerCount\" from (select rownum as rnum, m.* from(SELECT * FROM maker_board where (business_name||search_tag||skill_name||company_intro||funder_category) like ? order by 1 desc)m)mb  where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MakerBoard fb = new MakerBoard();
				fb.setWriterNo(rset.getInt("writer_no"));
				fb.setBusinessName(rset.getString("business_name"));
				fb.setOpenDate(rset.getString("open_date"));
				fb.setBoardEmail(rset.getString("board_email"));
				fb.setSearchTag(rset.getString("search_tag"));
				fb.setSkillName(rset.getString("skill_name"));
				fb.setSkillLevel(rset.getString("skill_level"));
				fb.setCompanyAddr(rset.getString("company_addr"));
				fb.setCompanyIntro(rset.getString("company_intro"));
				fb.setProfileFilepath(rset.getString("profile_filepath"));
				fb.setFunderCategory(rset.getString("funder_category"));
				fb.setMakerBoardNo(rset.getInt("maker_board_no"));
				fb.setWriteDate(rset.getString("write_date"));
				fb.setFunderCount(rset.getInt("makerCount"));
				mblist.add(fb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mblist;
	}
	//11. 카테고리 게시물 수 구하기 
	public int selectTotalCount(Connection conn, String category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from maker_board where funder_category=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//12. 카테고리 누를경우 해당 전체 게시물  조회 
	public ArrayList<MakerBoard> selectFunderList(Connection conn, int start, int end, String category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MakerBoard> mblist = new ArrayList<MakerBoard>();
		String query = "select mb.*,(select count(*) from funder_like where liked_business_no=mb.writer_no) as \"makerCount\" from (select rownum as rnum, m.* from(select * from MAKER_BOARD where funder_category=? order by 1 desc)m)mb  where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				MakerBoard fb = new MakerBoard();
				fb.setWriterNo(rset.getInt("writer_no"));
				fb.setBusinessName(rset.getString("business_name"));
				fb.setOpenDate(rset.getString("open_date"));
				fb.setBoardEmail(rset.getString("board_email"));
				fb.setSearchTag(rset.getString("search_tag"));
				fb.setSkillName(rset.getString("skill_name"));
				fb.setSkillLevel(rset.getString("skill_level"));
				fb.setCompanyAddr(rset.getString("company_addr"));
				fb.setCompanyIntro(rset.getString("company_intro"));
				fb.setProfileFilepath(rset.getString("profile_filepath"));
				fb.setFunderCategory(rset.getString("funder_category"));
				fb.setMakerBoardNo(rset.getInt("maker_board_no"));
				fb.setWriteDate(rset.getString("write_date"));
				fb.setFunderCount(rset.getInt("makerCount"));
				mblist.add(fb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mblist;
	}

}
