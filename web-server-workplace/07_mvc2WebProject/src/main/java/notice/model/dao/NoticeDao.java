package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

public class NoticeDao {

	public int insertNotice(Connection conn, Notice notice) {
		String query = "insert into notice values(notice_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?)";

		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			int i = 1;
			pstmt.setString(i++, notice.getNoticeTitle());
			pstmt.setString(i++, notice.getNoticeContent());
			pstmt.setString(i++, notice.getNoticeWriter());
			pstmt.setString(i++, notice.getFilename());
			pstmt.setString(i++, notice.getFilepath());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public ArrayList<Notice> selectNoticeList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		String query = "select * from(select rownum as rnum, n.* from (select * from notice order by notice_no desc)n) where rnum between ? and ?";
		ArrayList<Notice> notices = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			r = pstmt.executeQuery();

			while (r.next()) {
				int i = 2;
				notices.add(new Notice(r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++),
						r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(r);
			JDBCTemplate.close(pstmt);
		}

		return notices;
	}

	public int selectTotalCount(Connection conn) {
		int result = 0;

		String query = "select count(*) as cnt from notice";

		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet r = pstmt.executeQuery();) {

			if (r.next()) {
				result = r.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int updateReadCount(Connection conn, int noticeNo) {
		String query = "update notice set read_count = read_count+1 where notice_no = ?";
		int result = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Notice selectOneMember(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		String query = "select * from notice where notice_no = ?";
		Notice notice = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			r = pstmt.executeQuery();

			if (r.next()) {
				int i = 1;
				notice = new Notice(r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++), r.getInt(i++),
						r.getString(i++), r.getString(i++), r.getString(i++));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(r);
			JDBCTemplate.close(pstmt);
		}

		return notice;

	}

	public int deleteNotice(Connection conn, int noticeNo) {
		String query = "delete from notice where notice_no = ?";
		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, noticeNo);
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public int updateNotice(Connection conn, Notice notice) {
		String query = "update notice set notice_title=?, notice_content=?, filename=?, filepath =? where notice_no = ?";

		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			int i = 1;
			pstmt.setString(i++, notice.getNoticeTitle());
			pstmt.setString(i++, notice.getNoticeContent());
			pstmt.setString(i++, notice.getFilename());
			pstmt.setString(i++, notice.getFilepath());
			pstmt.setInt(i++, notice.getNoticeNo());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public ArrayList<Notice> selectSearchNotice(Connection conn, int start, int end, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet r = null;

		type = type.equals("title") ? "title" : "writer";

		String query = "select * from(select rownum as rnum, n.* from (select * from notice where notice_" + type
				+ " like ? order by notice_no desc)n) where rnum between ? and ?";

		ArrayList<Notice> notices = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			r = pstmt.executeQuery();

			while (r.next()) {
				int i = 2;
				notices.add(new Notice(r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++),
						r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(r);
			JDBCTemplate.close(pstmt);
		}

		return notices;
	}

	public int selectTotalCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet r = null;

		type = type.equals("title") ? "title" : "writer";

		String query = "select count(*) as cnt from notice where notice_" + type + " like ? ";

		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");

			r = pstmt.executeQuery();

			if (r.next()) {
				result = r.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(r);
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int insertComment(Connection conn, NoticeComment nc) {
		String query = "insert into notice_comment values(nc_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'),?,?)";

		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			int i = 1;
			pstmt.setInt(i++, nc.getNcLevel());
			pstmt.setString(i++, nc.getNcWriter());
			pstmt.setString(i++, nc.getNcContent());
			pstmt.setInt(i++, nc.getNoticeRef());
			//pstmt.setInt(i++, nc.getNcRef());
			pstmt.setString(i++, (nc.getNcRef()==0? null:String.valueOf(nc.getNcRef())));
			
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public ArrayList<NoticeComment> selectCommentList(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet r = null;
		
		ArrayList<NoticeComment> noticeComments = new ArrayList<>();
		
		String query = "select * from notice_comment where notice_ref = ? order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			r = pstmt.executeQuery();
			
			while(r.next()) {
				int i = 1;
				noticeComments.add(new NoticeComment(
						r.getInt(i++),
						r.getInt(i++),
						r.getString(i++),
						r.getString(i++),
						r.getString(i++),
						r.getInt(i++),
						r.getInt(i++) //nc_ref 이 null인 경우 자동으로 0리턴
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(r);
		}
		
		return noticeComments;
	}

	public int deleteComment(Connection conn, int ncNo) {
		String query = "delete from notice_comment where nc_no = ?";
		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, ncNo);
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
		}
}
