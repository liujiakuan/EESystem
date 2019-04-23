package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class AddEmployeeData {
	//批量添加员工信息数据
	private static final String URL="jdbc:mysql://localhost:3306/employeesystem";
	private static final String USER="root";
	private static final String PASSWORD="dream37326";
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(URL,USER,PASSWORD);
			
			String[] sex={"男","女"};
			int[] age={20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,
					50,51,52,53,54,55,56,57,58,59};
			String[] grade={"高中生","专科生","本科生","研究生","博士生"};
			String[] grade1={"java程序员","软件开发工程师","大数据开发工程师","经理","会计","HR","保安","扫地阿姨","质检员","产品专员","设计师","销售员"};
			String[] grade2={"总经办","财务部","人事部","研发部","销售部","产品部","设计部","质检部","后勤部","综合管理部"};
			String[] grade3={"成都","大成都","宜宾","南溪","北京","上海","杭州","深圳","广州","长沙","大理","丽江","甘孜","阿坝","乌鲁不齐","重庆","珠海","澳门"};
			String[] grade4={"已婚","未婚"};//性别
			String[] grade6={"成都信息工程大学","北京大学","清华大学","哈佛大学","牛津大学","剑桥大学","四川大学","电子科技大学","斯坦福大学","香港大学","北京邮电大学","国防科技大学"};//学校
			String[] grade7={"计算机科学与技术","软件工程","财务管理","市场营销","财务会计","数字媒体","外贸英语","网络安全","幼师","大气科学技术","人力资源管理","程序猿","java软件开发","大数据分析","人工智能"};
			Random random=new Random();
			PreparedStatement preparedStatement = connection.prepareStatement("insert into es_employee(es_employee_name,es_employee_age,es_employee_sex,es_employee_img,es_employee_idcard,es_employee_marriage,es_employee_qualification,es_employee_school,es_employee_major,es_employee_department,es_employee_post,es_employee_entry,es_employee_call,es_employee_mail,es_employee_bankcard,es_employee_labourcard,es_employee_pafcard,es_employee_pod,es_employee_domicile,es_employee_motto,es_employee_aim,es_employee_qq,es_employee_wechat,es_employee_hobby,es_employee_count,es_employee_password) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			for(int i=0;i<2048;i++){
				preparedStatement.setString(1,"ljk"+(i+1));
				preparedStatement.setInt(2,age[random.nextInt(40)]);
				preparedStatement.setString(3,sex[random.nextInt(2)]);
				preparedStatement.setString(4,"http://localhost:8080/headImg/headImg/headImg.png");
				preparedStatement.setString(5,"5"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(9)+random.nextInt(9)+random.nextInt(9)+random.nextInt(9)+random.nextInt(9)+random.nextInt(9)+random.nextInt(9)+random.nextInt(9)+random.nextInt(9)+random.nextInt(9));
				preparedStatement.setString(6,grade4[random.nextInt(2)]);
				preparedStatement.setString(7,grade[random.nextInt(5)]);//学历
				preparedStatement.setString(8,grade6[random.nextInt(12)]);//学校
				preparedStatement.setString(9,grade7[random.nextInt(15)]);//专业
				preparedStatement.setString(10,grade2[random.nextInt(10)]);//部门
				preparedStatement.setString(11,grade1[random.nextInt(12)]);//职位
				preparedStatement.setDate(12,new java.sql.Date(2017, 12, 2));//入职日期
				preparedStatement.setString(13,"1"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10));//员工电话号码
				preparedStatement.setString(14,"1"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+"@qq.com");//员工邮箱
				preparedStatement.setString(15,"6"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10));//员工银行卡号
				preparedStatement.setString(16,"6"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10));//劳动保障号
				preparedStatement.setString(17,"6"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10));//员工公积金账号
				preparedStatement.setString(18,grade3[random.nextInt(18)]);//员工户籍地
				preparedStatement.setString(19,grade3[random.nextInt(18)]);//现在居住地
				preparedStatement.setString(20,"成为富一代、迎娶小仙女、走向人生巅峰。");//员工座右铭
				preparedStatement.setString(21,"成为富一代、迎娶小仙女、走向人生巅峰。");//员工目标
				preparedStatement.setString(22,random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+"1"+random.nextInt(10)+random.nextInt(10));//员工QQ号
				preparedStatement.setString(23,"WeChat"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+"1"+random.nextInt(10)+random.nextInt(10));//员工微信号
				preparedStatement.setString(24,"成为富一代、迎娶小仙女、走向人生巅峰。");//员工爱好
				preparedStatement.setString(25,"ljk"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10));//员工登录账号
				preparedStatement.setString(26,"ljk"+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10));//员工登录密码
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();//插入、更新语句必须手动进行提交，*********当然jdbc可以设置自动提交
			preparedStatement.cancel();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
}
