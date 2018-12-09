package vip.itellyou.util.base;

import java.util.List;

/**
 * ���ݷ��ʵĸ��ӿڣ�����ͨ�õ���ɾ�Ĳ鷽��
 * @author �̺�
 *
 */
public interface BaseDao {
	//��
	//����ֵint��ʾ�����˼��У�0�����ɹ�
	//Object data:��������ɾ����ʵ���࣬��̬��Ӧ��
	public int insert(Object data) throws Exception;
	//����ʹ��id �Զ�������������Ϊ����
	//����ʱ�������е�id==null���޸�ʱ��idһ��Ҫ��ֵ
	public int update(Object data) throws Exception;
	public int delete(int id) throws Exception;
	
	//������ѯ����
	//1 ����������
	public List findAll() throws Exception;
	//2 ����id��һ��
	public Object findOne(int id) throws Exception;
	//3 ����������������������������
	//	ֱ�Ӵ�������"where ...."��Υ�����ԭ��
	//	���빹�����������ݣ�����Ӧ����ʵ���������ֵ
	//		һ�������һЩ�仯��ֵ
	public List findByCondition(BaseQueryModel queryModel) throws Exception;
}
