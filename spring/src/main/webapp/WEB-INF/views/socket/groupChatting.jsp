<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#input-form {
		width: 800px;
		height: 600px;
		
		border: 1px solid lightgrey;
		margin : auto;
		
		> input {
			width: 80%;
		}
		
		> button {
			width: 14%;
		}
	}
	
	#content-area {
		width: 100%;
		height: 400px;
		overflow: auto;
	}


</style>

</head>
<body>
	
	<jsp:include page="../common/menubar.jsp"></jsp:include>

	<h1>전화국</h1>
	
	
	
	
	<button onclick="connect();">전화걸기</button>
	<button onclick="disconnect();">전화끊기</button>
	
	
	
	<input type="text" id="message">
	<button onclick="send();">메시지 전송</button>
	<script>
		let phone;
		function connect() {
			
			
			const uri = 'ws://localhost/spring/group';
			phone = new WebSocket(uri);
			
			
			phone.onopen = () => {
				console.log('서버와의 연결!');
				
			};
			
			phone.onerror = (e) => {
				console.log(e);
				console.log('서버와의 연결과정의 문제');
			}
			
			phone.onmessage = (e) => {
				
				
				
				console.log(e.data);
			};
			
			

		}
		
		
		function disconnect() {
			phone.close();
			console.log('서버와의 연결 종료!');
		}
		
		
		function send() {
			const message = document.getElementById('message').value;
			phone.
			
		}
	
	</script>
	
	
	
</body>
</html>