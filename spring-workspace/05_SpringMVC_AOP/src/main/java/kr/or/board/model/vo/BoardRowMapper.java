package kr.or.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BoardRowMapper implements RowMapper{

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		Board board = new Board();
		board.setBoardNo(rs.getInt("board_no"));
		board.setBoardTitle(rs.getString("board_title"));
		board.setBoardContent(rs.getString("board_content"));
		board.setBoardWriter(rs.getString("board_writer"));
		board.setBoardDate(rs.getString("board_date"));
		return board;
	}

}
