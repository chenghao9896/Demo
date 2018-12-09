package vip.itellyou.service;

import vip.itellyou.pojo.User;

/**
 * 用户业务逻辑接口
 * 	1>事务脚本：小型项目里，有限的几个类，每个方法完成一个功能
 * 	2>活动记录：一张表写一个业务逻辑类
 * 	3>领域模型：大型的项目
 * @author 程浩
 *
 */
public interface UserService {
	//处理注册的方法
	public void register(User user) throws Exception;
	//处理用户验证的方法
	public void verify(User user) throws Exception;
}
