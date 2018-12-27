package vip.itellyou.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vip.itellyou.dao.RecordDao;
import vip.itellyou.dao.impl.RecordDaoImpl;
import vip.itellyou.pojo.Record;
import vip.itellyou.pojo.RecordQueryModel;
import vip.itellyou.service.RecordService;
import vip.itellyou.util.exception.RuleException;

public class RecordServiceImpl implements RecordService {
	private RecordDao recordDao;
	private Record record;
	public RecordServiceImpl(){
		recordDao = new RecordDaoImpl();
		record = new Record();
	}
	@Override
	public void add(Record r) throws Exception {
		RecordQueryModel queryModel = new RecordQueryModel();
		queryModel.setSubjectId(r.getSubjectId());
		List list = recordDao.findByCondition(queryModel);
		if(r.getOptionsId()==null){
			throw new RuleException("选项不能为空");
		}
		if(list!=null&&list.size()>0){
			for (Object data : list) {
				record = (Record)data;
				if(record.getUserId().longValue()==r.getUserId().longValue()){
					throw new RuleException("请勿重复投票");
				}
			}
		}
		String[] optionsId = r.getOptionsId();
		for (String optionId : optionsId) {
			r.setOptionId(new Long(optionId));
			recordDao.insert(r);
		}
	}
	@Override
	public int getUserCount(Long id) throws Exception {
		int count = 0;
		Set<Long> set = new HashSet<Long>();
		RecordQueryModel queryModel = new RecordQueryModel();
		queryModel.setSubjectId(new Long(id));
		List list = recordDao.findByCondition(queryModel);
		if(list!=null&&list.size()>0){
			for (Object data : list) {
				record = (Record)data;
				set.add(record.getUserId());
			}
			count = set.size();
		}
		return count;
	}

}
