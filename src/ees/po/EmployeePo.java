package ees.po;

import java.util.Date;
import java.util.List;

public class EmployeePo {
	private int es_employee_id;//员工Id
	private String es_employee_name;//员工姓名
	private int es_employee_age;//员工年龄
	private String es_employee_sex;//员工性别
	private String es_employee_img;//员工头像存储路径
	private String es_employee_idcard;//员工身分证
	private String es_employee_marriage;//员工是否结婚
	private String es_employee_qualification;//员工学历
	private String es_employee_school;//员工毕业院校
	private String es_employee_major;//员工专业
	private String es_employee_department;//员工部门
	private String es_employee_post;//员工职位
	private Date es_employee_entry;//员工入职日期
	private String es_employee_call;//员工联系方式
	private String es_employee_mail;//员工邮箱
	private String es_employee_bankcard;//员工工资卡号
	private String es_employee_labourcard;//员工劳动保障号
	private String es_employee_pafcard;//员工公积金账号
	private String es_employee_pod;//员工户籍地
	private String es_employee_domicile;//员工现居住地
	private String es_employee_motto;//员工座右铭
	private String es_employee_aim;//员工目标计划
	private String es_employee_qq;//员工QQ号
	private String es_employee_wechat;//员工微信号
	private String es_employee_hobby;//员工业余爱好
	private String es_employee_count;//员工登录账号
	private String es_employee_password;//员工登录密码
	private List<ContactsPo> contactsPoList;//对应一对多的集合
	private List<FeedbackPo> feedbackPoList;//对应一对多的集合
	public int getEs_employee_id() {
		return es_employee_id;
	}
	public void setEs_employee_id(int es_employee_id) {
		this.es_employee_id = es_employee_id;
	}
	public String getEs_employee_name() {
		return es_employee_name;
	}
	public void setEs_employee_name(String es_employee_name) {
		this.es_employee_name = es_employee_name;
	}
	public int getEs_employee_age() {
		return es_employee_age;
	}
	public void setEs_employee_age(int es_employee_age) {
		this.es_employee_age = es_employee_age;
	}
	public String getEs_employee_sex() {
		return es_employee_sex;
	}
	public void setEs_employee_sex(String es_employee_sex) {
		this.es_employee_sex = es_employee_sex;
	}
	public String getEs_employee_img() {
		return es_employee_img;
	}
	public void setEs_employee_img(String es_employee_img) {
		this.es_employee_img = es_employee_img;
	}
	public String getEs_employee_idcard() {
		return es_employee_idcard;
	}
	public void setEs_employee_idcard(String es_employee_idcard) {
		this.es_employee_idcard = es_employee_idcard;
	}
	public String getEs_employee_marriage() {
		return es_employee_marriage;
	}
	public void setEs_employee_marriage(String es_employee_marriage) {
		this.es_employee_marriage = es_employee_marriage;
	}
	public String getEs_employee_qualification() {
		return es_employee_qualification;
	}
	public void setEs_employee_qualification(String es_employee_qualification) {
		this.es_employee_qualification = es_employee_qualification;
	}
	public String getEs_employee_school() {
		return es_employee_school;
	}
	public void setEs_employee_school(String es_employee_school) {
		this.es_employee_school = es_employee_school;
	}
	public String getEs_employee_major() {
		return es_employee_major;
	}
	public void setEs_employee_major(String es_employee_major) {
		this.es_employee_major = es_employee_major;
	}
	public String getEs_employee_department() {
		return es_employee_department;
	}
	public void setEs_employee_department(String es_employee_department) {
		this.es_employee_department = es_employee_department;
	}
	public String getEs_employee_post() {
		return es_employee_post;
	}
	public void setEs_employee_post(String es_employee_post) {
		this.es_employee_post = es_employee_post;
	}
	public Date getEs_employee_entry() {
		return es_employee_entry;
	}
	public void setEs_employee_entry(Date es_employee_entry) {
		this.es_employee_entry = es_employee_entry;
	}
	public String getEs_employee_call() {
		return es_employee_call;
	}
	public void setEs_employee_call(String es_employee_call) {
		this.es_employee_call = es_employee_call;
	}
	public String getEs_employee_mail() {
		return es_employee_mail;
	}
	public void setEs_employee_mail(String es_employee_mail) {
		this.es_employee_mail = es_employee_mail;
	}
	public String getEs_employee_bankcard() {
		return es_employee_bankcard;
	}
	public void setEs_employee_bankcard(String es_employee_bankcard) {
		this.es_employee_bankcard = es_employee_bankcard;
	}
	public String getEs_employee_labourcard() {
		return es_employee_labourcard;
	}
	public void setEs_employee_labourcard(String es_employee_labourcard) {
		this.es_employee_labourcard = es_employee_labourcard;
	}
	public String getEs_employee_pafcard() {
		return es_employee_pafcard;
	}
	public void setEs_employee_pafcard(String es_employee_pafcard) {
		this.es_employee_pafcard = es_employee_pafcard;
	}
	public String getEs_employee_pod() {
		return es_employee_pod;
	}
	public void setEs_employee_pod(String es_employee_pod) {
		this.es_employee_pod = es_employee_pod;
	}
	public String getEs_employee_domicile() {
		return es_employee_domicile;
	}
	public void setEs_employee_domicile(String es_employee_domicile) {
		this.es_employee_domicile = es_employee_domicile;
	}
	public String getEs_employee_motto() {
		return es_employee_motto;
	}
	public void setEs_employee_motto(String es_employee_motto) {
		this.es_employee_motto = es_employee_motto;
	}
	public String getEs_employee_aim() {
		return es_employee_aim;
	}
	public void setEs_employee_aim(String es_employee_aim) {
		this.es_employee_aim = es_employee_aim;
	}
	public String getEs_employee_qq() {
		return es_employee_qq;
	}
	public void setEs_employee_qq(String es_employee_qq) {
		this.es_employee_qq = es_employee_qq;
	}
	public String getEs_employee_wechat() {
		return es_employee_wechat;
	}
	public void setEs_employee_wechat(String es_employee_wechat) {
		this.es_employee_wechat = es_employee_wechat;
	}
	public String getEs_employee_hobby() {
		return es_employee_hobby;
	}
	public void setEs_employee_hobby(String es_employee_hobby) {
		this.es_employee_hobby = es_employee_hobby;
	}
	public String getEs_employee_count() {
		return es_employee_count;
	}
	public void setEs_employee_count(String es_employee_count) {
		this.es_employee_count = es_employee_count;
	}
	public String getEs_employee_password() {
		return es_employee_password;
	}
	public void setEs_employee_password(String es_employee_password) {
		this.es_employee_password = es_employee_password;
	}
	public List<ContactsPo> getContactsPoList() {
		return contactsPoList;
	}
	public void setContactsPoList(List<ContactsPo> contactsPoList) {
		this.contactsPoList = contactsPoList;
	}
	public List<FeedbackPo> getFeedbackPoList() {
		return feedbackPoList;
	}
	public void setFeedbackPoList(List<FeedbackPo> feedbackPoList) {
		this.feedbackPoList = feedbackPoList;
	}
}
