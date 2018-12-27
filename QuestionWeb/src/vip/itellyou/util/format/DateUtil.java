package vip.itellyou.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类：提供日期处理的通用功能
 * @author 程浩
 *
 */
public class DateUtil {
	//将长整型的数据转换成日期格式的字符串
	public static String toShortDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		return df.format(new Date(time));
	}
	
	//将字符串格式的日期转换为长整型
	public static Long toLong(String time) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(time).getTime();
	}
}
