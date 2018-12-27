package vip.itellyou.service;

import java.util.List;

import vip.itellyou.pojo.Subject;
import vip.itellyou.pojo.User;

/**
 * 调查项目的业务逻辑接口
 * @author 程浩
 *
 */
public interface SubjectService {
	public void add(Subject subject,User user) throws Exception;
	public void updateSubject(Subject subject) throws Exception;
	public Subject getOneSubject(int id) throws Exception;
	public List<Subject> getSubjects() throws Exception;
	public List<Subject> getUserSubjects(User user) throws Exception;
	public List<Subject> getByStringSubjects(String s) throws Exception;
}






