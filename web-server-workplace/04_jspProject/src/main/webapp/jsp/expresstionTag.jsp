<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <%@page import="java.util.stream.Stream" %>
</head>
<body>
	<h1>표현식 태그</h1>
	<hr>
	<h3>자바의 데이터를 HTML의 데이터로 표현하기위해 사용하는 태그</h3>
	<%
	int age = 20;
	%>
	<p><%=age%>살은</p>
	<%
	String text = age >= 20 ? "성인입니다" : "미성년자 입니다";
	%>
	<h2><%=text %></h2>
	<% 
		String[] arr = {"변덕01","변덕02","변덕03","변덕04"};
		//Stream.of(arr).forEach(System.out::println);
	%>
</body>
</html>