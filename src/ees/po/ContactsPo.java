package ees.po;

public class ContactsPo {
	private int es_employee_contacts_id;//员工紧急联系人Id
	private String es_employee_contacts_name;//员工紧急联系人姓名
	private String es_employee_contacts_relation;//员工紧急联系人关系 
	private String es_employee_contacts_call;//员工紧急联系人联系方式
	private int es_employee_id;//员工紧急联系人，员工Id,外键
	public int getEs_employee_contacts_id() {
		return es_employee_contacts_id;
	}
	public void setEs_employee_contacts_id(int es_employee_contacts_id) {
		this.es_employee_contacts_id = es_employee_contacts_id;
	}
	public String getEs_employee_contacts_name() {
		return es_employee_contacts_name;
	}
	public void setEs_employee_contacts_name(String es_employee_contacts_name) {
		this.es_employee_contacts_name = es_employee_contacts_name;
	}
	public String getEs_employee_contacts_relation() {
		return es_employee_contacts_relation;
	}
	public void setEs_employee_contacts_relation(String es_employee_contacts_relation) {
		this.es_employee_contacts_relation = es_employee_contacts_relation;
	}
	public String getEs_employee_contacts_call() {
		return es_employee_contacts_call;
	}
	public void setEs_employee_contacts_call(String es_employee_contacts_call) {
		this.es_employee_contacts_call = es_employee_contacts_call;
	}
	public int getEs_employee_id() {
		return es_employee_id;
	}
	public void setEs_employee_id(int es_employee_id) {
		this.es_employee_id = es_employee_id;
	}
	
}
