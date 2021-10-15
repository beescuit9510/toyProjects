<%@page import="org.apache.jasper.tagplugins.jstl.core.When"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글보기</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<fieldset>
			<legend>공지사항(New)</legend>
			<table class="table" id="noticeView" style="">
				<tr class="table-info">
					<th colspan="4">${notice.noticeTitle }</th>
				</tr>
				<tr class="table-light">
					<th>작성자</th>
					<th>${notice.noticeWriter }</th>
					<th>작성일</th>
					<th>${notice.regDate }</th>
				</tr>
				<tr class="table-light">
					<th>첨부파일</th>
					<th>
						<c:if test="${not empty notice}">
							<img src="/img/file.png" width="16px">
							<a href='/fileDown?noticeNo=${notice.noticeNo}'>${notice.filename}</a>
						</c:if>
					</th>
					<th>조회수</th>
					<th>${notice.readCount }</th>
				</tr>
				<tr class="table-light">
					<th>내용</th>
					<%-- 서머노트 덕분에 notice.getNoticeContentBr() 안써도됨 
				<th colspan="3"><%=notice.getNoticeContentBr() %></th>
				--%>
					<th colspan="3">${notice.noticeContent}</th>
				</tr>
				<tr class="table-light">
					<th colspan="4" style="text-align: center;">
						<button class="btn btn-info" onclick="history.go(-1);">이전화면</button>
						<c:if
							test="${not empty sessionScope.member && member.memberId eq notice.noticeWriter }">
							<a href="noticeUpdateFrm?noticeNo=${notice.noticeNo }"
								class="btn btn-info">수정하기</a>
							<a href="noticeDelete?noticeNo=${notice.noticeNo }"
								class="btn btn-info">삭제하기</a>
						</c:if>
					</th>
				</tr>
			</table>
		</fieldset>

		<c:if test="${not empty sessionScope.member }">
			<div class="inputCommentBox">
				<form action="/insertComment" method="post">
					<ul>
						<li><i class="far fa-user fa-5x"></i></li>
						<li><input type="hidden" name="ncLevel" value="1"> <input
							type="hidden" name="ncWriter"
							value="${sessionScope.member.memberId}"> <input
							type="hidden" name="noticeRef" value="${notice.noticeNo}">
							<input type="hidden" name="ncRef" value=0> <textarea
								name="ncContent" class="form-control"></textarea></li>
						<li>
							<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
						</li>
					</ul>
				</form>
			</div>
		</c:if>

		<%-- 댓글 출력 --%>

		<div class="commenBox">
			<c:forEach items="${noticeComments }" var="noticeComment" varStatus="i">
				<c:if test="${noticeComment.ncLevel eq 1}">
					<ul class="comments">
						<li><i class="far fa-user fa-3x"></i>
							<p>${noticeComment.ncWriter}</p>
							<p>${noticeComment.ncDate}</p></li>
						<li>
							<p>${noticeComment.ncContentBr}</p>
							<textarea name="ncContent" class="form-control" style="display: none;">${noticeComment.ncContent}</textarea>
							<p class="commentsBtn">
								<c:if test="${not empty sessionScope.member }">
									<c:if test="${sessionScope.member.memberId eq noticeComment.ncWriter}">
										<a href="javascript:void(0)" onclick="modifyComment(this, '${noticeComment.ncNo}','${notice.noticeNo}')">수정</a>
										<a href="javascript:void(0)" onclick="deleteComment(this, '${noticeComment.ncNo}','${notice.noticeNo}')">삭제</a>
									</c:if>
									<a href="javascript:void(0)" class="recShow">답글달기</a>
								</c:if>
							</p>
							<c:if test="${not empty sessionScope.member }">
								<form action="/insertComment" class="recoment">
									<input type="hidden" name="ncLevel" value="2">
									<input type="hidden" name="ncWriter" value="${sessionScope.member.memberId}">
									<input type="hidden" name="noticeRef" value="${notice.noticeNo}">
									<input type="hidden" name="ncRef" value="${noticeComment.ncNo}">
									<textarea name="ncContent" class="form-control"></textarea>
									<div>
										<button type="submit" class="btn btn-outline-primary">등록</button>
										<button type="reset" class="btn btn-outline-primary recCancel">취소</button>
									</div>
								</form>
							</c:if>
						</li>
					</ul>


					<%-- 대댓글 시작 --%>
					<c:forEach items="${noticeComments }" var="ncc">
						<c:if test="${ncc.ncLevel eq 2 && noticeComment.ncNo == ncc.ncRef}">					
							<ul class="recomments">
								<li><i class="fas fa-angle-double-right fa-3x"></i></li>
								<li>
									<i class="far fa-user fa-3x"></i>
									<p>${ncc.ncWriter}</p>
									<p>${ncc.ncDate}</p>
								</li>
								<li>
									<p>${ncc.ncContentBr}</p> 
									<%-- 텍스트에리아는 사용시 무조건 한줄로 써야함 아니면 본문에 이상생김. --%>
									<textarea name="ncContent" class="form-control" style="display: none;">${ncc.ncContent}</textarea>
									<p class="commentsBtn">
										<c:if test="${not empty sessionScope.member && sessionScope.member.memberId eq ncc.ncWriter}">
											<a href="javascript:void(0)"
												onclick="modifyComment(this, '${ncc.ncNo}','${notice.noticeNo}')">수정</a>
											<a href="javascript:void(0)"
												onclick="deleteComment(this, '${ncc.ncNo}','${notice.noticeNo}')">삭제</a>
										</c:if>
									</p>
								</li>
							</ul>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
	<script>
		$(".recShow").click(function() {
			var idx = $(".recShow").index(this);
			$(this).hide();
			$(".recoment").eq(idx).css("display", "flex");
		})
		$(".recCancel").click(function() {
			var idx = $(".recCancel").index(this);
			$(".recoment").eq(idx).css("display", "none");
			$(".recShow").eq(idx).show();
		})

		function modifyComment(obj, ncNo, noticeNo) {
			//textarea를 화면에 표현
			$(obj).parent().prev().show();
			//기존 본문 내용을 숨김
			$(obj).parent().prev().prev().hide();

			//수정 -> 수정완료
			$(obj).html("수정완료");
			$(obj).attr("onclick",
					"modifyComplete(this,'" + ncNo + "','" + noticeNo + "');");

			//삭제 -> 취소
			$(obj).next().html("취소");
			$(obj).next().attr("onclick",
					"modifyCancel(this,'" + ncNo + "','" + noticeNo + "');");

			//답글달기버튼 숨기기
			$(obj).next().next().hide();
		}

		function modifyCancel(obj, ncNo, noticeNo) {
			//textarea 숨김
			$(obj).parent().prev().hide();
			//기존 본문내용을 화면에 다시 표현
			$(obj).parent().prev().prev().show();

			//수정완료 -> 수정
			$(obj).prev().html("수정");
			$(obj).prev().attr("onclick",
					"modifyComment(this,'" + ncNo + "','" + noticeNo + "');");

			// 취소 -> 삭제
			$(obj).html("삭제")
			$(obj).attr("onclick",
					"deleteComment(this,'" + ncNo + "','" + noticeNo + "');");

			//답글달기버튼 화면에 표현
			$(obj).next().show();
		}

		function modifyComplete(obj, ncNo, noticeNo) {
			//새로운 form태그를 생성
			var form = $("<form action='/updateComment' method='post'></form>");
			//form안에 수정댓글번호 설정
			form.append($("<input type='text' name='ncNo' value='"+ncNo+"'>"));
			//form안에 공지사항번호 설정
			form
					.append($("<input type='text' name='noticeNo' value='"+noticeNo+"'>"));
			//수정한 댓글 내용을 설정
			form.append($(obj).parent().prev());
			//전송할 form태그를 현재 페이지에 추가
			$("body").append(form);
			//form태그 전송
			form.submit();

		}

		function deleteComment(obj, ncNo, noticeNo) {
			if (confirm("댓글을 삭제하시겠습니까?")) {
				location.href = "/deleteComment?ncNo=" + ncNo + "&noticeNo="
						+ noticeNo;
			}

		}
	</script>

</body>
</html>