package board.model.service;

import java.sql.Connection;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import common.JDBCTemplate;

public class BoardService {

	public int insertBoard(Board board) {
		Connection conn = JDBCTemplate.getConnection();
		
		int r = new BoardDao().insertBoard(conn, board);
		
		if(r>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return r;
	}

}
