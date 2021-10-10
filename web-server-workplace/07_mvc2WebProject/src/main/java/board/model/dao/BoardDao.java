package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.model.vo.Board;
import common.JDBCTemplate;

public class BoardDao {

	public int insertBoard(Connection conn, Board board) {
		String query = "insert into board values(board_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?)";

		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			int i = 1;
			pstmt.setString(i++, board.getBoardTitle());
			pstmt.setString(i++, board.getBoardContent());
			pstmt.setString(i++, board.getBoardWriter());
			pstmt.setString(i++, board.getFilename());
			pstmt.setString(i++, board.getFilepath());

			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public ArrayList<Board> selectBoardList(Connection conn, int start, int end) {
//		String query = "select * from(select rownum as rnum, b.* from(select * from board order by board_no desc)b) where rnum between ? and ?";
		String query = "select bb.*, (select count(*) from board_comment bc where bc.board_ref=bb.board_no) as \"bc_count\" from (select rownum as rnum, b.* from(select * from board order by board_no desc)b)bb where rnum between ? and ?";
		ArrayList<Board> boards = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet r = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			r = pstmt.executeQuery();

			while (r.next()) {
				int i = 2;
				boards.add(new Board(r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++), r.getInt(i++),
						r.getString(i++), r.getString(i++), r.getString(i++), r.getInt(i++)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return boards;

	}

	public int selectTotalCount(Connection conn) {
		int result = 0;

		String query = "select count(*) as cnt from board";

		try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet r = pstmt.executeQuery();) {

			if (r.next()) {
				result = r.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Board selectOneBoard(Connection conn, int boardNo) {
		String query = "select * from board where board_no = ?";

		Board board = null;

		PreparedStatement pstmt = null;

		ResultSet r = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			r = pstmt.executeQuery();

			if (r.next()) {
				int i = 1;
				board = new Board(r.getInt(i++), r.getString(i++), r.getString(i++), r.getString(i++), r.getInt(i++),
						r.getString(i++), r.getString(i++), r.getString(i++));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return board;
	}

	public int updateReadCount(Connection conn, int boardNo) {
		String query = "update board set read_count = read_count+1 where board_no = ?";

		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, boardNo);
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public int deleteBoard(Connection conn, int boardNo) {
		String query = "delete from board where board_no = ?";

		int r = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(query);) {
			int i = 1;
			pstmt.setInt(i++, boardNo);

			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public int updateBoard(Connection conn, Board board) {
		String query = "update board set board_title = ?, board_content = ?, filename = ?, filepath = ? where board_no = ?";

		PreparedStatement pstmt = null;

		int r = -1;

		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			pstmt.setString(i++, board.getBoardTitle());
			pstmt.setString(i++, board.getBoardContent());
			pstmt.setString(i++, board.getFilename());
			pstmt.setString(i++, board.getFilepath());
			pstmt.setInt(i++, board.getBoardNo());
			r = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}

		return r;
	}

}
