package adminPage.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import adminPage.model.dao.AdminPageDao;
import adminPage.model.vo.AdminPageFunderData;
import adminPage.model.vo.AdminPageFundingData;
import adminPage.model.vo.AdminPageFundingRewardPayment;
import adminPage.model.vo.AdminPageMemberData;
import common.JDBCTemplate;
import table.model.vo.MakerBoard;
import table.model.vo.Member;
import table.model.vo.ProjectBasicInfo;

public class AdminPageService {
	
	
	
	public AdminPageMemberData selectAdminPageMemberList(int reqPage, String type) {
		Connection conn = JDBCTemplate.getConnection();
		/*
		 페이징 처리를 위해서 지정해야 할 항목
		 1. 한 페이지당 게시물을 몇 개 보여줄 지 -> 5개
		 */
		int totalCount = 0;
		//한 페이지당 게시물 수
		int numPerPage = 10;
		//reqPage = 1 start = 1/ end = 10, reqPage = 2 start = 11 / end = 20
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		AdminPageDao dao = new AdminPageDao();
		ArrayList<Member> list = new ArrayList<Member>();
		if(type.equals("1")) {
			list = dao.selectAdminPageMemberList(conn, start, end);
			totalCount = dao.selectTotalCount(conn);
		}else if(type.equals("2")){
			list = dao.selectAdminPageFundingMemberList(conn, start, end);
			totalCount = dao.selectFundingTotalCount(conn);
		}else if(type.equals("3")) {
			list = dao.selectAdminPageFunderMemberList(conn, start, end);
			totalCount = dao.selectFunderTotalCount(conn);
		}
		
		
		//페이지 네비게이션을 제작
		//DB조회해야하는 값 -> 전체 게시물의 수
		
		//전체 페이지 수를 계산
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		/*
		 지정해야 할 항목
		 2. 페이지 네비의 길이(네비게이션 숫자 최대 개수)
		 */
		int pageNaviSize = 5;
		//1~5 페이지 요청 시(reqPage 값) -> 네비게이션 1 2 3 4 5
		//6~10 페이지 요청 시 -> 네비게이션 6 7 8 9 10
		//11~15 페이지 요청 시 -> 네비게이션 11 12 13 14 15
		//페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		//페이지네비 태그 제작 시작
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/adminPageMemberList?reqPage="+(pageNo-1)+"&type="+type+"'>";
			pageNavi += "&lt;</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/adminPageMemberList?reqPage="+pageNo+"&type="+type+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/adminPageMemberList?reqPage="+pageNo+"&type="+type+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/adminPageMemberList?reqPage="+pageNo+"&type="+type+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		//게시물목록(ArrayList), 페이지네비(String), start(번호 표시용)
		AdminPageMemberData amd = new AdminPageMemberData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return amd;
	}
	
	
	public int changeLevel(int memberNo, int memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AdminPageDao().changeLevel(conn, memberNo, memberLevel);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public boolean chkChangeLevel(String num, String level) {
		Connection conn = JDBCTemplate.getConnection();
		//num = 1/2/21	level = 1/2/3	-> 3명 회원등급 변경
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		StringTokenizer sT2 = new StringTokenizer(level,"/");
		AdminPageDao dao = new AdminPageDao();
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int memberNo = Integer.parseInt(sT1.nextToken());	//sT1 다음 토큰을 1개 꺼내와 정수로 변환
			int memberLevel = Integer.parseInt(sT2.nextToken());	//sT2 다음 토큰을 1개 꺼내와 정수로 변환
			int result1 = dao.changeLevel(conn, memberNo, memberLevel);
			if(result1 == 0) {
				result = false;
				break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public AdminPageMemberData searchMember(int reqPage, String type, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int numPerPage = 10;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		AdminPageDao dao = new AdminPageDao();
		ArrayList<Member> list = dao.selectSearchMember(conn,start,end,type,keyword);
		
		int totalCount = dao.selectTotalCount(conn, type, keyword);
				//전체 페이지 수를 계산
				int totalPage = 0;
				if(totalCount%numPerPage == 0) {
					totalPage = totalCount/numPerPage;
				} else {
					totalPage = totalCount/numPerPage+1;
				}
				/*
				 지정해야 할 항목
				 2. 페이지 네비의 길이(네비게이션 숫자 최대 개수)
				 */
				int pageNaviSize = 5;
				//1~5 페이지 요청 시(reqPage 값) -> 네비게이션 1 2 3 4 5
				//6~10 페이지 요청 시 -> 네비게이션 6 7 8 9 10
				//11~15 페이지 요청 시 -> 네비게이션 11 12 13 14 15
				//페이지 네비게이션 시작번호 계산
				int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
				//페이지네비 태그 제작 시작
				String pageNavi = "<ul class='pagination'>";
				//이전버튼
				if(pageNo != 1) {
					pageNavi += "<li class='page-item'>";
					pageNavi += "<a class='page-link' href='/searchMember?reqPage=" + (pageNo-1) + "&type=" + type + "&keyword=" + keyword + "'>";
					pageNavi += "&lt;</a></li>";
				}
				//페이지숫자
				for(int i=0; i<pageNaviSize; i++) {
					if(pageNo == reqPage) {
						pageNavi += "<li class='page-item active'>";
						pageNavi += "<a class='page-link' href='/searchMember?reqPage=" + pageNo + "&type=" + type + "&keyword=" + keyword + "'>";
						pageNavi += pageNo+"</a></li>";
					}else {
						pageNavi += "<li class='page-item'>";
						pageNavi += "<a class='page-link' href='/searchMember?reqPage=" + pageNo + "&type=" + type + "&keyword=" + keyword + "'>";
						pageNavi += pageNo+"</a></li>";
					}
					pageNo++;
					if(pageNo>totalPage) {
						break;
					}
				}
				//다음버튼
				if(pageNo <= totalPage) {
					pageNavi += "<li class='page-item'>";
					pageNavi += "<a class='page-link' href='/searchMember?reqPage=" + pageNo + "&type=" + type + "&keyword=" + keyword + "'>";
					pageNavi += "&gt;</a></li>";
				}
				pageNavi += "</ul>";
				
				//게시물목록(ArrayList), 페이지네비(String), start(번호 표시용)
				AdminPageMemberData amd = new AdminPageMemberData(list, pageNavi, start);
				JDBCTemplate.close(conn);
				return amd;
	}

	public AdminPageFundingData selectAdminPageFundingList(int reqPage) {	//관리자페이지 펀딩목록, 페이징을 불러오기 위한 서비스
		Connection conn = JDBCTemplate.getConnection();
		/*
		 페이징 처리를 위해서 지정해야 할 항목
		 1. 한 페이지당 게시물을 몇 개 보여줄 지 -> 5개
		 */
		//한 페이지당 게시물 수
		int numPerPage = 6;
		//reqPage = 1 start = 1/ end = 10, reqPage = 2 start = 11 / end = 20
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		AdminPageDao dao = new AdminPageDao();
		ArrayList<AdminPageFundingRewardPayment> list = dao.selectAdminPageFundingList(conn, start, end);
		
		for(int i = 0; i<list.size(); i++) {
			int totalPrice = list.get(i).getTotal() * list.get(i).getRewardPrice();
			list.get(i).setTotal(totalPrice);
			double root = (double)totalPrice / list.get(i).getTargetPrice();
			double percent = root*100;
			System.out.println(percent);
			percent = Math.round(percent*100)/100.0;
			list.get(i).setPercent(percent);
		}
		
		//페이지 네비게이션을 제작
		//DB조회해야하는 값 -> 전체 게시물의 수
		int totalCount = dao.selectFundingViewTotalCount(conn);
		//전체 페이지 수를 계산
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		/*
		 지정해야 할 항목
		 2. 페이지 네비의 길이(네비게이션 숫자 최대 개수)
		 */
		int pageNaviSize = 5;
		//1~5 페이지 요청 시(reqPage 값) -> 네비게이션 1 2 3 4 5
		//6~10 페이지 요청 시 -> 네비게이션 6 7 8 9 10
		//11~15 페이지 요청 시 -> 네비게이션 11 12 13 14 15
		//페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		//페이지네비 태그 제작 시작
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/adminPageFundingList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/adminPageFundingList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/adminPageFundingList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/adminPageFundingList?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		//게시물목록(ArrayList), 페이지네비(String), start(번호 표시용)
		AdminPageFundingData afd = new AdminPageFundingData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		
		
		return afd;
	}
	
	public AdminPageFunderData selectAdminPageFunderList(int reqPage) {	//관리자페이지 펀더목록, 페이징을 불러오기 위한 서비스
		Connection conn = JDBCTemplate.getConnection();
		/*
		 페이징 처리를 위해서 지정해야 할 항목
		 1. 한 페이지당 게시물을 몇 개 보여줄 지 -> 5개
		 */
		//한 페이지당 게시물 수
		int numPerPage = 6;
		//reqPage = 1 start = 1/ end = 10, reqPage = 2 start = 11 / end = 20
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		AdminPageDao dao = new AdminPageDao();
		ArrayList<MakerBoard> list = dao.selectAdminPageFunderList(conn, start, end);
		//페이지 네비게이션을 제작
		//DB조회해야하는 값 -> 전체 게시물의 수
		int totalCount = dao.selectFunderViewTotalCount(conn);
		//전체 페이지 수를 계산
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		/*
		 지정해야 할 항목
		 2. 페이지 네비의 길이(네비게이션 숫자 최대 개수)
		 */
		int pageNaviSize = 5;
		//1~5 페이지 요청 시(reqPage 값) -> 네비게이션 1 2 3 4 5
		//6~10 페이지 요청 시 -> 네비게이션 6 7 8 9 10
		//11~15 페이지 요청 시 -> 네비게이션 11 12 13 14 15
		//페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		//페이지네비 태그 제작 시작
		String pageNavi = "<ul class='pagination pagination-lg'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/adminPageFunderList?reqPage="+(pageNo-1)+"'>";
			pageNavi += "&lt;</a></li>";
		}
		//페이지숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/adminPageFunderList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/adminPageFunderList?reqPage="+pageNo+"'>";
				pageNavi += pageNo+"</a></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/adminPageFunderList?reqPage="+pageNo+"'>";
			pageNavi += "&gt;</a></li>";
		}
		pageNavi += "</ul>";
		
		//게시물목록(ArrayList), 페이지네비(String), start(번호 표시용)
		AdminPageFunderData afd = new AdminPageFunderData(list, pageNavi, start);
		JDBCTemplate.close(conn);
		return afd;
	}
	
}
