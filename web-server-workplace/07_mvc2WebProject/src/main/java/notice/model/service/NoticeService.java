package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.dao.NoticeDao;
import notice.model.dao.NoticePageData;
import notice.model.vo.Notice;

public class NoticeService {

	public int insertNotice(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();
		
		int r = new NoticeDao().insertNotice(conn, notice);
		
		if(r > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return r;
	}

	public NoticePageData selectNoticeList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		/*/
		 * 페이징 처리를 위해서 지정해야할 항목
		 * 1. 한페이지당 게시물을 몇개 보여줄지 -> 10개
		 */
		
		// 한 페이지당 게시물 수
		int numPerPage = 10;
		//reqPage = 1 start = 1/end =10, reqPage = 2 start=11/end=20 
		
		int end = reqPage*numPerPage;

		int start = end - numPerPage + 1;
		
		NoticeDao dao = new NoticeDao();
		
		ArrayList<Notice> notices = dao.selectNoticeList(conn, start, end);
		
		
		//페이지 네비게이션을 제작
		//DB조회해야하는 값 -> 전체 게시물의 수
		int totalCount = dao.selectTotalCount(conn);
		
		//전체 페이지수를 계산;
		int totalPage = totalCount%numPerPage==0? totalCount/numPerPage:totalCount/numPerPage+1;
		
		
//		지정해야 할 항목
//		2.페이지 네비의 길이(네비게이션 숫자 최대 길이)
		int pageNaviSize = 5;
//		1~5페이지 요청시(reqPage 값) -> 네비게이션 1 2 3 4 5
//		6~10페이지 요청시 -> 네비게이션 6 7 8 9 10
//		11~55페이지 요청시 -> 네비게이션 11 12 13 14 15
//		페이지 네비게이션 시작번호 계산

		//숫자 시작 번호 ex> req (6~10) -> 6;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
//		reqPage(1~5)-1 = 0~4
//		0~4 / 5(pageNaviSzie) = 0~0 (int이기때문에 모두 0)
//		0 * 5 = 0 
//		0 + 1 = 1
		
		String pageNavi = "<ul class='pagination pagination-lg'>";
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-tim'>";
			pageNavi += "<a class='page-link' href='/noticeList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt</a></li>";			
		}
		
		//페이지 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/noticeList?reqPage="+pageNo+">";
				pageNavi += pageNo+"</a></li>";
			}else{
				pageNavi += "<li class='page-item '>";
				pageNavi += "<a class='page-link' href='/noticeList?reqPage="+pageNo+">";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item '>";
			pageNavi += "<a class='page-link' href='/noticeList?reqPage="+pageNo+">";
			pageNavi += "&gt;</a></li>";			
		}
		
		pageNavi += "</ul>";
		
		//게시물 목록(ArrayList), 페이지 네비(String);
		NoticePageData noticePageDate = new NoticePageData(notices, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return noticePageDate;
	}

}