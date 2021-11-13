package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardRowMapper;

@Repository
public class BoardDao {
	@Autowired
	// MemberDao 에서 사용한 동일한 객체(servlert-context.xml에서 만든 bean)을 그대로 사용중임.(완전히 동일한 객체)
	private JdbcTemplate jdbcTemplate;

	public ArrayList<Board> selectBoardList() {
		String query = "select * from board";
		
		List list = jdbcTemplate.query(query, new BoardRowMapper());
		
		return (ArrayList<Board>)list;
	}

	public Board boardView(String boardNo) {
		String query = "select * from board where board_no = ?";
		
		List list = jdbcTemplate.query(query,new Object[] {boardNo}, new BoardRowMapper());
		
		return (Board)list.get(0);
	}

	public int insertBoard1(Board board) {
		String query = "insert into board values(board_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		
		Object[] param = {board.getBoardTitle(),board.getBoardContent(),board.getBoardWriter()};
		
		int result = jdbcTemplate.update(query,param);
		
		return result;
	}

	public Board selectOneBoard(int boardNo) {
		String query = "select * from board where board_no = ?";
		
		List list = jdbcTemplate.query(query,new Object[] {boardNo}, new BoardRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			return (Board)list.get(0);
		}
		
	}

}
