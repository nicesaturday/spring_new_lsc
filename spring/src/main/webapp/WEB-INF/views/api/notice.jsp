<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항이당당</title>
<style>
	#content {
		width : 800px;
		height : auto;
		margin : auto;
	}

	#outerDiv{
		width : 800px;
		height : 400px;
		padding-top : 50px;
	}

	.noticeEl {
		width: 100%;
		height : 60px;
		margin: auto;
		line-height: 60px;
		text-align: center;
	}

	.noticeEl > div {
		display: inline-block;
	}

	#title{
		margin-top : 100px;
		text-align: center;
	}

	#detail{
		background-color:#23C293; 
		width:800px; 
		margin: auto;
		text-align:center;
		color : white;
		height : 150px;
		display: none;
	}
	
	#detail > div{
		height : 50px;
		line-height: 50px;
		border : 1px solid rgba(255, 255, 255, 0.656);
	}

</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp"/>
	
	<div id="content">
		<h1 id="title">공지사항 게시판입니다!</h1>
		<button class="btn btn-danger btn-sm" data-toggle="modal" href="#noticeModal">글작성</button>

	</div>	
	
	<div id="detail">
		
	</div>
	
	<jsp:include page="../common/footer.jsp"/>

	<div class="modal fade" id="noticeModal">
		<div class="modal-dialog">
		  <div class="modal-content">
	
			<div class="card">
			  <div class="card-header text-white" style="background-color: #ff52a0;">게시글 작성하기</div>
			  <div class="card-body">       
				
				  <div class="form-group">
					<label>작성자</label>
					<input type="text" class="form-control" id='noticeWriter' value="${ sessionScope.getMember.userId }" readonly>
				  </div>
				  
				  <div class="form-group">
					<label>제목</label>
					<input type="text" class="form-control" id='noticeTitle' value="">
				  </div>
		
				  <div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="5" id='noticeContent' style="resize: none;"></textarea>
				  </div>
				 
				  <a class="btn" data-dismiss="modal"
			  style="background-color: #ff52a0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">닫기</a>&nbsp;&nbsp;
					  <a class="btn" onclick="insert();"
				  style="background-color: red; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">작성하기</a>&nbsp;&nbsp;
				   
			  </div>
			</div>
		  </div>
		</div>
	</div>

	<div class="modal fade" id="updateModal">
		<div class="modal-dialog">
		  <div class="modal-content">
	
			<div class="card">
			  <div class="card-header text-white" style="background-color: #ff52a0;">게시글 수정하기</div>
			  <div class="card-body">       
				
					<input type="hidden" value="" id="updateNo"/>
				  <div class="form-group">
					<label>작성자</label>
					<input type="text" class="form-control" id='updateWriter' value="${ sessionScope.getMember.userId }" readonly>
				  </div>
				  
				  <div class="form-group">
					<label>제목</label>
					<input type="text" class="form-control" id='updateTitle' value="">
				  </div>
		
				  <div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="5" id='updateContent' style="resize: none;"></textarea>
				  </div>
				 
				  <a class="btn" data-dismiss="modal"
			  style="background-color: #ff52a0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">닫기</a>&nbsp;&nbsp;
					  <a class="btn" onclick="update();"
				  style="background-color: orange; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">수정하기</a>&nbsp;&nbsp;
				   
			  </div>
			</div>
		  </div>
		</div>
	</div>
	
	<script>
		window.onload = () => {
			findAll();
			
		}
		
		
		
		function insert() {
			const requestData = {
					"noticeWriter": $('#noticeWriter').val(),
					"noticeTitle": $('#noticeTitle').val(),
					"noticeContent" : $('#noticeContent').val()
			};
			
			$.ajax({
				url : 'notice' ,
				type : 'post' ,
				data : requestData,
				success: res => {
					console.log(res);
					
					if(res.message === "서비스 요청 성공") {
						$('#outerDiv').remove();
						findAll();
						$('#noticeTitle').val('');
						$('#noticeContent').val('');
					}
					
				}
			})
		}
		
		
		
		
		function deleteById(noticeNo) {
			
			$.ajax({
				url: 'notice/' + noticeNo,
				type: 'delete',
				success: res => {
					console.log(res);
					if(res.data === '삭제 성공') {
						$('#detail').slideUp(300);
						$('#outerDiv').remove();
						findAll();
					};
				}
				
				
			})
		}
		
		
		
		
		
		
		$('#content').on('click' , '.noticeEl' , e => {
			console.log(e.currentTarget.childNodes[0].innerText);
			const noticeNo = e.currentTarget.childNodes[0].innerText;
			$.ajax({
				url: 'notice/' + noticeNo,
				type: 'get',
				success: response => {
					console.log(response.data);
					
					const notice = response.data;
					
					const contentValue = '<div id="notice-detail">'
					
					
										+ '<div>' + notice.noticeTitle + '</div>'
										+ '<div>' + notice.noticeContent + '</div>'
										+ '<div>'
										+ '<a class="btn btn-sm btn-warning" data-toggle="modal" href="#updateModal">'
										+ '수정하기'
										+ '</a>'
										+ '<a class="btn btn-sm btn-secondary" onclick="deleteById(' + notice.noticeNo +')">삭제하기</a>'
										+ '</div>'
										+ '</div>';
					$('#detail').html(contentValue);
					$('#detail').slideDown(500);
										
										
				}
			})
			
		});
		
		
		
		
		function findAll() {
			$.ajax({
				url: "notice",
				type: 'get',
				success: response => {
					console.log(response.data);
					
					const outerDiv = document.createElement("div");
					outerDiv.id = 'outerDiv';
					
					
					response.data.map((item) => {
						
						const noticeEl = document.createElement('div');
						noticeEl.className = 'noticeEl';
						
						
						
						noticeEl.appendChild(createDiv(item.noticeNo , '50px'));
						noticeEl.appendChild(createDiv(item.noticeTitle , '400px'));
						noticeEl.appendChild(createDiv(item.noticeWriter , '150px'));
						noticeEl.appendChild(createDiv(item.createDate , '200px'));
						
						outerDiv.appendChild(noticeEl);
						console.log(noticeEl);
					});
					
					document.getElementById('content').appendChild(outerDiv);
					
				}
			})
		}
		
		function createDiv(data, style) {
			const divEl = document.createElement('div');
			const divText = document.createTextNode(data);
			divEl.style.width = style;
			divEl.appendChild(divText);
			return divEl;
		}
		
		
	</script>

	

</body>
</html>