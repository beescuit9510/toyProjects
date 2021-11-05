package funder.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import funder.model.dao.FunderDao;
import table.model.vo.FunderCategory;
import table.model.vo.FunderPageData;
import table.model.vo.MakerBoard;

public class FunderService {
	//1. 글 목록 조회
	public FunderPageData selectFunderList(int reqPage, String category) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end  - numPerPage+1;
		FunderDao dao = new FunderDao();
		ArrayList<MakerBoard> mblist = new ArrayList<MakerBoard>();
		int totalCount = 0;
		if(category.equals("전체")) {			
			mblist = dao.selectFunderList(conn,start,end);
			totalCount = dao.selectTotalCount(conn);
		}else {
			mblist = dao.selectFunderList(conn,start,end,category);
			totalCount = dao.selectTotalCount(conn,category);
		}
				
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		}else {
			totalPage = totalCount /numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize) * pageNaviSize + 1;
		String pageNavi = "<ul class='pageination_funder'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page_item'>";
			pageNavi += "<a class='page_link' href='/funder/funderList?reqPage="+(pageNo-1)+"&funderCategory="+category+"'>";
			pageNavi += "&lt;</a></li>";
		}
		for(int i = 0; i < pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page_item active'>";
				pageNavi += "<a class='page_link' href='/funder/funderList?reqPage="+pageNo+"&funderCategory="+category+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page_link' href='/funder/funderList?reqPage="+pageNo+"&funderCategory="+category+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음버튼	
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page_item'>";
			pageNavi += "<a class='page_link' href='/funder/funderList?reqPage="+pageNo+"&funderCategory="+category+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		FunderPageData funderpd = new FunderPageData(mblist,pageNavi,start);
		JDBCTemplate.close(conn);
		return funderpd;
	}
	//2. 카테고리 조회
	public ArrayList<FunderCategory> selectCategoryList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<FunderCategory> catelist = new FunderDao().selectCategoryList(conn);
		JDBCTemplate.close(conn);
		return catelist;
	}
	//3. 펀더 등록
	public int insertFunder(MakerBoard mb) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FunderDao().insertFunder(conn,mb);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		} JDBCTemplate.close(conn);
		return result;
	}
	//4. 좋아요 조회
	public int selectLike(int likedBusinessNo) {
		Connection conn = JDBCTemplate.getConnection();
		int like_cnt = new FunderDao().selectLike(conn,likedBusinessNo);
		JDBCTemplate.close(conn);
		return like_cnt;
	}
	//5. 좋아요 눌렀는지 체크
	public int selectLikeCheck(int likeMemberNo, int likedBusinessNo) {
		Connection conn = JDBCTemplate.getConnection();
		int like_check = new FunderDao().selectLikeCheck(conn,likeMemberNo,likedBusinessNo);
		JDBCTemplate.close(conn);
		return like_check;
	}
	//6. 좋아요 취소 
	public int deleteLike(int likeMemberNo, int likedBusinessNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FunderDao().deleteLike(conn,likeMemberNo,likedBusinessNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		} JDBCTemplate.close(conn);
		return result;
	}
	//7. 좋아요
	public int updateLike(int likeMemberNo, int likedBusinessNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FunderDao().updateLike(conn,likeMemberNo,likedBusinessNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		} JDBCTemplate.close(conn);
		return result;
	}
	//8. 펀더 상세보기
	public MakerBoard selectOneFunderView(int writerNo) {
		Connection conn = JDBCTemplate.getConnection();
		MakerBoard mb = new FunderDao().selectOneFunderView(conn,writerNo);
		JDBCTemplate.close(conn);
		return mb;
	}	
	//09. 게시물 검색
	public FunderPageData searchFunder(int reqPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		FunderDao dao = new FunderDao();
		ArrayList<MakerBoard> mblist = dao.selectSearchFunder(conn, start, end, keyword);
		int totalCount = dao.selectTotalCount(conn,keyword);		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize) * pageNaviSize + 1;
		String pageNavi = "<ul class='pageination_funder'>";
		if(pageNo != 1) {
			pageNavi += "<li class='page_item'>";
			pageNavi += "<a class='page_link' href='/funder/searchFunder?reqPage="+(pageNo-1)+"&keyword="+keyword+"'>";
			pageNavi += "&lt;</a></li>";
		}
		for(int i = 0; i < pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page_item active'>";
				pageNavi += "<a class='page_link' href='/funder/searchFunder?reqPage="+pageNo+"&keyword="+keyword+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page_link' href='/funder/searchFunder?reqPage="+pageNo+"&keyword="+keyword+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음버튼	
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page_item'>";
			pageNavi += "<a class='page_link' href='/funder/searchFunder?reqPage="+pageNo+"&keyword="+keyword+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		FunderPageData funderpd = new FunderPageData(mblist,pageNavi,start);
		JDBCTemplate.close(conn);
		return funderpd;
	}

}
