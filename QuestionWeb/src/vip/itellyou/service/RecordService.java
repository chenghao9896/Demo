package vip.itellyou.service;

import vip.itellyou.pojo.Record;

public interface RecordService {
	public void add(Record r) throws Exception;
	public int getUserCount(Long subjectId) throws Exception;
}
