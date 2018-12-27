package vip.itellyou.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据访问的辅助类
 * 提供数据库连接对象；释放资源
 * @author 程浩
 *
 */
public class DbHelper {
	//不要和陌生人讲话（一般将使用相同对象的方法定义在同一个类中）
	//提供数据连接对象
	//1 创建连接池对象
	private static DataSource dataSource = new 
			ComboPooledDataSource();
	//2 获取连接对象的方法
	//在同一个请求处理过程中获取的连接对象是同一个
	//保证：同一个线程中的连接对象是单例的
	// ThreadLocal：线程槽
	private static ThreadLocal<Connection> cons = new ThreadLocal<Connection>();
	public static Connection getConnection() throws Exception {
		Connection con = cons.get();
		if(con==null){
			con = dataSource.getConnection();
			cons.set(con);
		}
		return con;
	};
	
	//关闭连接对象的同时，必须在线程槽中清除连接对象
	public static void close() throws Exception {
		//从线程槽中获取出连接对象
		Connection con = cons.get();
		//有连接对象
		if(con!=null){
			//关闭连接
			con.close();
			//同时，从线程槽中移除连接对象
			cons.remove();
		}
	}
		
	//释放资源
	public static void closeAll(Connection con, PreparedStatement pst, ResultSet res) throws Exception {
		if(res != null){
			res.close();
		}
		if(pst != null){
			pst.close();
		}
		if(con != null){
			con.close();
		}
	}
	
	//封装三个事务处理的方法:开启事务，提交事务，回滚事务
	public static void beginTransaction() throws Exception {
		getConnection().setAutoCommit(false);
	}
	public static void commitTransaction() throws Exception {
		getConnection().commit();
	}
	public static void rollbackTransaction() throws Exception {
		getConnection().rollback();
	}
}
