package vip.itellyou.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ݷ��ʵĸ�����
 * �ṩ���ݿ����Ӷ����ͷ���Դ
 * @author �̺�
 *
 */
public class DbHelper {
	//�ṩ�������Ӷ���
	//1 �������ӳض���
	private static DataSource dataSource = new 
			ComboPooledDataSource();
	//2 ��ȡ���Ӷ���ķ���
	public static Connection getConnection() throws Exception{
		return dataSource.getConnection();
	};
	//�ͷ���Դ
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
