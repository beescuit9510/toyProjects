<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전체회원조회(AJAX)</h1>
	<hr/>
	<button id="btn">전체회원정보 불러오기</button>
	<div id="result"></div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$("#btn").click(function(){
		$.ajax({
			url:"/ajaxAllMember.do",
			success : function(data) {
				console.log(data);
				$("#result").empty();
				var table = $("<table border='1'>");
				var titleTr = $("<tr>");
				titleTr.append("<th>아이디</th><th>비밀번호</th><th>이름</th><th>주소</th><th>가입일</th>")
				table.append(titleTr);
				for(var i=0;i<data.length;i++){
					var tr = $("<tr>");
					console.log(data[i]);
					tr.append("<td>"+data[i].memberId+"</td>");
					tr.append("<td>"+data[i].memberPw+"</td>");
					tr.append("<td>"+data[i].memberName+"</td>");
					tr.append("<td>"+data[i].address+"</td>");
					tr.append("<td>"+data[i].enrollDate+"</td>");
					table.append(tr);
				}
				$("#result").append(table);
			}
		})
	})
</script>
</html>