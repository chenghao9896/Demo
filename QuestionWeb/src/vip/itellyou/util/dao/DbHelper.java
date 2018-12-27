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
	//��Ҫ��İ���˽�����һ�㽫ʹ����ͬ����ķ���������ͬһ�����У�
	//�ṩ�������Ӷ���
	//1 �������ӳض���
	private static DataSource dataSource = new 
			ComboPooledDataSource();
	//2 ��ȡ���Ӷ���ķ���
	//��ͬһ������������л�ȡ�����Ӷ�����ͬһ��
	//��֤��ͬһ���߳��е����Ӷ����ǵ�����
	// ThreadLocal���̲߳�
	private static ThreadLocal<Connection> cons = new ThreadLocal<Connection>();
	public static Connection getConnection() throws Exception {
		Connection con = cons.get();
		if(con==null){
			con = dataSource.getConnection();
			cons.set(con);
		}
		return con;
	};
	
	//�ر����Ӷ����ͬʱ���������̲߳���������Ӷ���
	public static void close() throws Exception {
		//���̲߳��л�ȡ�����Ӷ���
		Connection con = cons.get();
		//�����Ӷ���
		if(con!=null){
			//�ر�����
			con.close();
			//ͬʱ�����̲߳����Ƴ����Ӷ���
			cons.remove();
		}
	}
		
	//�ͷ���Դ
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
	
	//��װ����������ķ���:���������ύ���񣬻ع�����
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
