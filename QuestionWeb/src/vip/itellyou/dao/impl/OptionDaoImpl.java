package vip.itellyou.dao.impl;

import java.sql.ResultSet;

import vip.itellyou.dao.OptionDao;
import vip.itellyou.pojo.Option;
import vip.itellyou.pojo.OptionQueryModel;
import vip.itellyou.pojo.UserQueryModel;
import vip.itellyou.util.base.BaseDaoImpl;
import vip.itellyou.util.base.BaseQueryModel;

public class OptionDaoImpl extends BaseDaoImpl implements OptionDao {

	@Override
	public String getInsertSql(Object data) {
		Option option =(Option)data;
		return "insert into t_option(content,idx,subjectId) values('"+option.getContent()+"',"+option.getIdx()+","+option.getSubjectId()+")";
	}

	@Override
	public String getUpdateSql(Object data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteSql(int id) {
		return "delete from t_option where id="+id;
	}

	@Override
	public String getFindAllSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		Option option = new Option();
		option.setId(rs.getLong("id"));
		option.setContent(rs.getString("content"));
		option.setIdx(rs.getInt("idx"));
		option.setSubjectId(rs.getLong("subjectId"));
		return option;
	}

	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel) {
		// ����������Ĳ�ѯSQL��䣺�û���t_option
		// ƴ��sql��䣺String
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_option ");
		sb.append("where 1=1 ");	//���ֻ��һ������������Ҫ�жϺ����Ƿ��and
		// ������ģ�Ͷ�����ĳЩ�����Ƿ���ֵ
		// ��ֵ�����Ծ�Ҫƴ�ӵ���������
		OptionQueryModel qm = (OptionQueryModel)queryModel;
		if(qm.getContent()!=null&&qm.getContent().trim().length()>0){
			sb.append("and content='"+qm.getContent()+"'");
		}
		if(qm.getIdx()>0){
			sb.append("and idx="+qm.getIdx());
		}
		if(qm.getSubjectId()!=null){
			sb.append("and subjectId="+qm.getSubjectId());
		}
		return sb.toString();
	}

	@Override
	public String getFindOneSql(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
