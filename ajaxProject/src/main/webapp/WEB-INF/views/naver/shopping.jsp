<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  .items {
    width: 1000px;
    margin: auto;
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
  }

</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg bg-dark">
		<div class="container-fluid">
			<a class="navbar-barnd">
				<img href="">
			</a>
		</div>
		<div class="container-fluid">
			<a class="navbar-barnd">NavBar</a>
			<form class="d-flex" role="search">
				<input class="form-control me-2" id="keyword" type="search" placeholder="상품명을 입력하세요" aria-label="Search" />
				<button type="button" style="width: 300px;" class="btn btn-outline-success" id="search-btn">클릭클릭</button>
			</form>
		</div>
	</nav>
	
	
	<div id="kh-bg">
	
	</div>

	<hr/>
	<br><br><br><br><br><br><br><br>
	
	<div id="total-count" style="display: none; padding: 30px; text-align: right";>
		
	</div>
	<br>
	<div class="items">
	
	</div>
	
	<button class="button btn-lg btn-primary" style="background: lightgreen; color: white; font-weight: bold;" onclick="nextPage()">다음</button>
	
	<script>
	
		$('#search-btn').click(() => {
			searchItem();
		})
	
		
		var startNo = 1;
		
		
		function nextPage() {
			startNo += 9;
			searchItem();
		};
		
		function searchItem() {
			
			const keyword = $('#keyword').val();
			
			$.ajax({
				
				url: 'product',
				type: 'get',
				data: {
					keyword : keyword,
					start : startNo
				},
				success : product => {
					$('#total-count').fadeOut(300);
					console.log(product);
					$('#total-count').html('총 <b>' + product.total + '</b>건의 '
										  + '<label style="color:red; font-weight:bold;">' + keyword + '</label>이(가) 검색되었습니다.')
										  .fadeIn(1000);
					
					const items = product.items;
					
					let item = '';
					
					for(let i in items) {
						
						item += '<div class="card" style="width: 18rem;">'
						      + '<img src="' + items[i].image + '" class="card-img-top" alt="...">'
							  + '<div class="card-body">'
							  +   '<h5 class="card-title">' + items[i].brand + '</h5>'
							  +	  '<p class="card-text">' + items[i].title + '</p>'
							  + '</div>'
							  + '<ul class="list-group list-group-flush">'
							  +   '<li class="list-group-item">가격</li>'
							  +   '<li class="list-group-item">' + items[i].lprice + '</li>'
							  + '</ul>'
							  + '<div class="card-body">'
							  +   '<a href="' + items[i].link + '" class="btn btn-primary" target="_blank">구매하러 가기</a>'
							  + '</div>'
							  + '</div>'
					};
					
					$('.items').html(item);
				}
				
			})
		}
	</script>
	
	
	
	
	
</body>
</html>