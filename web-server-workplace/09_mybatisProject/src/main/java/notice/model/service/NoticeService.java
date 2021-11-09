package notice.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import notice.model.vo.NoticePageData;

public class NoticeService {

	private SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml";// 마이바티스 설정파일 경로;
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false); // 오토커밋 해제를 위해 매개변수 false 전달;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return session;
	}

	public NoticePageData selectNoticeList(int reqPage) {
		SqlSession session = getSqlSession();
		
		//한페이비에 보여줄 게시물 수
		int numPerPage = 50;
		
		// 1page -> 1 ~ 50 / 2 page -> 51 ~100
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		// 한 페이지에 보여줄 게시물 목록조회
		NoticeDao dao = new NoticeDao();
		ArrayList<Notice> list = dao.selectNoticeList(session, Map.of("start",start,"end",end));
		
		//페이지 네이게이션 제작
		int totalCount = dao.totalCount(session);
		
		//전체 페이지수 계산;
		int totalPage = 0;
		
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		}else {
			totalPage = totalCount /numPerPage+1;			
		}
		
		int pageNaviSize = 5;
		//요청 페이지가 가운데 올 수 있는 pageNo설정
		int pageNo = 1;
		if(reqPage > 3) {
			pageNo =  reqPage - 2;

			if(totalPage - reqPage < (pageNaviSize-1)) {
				pageNo = totalPage - (pageNaviSize-1);
			}
		
		}
		
		//페이지 네비 제작
		String pageNavi = "";
		
		//이전 버튼 생성
		if(pageNo != 1) {
			pageNavi = "<a href='noticeList?reqPage="+(reqPage-1)+"'>[이전]</a>";
		}
		
		
		//페이지 숫자 생성
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<span>"+pageNo+"</span>";
			}
			else {
				pageNavi += "<a href='/noticeList?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
			
		}
		//다음 버튼
		
		if(pageNo <= totalPage) {
			pageNavi += "<a href='/noticeList?reqPage="+(reqPage+1)+"'>다움</a>";
		}
		
		
		session.close();
		
		NoticePageData npd= new NoticePageData(list,pageNavi,start);
		
		return npd;
	}

}
