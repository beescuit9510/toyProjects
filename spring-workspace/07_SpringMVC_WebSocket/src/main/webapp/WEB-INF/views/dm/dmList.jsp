<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/dm/alarm.jsp"/>
	<h1>쪽지함</h1>
	<hr/>
	<h2>받은 쪽지함</h2>
	<table border="1">
		<tr>
			<th>번호</th><th>보낸이</th><th>내용</th><th>날짜</th><th>읽음여부</th>
		</tr>
		<c:forEach items="${list }" var="dm" >
	 		<c:if test="${dm.receiver eq m.memberId }">
				<tr>
					<td>${dm.dmNo }</td>
					<td>${dm.sender}</td>
					<td>${dm.dmContent }</td>
					<td>${dm.dmTime }</td>
					<td>${dm.readStatus }</td>
				</tr>
			</c:if>	
		</c:forEach>
	</table>
	<hr/>
	<h2>보낸 쪽지함</h2>
	<table border="1">
		<tr>
			<th>번호</th><th>받은사람</th><th>내용</th><th>날자</th><th>읽음여부</th>
		</tr>
		<c:forEach items="${list }" var="dm" >
			<c:if test="${dm.sender eq m.memberId }">
				<tr>
					<td>${dm.dmNo }</td>
					<td>${dm.receiver}</td>
					<td>${dm.dmContent }</td>
					<td>${dm.dmTime }</td>
					<td>${dm.readStatus }</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<hr/>
	<h2>쪽지 보내기</h2>
	내용 <input type="text" id="msg"/><br/>
	받는이 <input type="text" id="receiver"/><br/>
	<button id="sendMsg">쪽지보내기</button>
</body>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$("#sendMsg").click(function() {
		var dmContent = $("#msg").val();
		var receiver = $("#receiver").val()
		if(dmContent != '' && receiver !=''){			
			$.ajax({
				url:"/sendDm.do",
				method:"get",
				data:{dmContent:dmContent,receiver:receiver},
				success:function(data){
					if(data==1){
						dmCount(receiver);
					}
	//				alert(data==1? "성공":"실패")
					
					//location.reload();
				}
			})
		}
	})
</script>
</html>