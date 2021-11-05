<%@page import="table.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
fieldset{
	border: none;
	overflow: hidden;
}
.memberList-table-all, .memberList-table-funding, .memberList-table-funder{
	width: 1000px;
	float:right;
	/* display: none; */
}
.table{
	text-align: center;
	margin-top: 30px;
	border-spacing: 0;
}
.table-primary, .table-light{
	height: 50px;
}
.table-primary>th{
	color: gray;
	border-bottom: 1px solid black;
}
.table-light>td{
	border-bottom: 1px solid black;
	height: 60px;
}

.grade-control{
	width: 90px;
	height: 35px;
	padding-left: 10px;
}
#pageNavi{
    margin-bottom: 30px;
	text-align: center;
	width: 1000px;
	float: right;
	
}
.pagination{
	width:210px;
	overflow: hidden;
	text-align: center;
	margin: 0 auto;
}
.page-item{
	width: 30px;
	float: left;
}
.memberList-sidebar{
	display: inline-block;
	margin-top: 50px;
}
.memberList-sidebar>ul>li{
	width: 150px;
	height:50px;
	font-weight: 900;
	line-height: 50px;
	padding-left: 10px;
	border-bottom: 1px solid gray;

}
.chkChangeLevel{
	margin-top: 15px;
	margin-bottom: 40px;
}
.searchBox{
	display: inline-block;
	width: 1000px;
	float: right;
}
.searchBox{
	width: 800px;
	margin: 0 auto;
}
select{
	padding-left: 7px;
    width: 100px;
    height: 36px;
}
.form-control{
	width: 300px;
    height: 36px;
    padding-left: 10px;
    outline: none;
}
.search-btn{
    width: 60px;
    height: 36px;
    color: white;
    background-color: #00B9CE;
    font-weight: 900;
    border: none;
    cursor: pointer;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<fieldset>
			<legend><h1>회원관리</h1></legend>
			<div class="memberList-sidebar">
				<ul>
					<li>
						<a href="/adminPageMemberList?reqPage=1&type=1" name="type">전체 사용자</a>
					</li>
					<li>
						<a href="/adminPageMemberList?reqPage=1&type=2" name="type">펀딩 회원</a>
					</li>
					<li>
						<a href="/adminPageMemberList?reqPage=1&type=3" name="type">펀더 회원</a>
					</li>
				</ul>
			</div>
			 
			 
			 
			<div class="memberList-table-all">
				<table class="table" style="width:100%;">
					<tr class="table-primary">
						<th>선택</th>
						<th>회원번호</th>
						<th>이메일</th>
						<th>이름</th>
						<th>가입일</th>
						<th>회원등급</th>
						<th>등급변경</th>
					</tr>
					<%for(Member m : list){ %>		<!--member -> m으로 변경  -->
						<tr class="table-light">
							<td><input type="checkbox" class="chk"></td>
							<td><%=m.getcMemberNo() %></td>
							<td><%=m.getcEmail() %></td>
							<td><%=m.getcName() %></td>
							<td><%=m.getcEnrollDate() %></td>
							<td>
							<select class="grade-control" style=:width:80%;>
							<%if(m.getcLevel() == 1){ %>
								<option value="1" selected>관리자</option>
								<option value="2">펀딩회원</option>
								<option value="3">펀더회원</option>
							<%} else if(m.getcLevel() == 2){ %>
								<option value="1">관리자</option>
								<option value="2" selected>펀딩회원</option>
								<option value="3">펀더회원</option>
							<%} else if(m.getcLevel() == 3){%>
								<option value="1">관리자</option>
								<option value="2">펀딩회원</option>
								<option value="3" selected>펀더회원</option>
							<%} %>
							</select>
							</td>
							<td>
								<button class="btn changeLevel" style="width:100px; height:40px; font-size:16px; line-height:40px;">등급변경</button>
							</td>
						</tr>
					<%} %>
					<tr>
						<th colspan="7">
							<button class="btn chkChangeLevel" style="width:1000px;">선택회원등급변경</button>
						</th>
					</tr>
				</table>
				<div id="pageNavi" >${pageNavi }</div>
			</div>
		 
		 <div class="searchBox">
					<form action="/searchMember" method="post">
					<!-- <input type="hidden" name="reqPage" value="1"> -->
					<c:choose>
						<c:when test="${not empty type }">
							<select name="type">
							<c:choose>
								<c:when test='${type eq "title" }'>
									<option value="email" selected>이메일계정</option>
									<option value="memberNo">회원번호</option>
								</c:when>
								<c:otherwise>
									<option value="email">이메일계정</option>
									<option value="memberNo" selected>회원번호</option>
								</c:otherwise>
							</c:choose>
							</select>
						</c:when>
						<c:otherwise>
							<select name="type">
								<option value="email">이메일계정</option>
								<option value="memberNo">회원번호</option>
							</select>
						</c:otherwise>
					</c:choose>
					<input type="text" name="keyword" class="form-control" autocomplete="off" value="${keyword }"><button type="submit" class="search-btn">검색</button>
					</form>
				</div>
		 
		</fieldset>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
		$(".changeLevel").click(function(){
			var memberLevel = $(this).parent().prev().children().val();
			var memberNo = $(this).parent().parent().children().eq(1).html();
			location.href="/changeLevel?memberNo="+memberNo+"&memberLevel="+memberLevel;
		});
		$(".chkChangeLevel").click(function(){
			var inputs = $(".chk:checked");
			var num = new Array();		//회원번호 저장할 배열
			var level = new Array();	//변경할 등급을 저장할 배열
			inputs.each(function(idx,item){
				var memberNo = $(item).parent().next().html();
				num.push(memberNo);
				var memberLevel = $(item).parent().parent().find("select").val();
				level.push(memberLevel);
			});
			console.log(num);
			console.log(level);
			location.href="/chkChangeLevel?num="+num.join("/")+"&level="+level.join("/");
		});
		
		 $(function(){
            var type = '${type}';
            $(".memberList-sidebar").find("a").eq((type-1)).css("color","#00B9CE");  
        }); 
		 
	</script>

</body>
</html>