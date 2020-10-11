package domain;

public class File { 
	
	private int id; //file�� ��ȣ
	private String fileName; // ����ڰ� �Է��� ���� �̸�
	private String fileRealName; //�ý����� �ԷµǴ� �����̸�
	private int rootid; // �ش� ������ ������ȣ

	public File(int id, String fileName, String fileRealName, int rootid) { //���� ������
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.rootid = rootid;
	}

	public int getId() {  //��ȣ�� getter
		return id;
	}
	
	public void setId(int id) { //��ȣ�� setter
		this.id = id;
	}
	
	public String getFileName() { //����ڰ� ���� ���� �̸��� getter
		return fileName;
	}
	
	public void setFileName(String fileName) { //����ڰ� ���� ���� �̸��� setter
		this.fileName = fileName;
	}
	
	public String getFileRealName() { //�ý��ۿ� ����� ���� �̸��� getter
		return fileRealName;
	}
	
	public void setFileRealName(String fileRealName) { //�ý��ۿ� ����� ���� �̸��� setter
		this.fileRealName = fileRealName;
	}
	
	public int getRootid() { // ������ȣ getter
		return rootid;
	}
	
	public void setRootid(int rootid) { // ������ȣ setter
		this.rootid = rootid;
	}

}
