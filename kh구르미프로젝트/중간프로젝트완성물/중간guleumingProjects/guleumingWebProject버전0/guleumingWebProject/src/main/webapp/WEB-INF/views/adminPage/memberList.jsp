<%@page import="table.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	display: flex;
    width: 420px;
    margin: 40px auto 20px auto;
    justify-content: center;
}
.page-item{
	width:33px;
	height:33px;
	/* line-height:30px; */
	padding:0px;
	text-align:center;
	margin: auto 5px;
	float: left;
	border: 3px solid transparent;
}
.pagination > .active{
	border: 3px solid #00B9CE;
    border-radius: 100%;
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
em{
	text-align: center;
	font-size: 45px;
	margin-bottom: 50px;
	color: #00B9CE;
	font-family: "logo";
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<fieldset>
			<legend><em>????????????</em></legend>
			<%-- <c:choose>
				<c:when test="${type eq 1 }">
					<span>?????? ????????? 0???</span></legend>
				</c:when>
				<c:when test="${type eq 2 }">
					<span>?????? ?????? 1???</span></legend>
				</c:when>
				<c:otherwise>
					<span>?????? ?????? 2???</span></legend>
				</c:otherwise>
			</c:choose> --%>
			
			<div class="memberList-sidebar">
				<ul>
					<li>
						<a href="/adminPageMemberList?reqPage=1&type=1" name="type">?????? ?????????</a>
					</li>
					<li>
						<a href="/adminPageMemberList?reqPage=1&type=2" name="type">?????? ??????</a>
					</li>
					<li>
						<a href="/adminPageMemberList?reqPage=1&type=3" name="type">?????? ??????</a>
					</li>
				</ul>
			</div>
			 
			<div class="memberList-table-all">
				<table class="table" style="width:100%;">
					<tr class="table-primary">
						<th>??????</th>
						<th>????????????</th>
						<th>?????????</th>
						<th>??????</th>
						<th>?????????</th>
						<th>????????????</th>
						<th>????????????</th>
					</tr>
					<%for(Member m : list){ %>		<!--member -> m?????? ??????  -->
						<tr class="table-light">
							<td><input type="checkbox" class="chk"></td>
							<td><%=m.getcMemberNo() %></td>
							<td><%=m.getcEmail() %></td>
							<td><%=m.getcName() %></td>
							<td><%=m.getcEnrollDate() %></td>
							<td>
							<select class="grade-control" style=:width:80%;>
							<%if(m.getcLevel() == 1){ %>
								<option value="1" selected>?????????</option>
								<option value="2">????????????</option>
								<option value="3">????????????</option>
							<%} else if(m.getcLevel() == 2){ %>
								<option value="1">?????????</option>
								<option value="2" selected>????????????</option>
								<option value="3">????????????</option>
							<%} else if(m.getcLevel() == 3){%>
								<option value="1">?????????</option>
								<option value="2">????????????</option>
								<option value="3" selected>????????????</option>
							<%} %>
							</select>
							</td>
							<td>
								<button class="btn changeLevel" style="width:100px; height:40px; font-size:16px; line-height:40px;">????????????</button>
							</td>
						</tr>
					<%} %>
					<tr>
						<th colspan="7">
							<button class="btn chkChangeLevel" style="width:1000px;">????????????????????????</button>
						</th>
					</tr>
				</table>
				<div id="pageNavi" >${pageNavi }</div>
			</div>
		 
		 <div class="searchBox">
					<form action="/searchMember" method="post">
					<c:choose>
						<c:when test="${not empty type }">
							<select name="type">
							<c:choose>
								<c:when test='${type eq "title" }'>
									<option value="email" selected>???????????????</option>
									<option value="memberNo">????????????</option>
								</c:when>
								<c:otherwise>
									<option value="email">???????????????</option>
									<option value="memberNo" selected>????????????</option>
								</c:otherwise>
							</c:choose>
							</select>
						</c:when>
						<c:otherwise>
							<select name="type">
								<option value="email">???????????????</option>
								<option value="memberNo">????????????</option>
							</select>
						</c:otherwise>
					</c:choose>
					<input type="text" name="keyword" class="form-control" autocomplete="off" value="${keyword }"><button type="submit" class="search-btn">??????</button>
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
			var num = new Array();		//???????????? ????????? ??????
			var level = new Array();	//????????? ????????? ????????? ??????
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
            var reqPage = '${reqPage}';
        });
		 
	</script>

</body>
</html>