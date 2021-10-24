package mypageMember.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypageFunderFunding.model.vo.FundedFunding;
import mypageFunderFunding.model.vo.Like;
import mypageFunderFunding.model.vo.MyOwnProject;
import mypageMember.model.dao.MypageMemberDao;
import table.model.vo.FundingComment;

public class MypageMemberService {

	public static void main(String[] args) {
//		ArrayList<Like> like = selectLikeList(5);

//		Collections.sort(like);
////	    Collections.sort(like, (like1, like2) -> like2.getLikeNo()-like1.getLikeNo());
//		for(Like likeM : like) {
//			
//			System.out.println(likeM.getLikeNo());
//			
//			if(likeM instanceof LikedFunder) {
//				LikedFunder obj = (LikedFunder)likeM;
//				System.out.println(obj.getMakerboard());
//			}
//
//			if(likeM instanceof LikedFunding) {
//				
//				LikedFunding obj = (LikedFunding)likeM;
//				System.out.println(obj.getFunding());
//			}
//		}

//		ArrayList<FundedFunding> fundedFundings = selectFundedFunding(5);
//		
//		for(FundedFunding fundedFunding : fundedFundings) {
//			System.out.println(fundedFunding);
//			
//		}

//		ArrayList<MyOwnProject> myOwnProjects = selectMyOwnProject(3);
//		
//		int i = 0;
//		int j = 0;
//		for(MyOwnProject myOwnProject : myOwnProjects) {
//			System.out.println();
//			System.out.println();
//			System.out.println();
//
//			System.out.println(++i+"번째 프로젝트");
//			System.out.println(myOwnProject.getFunding().toString());
//
//			System.out.println(i+"번째 프로젝트의 댓글");
//			j = 0;
//			for(FundingComment fc : myOwnProject.getComments()) {
//				System.out.println(++j+"번째 댓글!");
//				System.out.println(fc);
//			}
//
//			System.out.println(i+"번째 프로젝트의 주문목록");
//			j = 0;
//			for(MyOwnProjectCustomer mc : myOwnProject.getMyOwnProjectCustomers()) {
//				System.out.println(++j+"번째 주문목록!");
//				System.out.println(mc.getPaymentInfo());
//				System.out.println(mc.getMember());
//			}
//			
//		}

//		int r =unLikeFunder(5,3);
//		System.out.println(r);
//
//		int r2 =unLikeFunding(5,6);
//		System.out.println(r2);

//		FundingComment fc = new FundingComment();
//		fc.setCommentContent("댓글댓글내용내용3333");
//		fc.setProjectRefNo(1);
//		fc.setCommentRefNo(4);
//		fc.setCommentWriter(3);
//		int r3 = replyComment(fc);
//		System.out.println(r3);
	}

	public ArrayList<FundedFunding> selectFundedFunding(int cMemberNo, int start, int end) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<FundedFunding> fundings = new MypageMemberDao().selectFundedFunding(conn, cMemberNo, start, end);

		JDBCTemplate.close(conn);

		fundings = fundings.size() <= 0 ? null : fundings;

		return fundings;
	}

	public ArrayList<Like> selectLikeList(int cMemberNo) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Like> likeList = new ArrayList<>();

		new MypageMemberDao().selectLikedFunding(conn, cMemberNo, likeList);

		new MypageMemberDao().selectLikedFunder(conn, cMemberNo, likeList);

		JDBCTemplate.close(conn);

		likeList = likeList.size() <= 0 ? null : likeList;

		return likeList;
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

}
