package mypageMember.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import mypageFunderFunding.model.vo.FundedFunding;
import mypageFunderFunding.model.vo.Funding;
import mypageFunderFunding.model.vo.Like;
import mypageFunderFunding.model.vo.LikedFunder;
import mypageFunderFunding.model.vo.LikedFunding;
import mypageFunderFunding.model.vo.MyOwnProject;
import mypageFunderFunding.model.vo.MyOwnProjectComment;
import mypageFunderFunding.model.vo.MyOwnProjectCustomer;
import table.model.vo.FundingComment;
import table.model.vo.MakerBoard;
import table.model.vo.MakerInfo;
import table.model.vo.Member;
import table.model.vo.PaymentInfo;
import table.model.vo.ProjectBasicInfo;
import table.model.vo.Reward;

public class MypageMemberDao {

	public ArrayList<FundedFunding> selectFundedFunding(Connection conn, int cMemberNo, int start, int end) {
		String query = "select *\r\n"
				+ "from(\r\n"
				+ "select rownum as rnum,\r\n"
				+ "t.* \r\n"
				+ "from (select \r\n"
				+ "(select count(*) from payment_info pi\r\n"
				+ "where pi.project_no = p.project_no) as total,\r\n"
				+ "p.payment_no,p.quantity,p.receive_addr,p.order_date,p.receive_name,p.receive_phone,p.c_member_no,\r\n"
				+ "pbi.*,r.*,m.*\r\n"
				+ "from payment_info p\r\n"
				+ "join project_basic_info pbi\r\n"
				+ "on p.project_no = pbi.project_no\r\n"
				+ "join reward r\r\n"
				+ "on p.project_no = r.reward_no\r\n"
				+ "join maker_info m\r\n"
				+ "on p.project_no = m.maker_info_no\r\n"
				+ "where p.c_member_no = ?\r\n"
				+ "order by order_date desc) t)\r\n"
				+ "where rnum between ? and ?\r\n";

		
///		select *
///		from(
///		select rownum as rnum,
///		t.* 
///		from (select 
///		(select count(*) from payment_info pi
///		where pi.project_no = p.project_no) as total,
///		p.payment_no,p.quantity,p.receive_addr,p.order_date,p.receive_name,p.receive_phone,p.c_member_no,
///		pbi.*,r.*,m.*
///		from payment_info p
///		join project_basic_info pbi
///		on p.project_no = pbi.project_no
///		join reward r
///		on p.project_no = r.reward_no
///		join maker_info m
///		on p.project_no = m.maker_info_no
///		where p.c_member_no = ?
///		order by order_date desc) t)
///		where rnum between ? and ?

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<FundedFunding> fundings = new ArrayList<>();

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
				Reward reward = new Reward();
				MakerInfo makerInfo = new MakerInfo();
				PaymentInfo paymentInfo = new PaymentInfo();
				int total = 0;

				projectBasicInfo.setProjectNo(rset.getInt("project_no"));
				projectBasicInfo.setBusinessNo(rset.getInt("business_no"));
				projectBasicInfo.setProjectTitle(rset.getString("project_title"));
				projectBasicInfo.setTargetPrice(rset.getInt("target_price"));
				projectBasicInfo.setFilepath(rset.getString("filepath"));
				projectBasicInfo.setEndDate(rset.getString("end_date"));
				projectBasicInfo.setProjectStory(rset.getString("project_story"));
				projectBasicInfo.setFundingCategory(rset.getString("funding_category"));
				projectBasicInfo.setStartDate(rset.getString("start_date"));

				reward.setRewardNo(rset.getInt("reward_no"));
				reward.setRewardPrice(rset.getInt("reward_price"));
				reward.setRewardTitle(rset.getString("reward_title"));
				reward.setRewardContent(rset.getString("reward_content"));
				reward.setShippingDate(rset.getString("shipping_date"));
				reward.setCancelPolicy(rset.getString("cancel_policy"));
				reward.setqEmail(rset.getString("q_email"));
				reward.setqPhone(rset.getString("q_phone"));

				makerInfo.setMakerInfoNo(rset.getInt("maker_info_no"));
				makerInfo.setTradeBank(rset.getString("trade_bank"));
				makerInfo.setAccountNumber(rset.getInt("account_number"));
				makerInfo.setDepositName(rset.getString("deposit_name"));

				paymentInfo.setPaymentNo(rset.getLong("payment_no"));
				paymentInfo.setQuantity(rset.getInt("quantity"));
				paymentInfo.setReceiveAddr(rset.getString("receive_addr"));
				paymentInfo.setOrderDate(rset.getString("order_date"));
				paymentInfo.setReceiveName(rset.getString("receive_name"));
				paymentInfo.setReceivePhone(rset.getString("receive_phone"));
				paymentInfo.setProjectNo(rset.getInt("project_no"));
				paymentInfo.setcMemberNo(rset.getInt("c_member_no"));

				total = rset.getInt("total");

				FundedFunding funding = new FundedFunding(total, projectBasicInfo, reward, makerInfo, paymentInfo);

				fundings.add(funding);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return fundings;
	}

	public void selectLikedFunder(Connection conn, int cMemberNo, ArrayList<Like> likeList) {
		String query = "select fl.like_no, mb.*\r\n"
				+ "from member m\r\n"
				+ "join funder_like fl\r\n"
				+ "on m.c_member_no = fl.c_member_no\r\n"
				+ "join member fm\r\n"
				+ "on fl.liked_business_no = fm.c_member_no\r\n"
				+ "join maker_board mb\r\n"
				+ "on fl.liked_business_no = mb.writer_no\r\n"
				+ "where m.c_member_no = ?\r\n"
				+ "order by fl.like_no desc\r\n";

//select fl.like_no, mb.*
//from member m
//join funder_like fl
//on m.c_member_no = fl.c_member_no
//join member fm
//on fl.liked_business_no = fm.c_member_no
//join maker_board mb
//on fl.liked_business_no = mb.writer_no
//where m.c_member_no = ?
//order by fl.like_no desc
		
		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, cMemberNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {

				MakerBoard makerBoard = new MakerBoard();

				makerBoard.setWriterNo(rset.getInt("writer_no"));
				makerBoard.setBusinessName(rset.getString("business_name"));
				makerBoard.setOpenDate(rset.getString("open_date"));
				makerBoard.setBoardEmail(rset.getString("board_email"));
				makerBoard.setSearchTag(rset.getString("search_tag"));
				makerBoard.setSkillName(rset.getString("skill_name"));
				makerBoard.setSkillLevel(rset.getString("skill_level"));
				makerBoard.setCompanyAddr(rset.getString("company_addr"));
				makerBoard.setCompanyIntro(rset.getString("company_intro"));
				makerBoard.setProfileFilepath(rset.getString("profile_filepath"));
				makerBoard.setFunderCategory(rset.getString("funder_category"));

				LikedFunder likedFunder = new LikedFunder(rset.getInt("like_no"), makerBoard);

				likeList.add(likedFunder);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return;
	}

	public void selectLikedFunding(Connection conn, int cMemberNo, ArrayList<Like> likeList) {
		String query = "select rownum as total, fl.like_no, pbi.*,r.*,mi.* \r\n"
				+ "from member m\r\n"
				+ "join funding_like fl\r\n"
				+ "on m.c_member_no = fl.c_member_no\r\n"
				+ "join project_basic_info pbi\r\n"
				+ "on fl.liked_project_no = pbi.project_no\r\n"
				+ "join reward r\r\n"
				+ "on fl.liked_project_no = r.reward_no\r\n"
				+ "join maker_info mi\r\n"
				+ "on fl.liked_project_no = mi.maker_info_no\r\n"
				+ "where m.c_member_no = ?\r\n"
				+ "order by fl.like_no desc\r\n";

//		 		select rownum as total, fl.like_no, pbi.*,r.*,mi.* 
//				from member m
//				join funding_like fl
//				on m.c_member_no = fl.c_member_no
//				join project_basic_info pbi
//				on fl.liked_project_no = pbi.project_no
//				join reward r
//				on fl.liked_project_no = r.reward_no
//				join maker_info mi
//				on fl.liked_project_no = mi.maker_info_no
//				where m.c_member_no = ?
//				order by fl.like_no desc

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				
				ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
				Reward reward = new Reward();
				MakerInfo makerInfo = new MakerInfo();
				int total = 0;

				projectBasicInfo.setProjectNo(rset.getInt("project_no"));
				projectBasicInfo.setBusinessNo(rset.getInt("business_no"));
				projectBasicInfo.setProjectTitle(rset.getString("project_title"));
				projectBasicInfo.setTargetPrice(rset.getInt("target_price"));
				projectBasicInfo.setFilepath(rset.getString("filepath"));
				projectBasicInfo.setEndDate(rset.getString("end_date"));
				projectBasicInfo.setProjectStory(rset.getString("project_story"));
				projectBasicInfo.setFundingCategory(rset.getString("funding_category"));
				projectBasicInfo.setStartDate(rset.getString("start_date"));

				reward.setRewardNo(rset.getInt("reward_no"));
				reward.setRewardPrice(rset.getInt("reward_price"));
				reward.setRewardTitle(rset.getString("reward_title"));
				reward.setRewardContent(rset.getString("reward_content"));
				reward.setShippingDate(rset.getString("shipping_date"));
				reward.setCancelPolicy(rset.getString("cancel_policy"));
				reward.setqEmail(rset.getString("q_email"));
				reward.setqPhone(rset.getString("q_phone"));

				makerInfo.setMakerInfoNo(rset.getInt("maker_info_no"));
				makerInfo.setTradeBank(rset.getString("trade_bank"));
				makerInfo.setAccountNumber(rset.getInt("account_number"));
				makerInfo.setDepositName(rset.getString("deposit_name"));

				total = rset.getInt("total");

				Funding funding = new Funding(total,projectBasicInfo, reward, makerInfo);

				LikedFunding likedFunding = new LikedFunding(rset.getInt("like_no"), funding);
			

				likeList.add(likedFunding);
				
				System.out.println(likedFunding);
				
				

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return;
	}

	public void selectMyOwnProjectFunding(Connection conn, int cMemberNo, ArrayList<MyOwnProject> myOwnProjects, int start, int end) {
		String query = "select t.* from(\r\n"
				+ "select (select count(*) from payment_info where pbi.project_no = project_no ) \r\n"
				+ "as total, \r\n"
				+ "rownum as rnum, pbi.*, r.*, mi.*\r\n"
				+ "from member m\r\n"
				+ "join project_basic_info pbi\r\n"
				+ "on m.c_member_no = pbi.business_no\r\n"
				+ "join reward r\r\n"
				+ "on pbi.project_no = r.reward_no\r\n"
				+ "join maker_info mi\r\n"
				+ "on pbi.project_no = mi.maker_info_no\r\n"
				+ "where m.c_member_no = ?\r\n"
				+ "order by pbi.project_no desc)t\r\n"
				+ "where rnum between ? and ?\r\n";

				//select t.* from(
				//select (select count(*) from payment_info where pbi.project_no = project_no ) 
				//as total, 
				//rownum as rnum, pbi.*, r.*, mi.*
				//from member m
				//join project_basic_info pbi
				//on m.c_member_no = pbi.business_no
				//join reward r
				//on pbi.project_no = r.reward_no
				//join maker_info mi
				//on pbi.project_no = mi.maker_info_no
				//where m.c_member_no = ?
				//order by pbi.project_no desc)t
				//where rnum between ? and ?
		
		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
				Reward reward = new Reward();
				MakerInfo makerInfo = new MakerInfo();
				int total = 0;

				projectBasicInfo.setProjectNo(rset.getInt("project_no"));
				projectBasicInfo.setBusinessNo(rset.getInt("business_no"));
				projectBasicInfo.setProjectTitle(rset.getString("project_title"));
				projectBasicInfo.setTargetPrice(rset.getInt("target_price"));
				projectBasicInfo.setFilepath(rset.getString("filepath"));
				projectBasicInfo.setEndDate(rset.getString("end_date"));
				projectBasicInfo.setProjectStory(rset.getString("project_story"));
				projectBasicInfo.setFundingCategory(rset.getString("funding_category"));
				projectBasicInfo.setStartDate(rset.getString("start_date"));

				reward.setRewardNo(rset.getInt("reward_no"));
				reward.setRewardPrice(rset.getInt("reward_price"));
				reward.setRewardTitle(rset.getString("reward_title"));
				reward.setRewardContent(rset.getString("reward_content"));
				reward.setShippingDate(rset.getString("shipping_date"));
				reward.setCancelPolicy(rset.getString("cancel_policy"));
				reward.setqEmail(rset.getString("q_email"));
				reward.setqPhone(rset.getString("q_phone"));

				makerInfo.setMakerInfoNo(rset.getInt("maker_info_no"));
				makerInfo.setTradeBank(rset.getString("trade_bank"));
				makerInfo.setAccountNumber(rset.getInt("account_number"));
				makerInfo.setDepositName(rset.getString("deposit_name"));

				total = rset.getInt("total");

				Funding funding = new Funding(total, projectBasicInfo, reward, makerInfo);

				MyOwnProject myOwnProject = new MyOwnProject();

				myOwnProject.setFunding(funding);

				myOwnProjects.add(myOwnProject);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return;
	}

	public void selectMyOwnProjectComment(Connection conn, int projectNo, MyOwnProject myOwnProject) {
		String query = "select fc.*, m.*\r\n"
				+ "from project_basic_info pbi\r\n"
				+ "join funding_comment fc\r\n"
				+ "on pbi.project_no = fc.project_ref_no\r\n"
				+ "join member m\r\n"
				+ "on m.c_member_no = fc.comment_writer\r\n"
				+ "where pbi.project_no = ?\r\n"
				+ "order by fc.comment_no desc ";

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();

			ArrayList<MyOwnProjectComment> myOwnProjectComments = new ArrayList<>();
			
			while (rset.next()) {


				FundingComment fundingComment = new FundingComment();

				fundingComment.setCommentNo(rset.getInt("comment_no"));
				fundingComment.setCommentContent(rset.getString("comment_content"));
				fundingComment.setWriteDate(rset.getString("write_date"));
				fundingComment.setCommentLevel(rset.getInt("comment_level"));
				fundingComment.setProjectRefNo(rset.getInt("project_ref_no"));
				fundingComment.setCommentRefNo(rset.getInt("comment_ref_no"));
				fundingComment.setCommentWriter(rset.getInt("comment_writer"));
				
				Member member = new Member();

				member.setcMemberNo(rset.getInt("c_member_no"));
				member.setcName(rset.getString("c_name"));
				member.setcPassword(rset.getString("c_password"));
				member.setcPhone(rset.getString("c_phone"));
				member.setcEnrollDate(rset.getString("c_enroll_date"));
				member.setcEmail(rset.getString("c_email"));
				member.setcLevel(rset.getInt("c_level"));


				MyOwnProjectComment myOwnProjectComment = new MyOwnProjectComment(fundingComment, member);
			
				myOwnProjectComments.add(myOwnProjectComment);
			}

			myOwnProject.setMyOwnProjectComment(myOwnProjectComments);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return;

	}

	public void selectMyOwnProjectPaymentInfo(Connection conn, int projectNo, MyOwnProject myOwnProject) {
		String query = "select pi.*, m.*\r\n" + "from project_basic_info pbi\r\n" + "join payment_info pi\r\n"
				+ "on pbi.project_no = pi.project_no\r\n" + "join member m\r\n"
				+ "on pi.c_member_no = m.c_member_no\r\n" + "where pbi.project_no = ?\r\n"
				+ "order by pi.order_date desc\r\n";

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();

			ArrayList<MyOwnProjectCustomer> myOwnProjectCustomers = new ArrayList<MyOwnProjectCustomer>();

			while (rset.next()) {

				PaymentInfo paymentInfo = new PaymentInfo();

				paymentInfo.setPaymentNo(rset.getLong("payment_no"));
				paymentInfo.setQuantity(rset.getInt("quantity"));
				paymentInfo.setReceiveAddr(rset.getString("receive_addr"));
				paymentInfo.setOrderDate(rset.getString("order_date"));
				paymentInfo.setReceiveName(rset.getString("receive_name"));
				paymentInfo.setReceivePhone(rset.getString("receive_phone"));
				paymentInfo.setProjectNo(rset.getInt("project_no"));
				paymentInfo.setcMemberNo(rset.getInt("c_member_no"));

				Member member = new Member();

				member.setcMemberNo(rset.getInt("c_member_no"));
				member.setcName(rset.getString("c_name"));
				member.setcPassword(rset.getString("c_password"));
				member.setcPhone(rset.getString("c_phone"));
				member.setcEnrollDate(rset.getString("c_enroll_date"));
				member.setcEmail(rset.getString("c_email"));
				member.setcLevel(rset.getInt("c_level"));

				MyOwnProjectCustomer myOwnProjectCustomer = new MyOwnProjectCustomer();

				myOwnProjectCustomer.setMember(member);

				myOwnProjectCustomer.setPaymentInfo(paymentInfo);

				myOwnProjectCustomers.add(myOwnProjectCustomer);
			
			}
			

			myOwnProject.setMyOwnProjectCustomers(myOwnProjectCustomers);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return;

	}

	public int unlikeFunder(Connection conn, int cMemberNo, int likedBusinessNo) {
		String query = "delete from funder_like\r\n" + "where c_member_no = ? and liked_business_no = ?\r\n";

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int r = -1;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			pstmt.setInt(2, likedBusinessNo);

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return r;

	}

	public int unlikeFunding(Connection conn, int cMemberNo, int likedProjectNo) {
		String query = "delete from funding_like\r\n" + "where c_member_no = ? and liked_project_no = ?\r\n";

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int r = -1;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			pstmt.setInt(2, likedProjectNo);

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return r;

	}

	public int replyComment(Connection conn, FundingComment fundingComment) {
		String query = "insert into funding_comment values(FUNDCOMM_SEQ.nextval,?,to_char(sysdate,'yyyy-mm-dd'),2,?,?,?)\r\n";

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int r = -1;

		try {

			pstmt = conn.prepareStatement(query);
			System.out.println(fundingComment.getCommentContent());
			System.out.println(fundingComment.getProjectRefNo());
			System.out.println(fundingComment.getCommentRefNo());
			System.out.println(fundingComment.getCommentWriter());

			
			pstmt.setString(1, fundingComment.getCommentContent());
			pstmt.setInt(2, fundingComment.getProjectRefNo());
			pstmt.setInt(3, fundingComment.getCommentRefNo());
			pstmt.setInt(4, fundingComment.getCommentWriter());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return r;
	}

	public int getTotalFundedFunding(Connection conn, int cMemberNo) {
		String query = "select count(*) as total from payment_info\r\n"
				+ "where c_member_no = ?\r\n";

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int r = 0;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				r = rset.getInt("total");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return r;

	}

	public int getTotalMyOwnProject(Connection conn, int cMemberNo) {
		String query = "select count(*) as total from project_basic_info\r\n"
				+ "where business_no = ?\r\n";

		query.replaceAll("\r\n", " ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int r = 0;

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				r = rset.getInt("total");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return r;

	}

	public int getTotalLikedFunding(Connection conn, int cMemberNo) {
		String query = "select count(*) as total from funding_like\r\n"
				+ "where c_member_no = ?\r\n";
		
		query.replaceAll("\r\n", " ");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int r = 0;
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				r = rset.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return r;
	}

	public int getTotalLikedFunder(Connection conn, int cMemberNo) {
		String query = "select count(*) as total from funder_like\r\n"
				+ "where c_member_no = ?";
		
		query.replaceAll("\r\n", " ");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int r = 0;
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				r = rset.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return r;
	}

	public int updateMember(Connection conn, int cMemberNo, String phone, String pw) {
		
		String query = "update member set c_password = ? , c_phone = ? where c_member_no = ?";
		
		query.replaceAll("\r\n", " ");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int r = 0;
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, phone);
			pstmt.setInt(3, cMemberNo);
			r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return r;

		


	}

	public int doseMemberFund(Connection conn, int cMemberNo) {
		
		String query = "select count(*) as total from payment_info where c_member_no = ?";
		
		query.replaceAll("\r\n", " ");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int r = 0;
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = rset.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return r ;

	}
	public int doseMemberhaveProject(Connection conn, int cMemberNo) {
		
		String query = "select count(*) as total from project_basic_info where business_no = ?";
		
		query.replaceAll("\r\n", " ");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int r = 0;
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = rset.getInt("total");
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return r ;


	}

	public int deleteMember(Connection conn, int cMemberNo) {
		
		String query = "delete from member where c_member_no = ?";
		
		query.replaceAll("\r\n", " ");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int r = 0;
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return r;

	}

	public int doseMemberhaveFunderPost(Connection conn, int cMemberNo) {
		
		String query = "select count(*) as total from maker_board where writer_no = ? \r\n";
		
		query.replaceAll("\r\n", " ");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int r = 0;
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cMemberNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = rset.getInt("total");
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return r ;


	}

}
