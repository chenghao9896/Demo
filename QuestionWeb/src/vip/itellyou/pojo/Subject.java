package vip.itellyou.pojo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.itellyou.util.format.DateUtil;

/**
 * 1> һ�ű��Ӧһ��ʵ����(��ϵ�����)
 * 2> �ֶ�=������
 * 3> ����ֶ�=�����������дΪ��������
 * 4> ���ǼӲ�����ͼֵ����=��jspҳ���ϵ���ʾ����
 * 5> ��̬����
 * @author �̺�
 *
 */
public class Subject {
	public static final int SINGLE = 1;
	public static final int MULTI = 2;
	//ʹ�ó���������ҳ������Ҫ��ʾ��ֵ
	public static final String SINGLE_NAME = "��ѡ";
	public static final String MULTI_NAME = "��ѡ";
	//ʹ��map��ֵ��������ҳ������Ҫ��ʾ���Ǽ���ֵ
	public static Map<Integer, String> numberMap = new HashMap<Integer, String>();
	static {
		numberMap.put(SINGLE, SINGLE_NAME);
		numberMap.put(MULTI, MULTI_NAME);
	}
	private Long id;
	private String title;
	private int number; //1 ��ѡ 2��ѡ
	private Long startTime;
	private Long endTime;
	
	//��������:����
	public User user;
	//ѡ��ĵ������ԣ�
	private List<Option> options;

	//��ͼֵ���ԣ� ���ڣ����޵ļ���ֵ
	//��ͼֵһ��û��setter������
	//����Ӧ�����Ը�ֵʱ������ͼ������Ӧ�ĸ�ֵ
	private String startTimeView;
	private String endTimeView;
	private String numberView;
	private int count;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
		this.numberView = numberMap.get(this.number);
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
		this.startTimeView = DateUtil.toShortDate(this.startTime);
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
		this.endTimeView = DateUtil.toShortDate(this.endTime);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStartTimeView() {
		return startTimeView;
	}
	public String getEndTimeView() {
		return endTimeView;
	}
	public String getNumberView() {
		return numberView;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public Subject() {
		super();
		this.user = new User();
		this.options = new ArrayList<Option>();
	}
	
}
