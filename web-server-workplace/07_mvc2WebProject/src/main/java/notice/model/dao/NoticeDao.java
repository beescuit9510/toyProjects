package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.vo.Notice;

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
}
