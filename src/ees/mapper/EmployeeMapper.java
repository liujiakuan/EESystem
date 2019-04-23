package ees.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ees.po.ContactsPo;
import ees.po.EmployeePo;
import ees.po.FeedbackPo;

public interface EmployeeMapper {
	//员工添加自己信息
	public void employeeAddEmployee(EmployeePo employeePo)throws Exception;
	
	//员工添加员工紧急联系人
	public void employeeAddContacts(List<ContactsPo> contactsPo)throws Exception;
	
	//这里可以获得刚插入表格的id，为后面Contact的插入提供了es_employee_id字段
	public int employeeSelectLastId(String es_employee_idcard)throws Exception;
	
	//员工登录,通过制定账号和密码查询指定员工
	public EmployeePo employeeLogin(@Param("es_employee_count")String es_employee_count,@Param("es_employee_password")String es_employee_password)throws Exception;
	
	//员工登录,通过制定账号和密码查询指定员工并且返回
	/**
	 * 其实这里只用返回EmployeePo，不用返回List<EmployeePo>，因为返回结果只有一个对象
	 * @param es_employee_count
	 * @param es_employee_password
	 * @return
	 * @throws Exception
	 */
	public List<EmployeePo> employeeIndex(@Param("es_employee_count")String es_employee_count,@Param("es_employee_password")String es_employee_password)throws Exception;
	//通过员工账号查找指定员工
	public List<EmployeePo> employeeByCount(@Param("es_employee_count")String es_employee_count)throws Exception;
	
	//更具员工账号获取员工id
	public int employeeId(String es_employee_count)throws Exception;
	//更具员工账号获取员工姓名
	public String employeeName(String es_employee_count)throws Exception;
	//员工信息修改反馈表插入信息
	public void insertEs_employee_feedback(FeedbackPo feedbackPo)throws Exception;
	
	//根据员工id，查询指定员工的信息修改反馈列表，反馈信息因该是未处理的信息
	public List<FeedbackPo> empSelectFeedback(int es_employee_id)throws Exception;
	
	//返回员工表的数据条数
	public int empGetEmployeeCount()throws Exception;
	
	//返回指定区间的返回指定区间的员工
	public List<EmployeePo> empGetEmployeeList(@Param("pageNow")int pageNow,@Param("pageSize")int pageSize)throws Exception;
}
