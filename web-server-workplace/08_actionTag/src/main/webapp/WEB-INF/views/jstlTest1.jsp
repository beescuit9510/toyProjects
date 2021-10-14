<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
	<%
		int num1 = 100;
	%>
	<h3>num1 : <%=num1 %></h3>
	 --%>
	<h1>JSTL 기본</h1>
	<hr>
	<c:set var="num1" value="100" scope="request"/>
	<c:set var="num2" value="300" scope="request"/>
	<c:set var="num1" value="200" scope="session"/>
	<h3>num1 : ${num1 }</h3>
	<h3>num1(session) : ${sessionScope.num1 }</h3>
	<h3>num1+num2 : ${num1+num2 }</h3>
	<h3>num2 : ${num2 }</h3>
	<c:remove var="num2" scope="request"/>
	<h3>num2 : ${num2 }</h3>
	<hr/>
	<h3>num1 : <c:out value="${num1 }"/></h3>
	<h3>num2 : <c:out value="${num2 }"/></h3>
	<h3>num2 : <c:out value="${num2 }" default="없음"/></h3>
	<p>num2 : <c:out value="글씨를 진하게 하려면 <b>태그</b>를사용" escapeXml="true"/></p>
	<p>num2 : <c:out value="글씨를 진하게 하려면 <b>태그</b>를사용" escapeXml="false"/></p>
</body>
</html>