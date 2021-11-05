<%@page import="table.model.vo.Business"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	
<meta charset="UTF-8">
<title>구르밍</title>
<style>
#uploadBtn{

}
</style>
<link rel="stylesheet" href="/defaultCss/default.css">
<!-- 폰트 CSS -->
<link rel="stylesheet" href="/defaultCss/font.css">
<!-- jQuery라이브러리 -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<script src ="/summernote/jquery-3.3.1.js"></script>
	<script src ="/summernote/summernote-lite.js"></script>
	<script src = "/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<link rel="stylesheet" href="/css/KimMinji/project.css">

	<div class="container">
		<form action="/projectPreview" name="preview" method="post" >
			<input type="hidden" name="preProjTitle" >
			<input type="hidden" name="preTarget">
			<input type="hidden" name="preProjStory">
			<input type="hidden" name="preShipDate">
			<input type="hidden" name="preEndDate">
			<input type="hidden" name="preReTitle">
		</form>
		<div class="left-menu">
			<h1>프로젝트 만들기</h1>
			<h4>빈칸없이 작성해주세요</h4>
			<ul class="left_menu">
				<li class="currentMenu">1.기본정보</li>
				<li class="currentMenu">2.리워드</li>
				<li class="currentMenu">3.부가정보</li>
				<li id="preview" style="display:none;" class="pro_preview"><img src="/img/KimMinji/image.png" id="previewImg">미리보기</li>
			</ul>
		</div>
		
		<form action="/projectFrm1" method="post" enctype="multipart/form-data" onsubmit="return checkVal();">
			<div class="projectContent">
				<input type="hidden" name="shipDate">
				<div class="projectFrm">
					<label for="projTitle">프로젝트 제목을 적어주세요</label> 
					<p class="comment">프로젝트의 핵심 내용을 담을 수 있고 간결한 제목을 정해주세요.</p>
					<input type="text" id="projTitle" name="projTitle" class="input_03">
				</div>
				<div class="projectFrm">
					<label for="target">목표 금액을 적어주세요</label>
					<input type="text" id="target"	name="target" placeholder="숫자만 입력해주세요" class="input_04">
					<span class="input_text">원</span>
				</div>
				<div class="projectFrm">
					<label >프로젝트의 진행기간을 선택해주세요</label><br>
						</div>
					  <input type="date" name="startDate" id="startDate" class="input_05">
					 <input type="date"	name="endDate" id="endDate" class="input_05">
			
				<div class="projectFrm">
					<label>제품 카테고리</label>
					<select name="category">
						<option value="가전">가전</option>
						<option value="도서">도서</option>
						<option value="리빙">리빙</option>
						<option value="반려동물">반려동물</option>
						<option value="악세서리">악세서리</option>
						<option value="여행레저">여행레저</option>
						<option value="푸드">푸드</option>
					</select>
				 </div>
				<div class="projectFrm">
					<label for="uploadImg">프로젝트 대표이미지를 등록해주세요</label><br>
					</div>
					 <input type="file" id="uploadImg" name="uploadImg" style="display:none;">
					 <button id="uploadBtn" ><img src="img/KimMinji/imageUpload.png"  accept=".jpg,.jpeg,.png,.git"></button>
				
				<div class="projectFrm">
					<label for="summernote">프로젝트 스토리</label> 
					<p class="comment" style="margin-bottom:20px;">제품의 스토리와 상세설명을 적어서 프로젝트를 소개해주세요 <span style="color:rgb(126,126,126);">( 사진첨부 x )</span></p>
					<textarea  id="summernote" name="projStory"></textarea>
				</div>
				<input type="button" id="nextBtn1" id="nextBtn1" value="다음페이지" class="btn btn_100">
			</div>
			<div class="projectContent"  style="display:none;">
				<div class="projectFrm">
					<label for="rePrice">리워드 옵션 금액을 적어주세요</label> 
					<input type="text" id="rePrice" name="rePrice"placeholder="숫자만 입력해주세요" class="input_04">
					<span class="input_text">원</span>
				</div>
				<div class="projectFrm">
					<label for="reTitle">리워드의 제목을 적어주세요</label>
					<input type="text" id="reTitle" name="reTitle" class="input_03"><br>
				</div>
				<div class="projectFrm">
					<label for="reContent">준비된 리워드와 설명을 적어주세요</label><br>
					<textarea name="reContent" id="reContent" class="textarea_pro"></textarea>
				</div>
				<div class="projectFrm">
					<label for="cancel">진행자 환불 및 교환 정책</label><br>
					<textarea name="cancel" id="cancel" class="textarea_pro" palceholder="구르미 환불정책 외에 적고싶은 사항이 있으면 적어주세요"></textarea>
				</div> 
				<div class="projectFrm">
					<label for="qPhone">문의 가능한 번호</label>
					<input type="text" id="qPhone" name="qPhone" placeholder="010-0000-0000" class="input_04"><br>
				</div>
				<div class="projectFrm">
					<label for="qEmail">문의 이메일</label>
					<input type="text" id="qEmail" name="qEmail" class="input_04"><br>
				</div>
				<input type="button" id="nextBtn2" id="nextBtn2" value="다음페이지" class="btn btn_100">
			</div>
			<div class="projectContent" style="display:none;">
				<div class="projectFrm">
					<label >거래은행</label> 
					<select name="bank" id="bank">
						<option selected>선택</option>
						<option value="신한은행">신한은행</option>
						<option value="국민은행">국민은행</option>
						<option value="하나은행">하나은행</option>
						<option value="농협은행">농협은행</option>
						<option value="카카오뱅크">카카오뱅크</option>
					</select>
				</div>	
				<div class="projectFrm">
					<label for="accoNum">계좌번호</label>
					<input type="text" id="accoNum" name="accoNum" placeholder="숫자만 입력해주세요" class="input_04"><br>
				</div>
				<div class="projectFrm">
					<label for="makerName">예금주명</label>
					<input type="text" name="makerName" id="makerName" class="input_04"><br>
				</div>	
				<input type="submit" value="저장하기 " name="joinSub" class="btn btn_100">
			</div>	
			<input type="hidden" name="busiNo" value="${sessionScope.member.cMemberNo }">	
		</form>
	</div>

	<script>
	  window.onload=function(){
			$(".currentMenu").eq(0).css("font-weight","900");
			$(".currentMenu").eq(0).css("font-size","20px");
			$(".currentMenu").eq(0).css("color","#00B9CE");
		}  
	$(function() {
		
		$("#uploadBtn").click(function(e) {
			e.preventDefault();
			$("#uploadImg").click();
		});
		$('#summernote').summernote({
			height: 400,
			lang:"ko-KR"
		});
	
	
	    function initTab(){
	        var frms = $(".projectContent");
	        for(var i=0;i<frms.length;i++){        
	        	$(".projectContent").eq(i).hide();
	    		$(".currentMenu").eq(i).css("font-weight","200");
	    		$(".currentMenu").eq(i).css("font-size","15px");	
	    		$(".currentMenu").eq(i).css("color","#333");	
	        }
	      }
		function selectOne(i){ 
			  initTab();
			  $(".projectContent").eq(i).css("display","block");
			  $(".currentMenu").eq(i).css("font-weight","900");
			  $(".currentMenu").eq(i).css("font-size","20px");
			  $(".currentMenu").eq(i).css("color","#00B9CE");
			}
		
			$("#nextBtn1").click(function() {
				var shipDate = $("#endDate").val();
				if($("#projTitle").val() == ""|| $("#target").val()==""|| $("#startDate").val() =="" | $("#endDate").val() =="" || $("#uploadImg").val() == "" ||  $("textarea[name=projStory]").val() =="" ){
					alert("빈칸없이 작성해주세요!");
				}else{
				$("input[name=shipDate]").val(shipDate);
				selectOne(1);
				
				}
		
			});
			$("#nextBtn2").click(function() {
				if($("#rePrice").val() == ""|| $("#reTitle").val()==""|| $("#reContent").val() =="" | $("#cancle").val() =="" || $("#qEmail").val() == "" ||  $("#qPhone").val() =="" ){
					alert("빈칸없이 작성해주세요!");
				}else{
					selectOne(2)
					$("#preview").css("display","block");
				}
				
				
			});
			$("#preview").click(function() {		
				var projTitle = $("#projTitle").val();
				var target = $("#target").val();
				var endDate = $("#endDate").val();
				var shipDate = $("#endDate").val();
				var reTitle = $("#reTitle").val();
				var projStory = $("#projStory").val();
				$("input[name=preProjTitle]").val(projTitle);
				$("input[name=preTarget]").val(target);
				$("input[name=preEndDate]").val(endDate);
				$("input[name=preShipDate]").val(shipDate);
				$("input[name=preReTitle]").val(reTitle);
				$("input[name=preProjStory]").val(projStory);
				window.open("/projectPreview","preview");
				$("[name=preview]").attr("target","preview")
				$("[name=preview]").submit();
			});
			
		
	});
	
	function checkVal() {
		if($("#bank").val()=="" || $("#accoNum").val() == "" || $("#makerName").val() == ""){
			alert("빈칸없이 작성해주세요!");
			return false;
		}else{
			return true;
		}
	}
	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>