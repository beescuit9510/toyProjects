package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
