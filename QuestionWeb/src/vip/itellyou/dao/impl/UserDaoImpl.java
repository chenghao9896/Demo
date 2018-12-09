package vip.itellyou.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import vip.itellyou.dao.UserDao;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;
import vip.itellyou.util.base.BaseDaoImpl;
import vip.itellyou.util.base.BaseQueryModel;

/**
 * ���ݷ��ʵ����ࣺ�û�
 * 1> ʵ�ֶ�Ӧ���ӽӿ�UserDao���̳и���BaseDaoImpl
 * @author �̺�
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public String getInsertSql(Object data) {
		//����ת��
		User user = (User)data;
		return "insert into t_user(name, pwd, online)" + "values('"+user.getName()+"', '"+user.getPwd()+"', '"+user.getOnline()+"')";
	}

	@Override
	public String getFindAllSql() {
		// ���ز�ѯt_user���������Ե�sql���
		return "select * from t_user";
	}
	
	@Override
	public String getFindOneSql(int id) {
		// ���ز�ѯt_user��ָ��id���������Ե����
		return "select * from t_user where id="+id;
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		//1 ����Userʵ�����
		User user = new User();
		//2 ��ResultSet������ж�ȡһ����¼����ÿ���ֶε�ֵ��ֵ��User����Ķ�Ӧ����
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setPwd(rs.getString("pwd"));
		user.setOnline(rs.getInt("online"));
		return user;
	}

	@Override
	public String getUpdateSql(Object data) {
		// ����ת��
		User user = (User)data;
		return "update t_user set name='"+user.getName()+"',pwd='"+user.getPwd()+"',online="+user.getOnline()+" where id="+user.getId();
	}

	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel){
		// ����������Ĳ�ѯSQL��䣺�û���t_user
		// ƴ��sql��䣺String
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_user ");
		sb.append("where 1=1 ");	//���ֻ��һ������������Ҫ�жϺ����Ƿ��and
		// ������ģ�Ͷ�����ĳЩ�����Ƿ���ֵ
		// ��ֵ�����Ծ�Ҫƴ�ӵ���������
		UserQueryModel qm = (UserQueryModel)queryModel;
		if(qm.getName()!=null && qm.getName().trim().length()>0){
			sb.append("and name='"+qm.getName()+"'");
		}
		if(qm.getPwd()!=null&&qm.getPwd().trim().length()>0){
			sb.append("and pwd='"+qm.getPwd()+"'");
		}
		if(qm.getOnline()>0){
			sb.append("and online="+qm.getOnline());
		}
		return sb.toString();
	}

	@Override
	public String getDeleteSql(int id) {
		return "delete from t_user where id="+id;
	}

}
