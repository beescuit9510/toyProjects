<%@page import="java.util.ArrayList"%>
<%@page import="java.util.stream.Stream"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>운영자 페이지</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<fieldset>
			<legend>전체회원정보</legend>
			<table class="table-hover" style="width:100%;">
				<tr class="table-primary">
					<th>선택</th>
					<th>회원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>가입일</th>
					<th>회원 등급</th>
					<th>등급 변경</th>
				</tr>
				<% for(Member m : members){ %>
				<tr class="table-light">
					<td><input type="checkbox" class="chk"></td>
				<%=m.getTd() %>	
				<%--<%=Stream.of(members).forEach(System.out::println)--%>				
					<td>
						<select class="form-control" style="width:80;display:line-block;">
							<%if(m.getMemberLevel()==1) {%>
							<option value="1" selected>관리자</option>
							<option value="2">정회원</option>
							<option value="3">준회원</option>
							<%} else if(m.getMemberLevel()==2) {%>
							<option value="1">관리자</option>
							<option value="2" selected>정회원</option>
							<option value="3">준회원</option>
							<%} else if(m.getMemberLevel()==3) {%>
							<option value="1">관리자</option>
							<option value="2">정회원</option>
							<option value="3" selected>준회원</option>
							<%} %>
						</select>
					</td>
					<td>
						<button class="btn btn-outline-warning btn-sm changeLevel">등급변경</button>
					</td>
				<%} %>
				</tr>
				<tr>
					<th colspan="9">
						<button class="btn btn-warning btn-block chkChangeLevel">선택 회원 등급변경</button>
					</th>
				</tr>
			</table>
		</fieldset>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
	 	$(".changeLevel").click(function() {
			var memberLevel = $(this).parent().prev().children().val();
			console.log(memberLevel);
			var memberNo = $(this).parent().parent().children().eq(1).html();
			console.log(memberNo);
			
			location.href="/changeLevel?memberNo="+memberNo+"&memberLevel="+memberLevel;

	 	});
	 	
	 	$(".chkChangeLevel").click(function () {
			var inputs = $(".chk:checked");
			var num = [];
			var level = [];
			inputs.each(function(idx,e){
				var memberNo = $(e).parent().next().html();
				num.push(memberNo);
				var memberLevel = $(e).parent().parent().find("select").val();
				level.push(memberLevel);
			})
			console.log(num);
			console.log(level);
			
			
			location.href = "/chkChangeLevel?num="+num.join("/")+"&level="+level.join("/");
			
			
			
	 	})
		
	</script>
</body>
</html>