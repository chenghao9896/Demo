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
	//�����û���֤�ķ���
	public void verify(User user) throws Exception;
}
