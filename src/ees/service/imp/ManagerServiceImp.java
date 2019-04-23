package ees.service.imp;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ees.mapper.ManagerMapper;
import ees.po.ContactsPo;
import ees.po.EmployeePo;
import ees.po.FeedbackPo;
import ees.po.ManagerPo;
import ees.service.ManagerService;

public class ManagerServiceImp implements ManagerService{
	
	private static ApplicationContext applicationContext;
	private static ManagerMapper managerMapper;
	public static void main(String[] args) throws Exception{
//		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
//		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
//		System.out.println(managerMapper.);
//		ManagerPo managerPo=new ManagerPo();
//		managerPo.setEs_manager_login_count("ljkroot");
//		managerPo.setEs_manager_login_password("ljkroot");
//		managerPo.setEs_manager_login_state(0);
//		//System.out.println(managerMapper.checkManagerState("ljkroot", "ljkroot"));
//		managerMapper.updateManagerState(managerPo);
//		List<ContactsPo> contactsPoList=new ArrayList<>();
//		ContactsPo contactsPoO=new ContactsPo();
//		ContactsPo contactsPoT=new ContactsPo();
//		contactsPoO.setEs_employee_contacts_name("ljk");
//		contactsPoO.setEs_employee_contacts_relation("ljk");
//		contactsPoO.setEs_employee_contacts_call("ljk");
//		contactsPoO.setEs_employee_id(1);
//		contactsPoT.setEs_employee_contacts_name("kkk");
//		contactsPoT.setEs_employee_contacts_relation("kkk");
//		contactsPoT.setEs_employee_contacts_call("kkk");
//		contactsPoT.setEs_employee_id(1);
//		contactsPoList.add(contactsPoO);
//		contactsPoList.add(contactsPoT);
//		managerMapper.managerAddContacts(contactsPoList);
	}
	//查询一个指定管理员是否存在，存在返回true，不存在返回false
	@Override
	public Boolean checkManager(String es_manager_login_count, String es_manager_login_password) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		if(managerMapper.checkManager(es_manager_login_count, es_manager_login_password)!=null){
			return true;//存在指定管理员返回true
		}
		else{
			return false;//不存在返回false
		}
	}
	//管理员登录状态值，判断管理员登录状态,离线状态才能登录
	@Override
	public int checkManagerState(String es_manager_login_count, String es_manager_login_password) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.checkManagerState(es_manager_login_count, es_manager_login_password);
	}
	//修改管理员在线状态，登陆后将管理员的状态设置为在线状态，退出后将管理员设置为离线
	@Override
	public void updateManagerState(ManagerPo managerPo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.updateManagerState(managerPo);
	}
	
	@Override
	public void updateManagerStateLogOut(String es_manager_login_count) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.updateManagerStateLogOut(es_manager_login_count);
	}
	//添加管理员
	@Override
	public void addManager(ManagerPo managerPo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.addManager(managerPo);
	}
	//返回所有管理员，在管理员列表页展示
	@Override
	public List<ManagerPo> getAllManager() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getAllManager();
	}
	//管理员添加员工
	@Override
	public void managerAddEmployee(EmployeePo employeePo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.managerAddEmployee(employeePo);
	}
	//管理员添加员工紧急联系人
	@Override
	public void managerAddContacts(List<ContactsPo> contactsPo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.managerAddContacts(contactsPo);
	}
	//通过生分证表示选择指定员工id，为后面Contact的插入提供了es_employee_id字段
	@Override
	public int selectLastId(String es_employee_idcard) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.selectLastId(es_employee_idcard);
	}
	//返回员工信息反馈表的指定区间的数据
	@Override
	public List<FeedbackPo> admGetFeedback(int pageNow, int pageSize) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetFeedback(pageNow, pageSize);
	}
	//返回员工信息反馈表的所有数据的总数量
	@Override
	public int admGetFeedbackCount() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetFeedbackCount();
	}
	//返回员工信息反馈表的指定区间的数据,没有考虑分页
	@Override
	public List<FeedbackPo> admGetFeedbackList() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetFeedbackList();
	}
	//修改员工信息反馈表的数据状态
	@Override
	public void updateFeedbackState(int es_employee_feedback_id) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.updateFeedbackState(es_employee_feedback_id);
	}
	//返回员工表的数据条数
	@Override
	public int admGetEmployeeCount() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetEmployeeCount();
	}
	//返回指定区间的返回指定区间的员工
	@Override
	public List<EmployeePo> admGetEmployeeList(int pageNow, int pageSize) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetEmployeeList(pageNow, pageSize);
	}
	//按照员工id查询得到指定员工
	@Override
	public List<EmployeePo> admGetEmployeeById(int es_employee_id,int pageNow,int pageSize) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetEmployeeById(es_employee_id,pageNow,pageSize);
	}
	//得到数据表里面总的数据条数,通过id
	@Override
	public int admGetEmployeeCountById(int es_employee_id) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetEmployeeCountById(es_employee_id);
	}
	//按照员工名字模糊查询
	@Override
	public List<EmployeePo> admGetEmployeeByName(String es_employee_name,int pageNow,int pageSize) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetEmployeeByName(es_employee_name,pageNow,pageSize);
	}
	//得到数据表里面总的数据条数，通过name
	@Override
	public int admGetEmployeeCountByName(String es_employee_name) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetEmployeeCountByName(es_employee_name);
	}
	//按照员工部门查询得到指定员工
	@Override
	public List<EmployeePo> admGetEmployeeByDepartment(String es_employee_department,int pageNow,int pageSize) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetEmployeeByDepartment(es_employee_department,pageNow,pageSize);
	}
	//得到数据表里面指定部门总的数据条数,通过指定部门
	@Override
	public int admGetEmployeeCountByDepartment(String es_employee_department) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.admGetEmployeeCountByDepartment(es_employee_department);
	}
	//更具id删除指定的员工，由于外键是联级关系，所以删除员工时候该员工的相关信息也会被删除
	@Override
	public void deleteEmployeeById(int es_employee_id) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.deleteEmployeeById(es_employee_id);
	}
	//更具id查询指定员工的信息
	@Override
	public EmployeePo selectEmployeeById(int es_employee_id) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.selectEmployeeById(es_employee_id);
	}
	//更具id更新员工
	@Override
	public void updateEmployeeById(EmployeePo employeePo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.updateEmployeeById(employeePo);
	}
	//更新员工紧急联系人信息
	@Override
	public void updateContacts(List<ContactsPo> contactsPo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.updateContacts(contactsPo);
	}
	//更具紧急联系人电话号码获取表id
	@Override
	public int selectIdByConCall(String es_employee_contacts_call) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.selectIdByConCall(es_employee_contacts_call);
	}
	//更具员工id删除指定员工紧急联系人
	@Override
	public void deleteContactsByEmployeeId(int es_employee_id) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.deleteContactsByEmployeeId(es_employee_id);
	}
	//更具员工id，重新插入员工紧急联系人
	@Override
	public void reInsertContacts(List<ContactsPo> contactsPo) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		managerMapper.reInsertContacts(contactsPo);
	}
	//获取员工总人数
	@Override
	public int getEmployeeAllCount() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getEmployeeAllCount();
	}
	//获取男性数量
	@Override
	public int getManCount() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getManCount();
	}
	//获取女性数量
	@Override
	public int getWomanCount() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getWomanCount();
	}
	//获取年龄在18-22岁的人
	@Override
	public int getAgeOne() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getAgeOne();
	}
	//获取年龄在23-27岁的人
	@Override
	public int getAgeTwo() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getAgeTwo();
	}
	//获取年龄在28-32岁的人
	@Override
	public int getAgeThree() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getAgeThree();
	}
	//获取年龄在33-37岁的人
	@Override
	public int getAgeFour() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getAgeFour();
	}
	//获取年龄在38-42岁的人
	@Override
	public int getAgeFive() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getAgeFive();
	}
	//获取年龄在大于42岁的人
	@Override
	public int getAgeSix() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getAgeSix();
	}
	//获取学历为初中的员工数量
	@Override
	public int getQualificationOne() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getQualificationOne();
	}
	//获取学历为高中的员工数量
	@Override
	public int getQualificationTwo() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getQualificationTwo();
	}
	//获取学历为专科的员工数量
	@Override
	public int getQualificationThree() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getQualificationThree();
	}
	//获取学历为本科的员工数量
	@Override
	public int getQualificationFour() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getQualificationFour();
	}
	//获取学历为硕士的员工数量
	@Override
	public int getQualificationFive() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getQualificationFive();
	}
	//获取学历为博士的员工数量
	@Override
	public int getQualificationSix() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		managerMapper = (ManagerMapper)applicationContext.getBean("managerMapper");
		return managerMapper.getQualificationSix();
	}
}
