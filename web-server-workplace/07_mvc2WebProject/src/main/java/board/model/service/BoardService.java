package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardPageData;
import common.JDBCTemplate;

public class BoardService {

	public int insertBoard(Board board) {
		Connection conn = JDBCTemplate.getConnection();

		int r = new BoardDao().insertBoard(conn, board);

		if (r > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return r;
	}

	public BoardPageData selectNoticeList(int reqPage) {

		Connection conn = JDBCTemplate.getConnection();

		BoardDao dao = new BoardDao();

		int boardsPerPage = 10;

		int end = reqPage * 10; // 10 20 30 40

		int start = end - 9; // 1 11 21 31

		ArrayList<Board> boards = dao.selectBoardList(conn, start, end);

		int totalCount = dao.selectTotalCount(conn);

		int totalPage = totalCount % boardsPerPage == 0 ? totalCount / boardsPerPage : totalCount / boardsPerPage + 1;

		int naviSize = 5;

		int pageNo = reqPage <= 3 ? 1 : (totalPage - 2) <= reqPage ? totalPage - 4 : reqPage - 2;
		pageNo = pageNo<=0? 1:pageNo;
		

//		int pageNo = ((reqPage - 1) / naviSize) * naviSize + 1;

		String pageNavi = "<ul class='pagination pagination-lg'>";

		if (pageNo != 1) {
			pageNavi += "<li class='page-item' >";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "&lt;";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}

		for (int i = 0; i < naviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active' >";
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (pageNo) + "'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";

			} else {
				pageNavi += "<li class='page-item' >";
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (pageNo) + "'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";

			}

			System.out.println(pageNo);
			System.out.println(" total page : " + totalPage);
			pageNo++;

			if (pageNo > totalPage) {
				break;
			}

		}

		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item' >";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (pageNo) + "'>";
			pageNavi += "&gt;";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}

		pageNavi += "</ul>";

		BoardPageData boardPageData = new BoardPageData(boards, pageNavi, start);

		return boardPageData;
	}

	public Board selectOneBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();

		BoardDao dao = new BoardDao();

		int r = -1;

		r = dao.updateReadCount(conn, boardNo);

		Board board = null;

		if (r > 0) {
			JDBCTemplate.commit(conn);

			board = dao.selectOneBoard(conn, boardNo);

		} else {
			JDBCTemplate.rollback(conn);

		}

		JDBCTemplate.close(conn);

		return board;
	}

	public Board getBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();

		Board board = new BoardDao().selectOneBoard(conn, boardNo);

		JDBCTemplate.close(conn);

		return board;
	}

	public int deleteBoard(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
