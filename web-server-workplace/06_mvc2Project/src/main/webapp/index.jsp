<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC2 프로젝트</title>
</head>
<body>
	<h1>MVC2 프로젝트</h1>
	<hr>
	<h3><a href="/views/loginFrm.jsp">1. 로그인</a></h3>
	<h3><a href="/views/joinFrm.jsp">2. 회원가입</a></h3>
	<h3><a href="/allMember">3. 전체회원조회</a></h3>
	
	<hr>
	<h4>	
		servlet
		//1.인코딩
		//2.view에서 보낸 데이터 추출
		//3.비지니스로직 수행.
		//4.화면구현		
				
		JSP(MVC1)
		//1.인코딩
		//2.view에서 보낸 데이터 추출
		//3.비지니스로직 수행.
		//4.화면구현		
	
				
		MVC2
		//1.인코딩
		//2.view에서 보낸 데이터 추출
		//3.비지니스로직 수행.
		//4.화면구현		
				
		JSP
		//4.화면구현			
		<br>
		<br>
		<br>

			
		//세션객체 생성
		// request.getSession();
		// -> 매개변수가 없는 경우 세션이 이미 존재하면 있는 세션을 가져오고
		// -> 매개변수가 있는 경우 세션이
		// 매개변수가 없는 경우 자동으로 true로 봄.
		
		// request.getSession(false) 매개변수로 false
		//기존에 세션이 존재하면 기존세션을 가져오고
		//기존에 세션이 존재하지 않으면 null 리턴;		
		<br>
		<br>
		<br>
			
			
		//1.인코딩;
		//request.setCharacterEncoding("utf-8");
		
		//2.view에서 보낸 값 추출;
		
		//3.비지니스 로직;
		ArrayList<Member> members = new MemberService().allMember();
		
		//4.화면처리 (동적인 페이지인 경우 -> servlet의 데이터가 화면에 필요한 경우);
		//4-1.처리할 화면 경로 지정;
		RequestDispatcher view = request.getRequestDispatcher("/views/allMember.jsp");
		//4-2.화면 구성에 필요한 데이터 등록;
		request.setAttribute("members", members);
		//4-3.페이지 이동;
		view.forward(request, response);
		
		//리퀘스트는 세션과 다르게 딱 다음페이지까지만 사용가능;
		<br>
		<br>
		<br>
	
		
		
		//서블릿에서 request 영역에 등록해놓은 데이터를 추출;
    	ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
		<br>
		<br>
		<br>
		
		
		
		web-inf > views 폴더안 jsp는 반드시 forward로만 접근가능 !
		"web-inf/views/allMember.jsp"는 접근 불가능.
		
		2xx : OK
		3xx : redirect
		4xx : 없는 페이지야
		5xx : 서버문제
		
		그냥 정적 페이지 -> views;
		동적 페이지 -> web-inf>views; 
		
</body>
</html>