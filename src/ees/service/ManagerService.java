package ees.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ees.po.ContactsPo;
import ees.po.EmployeePo;
import ees.po.FeedbackPo;
import ees.po.ManagerPo;

public interface ManagerService {
	//查询一个指定管理员是否存在，存在返回true，不存在返回false
	public Boolean checkManager(String es_manager_login_count,String es_manager_login_password)throws Exception;
	
	//管理员登录状态值，判断管理员登录状态,离线状态才能登录
	public int checkManagerState(String es_manager_login_count,String es_manager_login_password)throws Exception;
		
	//修改管理员在线状态，登陆后将管理员的状态设置为在线状态，退出后将管理员设置为离线
	public void updateManagerState(ManagerPo managerPo)throws Exception;
	
	//退出后将管理员设置为离线
	public void updateManagerStateLogOut(String es_manager_login_count)throws Exception;
	
	//添加管理员
	public void addManager(ManagerPo managerPo)throws Exception;
	
	//返回所有管理员，管理员列表页展示
	public List<ManagerPo> getAllManager()throws Exception;
	
	//管理员添加员工
	public void managerAddEmployee(EmployeePo employeePo)throws Exception;
	
	//管理员添加员工紧急联系人
	public void managerAddContacts(List<ContactsPo> contactsPo)throws Exception;
	
	//这里可以获得刚插入表格的id，为后面Contact的插入提供了es_employee_id字段
	public int selectLastId(String es_employee_idcard)throws Exception;
	
	//返回员工信息反馈表的指定区间的数据
	public List<FeedbackPo> admGetFeedback(@Param("pageNow")int pageNow,@Param("pageSize")int pageSize)throws Exception;

	//返回员工信息反馈表的所有数据的总数量
	public int admGetFeedbackCount()throws Exception;
	
	//返回员工信息反馈表的指定区间的数据,没有考虑分页
	public List<FeedbackPo> admGetFeedbackList()throws Exception;
	
	//修改员工信息反馈表的数据状态
	public void updateFeedbackState(int es_employee_feedback_id)throws Exception;
	
	//返回员工表的数据条数
	public int admGetEmployeeCount()throws Exception;
	
	//返回指定区间的返回指定区间的员工
	public List<EmployeePo> admGetEmployeeList(int pageNow,int pageSize)throws Exception;
	
	//按照员工id查询得到指定员工
	public List<EmployeePo> admGetEmployeeById(int es_employee_id,int pageNow,int pageSize)throws Exception;
	//得到数据表里面总的数据条数,通过id
	public int admGetEmployeeCountById(int es_employee_id)throws Exception;
	
	//按照员工名字模糊查询
	public List<EmployeePo> admGetEmployeeByName(String es_employee_name,int pageNow,int pageSize)throws Exception;
	//得到数据表里面总的数据条数，通过name
	public int admGetEmployeeCountByName(String es_employee_name)throws Exception;
	
	//按照员工部门查询得到指定员工
	public List<EmployeePo> admGetEmployeeByDepartment(String es_employee_department,int pageNow,int pageSize)throws Exception;
	//得到数据表里面指定部门总的数据条数,通过指定部门
	public int admGetEmployeeCountByDepartment(String es_employee_department)throws Exception;
	
	//更具id删除指定的员工，由于外键是联级关系，所以删除员工时候该员工的相关信息也会被删除
	public void deleteEmployeeById(int es_employee_id)throws Exception;
	
	//更具id查询指定员工的信息
	public EmployeePo selectEmployeeById(int es_employee_id)throws Exception;
	
	//更具id更新员工
	public void updateEmployeeById(EmployeePo employeePo)throws Exception;
	//更具紧急联系人电话号码获取表id
	public int selectIdByConCall(String es_employee_contacts_call)throws Exception;
	//更新员工紧急联系人信息
	public void updateContacts(List<ContactsPo> contactsPo)throws Exception;
	//更具员工id删除指定员工紧急联系人
	public void deleteContactsByEmployeeId(int es_employee_id)throws Exception;
	//更具员工id，重新插入员工紧急联系人
	public void reInsertContacts(List<ContactsPo> contactsPo)throws Exception;
	
	//获取员工总人数
	public int getEmployeeAllCount()throws Exception;
	//获取男性数量
	public int getManCount()throws Exception;
	//获取女性数量
	public int getWomanCount()throws Exception;
	//获取年龄在18-22岁的人
	public int getAgeOne()throws Exception;
	//获取年龄在23-27岁的人
	public int getAgeTwo()throws Exception;
	//获取年龄在28-32岁的人
	public int getAgeThree()throws Exception;
	//获取年龄在33-37岁的人
	public int getAgeFour()throws Exception;
	//获取年龄在38-42岁的人
	public int getAgeFive()throws Exception;
	//获取年龄在大于42岁的人
	public int getAgeSix()throws Exception;
	//获取学历为初中的员工数量
	public int getQualificationOne()throws Exception;
	//获取学历为高中的员工数量
	public int getQualificationTwo()throws Exception;
	//获取学历为专科的员工数量
	public int getQualificationThree()throws Exception;
	//获取学历为本科的员工数量
	public int getQualificationFour()throws Exception;
	//获取学历为硕士的员工数量
	public int getQualificationFive()throws Exception;
	//获取学历为博士的员工数量
	public int getQualificationSix()throws Exception;

}
