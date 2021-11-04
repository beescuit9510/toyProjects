<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/LeeSeulgi/event/eventList.css">
<style>
fieldset{
	border : none;
}
legend{
	margin-left: 500px;
	font-size: 35px;
	/* margin-bottom: 70px; */
	margin-bottom: 30px;
	font-weight: 900;
}
.event-main{
	width: 800px;
	margin-top: 200px;
	margin:0 auto;
}

.event-footer{
    width: 800px;
    margin: 0 auto;
}

select{
    width: 70px;
    height: 36px;
}

#searchBox{
    text-align: center;
    margin-bottom:50px;
}
.event-box{
	margin-top: 30px;
	border-bottom: 1px solid rgb(143, 143, 143);
}
/* 211101 추가 */
.event-box:first-child{
	border-top: 1px solid rgb(143, 143, 143);
	padding-top:30px;
}
.event-box>a>h2{
	/* width: 650px; */
	padding-left: 10px;
}
.etc{
	margin-bottom: 30px;
	/* padding-left: 30px; */
	padding-left: 12px;
}
.etc>span{
	font-size:13px;
	color: gray;
}
.form-control{
    width: 300px;
    height: 36px;
    padding-left: 10px;
    outline: none;
}
.event-footer{
	width: 800px;
	margin: 0 auto;
}
.write-btn{
	margin: 10px 0px;
	width: 60px; 
	height: 40px; 
	line-height: 40px; 
	font-size: 15px;
	border: 1px solid #00B9CE;
	border-radius: 10px;
	text-align: center;
}
.write-btn>a{
	color: #00B9CE;
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
/*
#pageNavi{
    margin: 30px 0px;
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
*/

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
.event-title{
	text-align: center;
	font-size: 65px;
	margin-bottom: 50px;
	color: #00B9CE;
	font-family: "logo";
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
   		<div class="container">
      		<fieldset>
			<legend><a href="/eventList?reqPage=1" class="event-title">이벤트</a></legend>

			<div class="event-main">
				<ul>
					<c:forEach items="${list }" var="e" varStatus="i">			<%--for문으로 이벤트 list가져오기  --%>
						<li class="event-box">
								<a href="/eventView?eventNo=${e.eventNo }">
									<h2>${e.eventTitle }</h2>
								</a>
							<div class="etc">
								<span>
									<c:if test="${e.eventWriter eq 1}">	<%--작성자번호 1(관리자)이면  --%>
										admin							
									</c:if>
								</span>
								<span>${e.writeDate }</span>
							</div>
						</li>
				</c:forEach>
				</ul>
			</div>
			<div class="event-footer">
				<c:if test="${not empty sessionScope.member && sessionScope.member.cLevel eq 1}">	<%--${not empty sessionScope.m && sessionScope.m.memberLevel eq 1}로 바꾸기 --%>
					<div class="write-btn">
						<a class="wrtie-btn" style="" href="/eventWriteFrm">글쓰기</a>
					</div>			
				</c:if>
				
				<div id="pageNavi" >${pageNavi }</div>
				
				<div id="searchBox">
					<form action="/searchEvent" method="post">
					<!-- <input type="hidden" name="reqPage" value="1"> -->
					<c:choose>
						<c:when test="${not empty type }">
							<select name="type">
							<c:choose>
								<c:when test='${type eq "title" }'>
									<option value="title" selected>제목</option>
									<option value="content">내용</option>
								</c:when>
								<c:otherwise>
									<option value="title">제목</option>
									<option value="content" selected>내용</option>
								</c:otherwise>
							</c:choose>
							</select>
						</c:when>
						<c:otherwise>
							<select name="type">
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
						</c:otherwise>
					</c:choose>
					<input type="text" name="keyword" class="form-control" autocomplete="off" value="${keyword }"><button type="submit" class="search-btn">검색</button>
					</form>
				</div>
			</div>
		</fieldset>
   		</div>   
    <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>