<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="domain.Board,service.BoardService" %>
<%@ page import="java.util.*" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">  <!-- 글 편집기를 사용할 수 있게 만들어준다. -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<script>
$(document).ready(function() {
	  $('#summernote').summernote({
 	    	placeholder: 'content',
	        minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	});
</script>
<script>
function checking() {  /*제목이 공백이나 null값이면 경고창이 나온다*/
	if (document.getElementById("title").value == "" || document.getElementById("title").value == null) {
		alert("빈 칸을 채워주세요");
	} else {
		document.rein.action="./board_write.jsp"; /*제목들이 공백이나 null값이 아니면 board_write.jsp로 이동*/
		document.rein.submit();  /*rein의 값을 넘긴다.*/
	}
}
</script>
</head>
<boby>
	<%
	BoardService serv = new BoardService();
	String day = serv.today();
	request.setCharacterEncoding("utf-8"); /*request로 받아온 파라미터의 인코딩을  utf-8형식으로*/
	int n = Integer.parseInt(request.getParameter("key")); /*공지 번호를 인자로 받아옴*/
	Board brd = serv.selectOne(n); //공지 번호에 해당하는 공지를 들고온다.
	%>
	<form method="post" name="rein" enctype="multipart/form-data">
	<table cellspacing=1 width=600px border=1>
	<tr> 
		<td width=70px>번호</td>
		<td colspan=2>댓글<input style="width:200px" type="text" name="num" id="num" value="insert" readonly></td>
	</tr>
	<tr> 
		<td width=70px>제목</td>
		<td colspan=2><input style="width:450px;" type="text" name="title" id="title"></td>
	</tr>
	<tr> 
		<td width=70px>일자</td>
		<td colspan=2><%=day%></td>
	</tr>
	<tr> 
		<td width=70px>파일</td>
		<td><input type="file" name="file1"></td>
	</tr>
	<tr> 
		<td width=70px>내용</td>		
		<td colspan=2 height=300px><textarea id="summernote" style="width:500px;height:200px;overflow-y:scroll;resize:none;"  type="text" name="content"></textarea></td>
	</tr>
	<tr> 
		<td width=70px>원글</td>
		<td colspan=2><input style="width:200px" type="text" name="root" id="root" value="<%=brd.getRootid()%>" readonly></td>
	</tr>
	<tr>
		<td width=70px>댓글 수준</td>
		<td style=border:0px;><input style="width:200px" type="text" name="relv" id="relv" value="<%=brd.getRelevel()+1%>" readonly></td>  <!-- 들고온 공지의 relevel에 1을 더함 -->
		<td style=border:0px;>댓글내 순서 <input style="width:100px" type="text" name="recnt" id="recnt" value="<%=brd.getRecnt()+1%>" readonly></td> <!-- 들고온 공지의 recnt에 1을 더함 -->
	</tr>
	</table>	
	<table width=650px>
	<tr>
		<td width=500px></td>
		<td><input type=button value="취소" Onclick="window.location='board_list.jsp'"> 
		<input type=button value="쓰기" Onclick="checking()"></td>
	</tr>
	</table>
</body>
</html>