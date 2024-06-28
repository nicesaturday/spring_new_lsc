<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>B강의장 프로젝트</title>
<style>
	.innerOuter {
		height: 800px;
	}
	
</style>
</head>
<body>

	<jsp:include page="common/menubar.jsp"></jsp:include>
	
	<div class="innerOuter">
	
		<h3>게시글 조회수 TOP 5</h3>
		<br>
		<a href="list.board" style="float: right; color: lightgrey; font-weight: 800; font-size: 14px">>> 더보기 </a>
		<br /><br />
		
		<table class="table table-hover" align="center" id="boardList">
			<thead>
				<tr>
				  <th>글번호</th>
				  <th>제목</th>
				  <th>작성자</th>
				  <th>조회수</th>
				  <th>작성일</th>
				  <th>첨부파일</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<br><br>
		
		<table id="board-detail" class="table">
			
		</table>
		
	</div>
	
	<script>
	
	$(document).on('click' , "#boardList > tbody > tr" , (e) => {
		
		const boardNo = $(e.currentTarget).children().eq(0).text();
		
		$.ajax({
			url: 'boards/' + boardNo,
			type: 'get',
			success: (res) => {
				console.log(res);
				let value = '<tr><td colspan="3">게시글 보기 ~~ </td></tr>';
				
				value += '<tr>'
					  +	 '<td width="300">' + res.boardTitle + '</td>'
					  +	 '<td width="300">' + res.boardContent + '</td>'
					  +	 '<td width="300">' + res.boardWriter + '</td>'
					  +	 '<td width="300">' + res.boardTitle + '</td>'
					  +  '</tr>'
					  +  '<br><br><br>' ;
					  
				$('#board-detail').html(value);					  
			}
		})
	})
	
	
	$(() => {
		findTopFiveBoard();
	})
	
	function findTopFiveBoard() {
		$.ajax({
			url: "boards",
			type: "get",
			success: (boards) => {
				console.log(boards);
				
				
				let value = "";
				
				for(let i in boards) {
					console.log(i);
					value += '<tr>'
						  +	 '<td>' + boards[i].boardNo + '</td>'
						  +	 '<td>' + boards[i].boardTitle + '</td>'
						  +	 '<td>' + boards[i].boardWriter + '</td>'
						  +	 '<td>' + boards[i].count + '</td>'
						  +	 '<td>' + boards[i].createDate + '</td>'
						  +  '<td>';
						  
						  if(boards[i].originName != null) {
							  value += '★★★★';
							  
						  }
						  +'</td></tr>';
						  
				}
				
				$('#boardList tbody').html(value);
				
				
			}
		})
		
		
	}
	
	
		
	</script>
	
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>