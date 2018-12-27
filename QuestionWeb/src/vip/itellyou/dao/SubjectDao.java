package vip.itellyou.dao;

import java.util.List;

import vip.itellyou.util.base.BaseDao;

/**
 * 用户数据访问类的子接口：继承BaseDao
 * @author 程浩
 *
 */
public interface SubjectDao extends BaseDao{
    //继承，有通用的CRUD方法
	//如果对应的表上有特殊的数据操作要求
	//可以添加特殊的处理方法
}
