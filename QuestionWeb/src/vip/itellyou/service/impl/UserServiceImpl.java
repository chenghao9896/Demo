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
 * �û�����ҵ���߼���
 * 1>ʵ�ֽӿ�
 * 2>���������ݷ��ʶ���---ҵ���߼����������ݷ���
 * @author �̺�
 *
 */
public class UserServiceImpl implements UserService {
	// �ӿڶ�����Ϊ���ԣ�ʵ��������ϵ
	private UserDao userDao;
	public UserServiceImpl(){
		userDao = new UserDaoImpl();
	}
	
	@Override
	public void register(User user) throws Exception {
		if(user.getName()==null || user.getName().trim().length()==0){
			// ���û���Ϊnull���߳���Ϊ0ʱ�׳��쳣�û�������Ϊ��
			//�û���������=>���ݻ��ԣ��Զ����쳣��
			throw new RuleException("�û�������Ϊ��!");
		}
		//...
		if(!user.getPwd().equals(user.getConfirmPwd())){
			throw new RuleException("ȷ��������������һ��!");
		}
		
		//�û��������ظ��������û�����ѯ
		//1 �����ѯģ�Ͷ���
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		List list = userDao.findByCondition(qm);
		if(list.size()>0){
			throw new RuleException("�û����Ѿ���ע��!");
		}
		
		user.setPwd(Md5Class.stringToMd5(user.getPwd()));
		user.setOnline(1);
		userDao.insert(user);
		
	}
	@Override
	public void verify(User user) throws Exception {
		//�û��������ظ��������û�����ѯ
		//1 �����ѯģ�Ͷ���
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		List list = userDao.findByCondition(qm);
		if(user.getName()==null || user.getName().trim().length()==0){
			// ���û���Ϊnull���߳���Ϊ0ʱ�׳��쳣�û�������Ϊ��
			//�û���������=>���ݻ��ԣ��Զ����쳣��
			throw new RuleException("�û�������Ϊ��!");
		}
		if(user.getPwd()==null || user.getPwd().trim().length()==0){
			throw new RuleException("���벻��Ϊ��!");
		}
		if(list.size()>0){
			User queryUser = new User();
			queryUser = (User)list.get(0);
			if(!user.getPwd().equals(queryUser.getPwd())){
				throw new RuleException("�������!");
			}
		}else{
			throw new RuleException("�û���������!");
		}
		
	}

}
