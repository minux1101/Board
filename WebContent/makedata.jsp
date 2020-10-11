<meta charset="UTF-8" content="text/html"; http-equiv="Content-type" /> <!--html에서의 한글처리-->
<%@ page contentType="text/html; charset=UTF-8" %> <!--jsp에서의 한글처리-->
<%@ page import="java.sql.*, javax.sql.*, java.io.*, java.util.*, service.*, domain.*" %> <!--필요한 임포트-->

<!DOCTYPE html>
<html>
<body>
<title>실습데이터넣기</title>
<% 
    request.setCharacterEncoding("utf-8");  // 해당 페이지의 캐릭터 인코딩 utf-8로 지정
    BoardService boardService = new BoardService();  //Board서비스 객체 생성
    FileService fileService = new FileService(); //File 서비스 객체 생성
    String today = boardService.today(); // 현재 날짜   
%>


<head>
</head>
<body> <!-- 내용 기입 -->
<%  
    boardService.create(); //공지 서비스의 create출 실행
    fileService.create();  //파일 서비스의 create출 실행
	for(int i = 0; i < 10; i++) { //0부터 9까지 for문을 실행하면서 공지 테이블에 값을 입력한다.
		Board board = new Board();
        board.setTitle("공지사항" + String.format("%02d",i+1));
        board.setDate(today);
        board.setContent("");
        board.setRootid(i+1);
        board.setRelevel(0);
        board.setRecnt(0);
        boardService.insert(board); 
      }
%>
<script>
	alert("등록이 완료되었습니다.");
	window.location.href="board_list.jsp";
</script>
</body>
</html>