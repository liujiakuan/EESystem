package ees.po;

import java.util.List;

public class DepartmentPo {
	//一个部门对应多个职位，用作后台动态添加部门职位，暂时没有做这个功能
	//这个功能为了方便，暂时先写死在程序里面
	private int es_department_id;//部门Id
	private String es_department_name;//部门名称
	private List<PostPo> postPoList;//部门职位列表
	public int getEs_department_id() {
		return es_department_id;
	}
	public void setEs_department_id(int es_department_id) {
		this.es_department_id = es_department_id;
	}
	public String getEs_department_name() {
		return es_department_name;
	}
	public void setEs_department_name(String es_department_name) {
		this.es_department_name = es_department_name;
	}
	public List<PostPo> getPostPoList() {
		return postPoList;
	}
	public void setPostPoList(List<PostPo> postPoList) {
		this.postPoList = postPoList;
	}
	
}
