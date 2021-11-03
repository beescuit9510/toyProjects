package mypageMember.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;

import common.JDBCTemplate;
import mypageFunderFunding.model.vo.FundedFunding;
import mypageFunderFunding.model.vo.Like;
import mypageFunderFunding.model.vo.MyOwnProject;
import mypageMember.model.dao.MypageMemberDao;
import table.model.vo.FundingComment;

public class MypageMemberService {


	public ArrayList<FundedFunding> selectFundedFunding(int cMemberNo, int start, int end) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<FundedFunding> fundings = new MypageMemberDao().selectFundedFunding(conn, cMemberNo, start, end);

		JDBCTemplate.close(conn);

		fundings = fundings.size() <= 0 ? null : fundings;

		return fundings;
	}

	public ArrayList<Like> selectLikeList(int cMemberNo, int start, int end) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Like> likeList = new ArrayList<>();

		new MypageMemberDao().selectLikedFunding(conn, cMemberNo, likeList);

		new MypageMemberDao().selectLikedFunder(conn, cMemberNo, likeList);

		JDBCTemplate.close(conn);

		System.out.println(likeList);
		
		likeList = likeList.size() <= 0 ? null : likeList;
		
		ArrayList<Like> likeListToReturn = new ArrayList<Like>();
		

		if(likeList != null) {
			System.out.println(likeListToReturn);
			Collections.sort(likeList);
			
			int endI = likeList.size()>end? end:likeList.size();
				
			for(int i=start-1;i<endI;i++) {
				likeListToReturn.add(likeList.get(i));
			}
		}
		
		
		


		return likeListToReturn;
	}

	public ArrayList<MyOwnProject> selectMyOwnProject(int cMemberNo, int start, int end) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<MyOwnProject> myOwnProjects = new ArrayList<MyOwnProject>();

		new MypageMemberDao().selectMyOwnProjectFunding(conn, cMemberNo, myOwnProjects, start, end);
		
		
		if (myOwnProjects.size() <= 0) {
			return null;
		}

		for (MyOwnProject myOwnProject : myOwnProjects) {

			int projectNo = myOwnProject.getFunding().getProjectBasicInfo().getProjectNo();

			new MypageMemberDao().selectMyOwnProjectComment(conn, projectNo, myOwnProject);

			// 아직 댓글 자기자신까지 포함해서 가져옴.(대댓 달린것도 가져온다)

			new MypageMemberDao().selectMyOwnProjectPaymentInfo(conn, projectNo, myOwnProject);

			// 회원정보까지.
		}

		JDBCTemplate.close(conn);
		
		myOwnProjects = myOwnProjects.size() <= 0 ? null : myOwnProjects;

		return myOwnProjects;
	}

	public int unLikeFunder(int cMemberNo, int likedBusinessNo) {
		Connection conn = JDBCTemplate.getConnection();

		int r = new MypageMemberDao().unlikeFunder(conn, cMemberNo, likedBusinessNo);

		if (r > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return r;
	}

	public int unLikeFunding(int cMemberNo, int likedProjectNo) {
		Connection conn = JDBCTemplate.getConnection();

		int r = new MypageMemberDao().unlikeFunding(conn, cMemberNo, likedProjectNo);

		if (r > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return r;
	}

	public int replyComment(FundingComment fundingComment) {
		Connection conn = JDBCTemplate.getConnection();

		int r = new MypageMemberDao().replyComment(conn, fundingComment);

		if (r > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return r;
	}

	public int getTotalFundedFunding(int cMemberNo) {
		Connection conn = JDBCTemplate.getConnection();

		int total = new MypageMemberDao().getTotalFundedFunding(conn, cMemberNo);

		JDBCTemplate.close(conn);

		return total;
	}

	public int getTotalMyOwnProjcet(int cMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int total = new MypageMemberDao().getTotalMyOwnProject(conn, cMemberNo);
		
		JDBCTemplate.close(conn);
		
		return total;
	}

	public int getTotalLikeList(int cMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int totalFunding = new MypageMemberDao().getTotalLikedFunding(conn, cMemberNo);
		int totalFunder = new MypageMemberDao().getTotalLikedFunder(conn, cMemberNo);
		
		
		int total = totalFunding+totalFunder;

		System.out.println("totalFunder: "+totalFunder);
		System.out.println("totalFunding: "+totalFunding);
		JDBCTemplate.close(conn);
		
		return total;
	}

	public int updateMember(int cMemberNo, String phone, String pw) {
		Connection conn = JDBCTemplate.getConnection();

		int r = new MypageMemberDao().updateMember(conn, cMemberNo, phone, pw);

		if (r > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return r;
	}

	public int deleteMember(int cMemberNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int r = -1;
		MypageMemberDao dao = new MypageMemberDao();
		
		if(dao.doseMemberFund(conn, cMemberNo)<=0&&dao.doseMemberhaveProject(conn, cMemberNo)<=0&&dao.doseMemberhaveFunderPost(conn, cMemberNo)<=0) {
			r = dao.deleteMember(conn, cMemberNo);			
		}
		
		
		if (r > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return r;
	}

}
