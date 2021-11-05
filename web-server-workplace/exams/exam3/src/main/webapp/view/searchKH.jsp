<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학원 조회 페이지</title>
</head>
<body>
	<h1>학원 정보 조회하기</h1>
	<hr />
	<input type="text" name="khName">
	<button>조회하기</button>
	<ul class="result"></ul>
</body>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$("button").click(function () {
		var khName = $("input[name='khName']").val();
		$.ajax({
			url:"/searchKH",
			data:{khName:khName},
			type:"POST",
			success:function(data){
				$(".result").children().remove();
				if(data != null){
					var html = "";
					html += "<li>";
					html += data['khName']
					html += "</li>";
					html += "<li>";
					html += data['addr']
					html += "</li>";
					html += "<li>";
					html += data['fax']
					html += "</li>";
					$(".result").append(html);
					console.log(html);
				}else{
					$(".result").append("<li>정보가 없습니다</li>");
				}
		}})
	})
</script>
</html>