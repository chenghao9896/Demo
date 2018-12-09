package vip.itellyou.util.base;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import vip.itellyou.dao.impl.UserDaoImpl;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;
/**
 * �����ࣺ
 * 1> ������Ŀ����İ���һ��
 * 2> ���� = "Test"+Ŀ��������
 * @author �̺�
 *
 */
public class TestUserDaoImpl {
	@Test	//ע��(����ֻ�����Ǻ�)
	//�����������������ǲ��Է���
	//���Է����� = "test"+Ŀ�귽����
	public void testInsert() throws Exception {
		//1 ����Ŀ����Ķ���
		UserDaoImpl userDao = new UserDaoImpl();
		//2 ����Ŀ������Ŀ�귽�����õ�ʵ�����еĽ��
		User user = new User();
		user.setName("����");
		user.setPwd("111111");
		user.setOnline(1);
		int actual = userDao.insert(user);
		//3 д����Ԥ�ƵĽ��(�˹����չ��ܼ���)
		int expected = 1;
		//4 ʹ�ö�����ķ����Ƚ�ʵ�����еĽ����Ԥ�ƵĽ��
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdate() throws Exception {
		//1 ����Ŀ����Ķ���
		UserDaoImpl userDao = new UserDaoImpl();
		//2 ����Ŀ������Ŀ�귽�����õ�ʵ�����еĽ��
		User user = new User();
		user.setId(1l);
		user.setName("С��");
		user.setPwd("123456");
		user.setOnline(1);
		int actual = userDao.update(user);
		int expected = 1;
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete() throws Exception {
		//1 ����Ŀ����Ķ���
		UserDaoImpl userDao = new UserDaoImpl();
		//2 ����Ŀ������Ŀ�귽�����õ�ʵ�����еĽ��
		int actual = userDao.delete(1);
		//3 д����Ԥ�ƵĽ��(�˹����չ��ܼ���)
		int expected = 1;
		//4 ʹ�ö�����ķ����Ƚ�ʵ�����еĽ����Ԥ�ƵĽ��
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testFindAll() throws Exception {
		UserDaoImpl userDao = new UserDaoImpl();
		List list = userDao.findAll();
		int expected = 1;
		Assert.assertEquals(expected, list.size());
	}
	
	@Test
	public void testFindOne() throws Exception{
		UserDaoImpl userDao = new UserDaoImpl();
		User actual = (User)userDao.findOne(2);
		String expected = "С��";
		Assert.assertEquals(expected, actual.getName());
	}
	
	@Test
	public void testFindByCondition() throws Exception {
		UserDaoImpl userDao = new UserDaoImpl();
		UserQueryModel qm = new UserQueryModel();
		qm.setName("С��");
		List list = userDao.findByCondition(qm);
		int expected = 2;
		Assert.assertEquals(expected, list.size());
		
	}
}
