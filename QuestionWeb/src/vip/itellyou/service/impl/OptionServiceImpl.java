package vip.itellyou.service.impl;

import java.util.List;

import vip.itellyou.dao.OptionDao;
import vip.itellyou.dao.impl.OptionDaoImpl;
import vip.itellyou.pojo.Option;
import vip.itellyou.pojo.OptionQueryModel;
import vip.itellyou.pojo.Subject;
import vip.itellyou.service.OptionService;

public class OptionServiceImpl implements OptionService {
	private OptionDao optionDao;
	public OptionServiceImpl(){
		this.optionDao = new OptionDaoImpl();
	}
	
	@Override
	public List<Option> getOptions(Subject subject) throws Exception {
		OptionQueryModel querymodel = new OptionQueryModel();
		querymodel.setSubjectId(subject.getId());
		return (List<Option>)optionDao.findByCondition(querymodel);
	}
}
