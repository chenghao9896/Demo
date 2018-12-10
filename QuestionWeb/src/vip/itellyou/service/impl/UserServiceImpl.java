package vip.itellyou.service.impl;

import java.util.List;

import vip.itellyou.dao.UserDao;
import vip.itellyou.dao.impl.UserDaoImpl;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;
import vip.itellyou.service.UserService;
import vip.itellyou.util.exception.RuleException;
import vip.itellyou.util.format.Md5Class;

/**
 * 用户数据业务逻辑类
 * 1>实现接口
 * 2>依赖于数据访问对象---业务逻辑依赖于数据访问
 * @author 程浩
 *
 */
public class UserServiceImpl implements UserService {
	// 接口对象作为属性，实现依赖关系
	private UserDao userDao;
	public UserServiceImpl(){
		userDao = new UserDaoImpl();
	}
	
	@Override
	public void register(User user) throws Exception {
		if(user.getName()==null || user.getName().trim().length()==0){
			// 当用户名为null或者长度为0时抛出异常用户名不能为空
			//用户操作不当=>数据回显：自定义异常类
			throw new RuleException("用户名不能为空!");
		}
		//...
		if(!user.getPwd().equals(user.getConfirmPwd())){
			throw new RuleException("确认密码和密码必须一致!");
		}
		
		//用户名不能重复，按照用户名查询
		//1 构造查询模型对象
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		List list = userDao.findByCondition(qm);
		if(list.size()>0){
			throw new RuleException("用户名已经被注册!");
		}
		
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		user.setOnline(1);
		userDao.insert(user);
		
	}
	@Override
	public User verify(User user) throws Exception {
		//完成登录
		//1 构造查询模型对象
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		qm.setPwd(user.getPwd());
		List list = userDao.findByCondition(qm);
		user = (User)list.get(0);
		if(user.getName()==null || user.getName().trim().length()==0){
			// 当用户名为null或者长度为0时抛出异常用户名不能为空
			//用户操作不当=>数据回显：自定义异常类
			throw new RuleException("用户名不能为空!");
		}
		if(user.getPwd()==null || user.getPwd().trim().length()==0){
			throw new RuleException("密码不能为空!");
		}
		if(user.getOnline()==User.ONLINE){
			throw new RuleException("用户已登录!");
		}else {
			if(list.size()==1){
				user.setOnline(User.ONLINE);
				userDao.update(user);
				return user;	
			}else{
				throw new RuleException("用户名不存在或密码错误!");
			}
		}
		
	}

	@Override
	public User getUser(int id) throws Exception {
		return (User)userDao.findOne(id);
	}

}
