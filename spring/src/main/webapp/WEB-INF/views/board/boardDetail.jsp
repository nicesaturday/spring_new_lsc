<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style> 
        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
        
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="boardlist">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${board.boardTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${ board.boardWriter}</td>
                    <th>작성일</th>
                    <td>${ board.createDate }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    
                    <c:choose>
                      <c:when test="${ empty board.originName}">
                    	<td colspan="3">
                        	파일이 없습니다.
                    	</td>
                       </c:when>
                       <c:otherwise>
                         <td colspan="3">
                        	<a href="${ board.changeName }" download="${ board.originName }">${board.originName }</a>
                    	 </td>
                       </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3">${ board.boardContent }</td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${ board.count }</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
              <c:if test="${ sessionScope.getMember.userId eq board.boardWriter }">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
                <a class="btn btn-primary" onclick="postSubmit(this.innerHTML)">수정하기</a>
                <a class="btn btn-danger" onclick="postSubmit(this.innerHTML)">삭제하기</a>
              </c:if> 
              
              
              <form action="" method="post" id="postForm">
              	<input type="hidden" name="boardNo" value=${board.boardNo }>
              	<input type="hidden" name="filePath" value=${ board.changeName }>
              </form>
              
              <script>
              	function postSubmit(eh) {
              		
              		
              		const result = eh == '수정하기' ? 'boardUpdate' : 'boardDelete';
              		
              		$('#postForm').attr('action' , result).submit();
              		
              	}
              </script>
              
              
              
            </div>
            <br><br>

            <!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                
                	<c:choose>
                 	<c:when test="${ empty sessionScope.getMember.userId }">
                 	  <tr>
                        <th colspan="2">
                            <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;">
                              로그인 이후 사용 가능합니다.
                            </textarea>
                        </th>
                        <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th> 
                      </tr>
                 	</c:when>
                
                    
                    <c:otherwise>
                    	<tr>
                        <th colspan="2">
                            <textarea class="form-control" name="replyContent" id="content" cols="55" rows="2" style="resize:none; width:100%;">
                            
                            </textarea>
                        </th>
                        <th style="vertical-align:middle"><button onclick="saveReply()" class="btn btn-secondary">등록하기</button></th> 
                      </tr>
                    
                    </c:otherwise>
                    
                    </c:choose>
                    
                    
                    
                    <tr>
                        <td colspan="3">댓글(<span id="rcount"></span>)</td>
                    </tr>
                </thead>
                <tbody>
                    
                    
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    <script>
    function saveReply() {
    	if($('#content').val().trim() != '') {
    		$.ajax({
    			url:'reply',
    			type: 'post',
    			data: {
    				refBoardNo : ${board.boardNo},
    				replyContent : $('#content').val(),
    				replyWriter : "${ sessionScope.getMember.userId}"
    			},
    			success : res => {
    				if(res == "success") {
    					selectReply();
    					$('#content').val('');
    				}
    			}
    			
    		})
    	}else {
    		alert("글을 작성해주세요.");
    	}
    	
    }

    
    $(() => {
      selectReply();
    })
    
    function selectReply() {
    	$.ajax({
    		url: "reply",
    		type: "get",
    		data: {
    			boardNo: ${board.boardNo}
    		},
    		success: result => {
    			let resultStr = '';
    			
    			for(let i in result) {
    				resultStr += '<tr>'
    						+	'<td>' + result[i].replyWriter + '</td>'
    						+	'<td>' + result[i].replyContent + '</td>'
    						+	'<td>' + result[i].createDate + '</td>'
    						+ '</tr>';
    			}
    			$('#replyArea tbody').html(resultStr);
    			$('#rcount').text(result.length);
    			console.log(result);
    			console.log(resultStr);
    		}
    	})
    }
    
    
    
    </script>
    <jsp:include page="../common/footer.jsp" />
    
</body>
</html>