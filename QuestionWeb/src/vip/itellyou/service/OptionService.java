package vip.itellyou.service;

import java.util.List;

import vip.itellyou.pojo.Option;
import vip.itellyou.pojo.Subject;


public interface OptionService {
	public List<Option> getOptions(Subject subject) throws Exception;
}
