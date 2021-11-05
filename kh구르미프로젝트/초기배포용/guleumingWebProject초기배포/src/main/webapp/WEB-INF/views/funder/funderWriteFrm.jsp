<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--  JSTL 확장 c 태그 선언문 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/Parksowon/funder.css">
<!-- 우편 api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="funder_add">
			<div class="add_left border_a">
                <div class="profileTop">                   
                    <div class="profileImg">
                    	<c:choose>
							<c:when test="${not empty fb.profileFilepath}">
								<img src="/upload/funder/'+${fb.profileFilepath}+'">
							</c:when>
							<c:otherwise>							
                        		<img src="/img/Parksowon/profile.png">
							</c:otherwise>
						</c:choose>
                    </div>
                    <span class="profileName">${m.businessName}</span>
                </div>
                <div class="border_b"></div>
                <div class="profileBottom">
                    <span class="profileText">
                        <p>업종</p>
                        <p>${fb.funderCategory}</p>
                    </span>
                    <span class="profileText">
                        <p>설립일</p>
                        <p>${fb.openDate}</p>
                    </span>
                    <span class="profileText margin_b">
                        <p>제작 프로젝트</p>
                        <p>개</p>
                    </span>
                    <div class="like_wr border_a">
                        <img src="/img/Parksowon/heart.png">
                        <p><b>${fb.makerCount}명이 관심</b>을 갖고 있습니다.</p>
                    </div>
                </div>
			</div>
			 <form action="/funderWrite" method="post" enctype="multipart/form-data" id="funder_add">
			    <div class="add_right border_a">
                    <table class="table">
                    	
                        <tr class="table-active center_list">
                            <th>프로필 이미지</th>
                            <td class="displayflex">
                                <div class="img-viewr">
                                    <img id="img-view" class="profile_view" src="/img/Parksowon/profile.png">
                                </div>
                                <label class="btn btn_sm btn_out" for="profile">프로필 선택</label>
                                <input type="file" name="profileFilepath" id="profile" onchange="loadImg(this);" accept=".jpg,.jpeg,.png,.gif" style="display: none;">
                            </td>
                        </tr>
                        <input type="hidden" name="writerNo" value="79">
                        <tr class="table-active">
                            <th>법인명</th>
                            <td><input type="text" name="businessName" class="input_03" value="" readonly></td>
                        </tr>
                        <tr class="table-active">
                            <th>설립일자</th>
                            <td><input type="text" name="openDate" class="input_03" placeholder="하이픈(-)과 숫자만 기재해주세요"></td>
                        </tr>
                        <tr class="table-active">
                            <th>유사 업종</th>
                            <td>
                                <div class="check_ent displayflex">
                                    <input type="checkbox" class="chk" name="funderCategory" id="fun_01" value="푸드" onclick="checkOnlyOne(this)"><label for="fun_01">푸드</label>
                                    <input type="checkbox" class="chk" name="funderCategory" id="fun_02" value="리빙" onclick="checkOnlyOne(this)"><label for="fun_02">리빙</label>
                                    <input type="checkbox" class="chk" name="funderCategory" id="fun_03" value="가전" onclick="checkOnlyOne(this)"><label for="fun_03">가전</label>
                                    <input type="checkbox" class="chk" name="funderCategory" id="fun_04" value="반려동물" onclick="checkOnlyOne(this)"><label for="fun_04">반려동물</label>
                                </div>
                                <div class="check_ent displayflex">
                                    <input type="checkbox" class="chk" name="funderCategory" id="fun_05" value="여행레저" onclick="checkOnlyOne(this)"><label for="fun_05">여행레저</label>
                                    <input type="checkbox" class="chk" name="funderCategory" id="fun_06" value="악세서리" onclick="checkOnlyOne(this)"><label for="fun_06">악세서리</label>
                                    <input type="checkbox" class="chk" name="funderCategory" id="fun_07" value="도서" onclick="checkOnlyOne(this)"><label for="fun_07">도서</label>
                                	<!-- <input type="hidden" name="category"> -->
                                </div>
                            </td>
                        </tr>
                        <tr class="table-active">
                            <th>문의사항</th>
                            <td>
                                <input type="text" name="boardEmail" class="input_03" placeholder="email을 입력해주세요.">
                            </td>
                        </tr>
                        <tr class="table-active">
                            <th>회사 주소</th>
                            <td>
                                <div class="postcode">
                                    <input type="text" id="postCode" class="input_04" readonly placeholder="우편번호">
                                    <button type="button" onclick="addrSearch();" class="btn btn_sm">주소검색</button>
                                </div>
                                <div class="postcode">
                                    <input type="text" id="roadAddr" class="input_03" placeholder="도로명 주소" name="companyAddr">
                                </div>
                                <div class="postcode">
                                    <input type="text" id="detailAddr" class="input_03" placeholder="상세주소">
                                </div>
                            </td>
                        </tr>
                        <tr class="table-active">
                            <th>검색 태그</th>
                            <td>
                                <div class="profile_tags">
                                    <ul class="tag_list">

                                    </ul>
                                    <input type="text" name="searchTag" placeholder="태그와 태그는 쉼표 또는 엔터로 구분해주세요" id="tag" onfocus="this.placeholder=''" onblur="this.placeholder='태그와 태그는 쉼표 또는 엔터로 구분해주세요'">
                                	<input type="hidden" name="tagadd">
                                </div>
                            </td>
                        </tr>
                        <tr class="table-active">
                            <th>회사 소개</th>
                            <td>
                                <textarea name="companyIntro" class="textarea_pro"></textarea>
                            </td>
                        </tr>
                        <tr class="table-active">
                        	<th>보유 자격증</th>
                        	<td>
                        		<input type="text" class="input_03 skill_search" placeholder="회사의 인재가 보유하고있는 자격증을 기재해주세요. 예) 반려동물 식품관리사" id="skill_add">
                        		<input type="hidden" name="skillName">
                        		<input type="hidden" name="skillLevel">
                        		<div class="profile_skill">
                        			<ul class="skill_list">
                        				
                        			</ul>
                        		</div>
                        	</td>
                        </tr>
                    </table>
                    <div class="next_btn">
                        <button class="btn btn_100" type="button">저장하기</button>
                    </div>
                </div>
            </form>
		</div>
	</div>
	<script>
		//이미지 미리보기
	    function loadImg(obj){
			var files = obj.files; // input type=file에서 선택한 파일을 배열로 가져옴
			if(files.length != 0){ //첨부파일이 있는 경우
				//파일에 대한 정보를 읽어오는 객체
				var reader = new FileReader();
				//파일 경로를 읽어옴
				reader.readAsDataURL(files[0]);
				//경로를 다 읽어오면 실행할 함수 설정
				reader.onload = function(e){
					//읽어온 경로를 img 태그의 src 속성에 설정
					$("#img-view").attr("src",e.target.result);
				}
			}else{ //첨부파일이 없는 경우
				$("#img-view").attr("src","");
			}
		}
	    //다음 우편번호 
	    function addrSearch(){
    	var width = 500; 
    	var height = 600; 
		 new daum.Postcode({
			width: width, 
			height: height,
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분
	            $("#postCode").val(data.zonecode); //우편
	            $("#roadAddr").val(data.roadAddress); //도로
	            $("#detailAddr").focus(); // 선택후 상세주소 포커스
	        },
		 theme:{
	  			searchBgColor: "#00B9CE", //검색창 배경색,
	  			queryTextColor: "#FFFFFF" //검색창 글자색
	  			}
		 }).open({ //팝업 위치 가운데로
			 popupTitle: '구르밍 우편번호 검색',
			 popupKey: 'popup1',
			 left: (window.screen.width / 2) - (width / 2),
			 top: (window.screen.height / 2) - (height / 2)
		 });
		};
		//태그
		 $(function () {
	            var tag = {};
	            var counter = 0;
	            // 태그 추가
	            function addTag(value) {
	            tag[counter] = value; // 태그 Object 안에 추가
	            counter++; // counter 증가 삭제를 위한 del-btn 의 고유 id 
	            }
	            $("#tag").on("keyup", function (e) {
	                var self = $(this);
	                // input 에 focus 되있을 때 쉼표 및 엔터 입력시 구동
	                if (e.key === "Enter" || e.keyCode === 188) {
	                var tagValue = self.val(); // 값 가져오기
	                tagValue = tagValue.replace(",", "");
	                if (tagValue !== "") {
	                    // 같은 태그가 있는지 검사. 있다면 해당값이 array 로 return 
	                    var result = Object.values(tag)
	                    .filter(function (word) {
	                        return word === tagValue;
	                    })
	                    // 태그 중복 검사
	                    if (result.length == 0) {
	                    $(".tag_list")
	                        .append("<li class='tag-item'>" + tagValue + "<span class='del-btn' idx='" + counter + "'><img src='/img/Parksowon/close.png'></span></li>");
	                    addTag(tagValue);
	                    self.val("");
	                    } else {
	                        $("#tag").val("중복된 태그입니다.");
	                    }
	                }
	                e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
	                }
	            });
	            // 삭제 버튼 - 비동기적 생성이므로 document 최초 생성시가 아닌 검색을 통해 이벤트를 구현
	            $(document).on("click", ".del-btn", function (e) {
	                var index = $(this)
	                .attr("idx");
	                tag[index] = "";
	                $(this)
	                .parent()
	                .remove();
	            });
	        });
			//체크박스 1개만 선택하도록
			function checkOnlyOne(target) {
			    document.querySelectorAll('input[type=checkbox]')
			    .forEach(el => el.checked = false);			
			    target.checked = true;
			}			
			// 제출 전 폼 입력받기
			$(".next_btn>button").click(function(){
				if($("input[name='openDate']").val() == ""){
					$("input[name='openDate']").focus();
					alert("설립일자를 입력해주세요.");
				}else if (!$("input:checked[name='funderCategory']").is(":checked")){
					$("input[name='funderCategory']").focus();
					alert("업종을 선택해주세요.");
				}else if($("input[name='boardEmail']").val() == ""){
					$("input[name='boardEmail']").focus();
					alert("문의사항을 받을 이메일을 입력해주세요.");
				}else if($("input[name='companyAddr']").val() == ""){
					$("input[name='companyAddr']").focus();
					alert("회사 주소를 입력해주세요.");
				}else if($("textarea").val() == ""){
					$("textarea").focus();
					alert("회사소개를 입력해주세요.");
				}else{
					$(this).attr("type","submit");
				}
			});
			//스킬
			 $(function () {
			    var skill = {};
			    var skillcounter = 0;
			    // 태그 추가
			    function addskill(value) {
			    skill[skillcounter] = value; // 태그 Object 안에 추가
			    skillcounter++; // counter 증가 삭제를 위한 del-btn 의 고유 id 
			    }
			    $("#skill_add").on("keyup", function (e) {
			        var skillself = $(this);
			        // input 에 focus 되있을 때 쉼표 및 엔터 입력시 구동
			        if (e.key === "Enter" || e.keyCode === 188) {
			        var skillValue = skillself.val(); // 값 가져오기
			        skillValue = skillValue.replace(",", "");
			        if (skillValue !== "") {
			            // 같은 태그가 있는지 검사. 있다면 해당값이 array 로 return 
			            var result = Object.values(skill)
			            .filter(function (word) {
			                return word === skillValue;
			            })
			            // 태그 중복 검사
			            if (result.length == 0) {
			            $(".skill_list")
			                .append("<li class='skill_item'> <h2 class='skill_name'>"+ skillValue +"</h2><select name='skill_lel'><option value='국가자격증'>국가자격증</option><option value='민간자격증'>민간자격증</option></select>"+"<span class='del_skill' idx='" + skillcounter + "'><img src='/img/Parksowon/close.png'></span></li>");
			            addskill(skillValue);
			            skillself.val("");
			            } else {
			                $("#skill_add").val("이미 추가되어있는 스킬입니다.");
			            }
			            $(".profile_skill").css("display","block");
			        }
			        e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
			        }
			    });
			    // 삭제 버튼 - 비동기적 생성이므로 document 최초 생성시가 아닌 검색을 통해 이벤트를 구현
			    $(document).on("click", ".del_skill", function (e) {
			        var index = $(this)
			        .attr("idx");
			        skill[index] = "";
			        $(this)
			        .parent()
			        .remove();
			    });
			});
			//체크박스 여러개 선택 시 
			$("#funder_add").submit(function(){
				/* //체크박스 부분
	            var inputs = $("input[name='funderCategory']:checked");
				var category = new Array();    //카테고리
				inputs.each(function(){
					var cate = $(this).next().html();
					category.push(cate);
				}); */
				//태그 부분
				var test = new Array();
				$(".tag_list > .tag-item").each(function(){
				    test.push(this.innerText);
				})
				//$("input[name='category']").val(category.join("/"));
				$("input[name='tagadd']").val(test.join("/"));
				//skill
				var skillname = new Array();
				var skilllevel = new Array();
				$(".skill_list > .skill_item").each(function(idx,item){
					var skilltext = $(item).find(".skill_name").text();
					skillname.push(skilltext);
					var leveltext = $(item).find("select[name='skill_lel']").val();
					skilllevel.push(leveltext);
				});
				$("input[name='skillName']").val(skillname.join("/"));
				$("input[name='skillLevel']").val(skilllevel.join("/"));
			});
			//폼 안에서 태그 엔터로 허용하기 위해서 나머지 엔터 막기
			$("form > input").keydown(function(event){
				if(event.keyCode === 13){
					event.preventDefault();
				};
			},true);
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>