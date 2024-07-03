<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	
	<h1>부산 수질에 대해 알아보자</h1>
	
	
	<table class="table table-success">
		<thead>
			<tr>
				<th>측정지점</th>
				<th>대장균 측정값</th>
				<th>장구균 측정값</th>
				<th>측정일시</th>
			</tr>
		</thead>
		<tbody>
		
		
		</tbody>
	</table>
	
	<script>
		$(() => {
			
			var pageNo = 1;
			getBeachInfo(pageNo);
		});
			function getBeachInfo(pageNo) {
				$.ajax({
			
				url: 'beach',
				type: 'get',
				data: {
					pageNo : pageNo
				},
				success: info => {
					console.log(info);
					const items = info.getBeachInfo.body.items.item;
					
					let strEl = '';
					
					for(let i in items) {
						const item = items[i];
						
						strEl += '<tr>'
						       + '<td>' + item.inspecArea + '</td>'
						       + '<td>' + item.water1 + '</td>'
						       + '<td>' + item.water2 + '</td>'
						       + '<td>' + item.inspecYm + '</td>'
						       + '</tr>';
					}
					
					$('tbody').html(strEl);
					
				}
				});
			}
	
	</script>
	
	
	
</body>
</html>