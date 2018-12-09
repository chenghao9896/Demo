package vip.itellyou.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import vip.itellyou.dao.UserDao;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;
import vip.itellyou.util.base.BaseDaoImpl;
import vip.itellyou.util.base.BaseQueryModel;

/**
 * 数据访问的子类：用户
 * 1> 实现对应的子接口UserDao，继承父类BaseDaoImpl
 * @author 程浩
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public String getInsertSql(Object data) {
		//向下转型
		User user = (User)data;
		return "insert into t_user(name, pwd, online)" + "values('"+user.getName()+"', '"+user.getPwd()+"', '"+user.getOnline()+"')";
	}

	@Override
	public String getFindAllSql() {
		// 返回查询t_user中所有属性的sql语句
		return "select * from t_user";
	}
	
	@Override
	public String getFindOneSql(int id) {
		// 返回查询t_user中指定id的所有属性的语句
		return "select * from t_user where id="+id;
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		//1 创建User实体对象
		User user = new User();
		//2 从ResultSet结果集中读取一条记录，将每个字段的值赋值给User对象的对应属性
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setPwd(rs.getString("pwd"));
		user.setOnline(rs.getInt("online"));
		return user;
	}

	@Override
	public String getUpdateSql(Object data) {
		// 向下转型
		User user = (User)data;
		return "update t_user set name='"+user.getName()+"',pwd='"+user.getPwd()+"',online="+user.getOnline()+" where id="+user.getId();
	}

	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel){
		// 构造带条件的查询SQL语句：用户表t_user
		// 拼接sql语句：String
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_user ");
		sb.append("where 1=1 ");	//如果只有一个条件，不需要判断后面是否加and
		// 看数据模型对象中某些属性是否有值
		// 有值的属性就要拼接到条件中来
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
