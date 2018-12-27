package vip.itellyou.util.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vip.itellyou.util.dao.DbHelper;

/**
 * ���ݷ��ʵĸ���
 * ʵ��BaseDao�ӿ��е�ͨ�õ���ɾ�Ĳ�ķ���
 * @author root
 *
 */
public abstract class BaseDaoImpl implements BaseDao {

	@Override
	public int insert(Object data) throws Exception {
		// JDBC
		//1.����connection����
		Connection con = DbHelper.getConnection();
		//2.��дSQL��䣬����PrepatedStatement����
		String sql = getInsertSql(data);
		PreparedStatement pst = con.prepareStatement(sql);
		//3.ִ�����excuteUpdate(��ɾ��)��excuteQuery(��)
		//����ֵ����Ӱ�쵽������
		int rows = pst.executeUpdate();
		//3.ִ�д���Ľ����ResultSet�еļ�¼=��java����
		//5.�ͷ���Դ
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

	@Override
	public int update(Object data) throws Exception {
		// JDBC
		//1.����connection����
		Connection con = DbHelper.getConnection();
		//2.��дSQL��䣬����PrepatedStatement����
		String sql = getUpdateSql(data);
		PreparedStatement pst = con.prepareStatement(sql);
		//3.ִ�����excuteUpdate(��ɾ��)��excuteQuery(��)
		int rows = pst.executeUpdate();
		//3.ִ�д���Ľ����ResultSet�еļ�¼=��java����
		//5.�ͷ���Դ
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

	@Override
	public int delete(int id) throws Exception {
		// JDBC
		//1.����connection����
		Connection con = DbHelper.getConnection();
		//2.��дSQL��䣬����PrepatedStatement����
		String sql = getDeleteSql(id);
		PreparedStatement pst = con.prepareStatement(sql);
		//3.ִ�����excuteUpdate(��ɾ��)��excuteQuery(��)
		int rows = pst.executeUpdate();
		//3.ִ�д���Ľ����ResultSet�еļ�¼=��java����
		//5.�ͷ���Դ
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

	@Override
	public List findAll() throws Exception{
		//1.����connection����
		Connection con = DbHelper.getConnection();
		//2.��дSQL��䣬����PrepatedStatement����
		String sql = getFindAllSql();
		//3.ִ�����excuteUpdate(��ɾ��)��excuteQuery(��)
		PreparedStatement pst = con.prepareStatement(sql);
		//����ֵ���ǽ����
		ResultSet rs = pst.executeQuery();
		//4.ִ�д���Ľ����ResultSet�еļ�¼=��java����
		List list = new ArrayList();
		//�Խ�����е����ݽ���ѭ����ÿѭ��һ�ζ�ȡһ����¼
		while(rs.next()){
			//��һ����¼ת��Ϊjava����
			Object data = rsToObject(rs);
			// ������ӵ�������
			list.add(data);
		}
		//5.�ͷ���Դ
		DbHelper.closeAll(null, pst, rs);
		return list;
	}
	
	@Override
	public List findByCondition(BaseQueryModel queryModel) throws Exception{
		//1.����connection����
		Connection con = DbHelper.getConnection();
		//2.��дSQL��䣬����PrepatedStatement����
		String sql = getFindByConditionSql(queryModel);
		//3.ִ�����excuteUpdate(��ɾ��)��excuteQuery(��)
		PreparedStatement pst = con.prepareStatement(sql);
		//����ֵ���ǽ����
		ResultSet rs = pst.executeQuery();
		//4.ִ�д���Ľ����ResultSet�еļ�¼=��java����
		List list = new ArrayList();
		//�Խ�����е����ݽ���ѭ����ÿѭ��һ�ζ�ȡһ����¼
		while(rs.next()){
			//��һ����¼ת��Ϊjava����
			Object data = rsToObject(rs);
			// ������ӵ�������
			list.add(data);
		}
		//5.�ͷ���Դ
		DbHelper.closeAll(null, pst, rs);
		return list;
	}
	
	@Override
	public Object findOne(int id) throws Exception{
		// JDBC
		//1.����connection����
		Connection con = DbHelper.getConnection();
		//2.��дSQL��䣬����PrepatedStatement����
		String sql = getFindOneSql(id);
		PreparedStatement pst = con.prepareStatement(sql);
		//3.ִ�����excuteUpdate(��ɾ��)��excuteQuery(��)
		ResultSet rs = pst.executeQuery();
		//3.ִ�д���Ľ����ResultSet�еļ�¼=��java����
		Object result = null;
		//4.���ս���������ݽ���ѭ��
		if(rs.next()){
			result = rsToObject(rs);
		}
		//5.�ͷ���Դ
		DbHelper.closeAll(null, pst, rs);
		return result;
	}
	//��ȡ��������ļ�¼��id
	@Override
	public Long findMaxId() throws Exception {
		// 1 ����Connection����
		Connection con = DbHelper.getConnection();
		// 2 ��дSQL��䣬����PreparedStatement����
		String sql = "SELECT LAST_INSERT_ID() AS maxid";
		PreparedStatement pst = con.prepareStatement(sql);
		// 3 ִ������:executeUpdate,executeQuery
		// ����ֵ���ǽ����
		ResultSet rs = pst.executeQuery();
		// 4 ����ִ�еĽ��(ResultSet�еļ�¼=>java����)
		Long result = null;
		// �Խ��������ѭ����ÿѭ��һ�ζ�ȡһ����¼
		if (rs.next()) {
			result = rs.getLong("maxid");
		}
		// 5 �ͷ���Դ
		DbHelper.closeAll(null, pst, null);
		return result;
	}
	@Override
	public List findStringAll(String s) throws Exception {
		//1.����connection����
		Connection con = DbHelper.getConnection();
		//2.��дSQL��䣬����PrepatedStatement����
		String sql = getFindStringAllSql(s);
		//3.ִ�����excuteUpdate(��ɾ��)��excuteQuery(��)
		PreparedStatement pst = con.prepareStatement(sql);
		//����ֵ���ǽ����
		ResultSet rs = pst.executeQuery();
		//4.ִ�д���Ľ����ResultSet�еļ�¼=��java����
		List list = new ArrayList();
		//�Խ�����е����ݽ���ѭ����ÿѭ��һ�ζ�ȡһ����¼
		while(rs.next()){
			//��һ����¼ת��Ϊjava����
			Object data = rsToObject(rs);
			// ������ӵ�������
			list.add(data);
		}
		//5.�ͷ���Դ
		DbHelper.closeAll(null, pst, rs);
		return list;
	}

	//��̬�������������SQL���Ĺ������������������ʵ��
	// ԭ��ֻ�о�������ݷ��ʵ�����Ŷ�Ӧ����ı�
	public abstract String getInsertSql(Object data);
	public abstract String getUpdateSql(Object data);
	public abstract String getDeleteSql(int id);
	public abstract String getFindAllSql();
	public abstract String getFindOneSql(int id);
	//����¼ת����һ��java����
	public abstract Object rsToObject(ResultSet rs) throws Exception;
	public abstract String getFindByConditionSql(BaseQueryModel queryModel);
	public String getFindStringAllSql(String s){return null;}
}
