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
		// 한 페이지당 게시물 수
		int numPerPage = 10;
		// reqPage = 1 start = 1/end =10, reqPage = 2 start=11/end=20

		int end = reqPage * numPerPage;

		int start = end - numPerPage + 1;

		ArrayList<Board> boards = dao.selectNoticeList(conn, start, end);
		
		int totalCount = dao.selectTotalCount(conn);

		// 전체 페이지수를 계산;
		int totalPage = totalCount % numPerPage == 0 ? totalCount / numPerPage : totalCount / numPerPage + 1;

//		지정해야 할 항목
//		2.페이지 네비의 길이(네비게이션 숫자 최대 길이)
		int pageNaviSize = 5;
//		1~5페이지 요청시(reqPage 값) -
		
		int pageNo = reqPage<=3? 1:totalPage-reqPage<=2? totalPage-5:reqPage-2;
		
		//req 1~3 = 1
		//req 4 = 2
		
		System.out.println(totalPage);
		
		String pageNavi = "<ul class='pagination pagination-lg'>";

		// 이전 버튼
		if (pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "&lt</a></li>";
		}

		// 페이지 숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item '>";
				pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			if (pageNo > totalPage) {
				break;
			}
			pageNo++;
		}
		// 다음 버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item '>";
			pageNavi += "<a class='page-link' href='/boardList?reqPage=" + pageNo + "'>";
			pageNavi += "&gt;</a></li>";
		}

		pageNavi += "</ul>";

		
		
		
		BoardPageData boardPageData = new BoardPageData(boards, pageNavi, start);
		
		JDBCTemplate.close(conn);
		
		return boardPageData;
	}

}
