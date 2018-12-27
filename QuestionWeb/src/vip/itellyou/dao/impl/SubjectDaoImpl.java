package vip.itellyou.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import vip.itellyou.dao.SubjectDao;
import vip.itellyou.pojo.Subject;
import vip.itellyou.pojo.SubjectQueryModel;
import vip.itellyou.util.base.BaseDaoImpl;
import vip.itellyou.util.base.BaseQueryModel;

public class SubjectDaoImpl extends BaseDaoImpl implements SubjectDao {

	@Override
	public String getInsertSql(Object data) {
		Subject subject = (Subject)data;
		
		return "insert into t_subject(title,number,startTime,endTime,userId) values('"+subject.getTitle()+"',"+subject.getNumber()+","+subject.getStartTime()+","+subject.getEndTime()+","+subject.getUser().getId()+")";
	}

	@Override
	public String getUpdateSql(Object data) {
		Subject subject = (Subject)data;
		return "update t_subject set title='"+subject.getTitle()+"',number="+subject.getNumber()+",starttime="+subject.getStartTime()+",endtime="+subject.getEndTime()+" where id="+subject.getId();
	}

	@Override
	public String getDeleteSql(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFindAllSql() {		
		return "select * from t_subject";
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		//将一条记录转换成一个实体类对象
		Subject subject= new Subject();
		//将rs中记录的字段值读取出来
		//赋值给实体类对象的属性
		subject.setId(rs.getLong("id"));
		subject.setTitle(rs.getString("title"));
		subject.setNumber(rs.getInt("number"));
		subject.setStartTime(rs.getLong("startTime"));
		subject.setEndTime(rs.getLong("endTime"));
		subject.getUser().setId(rs.getLong("userId"));
		
		return subject;
	}

	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel) {
		// 构造带条件的查询SQL语句：用户表t_option
		// 拼接sql语句：String
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_subject ");
		sb.append("where 1=1 ");	//如果只有一个条件，不需要判断后面是否加and
		SubjectQueryModel qm = (SubjectQueryModel)queryModel;
		if(qm.getTitle()!=null&&qm.getTitle().trim().length()>0) {
			sb.append("and title='"+qm.getTitle()+"'");
		}
		if(qm.getNumber()>0){
			sb.append("and number="+qm.getNumber());
		}
		if(qm.getStartTime()!=null){
			sb.append("and starttime="+qm.getStartTime());
		}
		if(qm.getEndTime()!=null){
			sb.append("and endtime="+qm.getEndTime());
		}
		if(qm.getUser()!=null) {
			sb.append("and userId="+qm.getUser().getId());
		}
		return sb.toString();
	}

	@Override
	public String getFindOneSql(int id) {
		// 返回查询t_user中指定id的所有属性的语句
		return "select * from t_subject where id="+id;
	}
	public String getFindStringAllSql(String s) {
		return "select * from t_subject where title like "+"'%"+s+"%'";
	}

}
