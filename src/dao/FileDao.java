package dao;

import java.sql.*;
import java.util.*;

import domain.Board;
import domain.File;

public class FileDao {

	public Connection connect() {
	Connection con = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver"); // mysql ����̹� �ε�, ����̹����� �����⸸ �ϸ� �ڵ� ��ü�� �����ǰ� DriverManager�� ��ϵȴ�.
		con = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/koposw19", "root", "root"); // ����� �������� mysql�� koposw19 �����ͺ��̽��� ����,����id root, password root�� ���� 
	} catch(Exception e) {
		e.printStackTrace();
	}
	return con;	
	}
	
	public static void conClose(Connection con) {  //connection ���� �޼ҵ�
	      try {
	         if (con != null)
	            con.close();
	      } catch (SQLException e) {
	      }
	   }
	
	public void create() { // DB�� ���̺��� ����� �޼ҵ�
		try {
			Connection con = connect(); //connection �޼ҵ带 ȣ���ؼ� DB�� ����
			Statement stmt = con.createStatement(); // Statement ��ü ���� sql ������ ����/�����ϸ�, ��ȯ�� ����� �������� �� �۾� ������ ����
			stmt.execute("create table file(" +
						 "id int not null auto_increment primary key," +
						"fileName varchar(200)," +
						"fileRealName varchar(200)," +
						"rootid int not null," +
						"FOREIGN KEY(rootid) REFERENCES board(id)" + 
						"on update cascade " + 
						"on delete cascade" +
						") DEFAULT CHARSET=utf8;"); //file���̺��� �����. ��ȣ, ����ڰ� �Է��� ���� �̸��� ������ ���� �̸�, ���� ��ȣ�� �ʿ��ϴ�.
			
			stmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int upload(String fileName, String fileRealName, int rootid) { //file�� insert�ϴ� �޼ҵ�
		int result = 0; 
		try {
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement("insert into file(fileName, fileRealName, rootid) values(?, ?, ?)");
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			pstmt.setInt(3,  rootid);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public File selectOne(int rootid) { //DB���̺� �ִ� file ������ �� ��ȣ�� �ش��ϴ� �� ���� �������� �޼ҵ�
		File file = null; //file ��ü�� ����.
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from file where rootid = " + rootid);
			while(rs.next()) { //file�� DB���� �޾ƿ� ���� ���ʴ�� �Է��Ѵ�.
				file = new File(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}		
			
			rs.close();  //result �ݱ�
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return file; //file�� �����Ѵ�.
	}
	
	public void delete(int id) {  //DB�� �ִ� �����͸� �����ϴ� �޼ҵ�
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.execute("DELETE from file where id = " + id);
			
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
			stmt.execute("DROP TABLE file;");
			
			stmt.close(); //statement �ݱ�
			con.close();  //connection �ݱ�
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}