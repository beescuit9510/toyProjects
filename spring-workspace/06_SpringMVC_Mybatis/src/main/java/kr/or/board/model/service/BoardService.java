package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardWithFile;
import kr.or.board.model.vo.FileVO;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;

	public ArrayList<Board> selectBoardList() {
		ArrayList<Board> list = dao.selectBoardList();
		
		return list;
	}

//	public Board boardView(String boardNo) {
//		Board board = dao.boardView(boardNo);
//		return board;
//	}

	public int insertBoard1(Board board) {
		int result = dao.insertBoard1(board);
		return result;
	}

	public Board selectOneBoard(int boardNo) {
		Board board = dao.selectOneBoard(boardNo);
		return board;
	}

	public int insertBoard2(Board b, ArrayList<FileVO> list) {
		int result1 = dao.insertBoard1(b);
		int result = 0;
		if(result1>0) {
			int boardNo = dao.selectBoardNo();
						
			for(FileVO fv :list) {
				fv.setBoardNo(boardNo);
				result += dao.insertFile(fv);
			}
			
		}else {
			return -1;
		}
		return result;
	}

	public Board selectBoard1(int boardNo) {
		
		//contoller는 service 한번만 호출
		//service에서 필요할때 dao 여러번 호출
		//데이터는 하나로 묶어서 리턴
		
		ArrayList<FileVO> list = dao.selectFileVO(boardNo);
		Board board = dao.selectOneBoard(boardNo);
		
		board.setList(list);
		
		
		return board;
	}

}