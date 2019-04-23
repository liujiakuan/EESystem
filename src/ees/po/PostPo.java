package ees.po;

public class PostPo {
	//一个部门对应多个职位，用作后台动态添加部门职位，暂时没有做这个功能
	//这个功能为了方便，暂时先写死在程序里面
	private int es_post_id;//职位Id
	private String es_post_name;//职位名称
	private int es_department_id;//部门Id,外键
	public int getEs_post_id() {
		return es_post_id;
	}
	public void setEs_post_id(int es_post_id) {
		this.es_post_id = es_post_id;
	}
	public String getEs_post_name() {
		return es_post_name;
	}
	public void setEs_post_name(String es_post_name) {
		this.es_post_name = es_post_name;
	}
	public int getEs_department_id() {
		return es_department_id;
	}
	public void setEs_department_id(int es_department_id) {
		this.es_department_id = es_department_id;
	}
	
}
