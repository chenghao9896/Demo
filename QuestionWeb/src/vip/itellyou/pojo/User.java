package vip.itellyou.pojo;
/**
 * �û�ʵ����
 * 1>�ֶ�=>����
 * 2>��ÿ�������ṩgetter��setter������
 * 3>�������ع��췽�������뱣��Ĭ�ϵĹ��췽����
 * 4>��ͼ���ԣ���ͼֵ���ṩ���Լ�¼
 * @author �̺�
 *
 */
public class User {
	//null��ʾû��ֵ
	private Long id;
	private String name;
	private String pwd;
	//1 ������  2 ����
	private int online;
	
	private String confirmPwd;
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
}
