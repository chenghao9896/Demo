package vip.itellyou.pojo;

import java.util.List;


/**
 * 投票记录的实体类
 * @author 程浩
 *
 */
public class Record {
	private Long id;
	private Long subjectId;
	private Long optionId;
	private Long userId;
	private String[] optionsId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String[] getOptionsId() {
		return optionsId;
	}
	public void setOptionsId(String[] optionsId) {
		this.optionsId = optionsId;
	}
}
