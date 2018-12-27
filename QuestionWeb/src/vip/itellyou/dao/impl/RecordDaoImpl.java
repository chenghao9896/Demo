package vip.itellyou.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import vip.itellyou.dao.RecordDao;
import vip.itellyou.pojo.Record;
import vip.itellyou.pojo.RecordQueryModel;
import vip.itellyou.util.base.BaseDaoImpl;
import vip.itellyou.util.base.BaseQueryModel;

public class RecordDaoImpl extends BaseDaoImpl implements RecordDao {

	@Override
	public String getInsertSql(Object data) {
		
		Record record = (Record)data;
		return "insert into t_record(subjectId, optionId, userId) values(" +record.getSubjectId() +","+record.getOptionId()+","+record.getUserId()+")";
	}

	@Override
	public String getUpdateSql(Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteSql(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFindAllSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFindOneSql(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		Record record = new Record();
		record.setId(rs.getLong("id"));
		record.setSubjectId(rs.getLong("subjectId"));
		record.setOptionId(rs.getLong("optionId"));
		record.setUserId(rs.getLong("userId"));
		return record;
	}

	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel) {
		// 构造带条件的查询SQL语句：用户表t_option
		// 拼接sql语句：String
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_record ");
		sb.append("where 1=1 ");	//如果只有一个条件，不需要判断后面是否加and
		// 看数据模型对象中某些属性是否有值
		// 有值的属性就要拼接到条件中来
		//新建查询模型
		RecordQueryModel qm = (RecordQueryModel)queryModel;
		if(qm.getSubjectId()!=null){
			sb.append("and subjectId="+qm.getSubjectId());
		}
		if(qm.getOptionId()!=null){
			sb.append("and optionId="+qm.getOptionId());
		}
		if(qm.getUserId()!=null){
			sb.append("and userId="+qm.getUserId());
		}
		return sb.toString();
	}
	
}
