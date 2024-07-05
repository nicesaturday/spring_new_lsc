<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	
	
	https://kauth.kakao.com/oauth/authorize
	
	
	2901854041e3d8507e06378aa5f59b72
	
	
	<a id="login-btn">
		<img src="resources/img/kakao_login.png" />
	</a>
	
	
	${loginUser.id }
	${loginUser.nickName }
	
	
	<img src=${loginUser.thumbnailImg } >
	
	<script>
		$("#login-btn").on('click',() => {
			location.href = "https://kauth.kakao.com/oauth/authorize?client_id=2901854041e3d8507e06378aa5f59b72"
						 +"&redirect_uri=http://localhost/ajaxpro/oauth"
						 +"&response_type=code&scope=profile_nickname,profile_image";
		});
		
		
		$("")
		
		
	</script>
	
	<br><br><br>
</body>
</html>