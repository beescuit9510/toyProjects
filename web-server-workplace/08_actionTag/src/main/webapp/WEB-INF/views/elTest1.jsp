<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>request.getAttribute() 이름 : <%=request.getAttribute("name") %></h2>
	<h2>request.getAttribute() 나이 : <%=request.getAttribute("age") %></h2>
	<h2>request.getAttribute() 주소 : <%=request.getAttribute("addr") %></h2>
	<br/><br/><br/><hr/>
	<h2>이름 : ${name }</h2>
	<h2>나이 : ${age }</h2>
	<h2>주소 : ${addr }</h2>
	<br/><br/><br/><hr/>
	<h2>requestScope.이름 : ${requestScope.name }</h2>
	<h2>requestScope.나이 : ${requestScope.age }</h2>
	<h2>requestScope.주소 : ${requestScope.addr }</h2>
	<br/><br/><br/><hr/>
	<h2>sessionScope.이름 : ${sessionScope.name }</h2>
	<h2>sessionScope.나이 : ${sessionScope.age }</h2>
	<h2>sessionScope.주소 : ${sessionScope.addr }</h2>
	<br/><br/><br/><hr/>
	<h2>(request에 등록된) str1 : ${str1}</h2>
	<h2>(session에 등록된) str2 : ${str2}</h2>
	<br/><br/><br/><hr/>
	<h3>request와 session에 이름이 겹치게 등록된 경우는 리퀘스트가 나옴!</h3>
	<h3 style="color:red;">requset는 ${name}사용/ session은 안겹치더라도 ${session.name}이 안헷갈림</h3>
	<h2>이름 : ${name}</h2>
	<h2>이름 : ${name}</h2>
	<br/><br/><br/>
	
</body>
</html>