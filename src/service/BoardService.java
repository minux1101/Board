package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.BoardDao;
import domain.Board;

public class BoardService {
	BoardDao boarddao = new BoardDao(); //Board�� ��ü ����
	
	public void create() {   /*Board ���̺��� �����ϴ�  boarddao�� �޼ҵ� create ȣ��*/
		boarddao.create();
	}	
		
	public void insert(Board board) {  /*Board ���̺� �����͸� �Է��ϴ� boarddao�� �޼ҵ� insert ȣ��*/
		List<Board> bList = selectAll();
		if (bList.size() <= 0) {  //DB�� �����Ͱ� ������ ������ id���� 1�� ���Ѵ�.
			board.setId(1);
		} else {  //�����Ͱ� ������ ������ id���� ���� ū ������ 1�� ���� ������ ����.
			board.setId(bList.get(bList.size()-1).getId() + 1);
		}
		if (board.getRelevel() == 0) { //������ 0�̸� rootid�� id�� ���� ������ �Ѵ�.
			board.setRootid(board.getId());
		}
		String date = today(); //������ �� ��¥�� date ������ ����
		board.setDate(date);  //������ �� ��¥�� date ������ ����
		boarddao.insert(board);		
	}
	
	public String today() { //�� ��  ��¥�� �����ϴ� �޼ҵ� today()
		Calendar cal = Calendar.getInstance(); // Calender Ŭ������ ��ücal�� �����ϰ�, �޼ҵ带 �̿��ؼ� ���糯¥�� �����´�.
		SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd"); // SimpleDateFormat Ŭ������ ��ä sdt�� �����ϰ� ��¥������ ���Ѵ�.
		return sdt.format(cal.getTime()); //getTime �޼ҵ�� ���� �ð��� �����ͼ� sdt�������� ��ȯ
	}
	
	// R	
	public List<Board> selectAll() { /*Board���̺� �ִ� �����͸� ��� �������� boarddao�� �޼ҵ� selectAll�� ȣ��*/   
		return boarddao.selectAll();
	}
	
	public Board selectOne(int id) { /*Board���̺� �ִ� ������ �ϳ��� ��������boarddao�� �޼ҵ� selectOne ȣ��*/
		return boarddao.selectOne(id);
	}
	
	public List<Board> selectPage(int startNum, int cnt) { //DB���̺� �ִ� ������ �� startNum���� cnt���� ��������  boarddao�� �޼ҵ� selectPAge ȣ��	
		return boarddao.selectPage(startNum, cnt);
	}
	
	public List<Board> searchAll(String condition, String search) {  //DB���̺� �ִ� ��� �����͸� �������� �޼ҵ�
		return boarddao.searchAll(condition, search); // ������� list ����, ���۷��� Ÿ���̶� �ּҸ� �ۿ��� ���� �� �ִ�.
	}
	
	public List<Board> search(String condition, String search, int startNum, int cnt) {	//�˻� ���ǿ� �ش��ϴ� ����� �����ϴ� �޼ҵ�
		return boarddao.search(condition, search, startNum, cnt);
	}
	
	public int count() {   /*�� ������ ���� ���� boarddao�� �޼ҵ� count ȣ��*/
		return boarddao.count();
	}
	
	//U
	public void update(Board gongji) { //DB ���̺� �ִ� ���� �����ϴ�  boarddao�� �޼ҵ� update�� ȣ��
		boarddao.update(gongji); 
	} 
	
	public void updateRecnt (int rootid, int recnt) { //recnt�� ������Ʈ �ϴ� �޼ҵ�
		boarddao.updateRecnt(rootid, recnt);
	}
	
	public int updateViewcnt(int id) { //��ȸ ���� ����ϴ� �޼ҵ�
		return boarddao.updateViewcnt(id);
	 }
	
	//D
	public void delete(int id) {  //DB�� �ִ� �����͸� �����ϴ�  boarddao�� �޼ҵ� delete�� ȣ��
		boarddao.delete(id);
	}
	
	public void drop() {   /* ���̺��� �����ϴ� boarddao�� �޼ҵ� drop�� ȣ��*/
		boarddao.drop();
	}
}
