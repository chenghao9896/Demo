package vip.itellyou.util.format;

import vip.itellyou.util.exception.RuleException;

public class StrFormatNum {
	public static int toInt(String s) throws Exception{
		int i;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new RuleException("请选择投票类型");
		}
		return i;
	}
}
