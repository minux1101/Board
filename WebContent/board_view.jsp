<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="domain.*,service.*" %>
<%@ page import="java.util.ArrayList,java.util.List,java.text.SimpleDateFormat" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<boby>
	<%
		int id = Integer.parseInt(request.getParameter("key")); //글 번호를 인자로 받아서 정수로 변환
		BoardService bs = new BoardService();
		bs.updateViewcnt(id);
		Board board = bs.selectOne(id); //해당 글 번호의 공지 객체를 들고옴
		FileService fser = new FileService();
		File file = fser.selectOne(id); //해당 글 번호의 파일 객체를 들고옴
		
	%>
	<form method="post" name="v">
	<table cellspacing=1 width=600px border=1> <!-- 들고온 공지의 값들을 테이블에서 출력한다. -->
	<tr> 
		<td width=70px>번호</td>
		<td colspan=2><%=id%></td>
	</tr>
	<tr> 
		<td width=70px>제목</td>
		<td colspan=2><%=board.getTitle()%></td>
	</tr>
	<tr> 
		<td width=70px>일자</td>
		<td colspan=2><%=board.getDate()%></td>
	</tr>
	<tr> 
		<td width=70px>조회수</td>
		<td colspan=2><%=board.getViewcnt()%></td>
	</tr>
	<% 
  	if(file == null) { //해당 공지번호의 파일 객체가 없으면 빈칸으로 출력
	%>
   <tr>
   		<td>파일</td>
   		<td></td>
   </tr>
	<%       
   } else {  //해당 공지번호의 파일 객체가 있으면 파일이름 출력하고, 다운로드 할 수 있게 한다.
	%>
   <tr>
      <td>파일</td>
      <td><a href="upload/<%=file.getFileName()%>" download><%=file.getFileName()%></a></td>
   </tr>
<%      
   }
%>
	<tr> 
		<td width=70px>내용</td>
		<td colspan=2 style=overflow:scroll; height=300px><%=board.getContent()%></td>
	</tr>
	<tr> 
		<td width=70px>원글</td>
		<td colspan=2><input style="border:none;" type="text" name="rootId" id="rootId" value="<%=board.getRootid()%>"></td>
	</tr>
	<tr> 
		<td width=70px>댓글수준</td>
		<td style=border:0px; width=200px><%=board.getRelevel()%></td>
		<td style=border:0px;>댓글내 순서  <input style=width:100px type="text" value="<%=board.getRecnt()%>"></td>
	</tr>
	</table>
	<table width=650px>
	<tr>
		<td width=400px></td>
		<td><input type=button value="목록" Onclick="window.location='board_list.jsp'"> 
		<input type=button value="수정" Onclick="window.location='board_update.jsp?key=<%=id%>'">
		<input type=button value="삭제" Onclick="window.location='board_delete.jsp?key=<%=id%>'">
		<input type=button value="댓글" Onclick="window.location='board_reinsert.jsp?key=<%=id%>'"></td>
	</tr>
	</table>
</body>
</html>