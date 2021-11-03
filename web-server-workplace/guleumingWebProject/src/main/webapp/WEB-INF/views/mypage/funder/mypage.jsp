<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/css/Yunyoung/mypage.css">
<title>Insert title here</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="title">설정</div>
		<div class="navi-wrap">
			<div class="navi">
				<a class="fundedFundings" href="/fundedFundingList">펀딩한 프로젝트</a> 
				<c:if test="${sessionScope.member.cLevel > 2}">
				<a class="myOwnProjects" href="/myOwnProject">제작한 프로젝트</a>
				</c:if>

				<a class="likeList" href="/likeList">관심 펀더 및 펀딩</a>
				<a class="mypage" href="/mypage">설정</a>
			</div>
		</div>
		    <div class="myinfo-wrap">
            <div class="myinfo-baisc-info">
                <div class="big-logo-title">기본 정보 설정</div>
                <div>
                    <div class="gray-title">이름</div>
                    <div><p class="name"></p>${sessionScope.member.cName}</div>
                </div>
                <div>
                    <div class="gray-title">이메일</div>
                    <div><p class="email"></p>${sessionScope.member.cEmail}</div>
                </div>
                <div>
                    <div class="gray-title">비밀번호<span class="changable line-css gray-title">*</span></div>
                    <input class="pw-to-change" type="password" placeholder="${sessionScope.member.cPassword}">
                </div>
                <div>
                    <div class="gray-title">전화번호<span class="changable line-css gray-title">*</span></div>
                    <input class="phone-to-change line-css" type="text" placeholder="${sessionScope.member.cPhone}">
                </div>
                <button class="myinfo-btn btn btn-sm update-myinfo-btn">설정 수정하기</button>
            </div>
            <c:if test="${sessionScope.member.cLevel >2}">
            <div class="myinfo-business">
                <div class="big-logo-title">사업자 등록 정보</div>
                <div>
                    <div class="gray-title">법인명</div>
                    <div><p class="email">${sessionScope.member.businessName}</p></div>
                </div>
                <div>
                    <div class="gray-title">사업자 등록 번호</div>
                    <div><p class="password">${sessionScope.member.businessCode}</p></div>
                </div>
                <div>
                    <div class="gray-title">담당자명</div>
                    <div><p class="phone">${sessionScope.member.managerName}</p></div>            
                </div>
            </div>
            </c:if>
            <div class="myinfo-sign-out">
                <div class="big-logo-title">회원 탈퇴</div>
                <div class="gray-title warning-sign-out">회원 탈퇴 시 관련 정보는 개인정보보호 관련 약관에 의거하여 처리됩니다.</div>
                <div class="gray-title warning-sign-out">제작중인 프로젝트나 참여중인 펀딩이 있을시엔 회원 탈퇴가 불가합니다.</div>
                <button class="myinfo-btn btn btn-sm myinfo-sign-out-btn">회원 탈퇴하기</button>
            </div>
    	</div>
		
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
<style></style>
<script>
	var pwBeforeChange = $(".pw-to-change").attr("placeholder")+"";
	var phoneBeforeChange = $(".phone-to-change").attr("placeholder");
	var placeholder = "•"
	
	console.log(pwBeforeChange);
	console.log(phoneBeforeChange);
	
	for(var i = 1; i<pwBeforeChange.length;i++){
		placeholder += "•";
	};
	
	$(".pw-to-change").attr("placeholder",placeholder);
	
	$(".mypage").attr("id","active-navi")
	$(".indexJSP").attr("display","none");
	
	$(".myinfo-sign-out-btn").click(function () {
	   	$.ajax({
	   		url:"/deleteMember",
	   		method:"post",
	   		success:function(data){
	   			alert(data);
	   			if(data === "펀더로 활동 및 프로젝트 제작 혹은 펀딩 참여중입니다."){
					return;
	   			}else{
		   			 location.replace("/login")
	   			}
	   		}
	   	})
	})
	
	$(".update-myinfo-btn").click(function () {
		var phone = $(".phone-to-change").val();
		var pw = $(".pw-to-change").val();
		
		if(phone == "" && pw == ""){			
			alert("전화번호 혹은 비밀번호를 입력후 수정이 가능합니다.")
			return;
		}
		
		if(phone == ""){
			phone = phoneBeforeChange;
			
		}
		
		if(pw == ""){
			pw = pwBeforeChange;
		}
		
		
		console.log("phone"+phone);
		console.log(pw);
		
	    if (/^[0-9]{3}-[0-9]{4}-[0-9]{4}/.test(phone)) {
	    	
	    	if(/^[a-zA-Z0-9]{4,}$/.test(pw)){
		    	$.ajax({
		    		url:"/changeMemberInfo",
		    		method:"post",
		    		data:{pw:pw,phone:phone},
		    		success:function(){
		    			alert("개인 정보 수정 성공");
		    			location.reload();
		    		}
		    	})
	    		
	    	}else{
	    		alert("비밀번호는 4자리 이상입니다.")
	    		
	    	}
	    }else{
	    	alert("전화번호는 000-0000-0000 형식으로 넣어주세요.")
	    }
	})
	
</script>
<style>
    .myinfo-wrap >div >*{
        padding-left: 5px;
        font-size:19px;
    }
    input:focus { 
    	outline: none; 
        font-size:19px;
        color:#00B9CE;
        
    }
    input{border: none;
   		color:black; 
    	font-size:19px;
        color:#00B9CE;
    }
    
    input::placeholder{
	    font-size:19px;
	    color:black;
    }

    .myinfo-wrap >div > *:last-child{
        margin-bottom: 8px;
    }
    .myinfo-wrap >div > div{
        margin-top: 8px;
    }
    .myinfo-wrap{
        display: flex;
        flex-direction: column;
        margin: auto auto;
        justify-content: center;
        width: 250px;
    }
    .updateInfo{
        display: block;
    }

    .myinfo-wrap > div{
        margin-top: 45px;
        border-bottom: 3px solid #00B9CE;
    }
    .myinfo-wrap > div:first-child{
        margin-top: 0px;
    }
    .myinfo-wrap > div > div:first-child{
        border-bottom: 3px solid #00B9CE;

    }

    .big-logo-title{
        font-size: 29px !important;
        color:#00B9CE;
        font-weight: 600;
        font-family: "logo";
    }
    .gray-title{
        color: gray;
    }
    .myinfo-sign-out{
        margin-bottom: 150px;
    }
    .myinfo-btn{
    	hegight:15px !important;
        margin-top: 8px !important;
        margin-bottom: 15px !important;
        font-family: "logo";
        
    }
    .myinfo-wrap{
    	margin-top:0px !important;
    }
    .warning-sign-out{
    	font-size:12px !important;
    }
/*    .changable{
	    display:inline-block;
	    text-align:center;
        font-size: 16px;
    }
*/    
    .line-css{
    	display:table-cel;
    	font-size:19px;    	
    	vertical-align:middle;
    	
    }
    .line-css::placeholder{
    	display:table-cel;
    	vertical-align:baseline !important;
    	
    }
    
</style>

</html>