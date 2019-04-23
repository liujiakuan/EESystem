package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class AddEmployeeContacts {
	//批量添加员工紧急联系人数据
	private static final String URL="jdbc:mysql://localhost:3306/employeesystem";
	private static final String USER="root";
	private static final String PASSWORD="dream37326";
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(URL,USER,PASSWORD);
			String[] grade={"父子","母子","姐弟","姐妹"};
			Random random=new Random();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into es_employee_contacts(es_employee_contacts_name,es_employee_contacts_relation,es_employee_contacts_call,es_employee_id) values(?,?,?,?)");
			for(int i=0;i<2048;i++){
				for(int j=0;j<2;j++){
					preparedStatement.setString(1,"yky"+(i+j));//姓名
					preparedStatement.setString(2,grade[random.nextInt(4)]);//关系
					preparedStatement.setString(3,"1"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10));//联系方式
					preparedStatement.setInt(4,i+2);//对应员工id
					preparedStatement.addBatch();
				}
			}
			preparedStatement.executeBatch();//插入、更新语句必须手动进行提交，*********当然jdbc可以设置自动提交
			preparedStatement.cancel();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
}
