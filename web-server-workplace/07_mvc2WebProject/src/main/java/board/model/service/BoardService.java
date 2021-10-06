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
		
		if(r>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return r;
	}

	public BoardPageData selectNoticeList(int reqPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		BoardDao dao = new BoardDao();
		
		
		
//		reqPage => 1 start=1/end=10
//		reqPage => 2 start=11/end=20
		
		int boardsPerPage = 10;
		int start = reqPage*boardsPerPage;		
		int end = boardsPerPage-9;

		ArrayList<Board> boards = dao.selectNoticeList(conn, start, end);
		
		int totalCount = dao.selectTotalCount(conn);
		
		int totalPage = totalCount%boardsPerPage==0? totalCount/boardsPerPage:totalCount/boardsPerPage+1;
				
		int pageNaviSize = 5;
		
		int pageNo = end/boardsPerPage;
		
		String pageNavi = "<ul><li>";
		
		if(reqPage!=1) {
			pageNavi += "<li><a href='noticeList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;"+"</a></li>";
		}
		
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage==pageNo) {
				pageNavi += "<li class='active'><a href='noticeList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li><a href='noticeList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";				
			}
			if(pageNo > totalPage) {
				break;
			}
			pageNo++;
		}

		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item '>";
			pageNavi += "<a class='page-link' href='/noticeList?reqPage="+pageNo+">";
			pageNavi += "&gt;</a></li>";			
		}

		
		pageNavi += "</li>";
		
		
		
		BoardPageData boardPageData = new BoardPageData(boards, pageNavi, start);
		
		JDBCTemplate.close(conn);
		
		return boardPageData;
	}

}
