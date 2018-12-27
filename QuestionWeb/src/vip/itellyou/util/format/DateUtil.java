package vip.itellyou.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڹ����ࣺ�ṩ���ڴ����ͨ�ù���
 * @author �̺�
 *
 */
public class DateUtil {
	//�������͵�����ת�������ڸ�ʽ���ַ���
	public static String toShortDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		return df.format(new Date(time));
	}
	
	//���ַ�����ʽ������ת��Ϊ������
	public static Long toLong(String time) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(time).getTime();
	}
}
