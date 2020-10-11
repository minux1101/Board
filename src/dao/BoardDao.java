package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Board;

public class BoardDao {
	
	public Connection connect() { //DB�� �����ϴ� �޼ҵ�
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  // mysql ����̹� �ε�, ����̹����� �����⸸ �ϸ� �ڵ� ��ü�� �����ǰ� DriverManager�� ��ϵȴ�.
			con = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/koposw19", "root", "root"); // ����� �������� mysql�� koposw19 �����ͺ��̽��� ����,����id root, password root�� ����
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con; //con�� �����Ѵ�.
	}
	
	
	public static void conClose(Connection con) {  //connection ���� �޼ҵ�
	      try {
	         if (con != null)
	            con.close();
	      } catch (SQLException e) {
	      }
	   }
	
	public void create() {  // DB�� ���̺��� ����� �޼ҵ�
		try {
		Connection con = connect();  //connection �޼ҵ带 ȣ���ؼ� DB�� ����
		Statement stmt = con.createStatement(); // Statement ��ü ���� sql ������ ����/�����ϸ�, ��ȯ�� ����� �������� �� �۾� ������ ����
		stmt.execute("create table board(" +
				"id int not null primary key auto_increment," + 
				"title varchar (70)," + 
				"date date," + 
				"content text," +
				"rootid int," +
				"relevel int," +
				"recnt int," +
				"viewcnt int)" + 
				"DEFAULT CHARSET=utf8;"); //gongji ���̺��� �����. id, ���� ��¥, ���� �ʵ尡 ����.
		stmt.close(); //statement �ݱ�
		con.close();  //connection �ݱ�
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	
	
	public void insert(Board board) {  //DB���̺� �����͸� �Է��ϴ� �޼ҵ�
		int result = 0;
		try {
		Connection con = connect();
		PreparedStatement psmt = con.prepareStatement("insert into board values(?, ?, ?, ?, ?, ?, ?, ?);");
		psmt.setInt(1, board.getId());
		psmt.setString(2, board.getTitle());
		psmt.setString(3, board.getDate());
		psmt.setString(4, board.getContent());
		psmt.setInt(5, board.getRootid());
		psmt.setInt(6, board.getRelevel());
		psmt.setInt(7, board.getRecnt());
		psmt.setInt(8, board.getViewcnt());
		result = psmt.executeUpdate(); // �� ���� �ԷµǾ����� �ݿ���.
		
		psmt.close(); // Preparedstatement �ݱ�
		con.close();  // connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> selectPage(int startNum, int cnt) {  //DB���̺� �ִ� ��� �����͸� �������� �޼ҵ�
		List<Board> list = new ArrayList<Board>(); //Gongji Ÿ���� list ����
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from board order by rootid desc, recnt asc limit " + startNum + ", " + cnt + ";");
			while(rs.next()) {  // while���� �����ϸ鼭 list�� ���� �߰��Ѵ�.
				Board board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				list.add(board);
			}
			rs.close();  //result �ݱ�
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list; // ������� list ����, ���۷��� Ÿ���̶� �ּҸ� �ۿ��� ���� �� �ִ�.
	}
	
	public List<Board> searchAll(String content, String search) {  //DB���̺� �ִ� ��� �����͸� �������� �޼ҵ�
		List<Board> list = new ArrayList<Board>(); //Gongji Ÿ���� list ����
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from board where " + content + " like '%" + search +"%' order by rootid desc, recnt asc;");
			while(rs.next()) {  // while���� �����ϸ鼭 list�� ���� �߰��Ѵ�.
				Board board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				list.add(board);
			}
			rs.close();  //result �ݱ�
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list; // ������� list ����, ���۷��� Ÿ���̶� �ּҸ� �ۿ��� ���� �� �ִ�.
	}
	
	public List<Board> search(String content, String search, int startNum, int cnt) {  //DB���̺� �ִ� ��� �����͸� �������� �޼ҵ�
		List<Board> list = new ArrayList<Board>(); //Gongji Ÿ���� list ����
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from board where " + content + " like '%" + search +"%' order by rootid desc, recnt asc limit " + startNum + ", " + cnt + ";");
			while(rs.next()) {  // while���� �����ϸ鼭 list�� ���� �߰��Ѵ�.
				Board board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				list.add(board);
			}
			rs.close();  //result �ݱ�
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list; // ������� list ����, ���۷��� Ÿ���̶� �ּҸ� �ۿ��� ���� �� �ִ�.
	}
	
	
	public List<Board> selectAll() {  //DB���̺� �ִ� ��� �����͸� �������� �޼ҵ�
		List<Board> list = new ArrayList<Board>(); //Gongji Ÿ���� list ����
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from board;");
			while(rs.next()) {  // while���� �����ϸ鼭 list�� ���� �߰��Ѵ�.
				Board board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				list.add(board);
			}
			rs.close();  //result �ݱ�
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list; // ������� list ����, ���۷��� Ÿ���̶� �ּҸ� �ۿ��� ���� �� �ִ�.
		}
	
	public Board selectOne(int id) { //DB���̺� �ִ� �ĺ��� �� ��ȣ�� �ش��ϴ� �� ���� �������� �޼ҵ�
		Board board = null; //Board ��ü�� ����.
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from board where id = " + id);
			while(rs.next()) { //board�� DB���� �޾ƿ� ���� ���ʴ�� �Է��Ѵ�.
				board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
			}		
			
			rs.close();  //result �ݱ�
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return board; //board�� �����Ѵ�.
	}
	
	public int count() { //board ���̺� �����͸� ���� �޼ҵ�
		int result = 0; //������ ���� result ����
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) from board;");
			while(rs.next()) { //result�� ��������� �����Ѵ�.
				result = rs.getInt(1);
			}
			
			rs.close();  //result �ݱ�
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return result; //result�� ����
	}
	
	public int countOne(int id) { //�� �ĺ����� ��ǥ���� ���� �޼ҵ�
		int result = 0; //������ ���� result ����
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) from board where id = " + id);
			while(rs.next()) {
				result = rs.getInt(1); //result�� ��������� �����Ѵ�.
			}
			
			rs.close();  //result �ݱ�
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return result; //result�� ����
	}	
		
	public Board update(Board board) { //DB ���̺� �ִ� ���� �����ϴ� �޼ҵ�
		int result = 0;
		try {
			Connection con = connect();
			// �ش� ��ȣ ���� ������ �����Ѵ�. Board ��ü���� ���� �����ͼ� �� ������ ������Ʈ ����
			PreparedStatement pstmt = con.prepareStatement("update board set title = ?, content = ? where id = ?;");
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			result = pstmt.executeUpdate(); // �� ���� �ԷµǾ����� �ݿ���.
			
			pstmt.close(); // Preparedstatement �ݱ�
			con.close(); // connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return board; //board ����
	} 
	
	public void updateRecnt (int rootid, int recnt) {
		
		int result = 0;
		try {
			Connection con = connect();
			// �ش� ��ȣ ���� ������ �����Ѵ�. Board ��ü���� ���� �����ͼ� �� ������ ������Ʈ ����
			PreparedStatement pstmt = con.prepareStatement("update board set recnt = recnt + 1 where rootid = ? and relevel != 0 and recnt >= ?;");
			pstmt.setInt(1, rootid);
			pstmt.setInt(2, recnt);
			result = pstmt.executeUpdate(); // �� ���� �ԷµǾ����� �ݿ���.
			
			pstmt.close(); // Preparedstatement �ݱ�
			con.close(); // connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public int updateViewcnt(int id) { 
		int result = 0;
	    try {
	    	Connection con = connect();
	    	Statement stmt = con.createStatement();
	        stmt.execute("UPDATE board SET viewcnt = viewcnt + 1 where id = " + id); 
	        ResultSet rs = stmt.executeQuery("select viewcnt from board where id = " + id); 
	        while(rs.next()) {
	        	result = rs.getInt(1);
	    	}	
	            
	        stmt.close(); //statement �ݱ�
		    con.close();  //connection �ݱ�
	       } catch(Exception e) {
	    	   e.printStackTrace();
	       }
	      return result; //result ����
	 }

	public void delete(int id) {  //DB�� �ִ� �����͸� �����ϴ� �޼ҵ�
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.execute("DELETE from board where id = " + id);
			stmt.execute("DELETE from board where rootid = " + id);
			
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void drop() {  //DB�� �ִ� ���̺��� �����ϴ� �޼ҵ�
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.execute("DROP TABLE board;");
			
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

