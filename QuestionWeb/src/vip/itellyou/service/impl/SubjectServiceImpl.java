package vip.itellyou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import vip.itellyou.dao.OptionDao;
import vip.itellyou.dao.SubjectDao;
import vip.itellyou.dao.impl.OptionDaoImpl;
import vip.itellyou.dao.impl.SubjectDaoImpl;
import vip.itellyou.pojo.Option;
import vip.itellyou.pojo.OptionQueryModel;
import vip.itellyou.pojo.Subject;
import vip.itellyou.pojo.SubjectQueryModel;
import vip.itellyou.pojo.User;
import vip.itellyou.service.OptionService;
import vip.itellyou.service.RecordService;
import vip.itellyou.service.SubjectService;
import vip.itellyou.util.exception.RuleException;

public class SubjectServiceImpl implements SubjectService {
    private SubjectDao subjectDao;
    private OptionDao optionDao;
    private RecordService recordService;
    private OptionService optionService;
    public SubjectServiceImpl(){
    	this.subjectDao = new SubjectDaoImpl();
    	this.optionDao =new OptionDaoImpl();
    	this.recordService = new RecordServiceImpl();
    	this.optionService = new OptionServiceImpl();
    }
	@Override
	public void add(Subject subject,User user) throws Exception {
	    if(subject.getTitle()==null || subject.getTitle().trim().length()==0){
	    	throw new RuleException("����ı��ⲻ��Ϊ��");
	    }
	    if(subject.getOptions().size()<2){
	    	throw new RuleException("����Ҫ��2��ѡ��");
	    }
	    for(int i=0;i<subject.getOptions().size();i++){
	    	String content = subject.getOptions().get(i).getContent();
	    	if("".equals(content)){
	    		throw new RuleException("ѡ���Ϊ��");
	    	}
	    }
	    for(int i=0;i<subject.getOptions().size()-1;i++){
	    	String content = subject.getOptions().get(i).getContent();
	    	for(int j=i+1;j<subject.getOptions().size();j++){
	    		if(content.equals(subject.getOptions().get(j).getContent())){
	    			throw new RuleException("�����ظ���ѡ��");
	    		}
	    		if("".equals(subject.getOptions().size())) {
	    			throw new RuleException("ѡ���Ϊ��");
	    		}
	    	}
	    }
	    
	    
	    
		//1 ��������
		// Ĭ�ϵĿ�ʼʱ��Ϊ��ǰʱ��
		Long now = new Date().getTime();
		subject.setStartTime(now);
		// Ĭ�ϵĽ���ʱ��Ϊ1���Ժ�
		subject.setEndTime(now+1*24*60*60*1000);
		// Ĭ�ϵķ������ǵ�¼�û�
		subject.setUser(user);
		subjectDao.insert(subject);
		subject.setId(subjectDao.findMaxId());
		//2 ����ѡ��
		int i=1;
		for(Option option : subject.getOptions()){
			//������ź�������
			option.setIdx(i++);
			option.setSubjectId(subject.getId());
			
			optionDao.insert(option);
		}

	}
	@Override
	public void updateSubject(Subject subject) throws Exception {
		if(subject.getTitle()==null || subject.getTitle().trim().length()==0){
	    	throw new RuleException("����ı��ⲻ��Ϊ��");
	    }
	    if(subject.getOptions().size()<2){
	    	throw new RuleException("����Ҫ��2��ѡ��");
	    }
	    for(int i=0;i<subject.getOptions().size();i++){
	    	String content = subject.getOptions().get(i).getContent();
	    	if("".equals(content)){
	    		throw new RuleException("ѡ���Ϊ��");
	    	}
	    }
	    for(int i=0;i<subject.getOptions().size()-1;i++){
	    	String content = subject.getOptions().get(i).getContent();
	    	for(int j=i+1;j<subject.getOptions().size();j++){
	    		if(content.equals(subject.getOptions().get(j).getContent())){
	    			throw new RuleException("�����ظ���ѡ��");
	    		}
	    	}
	    }
	    if(recordService.getUserCount(subject.getId())>0){
	    	throw new RuleException("��ͶƱ����Ŀ�����޸�");
	    }
	    //1 ��������
	    // Ĭ�ϵĿ�ʼʱ��Ϊ��ǰʱ��
	    Long now = new Date().getTime();
	    subject.setStartTime(now);
	    // Ĭ�ϵĽ���ʱ��Ϊ1���Ժ�
	    subject.setEndTime(now+1*24*60*60*1000);
	    subjectDao.update(subject);
	    //2 ɾ��ԭ�е�ѡ��
	    OptionQueryModel querymodel = new OptionQueryModel();
	    querymodel.setSubjectId(subject.getId());
		List<Option> subjectOptions = new ArrayList<Option>();
		subjectOptions = (List<Option>)optionDao.findByCondition(querymodel);
		for (Option option : subjectOptions) {
			optionDao.delete(option.getId().intValue());
		}
		//3 ����ѡ��
		int i=1;
		for(Option option : subject.getOptions()){
			//������ź�������
			option.setIdx(i++);
			option.setSubjectId(subject.getId());	
			optionDao.insert(option);
		}
	  
	    
		
	}
	@Override
	public Subject getOneSubject(int id) throws Exception {
		Subject subject = (Subject)subjectDao.findOne(id);
		OptionQueryModel qm = new OptionQueryModel();
		qm.setSubjectId(new Long((long)id));
		subject.setOptions(optionDao.findByCondition(qm));
		subject.setCount(recordService.getUserCount(new Long(id)));
		return subject;
	}
	@Override
	public List<Subject> getSubjects() throws Exception {
		List<Subject> subjectList = new ArrayList<Subject>();
		subjectList =  (List<Subject>)subjectDao.findAll();
		for (Subject subject : subjectList) {
			subject.setOptions(optionService.getOptions(subject));
			subject.setCount(recordService.getUserCount(subject.getId()));
		}
		return subjectList;
	}
	@Override
	public List<Subject> getUserSubjects(User user) throws Exception {
		List<Subject> subjectList = new ArrayList<Subject>();
		SubjectQueryModel qm = new SubjectQueryModel();
		qm.setUser(user);
		subjectList = (List<Subject>)subjectDao.findByCondition(qm);
		for (Subject subject : subjectList) {
			subject.setOptions(optionService.getOptions(subject));
			subject.setCount(recordService.getUserCount(subject.getId()));
		}
		return subjectList;
	}
	@Override
	public List<Subject> getByStringSubjects(String s) throws Exception {
		List<Subject> subjectList = new ArrayList<Subject>();
		subjectList = (List<Subject>)subjectDao.findStringAll(s);
		for (Subject subject : subjectList) {
			subject.setOptions(optionService.getOptions(subject));
			subject.setCount(recordService.getUserCount(subject.getId()));
		}
		return subjectList;
	}
}
