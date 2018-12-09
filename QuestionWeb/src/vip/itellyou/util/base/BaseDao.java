package vip.itellyou.util.base;

import java.util.List;

/**
 * 数据访问的父接口，声明通用的增删改查方法
 * @author 程浩
 *
 */
public interface BaseDao {
	//增
	//返回值int表示新增了几行，0代表不成功
	//Object data:将会代换成具体的实体类，多态的应用
	public int insert(Object data) throws Exception;
	//表中使用id 自动增长的数据作为主键
	//新增时，数据中的id==null，修改时，id一定要有值
	public int update(Object data) throws Exception;
	public int delete(int id) throws Exception;
	
	//声明查询方法
	//1 查所有数据
	public List findAll() throws Exception;
	//2 根据id查一条
	public Object findOne(int id) throws Exception;
	//3 根据条件查满足条件的所有数据
	//	直接传入条件"where ...."，违背设计原则
	//	传入构成条件的数据：数据应该是实体类的属性值
	//		一般会有有一些变化的值
	public List findByCondition(BaseQueryModel queryModel) throws Exception;
}
