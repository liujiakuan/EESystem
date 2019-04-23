package ees.po;

import java.util.Date;

public class FeedbackPo {
	private int es_employee_feedback_id;//员工反馈修改信息Id
	private String es_employee_feedback_content;//员工反馈修改信息内容
	private Date es_employee_feedback_date;//员工反馈修改信息反馈日期
	private int es_employee_feedback_state;//员工反馈修改信息处理状态
	private int es_employee_id;//员工反馈修改信息，反馈人Id，外键
	public int getEs_employee_feedback_id() {
		return es_employee_feedback_id;
	}
	public void setEs_employee_feedback_id(int es_employee_feedback_id) {
		this.es_employee_feedback_id = es_employee_feedback_id;
	}
	public String getEs_employee_feedback_content() {
		return es_employee_feedback_content;
	}
	public void setEs_employee_feedback_content(String es_employee_feedback_content) {
		this.es_employee_feedback_content = es_employee_feedback_content;
	}
	public Date getEs_employee_feedback_date() {
		return es_employee_feedback_date;
	}
	public void setEs_employee_feedback_date(Date es_employee_feedback_date) {
		this.es_employee_feedback_date = es_employee_feedback_date;
	}
	public int getEs_employee_feedback_state() {
		return es_employee_feedback_state;
	}
	public void setEs_employee_feedback_state(int es_employee_feedback_state) {
		this.es_employee_feedback_state = es_employee_feedback_state;
	}
	public int getEs_employee_id() {
		return es_employee_id;
	}
	public void setEs_employee_id(int es_employee_id) {
		this.es_employee_id = es_employee_id;
	}
	
}
