package vip.itellyou.pojo;
/**
 * 用户实体类
 * 1>字段=>属性
 * 2>给每个属性提供getter和setter访问器
 * 3>考虑重载构造方法（必须保留默认的构造方法）
 * 4>视图属性，视图值：提供属性记录
 * @author 程浩
 *
 */
public class User {
	//null表示没有值
	private Long id;
	private String name;
	private String pwd;
	//1 不在线  2 在线
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
