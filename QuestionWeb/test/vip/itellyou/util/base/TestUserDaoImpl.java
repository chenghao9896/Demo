package vip.itellyou.util.base;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import vip.itellyou.dao.impl.UserDaoImpl;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;
/**
 * 测试类：
 * 1> 包名和目标类的包名一致
 * 2> 类名 = "Test"+目标类类名
 * @author 程浩
 *
 */
public class TestUserDaoImpl {
	@Test	//注解(本身只是做记号)
	//表面下面的这个方法是测试方法
	//测试方法名 = "test"+目标方法名
	public void testInsert() throws Exception {
		//1 创建目标类的对象
		UserDaoImpl userDao = new UserDaoImpl();
		//2 调用目标对象的目标方法，得到实际运行的结果
		User user = new User();
		user.setName("张三");
		user.setPwd("111111");
		user.setOnline(1);
		int actual = userDao.insert(user);
		//3 写下来预计的结果(人工按照功能计算)
		int expected = 1;
		//4 使用断言类的方法比较实际运行的结果和预计的结果
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdate() throws Exception {
		//1 创建目标类的对象
		UserDaoImpl userDao = new UserDaoImpl();
		//2 调用目标对象的目标方法，得到实际运行的结果
		User user = new User();
		user.setId(1l);
		user.setName("小华");
		user.setPwd("123456");
		user.setOnline(1);
		int actual = userDao.update(user);
		int expected = 1;
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete() throws Exception {
		//1 创建目标类的对象
		UserDaoImpl userDao = new UserDaoImpl();
		//2 调用目标对象的目标方法，得到实际运行的结果
		int actual = userDao.delete(1);
		//3 写下来预计的结果(人工按照功能计算)
		int expected = 1;
		//4 使用断言类的方法比较实际运行的结果和预计的结果
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
		String expected = "小李";
		Assert.assertEquals(expected, actual.getName());
	}
	
	@Test
	public void testFindByCondition() throws Exception {
		UserDaoImpl userDao = new UserDaoImpl();
		UserQueryModel qm = new UserQueryModel();
		qm.setName("小华");
		List list = userDao.findByCondition(qm);
		int expected = 2;
		Assert.assertEquals(expected, list.size());
		
	}
}
