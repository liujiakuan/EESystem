package ees.service.imp;

import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ees.mapper.EmployeeMapper;
import ees.po.ContactsPo;
import ees.po.EmployeePo;
import ees.po.FeedbackPo;
import ees.service.EmployeeService;

public class EmployeeServiceImp implements EmployeeService{

	private ApplicationContext applicationContext;
	private EmployeeMapper employeeMapper;
	//员工添加自己信息
	@Override
	public void employeeAddEmployee(EmployeePo employeePo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		employeeMapper.employeeAddEmployee(employeePo);
	}
	//员工添加员工紧急联系人
	@Override
	public void employeeAddContacts(List<ContactsPo> contactsPo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		employeeMapper.employeeAddContacts(contactsPo);
	}
	//这里可以获得刚插入表格的id，为后面Contact的插入提供了es_employee_id字段
	@Override
	public int employeeSelectLastId(String es_employee_idcard) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		return employeeMapper.employeeSelectLastId(es_employee_idcard);
	}
	//员工登录,通过制定账号和密码查询指定员工,查询到返回true否则返回false
	@Override
	public Boolean employeeLogin(String es_employee_count, String es_employee_password) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		if(employeeMapper.employeeLogin(es_employee_count, es_employee_password) != null){
			return true;
		}
		else{
			return false;
		}
	}
	//员工登录,通过制定账号和密码查询指定员工并且返回
	@Override
	public List<EmployeePo> employeeIndex(String es_employee_count, String es_employee_password) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		return employeeMapper.employeeIndex(es_employee_count, es_employee_password);
	}
	//通过员工账号查找指定员工
	@Override
	public List<EmployeePo> employeeByCount(String es_employee_count) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		return employeeMapper.employeeByCount(es_employee_count);
	}
	
	//更具员工账号获取员工id,员工账号在登录的时候放在session里面
	@Override
	public int employeeId(String es_employee_count) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		return employeeMapper.employeeId(es_employee_count);
	}
	//根据员工账号获取员工姓名，员工姓名登录时候放在session里面
	@Override
	public String employeeName(String es_employee_count) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		return employeeMapper.employeeName(es_employee_count);
	}
	//员工信息修改反馈表插入信息
	@Override
	public void insertEs_employee_feedback(FeedbackPo feedbackPo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		employeeMapper.insertEs_employee_feedback(feedbackPo);
	}
	//根据员工id，查询指定员工的信息修改反馈列表，反馈信息因该是未处理的信息
	@Override
	public List<FeedbackPo> empSelectFeedback(int es_employee_id) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		return employeeMapper.empSelectFeedback(es_employee_id);
	}
	//得到员工表的数据条数
	@Override
	public int empGetEmployeeCount() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		return employeeMapper.empGetEmployeeCount();
	}
	//返回指定区间的员工
	@Override
	public List<EmployeePo> empGetEmployeeList(int pageNow, int pageSize) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		employeeMapper = (EmployeeMapper)applicationContext.getBean("employeeMapper");
		return employeeMapper.empGetEmployeeList(pageNow, pageSize);
	}
	
}
