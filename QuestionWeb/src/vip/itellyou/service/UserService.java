package vip.itellyou.service;

import vip.itellyou.pojo.User;

/**
 * �û�ҵ���߼��ӿ�
 * 	1>����ű���С����Ŀ����޵ļ����࣬ÿ���������һ������
 * 	2>���¼��һ�ű�дһ��ҵ���߼���
 * 	3>����ģ�ͣ����͵���Ŀ
 * @author �̺�
 *
 */
public interface UserService {
	//����ע��ķ���
	public void register(User user) throws Exception;
	//�����¼�ķ����������û�����������в�ѯ
	//����ֵ�Ǹ����û����������ѯ�����û�����
	public User verify(User user) throws Exception;
	//�����û�id�����û�����
	public User getUser(int id) throws Exception;
}
