package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
//import kr.or.board.model.vo.BoardRowMapper;
//import kr.or.board.model.vo.FileVO;
//import kr.or.board.model.vo.FileVORowMapper;
import kr.or.board.model.vo.FileVO;

@Repository
public class BoardDao {
	// MemberDao 에서 사용한 동일한 객체(servlert-context.xml에서 만든 bean)을 그대로 사용중임.(완전히 동일한 객체)
//	@Autowired
//	private JdbcTemplate jdbcTemplate;	
	
	@Autowired
	private SqlSessionTemplate sqlSession;


	public ArrayList<Board> selectBoardList() {
		List<Board> list = sqlSession.selectList("board.boardList");
		return (ArrayList<Board>)list;
	}
	
	public int insertBoard1(Board b) {
		return sqlSession.insert("board.insertBoard", b);
	}

	public int selectBoardNo() {
		return sqlSession.selectOne("board.getBoardNo");
	}

	public int insertFile(FileVO fv) {
		return sqlSession.insert("board.insertFile", fv);
	}

	public Board selectOneBoard(int boardNo) {
		return sqlSession.selectOne("board.selectOneBoard", boardNo);
	}

	public ArrayList<FileVO> selectFileVO(int boardNo) {
		List<FileVO> list = sqlSession.selectList("board.selectFilelist", boardNo);
		return (ArrayList<FileVO>)list;

	}
	
	

//	public Board boardView(String boardNo) {
//		String query = "select * from board where board_no = ?";
//		
//		List list = jdbcTemplate.query(query,new Object[] {boardNo}, new BoardRowMapper());
//		
//		return (Board)list.get(0);
//	}
//
//	public int insertBoard1(Board board) {
//		String query = "insert into board values(board_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
//		
//		Object[] param = {board.getBoardTitle(),board.getBoardContent(),board.getBoardWriter()};
//		
//		int result = jdbcTemplate.update(query,param);
//		
//		return result;
//	}
//
//	public Board selectOneBoard(int boardNo) {
//		String query = "select * from board where board_no = ?";
//		
//		List list = jdbcTemplate.query(query,new Object[] {boardNo}, new BoardRowMapper());
//		
//		if(list.isEmpty()) {
//			return null;
//		}else {
//			return (Board)list.get(0);
//		}
//		
//	}
//
//	public int selectBoardNo() {
//		String query = "select max(board_no) from board";
//		int boardNo = jdbcTemplate.queryForObject(query, int.class);
//		return boardNo;
//	}
//
//	public int insertFile(FileVO fv) {
//		String query = "insert into file_tbl values(file_seq.nextval,?,?,?)";
//		Object[] params = {fv.getBoardNo(),fv.getFilename(),fv.getFilepath()};
//		int result = jdbcTemplate.update(query,params);
//		return result;
//	}
//
//	public ArrayList<FileVO> selectFileVO(int boardNo) {
//		String query = "select * from file_tbl where board_no = ?";
//		List list = jdbcTemplate.query(query, new Object[] {boardNo}, new FileVORowMapper());
//		return (ArrayList<FileVO>)list;
//	}
//	public ArrayList<Board> selectBoardList() {
//		List<Board> list = sqlSession.selectList("board.boardList");
//		return (ArrayList<Board>)list;
////		String query = "select * from board";
////		List list = jdbcTemplate.query(query, new BoardRowMapper());
////		return (ArrayList<Board>)list;
//	}
//	


}
