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
	//提供数据连接对象
	//1 创建连接池对象
	private static DataSource dataSource = new 
			ComboPooledDataSource();
	//2 获取连接对象的方法
	public static Connection getConnection() throws Exception{
		return dataSource.getConnection();
	};
	//释放资源
	public static void closeAll(Connection con, PreparedStatement pst, ResultSet res) throws Exception{
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
}
