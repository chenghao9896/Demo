package vip.itellyou.pojo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.itellyou.util.format.DateUtil;

/**
 * 1> 一张表对应一个实体类(关系表除外)
 * 2> 字段=》属性
 * 3> 外键字段=》多数情况下写为导航属性
 * 4> 考虑加不加视图值属性=》jsp页面上的显示数据
 * 5> 静态常量
 * @author 程浩
 *
 */
public class Subject {
	public static final int SINGLE = 1;
	public static final int MULTI = 2;
	//使用常量来定义页面上需要显示的值
	public static final String SINGLE_NAME = "单选";
	public static final String MULTI_NAME = "多选";
	//使用map键值对来定义页面上需要显示的那几个值
	public static Map<Integer, String> numberMap = new HashMap<Integer, String>();
	static {
		numberMap.put(SINGLE, SINGLE_NAME);
		numberMap.put(MULTI, MULTI_NAME);
	}
	private Long id;
	private String title;
	private int number; //1 单选 2多选
	private Long startTime;
	private Long endTime;
	
	//导航属性:对象
	public User user;
	//选项的导航属性：
	private List<Option> options;

	//视图值属性： 日期，有限的几个值
	//视图值一般没有setter访问器
	//给对应的属性赋值时，给视图属性相应的赋值
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
