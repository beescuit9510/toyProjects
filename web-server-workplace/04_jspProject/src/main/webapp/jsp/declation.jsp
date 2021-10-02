<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%!
   		private int count = 10;
	   	public String test(){
	   		return "테스트 메소드 입니다";
	   	}
	   	public int getCount(){
	   		return this.count;
	   	}
   	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>선언식 태그</h1>
	<hr>
	<h3>jsp 클래스의 전역변수 및 추가 메소드를 생성하는 태그</h3>
	<hr>
	<h3><%=count %></h3>
	<h3><%=test() %></h3>
</body>
</html>