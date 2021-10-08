<%@page import="notice.model.vo.NoticeComment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Notice notice = (Notice)request.getAttribute("notice");
    
    	ArrayList<NoticeComment> noticeComments = (ArrayList<NoticeComment>)request.getAttribute("noticeComments");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글보기</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<div class="container">
	<fieldset>
		<legend>공지사항</legend>
		<table class="table" id="noticeView" style="width100%;">
			<tr class="table-info">
				<th colspan="4"><%=notice.getNoticeTitle() %></th>
			</tr>
			<tr class="table-light">
				<th>작성자</th>
				<th><%=notice.getNoticeWriter() %></th>
				<th>작성일</th>
				<th><%=notice.getRegDate() %></th>
			</tr>
			<tr class="table-light">
				<th>첨부파일</th>
				<th>
					<%if(notice.getFilename()!=null){ %>
					<img src="/img/file.png" width="16px">
					<a href='/fileDown?noticeNo=<%=notice.getNoticeNo() %>'><%=notice.getFilename() %></a>
					<%} %>
				</th>
				<th>조회수</th>
				<th><%=notice.getReadCount() %></th>
			</tr>
			<tr class="table-light">
				<th>내용</th>
				<th colspan="3"><%=notice.getNoticeContentBr() %></th>
			</tr>
			<tr class="table-light">
				<th colspan="4" style="text-align:center;">
					<button class="btn btn-info" onclick="history.go(-1);">이전화면</button>
					<% if(member!=null && member.getMemberId().equals(notice.getNoticeWriter())) {%>
					<a href="noticeUpdateFrm?noticeNo=<%=notice.getNoticeNo() %>"  class="btn btn-info" >수정하기</a>
					<a href="noticeDelete?noticeNo=<%=notice.getNoticeNo() %>" class="btn btn-info" >삭제하기</a>
					<%} %>
				</th>
			</tr>
		</table>
	</fieldset>
	
	<%if(member != null){ %>
	<div class="inputCommentBox">
		<form action="/insertComment" method="post">
			<ul>
				<li>
					<i class="far fa-user fa-5x"></i>
				</li>
				<li>
					<input type="hidden" name="ncLevel" value="1">
					<input type="hidden" name="ncWriter" value=<%=member.getMemberId() %>>
					<input type="hidden" name="noticeRef" value=<%=notice.getNoticeNo() %>>
					<input type="hidden" name="ncRef" value=0>
					<textarea name="ncContent" class="form-control"></textarea>
				</li>
				<li>
					<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
				</li>
			</ul>
		</form>
	</div>	
	<%} %>
	
	<%-- 댓글 출력 --%>
	
	<div class="commenBox">
		<%for(NoticeComment noticeComment : noticeComments) {%>
			<%if(noticeComment.getNcLevel()==1) {%>
				<ul class="comments">
					<li>
						<i class="far fa-user fa-3x"></i>
						<p><%=noticeComment.getNcWriter() %></p>
						<p><%=noticeComment.getNcDate() %></p>
					</li>
					<li>
						<p><%=noticeComment.getNcContentBr() %></p>
						<textarea name="ncContent" class="form-control" style="display:none;"><%=noticeComment.getNcContent() %></textarea>
						<p class="commentsBtn">
						<%if(member != null) {%>
							<%if(member.getMemberId().equals(noticeComment.getNcWriter())){ %>
								<a href="javascript:void(0)" onclick="modifyComment(this, '<%=noticeComment.getNcNo()%>','<%=notice.getNoticeNo() %>')">수정</a>
								<a href="javascript:void(0)" onclick="deleteComment(this, '<%=noticeComment.getNcNo()%>','<%=notice.getNoticeNo() %>')">삭제</a>
							<%} //로그인한 회원과 출력되는 댓글 작성자가 같은지 체크 %>
							<a href="javascript:void(0)" class="recShow">답글달기</a>
						<%} //댓글 수정,삭제,대댓글달기,버튼용 로그인 체크%>
						</p>
						
						<%if (member != null){ %>
						<form action="/insertComment" class="recoment">
							<input type="hidden" name="ncLevel" value="2">
							<input type="hidden" name="ncWriter" value="<%=member.getMemberId() %>">
							<input type="hidden" name="noticeRef" value="<%=notice.getNoticeNo() %>">
							<input type="hidden" name="ncRef" value=<%=noticeComment.getNcNo()%>>
							<textarea name="ncContent" class="form-control"></textarea>
							<div>
								<button type="submit" class="btn btn-outline-primary">등록</button>
								<button type="reset" class="btn btn-outline-primary recCancel">취소</button>
							</div>
						</form>
						<%} %>
						
					</li>
				</ul>
				<%-- 대댓글 시작 --%>
				<%for(NoticeComment ncc : noticeComments){ %>
					<%if(ncc.getNcLevel()==2 && noticeComment.getNcNo() == ncc.getNcRef()) {%>
						<ul class="recomments">
							<li>
								<i class="fas fa-angle-double-right fa-3x"></i>
							</li>
							<li>
								<i class="far fa-user fa-3x"></i>
								<p><%=ncc.getNcWriter() %></p>
								<p><%=ncc.getNcDate() %></p>
							</li>
							<li>
								<p><%=ncc.getNcContentBr() %></p>
								<%-- 테스츠에리아는 사용시 무조건 한줄로 써야함 아니면 본문에 이상생김. --%>
								<textarea name="ncContent" class="form-control" style="display:none;"><%=ncc.getNcContent() %></textarea>
								<p class="commentsBtn">
									<%if(member != null && member.getMemberId().equals(ncc.getNcWriter())){ %>
									<a href="javascript:void(0)" onclick="modifyComment(this, '<%=ncc.getNcNo()%>','<%=notice.getNoticeNo() %>')">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this, '<%=ncc.getNcNo()%>','<%=notice.getNoticeNo() %>')">삭제</a>
									<% }%>									
								</p>
							</li>
						</ul>
					<%} %>
				<%} //대댓글 반복문 끝%>


			<%} //일반 댓글 if문%>
		<%} //일반 댓글 반복문%>
	</div>
	
</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
<script>
	$(".recShow").click(function () {
		var idx = $(".recShow").index(this);
		$(this).hide();
		$(".recoment").eq(idx).css("display","flex");
	})
	$(".recCancel").click(function(){
		var idx = $(".recCancel").index(this);
		$(".recoment").eq(idx).css("display","none");
		$(".recShow").eq(idx).show();
	})
	
	function modifyComment(obj,ncNo,noticeNo){
		//textarea를 화면에 표현
		$(obj).parent().prev().show();
		//기존 본문 내용을 숨김
		$(obj).parent().prev().prev().hide();
		
		//수정 -> 수정완료
		$(obj).html("수정완료");
		$(obj).attr("onclick", "modifyComplete(this,'"+ncNo+"','"+noticeNo+"');");
		
		//삭제 -> 취소
		$(obj).next().html("취소");
		$(obj).next().attr("onclick","modifyCancel(this,'"+ncNo+"','"+noticeNo+"');");
		
		//답글달기버튼 숨기기
		$(obj).next().next().hide();
	}
	
	function modifyCancel(obj,ncNo,noticeNo){
		//textarea 숨김
		$(obj).parent().prev().hide();
		//기존 본문내용을 화면에 다시 표현
		$(obj).parent().prev().prev().show();
		
		//수정완료 -> 수정
		$(obj).prev().html("수정");
		$(obj).prev().attr("onclick","modifyComment(this,'"+ncNo+"','"+noticeNo+"');");
		
		// 취소 -> 삭제
		$(obj).html("삭제")
		$(obj).attr("onclick","deleteComment(this,'"+ncNo+"','"+noticeNo+"');");
		
		//답글달기버튼 화면에 표현
		$(obj).next().show();
	}

	function modifyComplete(obj,ncNo,noticeNo){
		alert("수정완료!");
	}
	
	function deleteComment(obj,ncNo,noticeNo) {
		if(confirm("댓글을 삭제하시겠습니까?")){
			location.html="/deleteComment?ncNo="+ncNo+"&noticeNo="+noticeNo;
		}
		alert("삭제완료!");
		
	}
	
</script>

</body>
</html>