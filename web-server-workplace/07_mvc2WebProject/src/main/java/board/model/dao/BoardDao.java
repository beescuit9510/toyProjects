package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import board.model.vo.Board;

public class BoardDao {

	public int insertBoard(Connection conn, Board board) {
		String query = "insert into board values(board_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?,?)";

		int r = -1;
		
		try (PreparedStatement pstmt = conn.prepareStatement(query);){
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

}
