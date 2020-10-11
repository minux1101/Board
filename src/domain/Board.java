package domain;

public class Board {
	private int id;  //���� ��ȣ
	private String title;  //������ ����
	private String date;  //������ �� ��¥
	private String content; //������ ����
	private int rootid; //������ ���� ��ȣ
	private int relevel; //������ ��� ����
	private int recnt;  //��� �켱 ����
	private int viewcnt; //��ȸ��
	
	public Board() { //������ �⺻ ������
		
	}

	//������ ������. id�� ����, ��¥, ����, ���� ��ȣ, ��� �켱����, ��ȸ���� �ʿ�
	public Board(int id, String title, String date, String content, int rootid, int relevel, int recnt, int viewcnt) {  
		this.id = id;
		this.title = title;
		this.date = date;
		this.content = content;
		this.rootid = rootid;
		this.relevel = relevel;
		this.recnt = recnt;
		this.viewcnt = viewcnt;
	}

	public int getId() { //id�� getter
		return id;
	}
	public void setId(int id) {   //id�� setter
		this.id = id;
	}
	public String getTitle() {   //������ getter
		return title;
	}
	public void setTitle(String title) {  //������ setter
		this.title = title;
	}
	public String getDate() {  //��¥�� getter
		return date;
	}
	public void setDate(String date) {  //��¥�� setter
		this.date = date;
	}
	public String getContent() {   //������ getter
		return content;
	}
	public void setContent(String content) {   //������ setter
		this.content = content;
	}	
	
	public int getRootid() {  //������ getter
		return rootid;
	}
	public void setRootid(int rootid) { //������ setter
		this.rootid = rootid;
	}
	public int getRelevel() { //��� ���� getter
		return relevel;
	}
	public void setRelevel(int relevel) { //��� ���� setter
		this.relevel = relevel;
	}
	public int getRecnt() {  //��� �켱���� getter
		return recnt;
	}
	public void setRecnt(int recnt) { //��� �켱���� setter
		this.recnt = recnt;
	}
	public int getViewcnt() { //��ȸ�� getter
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) { //��ȸ�� setter
		this.viewcnt = viewcnt;
	}
		
}
