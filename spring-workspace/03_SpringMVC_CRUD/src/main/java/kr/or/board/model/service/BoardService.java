package kr.or.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;

	public ArrayList<Board> selectBoardList() {
		ArrayList<Board> list = dao.selectBoardList();
		
		return list;
	}

	public Board boardView(String boardNo) {
		Board board = dao.boardView(boardNo);
		return board;
	}

	public int insertBoard1(Board board) {
		int result = dao.insertBoard1(board);
		return result;
	}

	public Board selectOneBoard(int boardNo) {
		Board board = dao.selectOneBoard(boardNo);
		return board;
	}

}