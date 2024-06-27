<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<h1>비동기 통신</h1>
	
	
	
	<button onclick="onajax1()">버튼</button>
	<script>
	
	function onajax1() {
	  $.ajax({
		url: 'ajax1',
		type: 'get',
		data: {
			menu: "haha"
		}
	  ,
	  success: (res) => {
		  console.log(res);
	  }
	  });
	}
	</script>
	
	<h3>2. DB에서 SELECT문을 이용해서 가져옴</h3>
	
	조회할ㄹ 음식 : <input type="number" id="inputNumber"/> <br><br>
	
	
	<button id="select-btn">버튼</button>
	<div>
	
	</div>
	
	
	<script>
	
	   $(() => {
		   document.querySelector('#select-btn').onclick = () => {
			   
			   $.ajax({
				   
				   url: 'ajax2',
				   type: 'get',
				   data: {
					   number: $('#inputNumber').val()
				   },
				   success: (res) => {
					   console.log(res);
				   },
				   error: (e) => {
					   console.log(e);
				   }
				   
			   })
			   
		   }
	   })
	
	
	
	</script>
	
	
	
</body>
</html>