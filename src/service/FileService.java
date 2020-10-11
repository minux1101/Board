package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.FileDao;
import domain.File;

public class FileService {
	
	FileDao filedao = new FileDao();
	
	public void create() { // DB�� ���̺��� ����� �޼ҵ�
		filedao.create();
	}
	
	public void upload(String fileName, String fileRealName, int rootid) { //DB�� ���� �����͸� �ϳ� �Է��ϴ� �޼ҵ�
		filedao.upload(fileName, fileRealName, rootid);
	}
	
	public File selectOne(int rootid) {   //DB���̺� �ִ� ���ϵ����͵� �� ��ȣ�� �ش��ϴ� �� ���� �������� �޼ҵ�
		return filedao.selectOne(rootid);  
	}
	
	public void delete(int id) {  //DB�� �ִ� �����͸� �����ϴ� �޼ҵ�
		filedao.delete(id);
	}
	
	public void drop() {  //DB�� �ִ� ���̺��� �����ϴ� �޼ҵ�
		filedao.drop();
	}
	
}
