package vip.itellyou.util.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vip.itellyou.util.dao.DbHelper;

/**
 * 数据访问的父类
 * 实现BaseDao接口中的通用的增删改查的方法
 * @author root
 *
 */
public abstract class BaseDaoImpl implements BaseDao {

	@Override
	public int insert(Object data) throws Exception {
		// JDBC
		//1.创建connection对象
		Connection con = DbHelper.getConnection();
		//2.编写SQL语句，创建PrepatedStatement对象
		String sql = getInsertSql(data);
		PreparedStatement pst = con.prepareStatement(sql);
		//3.执行命令：excuteUpdate(增删改)，excuteQuery(查)
		//返回值就是影响到的行数
		int rows = pst.executeUpdate();
		//3.执行处理的结果（ResultSet中的记录=》java对象）
		//5.释放资源
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

	@Override
	public int update(Object data) throws Exception {
		// JDBC
		//1.创建connection对象
		Connection con = DbHelper.getConnection();
		//2.编写SQL语句，创建PrepatedStatement对象
		String sql = getUpdateSql(data);
		PreparedStatement pst = con.prepareStatement(sql);
		//3.执行命令：excuteUpdate(增删改)，excuteQuery(查)
		int rows = pst.executeUpdate();
		//3.执行处理的结果（ResultSet中的记录=》java对象）
		//5.释放资源
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

	@Override
	public int delete(int id) throws Exception {
		// JDBC
		//1.创建connection对象
		Connection con = DbHelper.getConnection();
		//2.编写SQL语句，创建PrepatedStatement对象
		String sql = getDeleteSql(id);
		PreparedStatement pst = con.prepareStatement(sql);
		//3.执行命令：excuteUpdate(增删改)，excuteQuery(查)
		int rows = pst.executeUpdate();
		//3.执行处理的结果（ResultSet中的记录=》java对象）
		//5.释放资源
		DbHelper.closeAll(null, pst, null);
		return rows;
	}

	@Override
	public List findAll() throws Exception{
		//1.创建connection对象
		Connection con = DbHelper.getConnection();
		//2.编写SQL语句，创建PrepatedStatement对象
		String sql = getFindAllSql();
		//3.执行命令：excuteUpdate(增删改)，excuteQuery(查)
		PreparedStatement pst = con.prepareStatement(sql);
		//返回值就是结果集
		ResultSet rs = pst.executeQuery();
		//4.执行处理的结果（ResultSet中的记录=》java对象）
		List list = new ArrayList();
		//对结果集中的数据进行循环，每循环一次读取一条记录
		while(rs.next()){
			//将一条记录转换为java对象
			Object data = rsToObject(rs);
			// 将对象加到集合中
			list.add(data);
		}
		//5.释放资源
		DbHelper.closeAll(null, pst, rs);
		return list;
	}
	
	@Override
	public List findByCondition(BaseQueryModel queryModel) throws Exception{
		//1.创建connection对象
		Connection con = DbHelper.getConnection();
		//2.编写SQL语句，创建PrepatedStatement对象
		String sql = getFindByConditionSql(queryModel);
		//3.执行命令：excuteUpdate(增删改)，excuteQuery(查)
		PreparedStatement pst = con.prepareStatement(sql);
		//返回值就是结果集
		ResultSet rs = pst.executeQuery();
		//4.执行处理的结果（ResultSet中的记录=》java对象）
		List list = new ArrayList();
		//对结果集中的数据进行循环，每循环一次读取一条记录
		while(rs.next()){
			//将一条记录转换为java对象
			Object data = rsToObject(rs);
			// 将对象加到集合中
			list.add(data);
		}
		//5.释放资源
		DbHelper.closeAll(null, pst, rs);
		return list;
	}
	
	@Override
	public Object findOne(int id) throws Exception{
		// JDBC
		//1.创建connection对象
		Connection con = DbHelper.getConnection();
		//2.编写SQL语句，创建PrepatedStatement对象
		String sql = getFindOneSql(id);
		PreparedStatement pst = con.prepareStatement(sql);
		//3.执行命令：excuteUpdate(增删改)，excuteQuery(查)
		ResultSet rs = pst.executeQuery();
		//3.执行处理的结果（ResultSet中的记录=》java对象）
		Object result = null;
		//4.按照结果集的数据进行循环
		if(rs.next()){
			result = rsToObject(rs);
		}
		//5.释放资源
		DbHelper.closeAll(null, pst, rs);
		return result;
	}
	//获取最后新增的记录的id
	@Override
	public Long findMaxId() throws Exception {
		// 1 创建Connection对象
		Connection con = DbHelper.getConnection();
		// 2 编写SQL语句，创建PreparedStatement对象
		String sql = "SELECT LAST_INSERT_ID() AS maxid";
		PreparedStatement pst = con.prepareStatement(sql);
		// 3 执行命令:executeUpdate,executeQuery
		// 返回值就是结果集
		ResultSet rs = pst.executeQuery();
		// 4 处理执行的结果(ResultSet中的记录=>java对象)
		Long result = null;
		// 对结果集进行循环，每循环一次读取一条记录
		if (rs.next()) {
			result = rs.getLong("maxid");
		}
		// 5 释放资源
		DbHelper.closeAll(null, pst, null);
		return result;
	}
	@Override
	public List findStringAll(String s) throws Exception {
		//1.创建connection对象
		Connection con = DbHelper.getConnection();
		//2.编写SQL语句，创建PrepatedStatement对象
		String sql = getFindStringAllSql(s);
		//3.执行命令：excuteUpdate(增删改)，excuteQuery(查)
		PreparedStatement pst = con.prepareStatement(sql);
		//返回值就是结果集
		ResultSet rs = pst.executeQuery();
		//4.执行处理的结果（ResultSet中的记录=》java对象）
		List list = new ArrayList();
		//对结果集中的数据进行循环，每循环一次读取一条记录
		while(rs.next()){
			//将一条记录转换为java对象
			Object data = rsToObject(rs);
			// 将对象加到集合中
			list.add(data);
		}
		//5.释放资源
		DbHelper.closeAll(null, pst, rs);
		return list;
	}

	//多态，将产生具体的SQL语句的功能留给具体的子类来实现
	// 原因：只有具体的数据访问的子类才对应具体的表
	public abstract String getInsertSql(Object data);
	public abstract String getUpdateSql(Object data);
	public abstract String getDeleteSql(int id);
	public abstract String getFindAllSql();
	public abstract String getFindOneSql(int id);
	//将记录转换成一个java对象
	public abstract Object rsToObject(ResultSet rs) throws Exception;
	public abstract String getFindByConditionSql(BaseQueryModel queryModel);
	public String getFindStringAllSql(String s){return null;}
}
