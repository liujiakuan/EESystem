package ees.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ees.po.ContactsPo;
import ees.po.EmployeePo;
import ees.po.ManagerPo;
import ees.service.imp.ManagerServiceImp;
import util.Utf8;

@Controller
public class ManagerController {
	
	//管理员登录验证
	@RequestMapping("/checkLog")
	public ModelAndView checkLog(@RequestParam(value="es_manager_login_count",required=true)String es_manager_login_count,
			@RequestParam(value="es_manager_login_password",required=true)String es_manager_login_password,
			@RequestParam(value="checkCode",required=true)String checkCode,
			HttpSession httpSession
			) throws Exception{
		//先验证session里面的验证码是否输入正确
		//System.out.println(managerServiceImp.checkManager(es_manager_login_count, es_manager_login_password)+"Session================="+checkCode+":"+(String) httpSession.getAttribute("checkCode")+":"+checkCode.equalsIgnoreCase((String) httpSession.getAttribute("checkCode")));
		//验证通过输入的账号密码能够找到指定的管理员,同时验证管理员是否是离线状态
		//如果上面都检测通过则验证成功，否则验证失败
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		ModelAndView modelAndView=new ModelAndView();
		//System.out.println(es_manager_login_count+es_manager_login_password+managerServiceImp.checkManager(es_manager_login_count, es_manager_login_password)+"Session================="+checkCode+":"+(String) httpSession.getAttribute("checkCode")+":"+checkCode.equalsIgnoreCase((String) httpSession.getAttribute("checkCode")));
		if(managerServiceImp.checkManager(es_manager_login_count, es_manager_login_password)==true
			&&
			checkCode.equals((String) httpSession.getAttribute("checkCode"))==true
			&&
			managerServiceImp.checkManagerState(es_manager_login_count, es_manager_login_password)==0
				){//IgnoreCase
			//讲管理账号存入session
			httpSession.setAttribute("es_manager_login_count",es_manager_login_count);
			//修改管理员的登录状态为在线
			ManagerPo managerPo=new ManagerPo();
			managerPo.setEs_manager_login_count(es_manager_login_count);
			managerPo.setEs_manager_login_password(es_manager_login_password);
			managerPo.setEs_manager_login_state(1);//1表示在线，0表示离线
			managerServiceImp.updateManagerState(managerPo);
			//将管理员设置为在线状态，然后跳转到管理员后台首页
			modelAndView.setViewName("changeToIndex");	
		}
		else{
			//返回登录页并且提示信息输入错误（提示用户名或者密码或者验证码输入错误请检查）
			modelAndView.setViewName("error500");
		}
		return modelAndView;
	}
	//管理员退出登录==暂时没有
		@RequestMapping("/managerLogOut")
		public ModelAndView managerLogOut(
				HttpSession httpSession
				) throws Exception{
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			ModelAndView modelAndView=new ModelAndView();
			//修改管理员的登录状态为在线
			managerServiceImp.updateManagerStateLogOut((String)httpSession.getAttribute("es_manager_login_count"));
			httpSession.invalidate();
			//将管理员设置为在线状态，然后跳转到管理员后台首页
			modelAndView.setViewName("quit");	
			return modelAndView;
		}
	//添加管理员
	@RequestMapping("/addManager")
	ModelAndView addManager(@RequestParam(value="es_manager_login_count",required=true)String es_manager_login_count,
			@RequestParam(value="es_manager_login_password",required=true)String es_manager_login_password,
			@RequestParam(value="checkCode",required=true)String checkCode,
			HttpSession httpSession)throws Exception{
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		ModelAndView modelAndView=new ModelAndView();
		//验证是否存在相同账号密码的管理员，存在则不添加
		//验证输入的验证码是否与session里面的验证码一致
		//注:*****还应该在前端用js脚本做传递信息相关条件验证
		if(managerServiceImp.checkManager(es_manager_login_count, es_manager_login_password)!=true
			&&
			checkCode.equalsIgnoreCase((String) httpSession.getAttribute("checkCode"))==true
				){
			//验证成功后添加管理员
			ManagerPo managerPo=new ManagerPo();
			managerPo.setEs_manager_login_count(es_manager_login_count);
			managerPo.setEs_manager_login_password(es_manager_login_password);
			managerServiceImp.addManager(managerPo);
			modelAndView.setViewName("changeToIndex");	
		}
		else{
			//验证不成功则返回到登录几面，传递相关参数和值进行提示(验证码错误等提示)
			modelAndView.setViewName("error500");	
		}
		return modelAndView;
	}
	
	//返回所有管理员，在管理员列表页展示
	@RequestMapping("/managerList")
	public ModelAndView managerList()throws Exception{
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.getModel().put("managerList",managerServiceImp.getAllManager());
		modelAndView.setViewName("manager/managerList");
		return modelAndView;
		
	}
	
	//管理员上传修改组织架构图
	@RequestMapping(value="/uploadE.action")
	public String upLoadE(@RequestParam(value = "epOrganizeImg", required = false) MultipartFile epOrganizeImg, 
			HttpServletRequest request, ModelMap model){
		try {
		//图片存放路径
		String path="E:\\EESystemImg\\epOrganize\\";
		//图片原始名称
		String fileName = epOrganizeImg.getOriginalFilename();
		//获取文件后缀名
		int dot=fileName.lastIndexOf(".");
		String ext = "";//文件后缀名
        if ((dot > -1) && (dot < (fileName.length() - 1))) {
            ext = fileName.substring(dot + 1);
        }
		//新图片
		File targetFile=null;
		if(fileName!=null){
			targetFile= new File(path+"epOrganize.jpg");//epOrganize
		}
      //保存  ，并且判断是否是指定格式的图片
		if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)){
			epOrganizeImg.transferTo(targetFile);
		}
		else{
			//上传不能保存
		}
		String epOrganizeImgPath="http://localhost:8080/headImg/epOrganize/"+fileName;
      model.addAttribute("epOrganizeImg",epOrganizeImgPath);//以后数据库就存储这个路径
      return "manager/epOrganize";
      } catch (Exception e) {
          e.printStackTrace();
      }
     return null;
	}
	
	//管理员添加员工
	@RequestMapping("/adm_addEmployee")
	public String adm_addEmployee(
			@RequestParam(value="es_employee_img") MultipartFile es_employee_img, 
			@RequestParam(value="es_employee_name")String es_employee_name,
			@RequestParam(value="es_employee_age")Integer es_employee_age,
			@RequestParam(value="es_employee_sex")String es_employee_sex,
			@RequestParam(value="es_employee_marriage")String es_employee_marriage,
			@RequestParam(value="es_employee_idcard")String es_employee_idcard,
			@RequestParam(value="es_employee_qualification")String es_employee_qualification,
			@RequestParam(value="es_employee_major")String es_employee_major,
			@RequestParam(value="es_employee_school")String es_employee_school,
			@RequestParam(value="es_employee_department")String es_employee_department,
			@RequestParam(value="es_employee_post")String es_employee_post,
			@RequestParam(value="es_employee_entry")Date es_employee_entry,
			@RequestParam(value="es_employee_call")String es_employee_call,
			@RequestParam(value="es_employee_mail")String es_employee_mail,
			@RequestParam(value="es_employee_qq")String es_employee_qq,
			@RequestParam(value="es_employee_wechat")String es_employee_wechat,
			@RequestParam(value="es_employee_bankcard")String es_employee_bankcard,
			@RequestParam(value="es_employee_labourcard")String es_employee_labourcard,
			@RequestParam(value="es_employee_pafcard")String es_employee_pafcard,
			@RequestParam(value="es_employee_contacts_relation")String es_employee_contacts_relation,
			@RequestParam(value="es_employee_contacts_name")String es_employee_contacts_name,
			@RequestParam(value="es_employee_contacts_call")String es_employee_contacts_call,
			@RequestParam(value="es_employee_contacts_relationT")String es_employee_contacts_relationT,
			@RequestParam(value="es_employee_contacts_nameT")String es_employee_contacts_nameT,
			@RequestParam(value="es_employee_contacts_callT")String es_employee_contacts_callT,
			@RequestParam(value="es_employee_pod")String es_employee_pod,
			@RequestParam(value="es_employee_domicile")String es_employee_domicile,
			@RequestParam(value="es_employee_motto")String es_employee_motto,
			@RequestParam(value="es_employee_aim")String es_employee_aim,
			@RequestParam(value="es_employee_hobby")String es_employee_hobby,
			@RequestParam(value="es_employee_count")String es_employee_count,
			@RequestParam(value="es_employee_password")String es_employee_password,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model)throws Exception{
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		//ModelAndView modelAndView=new ModelAndView();
		//将上面接受到的参数封装到两个对象里面传递到Mapper里面去
		//接受参数，上传保存员工头像图片
		try {
			//图片存放路径
			String path="E:\\EESystemImg\\headImg\\";
			//图片原始名称
			String fileName = es_employee_img.getOriginalFilename();
			//获取文件后缀名
			int dot=fileName.lastIndexOf(".");
			String ext = "";//文件后缀名
	        if ((dot > -1) && (dot < (fileName.length() - 1))) {
	            ext = fileName.substring(dot + 1);
	        }
			//新图片
			File targetFile=null;
			if(fileName!=null){
				fileName = new Date().getTime()+"headImg";
				targetFile= new File(path+fileName);
			}
	      //保存  ，并且判断是否是指定格式的图片
			if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)){
				es_employee_img.transferTo(targetFile);
			}
			else{
				//上传不能保存
			}
			String epHeadImgPath="http://localhost:8080/headImg/headImg/"+fileName;
			model.addAttribute("epHeadImgPath",epHeadImgPath);//以后数据库就存储这个路径
			//员工对象数据封装
			EmployeePo employeePo=new EmployeePo();
			employeePo.setEs_employee_name(es_employee_name);
			employeePo.setEs_employee_age(es_employee_age);
			employeePo.setEs_employee_sex(es_employee_sex);
			employeePo.setEs_employee_img(epHeadImgPath);
			employeePo.setEs_employee_idcard(es_employee_idcard);
			employeePo.setEs_employee_marriage(es_employee_marriage);
			employeePo.setEs_employee_qualification(es_employee_qualification);
			employeePo.setEs_employee_school(es_employee_school);
			employeePo.setEs_employee_major(es_employee_major);
			employeePo.setEs_employee_department(es_employee_department);
			employeePo.setEs_employee_post(es_employee_post);
			employeePo.setEs_employee_entry(es_employee_entry);
			employeePo.setEs_employee_call(es_employee_call);
			employeePo.setEs_employee_mail(es_employee_mail);
			employeePo.setEs_employee_bankcard(es_employee_bankcard);
			employeePo.setEs_employee_labourcard(es_employee_labourcard);
			employeePo.setEs_employee_pafcard(es_employee_pafcard);
			employeePo.setEs_employee_pod(es_employee_pod);
			employeePo.setEs_employee_domicile(es_employee_domicile);
			employeePo.setEs_employee_motto(es_employee_motto);
			employeePo.setEs_employee_aim(es_employee_aim);
			employeePo.setEs_employee_qq(es_employee_qq);
			employeePo.setEs_employee_wechat(es_employee_wechat);
			employeePo.setEs_employee_hobby(es_employee_hobby);
			employeePo.setEs_employee_count(es_employee_count);
			employeePo.setEs_employee_password(es_employee_password);
			//封装好数据后添加到数据库
			managerServiceImp.managerAddEmployee(employeePo);
			//System.out.println("11============================="+es_employee_school);
			//System.out.println("22============================="+Utf8.utf8(es_employee_school));
			//员工紧急联系人对象数据封装
			int employeeId=managerServiceImp.selectLastId(Utf8.utf8(es_employee_idcard));
			//System.out.println("============================="+es_employee_school);
			List<ContactsPo> contactsPoList=new ArrayList<>();
			ContactsPo contactsPoO=new ContactsPo();
			ContactsPo contactsPoT=new ContactsPo();
			contactsPoO.setEs_employee_contacts_name(es_employee_contacts_name);
			contactsPoO.setEs_employee_contacts_relation(es_employee_contacts_relation);
			contactsPoO.setEs_employee_contacts_call(es_employee_contacts_call);
			contactsPoO.setEs_employee_id(employeeId);
			contactsPoT.setEs_employee_contacts_name(es_employee_contacts_nameT);
			contactsPoT.setEs_employee_contacts_relation(es_employee_contacts_relationT);
			contactsPoT.setEs_employee_contacts_call(es_employee_contacts_callT);
			contactsPoT.setEs_employee_id(employeeId);
			contactsPoList.add(contactsPoO);
			contactsPoList.add(contactsPoT);
			//传递封装好了的集合数据进行插入
			managerServiceImp.managerAddContacts(contactsPoList);
			return "changeToIndex";
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		return null;
	}
	//管理员查看员工信息修改反馈列表
	@RequestMapping("/admFeedbackList")
	ModelAndView admFeedbackList()throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		modelAndView.getModel().put("feedbackList",managerServiceImp.admGetFeedbackList());
		modelAndView.setViewName("manager/adm_feedbackList");
		return modelAndView;
	}
	//管理员查看员工信息修改反馈列表，修改处理之后状态
	@RequestMapping("/updateFeedbackState")
	ModelAndView updateFeedbackState(
			@RequestParam(value="es_employee_feedback_id")int es_employee_feedback_id
			)throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		managerServiceImp.updateFeedbackState(es_employee_feedback_id);
		modelAndView.setViewName("changeToIndex");
		return modelAndView;
	}
	//管理员查看员工列表,后台首页
	@RequestMapping("/admEmployeeList")
	public ModelAndView admEmployeeList() throws Exception{
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		//定义分页所需变量
		int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
		int pageSize=8;//指定每页显示多少条数据
		int pageCount=0;//共多少页，计算得出
		int rowCount=0;//表示共有多少条数据，数据库查询的出
		
		//总共多少条记录，从数据查询
		rowCount=managerServiceImp.admGetEmployeeCount();
		//System.out.println("================================"+managerServiceImp.getManagerCount());
		//总共多少页，通过计算得到
		pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
		//从数据库取出指定页数据
		//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
		List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeList((pageNow-1)*pageSize, pageSize);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.getModel().put("employeeList",employeeList);//员工列表
		modelAndView.getModel().put("pageCount",pageCount);//总共多少页
		modelAndView.getModel().put("pageNow",pageNow);//当前多少页
		modelAndView.getModel().put("rowCount",rowCount);//总共多少条记录
		modelAndView.setViewName("manager/index");
		return modelAndView;
	}
	//管理员查看员工列表上一页，下一页
	@RequestMapping("/admEmployeeListSearchPage")
	public ModelAndView admloyeeListSearchPage(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="pagenow",required=false)int pagenow,
			HttpSession httpSession
			) throws Exception{
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		//定义分页所需变量
		int pageNow=pagenow;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
		int pageSize=8;//指定每页显示多少条数据
		int pageCount=0;//共多少页，计算得出
		int rowCount=0;//表示共有多少条数据，数据库查询的出
		//总共多少条记录，从数据查询
		rowCount=managerServiceImp.admGetEmployeeCount();
		//System.out.println("================================"+);
		//总共多少页，通过计算得到
		pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
		//跳转到指定页
		//上一页，下一页点击事件
		if(page.equals("upOne")&&pageNow>1){
			pageNow-=1;
		}
		if(page.equals("nextOne")&&pageNow<pageCount){
			pageNow+=1;	
		}
		if(page.equals("firstPage")&&pageNow>1){
			pageNow=1;
		}
		if(page.equals("endPage")&&pageNow<pageCount){
			pageNow=pageCount;
		}
		//System.out.println(page+"==============================================="+pageNow);
		//从数据库取出指定页数据
		//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
		List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeList((pageNow-1)*pageSize, pageSize);
		//System.out.println(managerList.size()+"===============================================");
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.getModel().put("employeeList",employeeList);//员工列表
		modelAndView.getModel().put("pageCount",pageCount);//总共多少页
		modelAndView.getModel().put("pageNow",pageNow);//当前多少页
		modelAndView.getModel().put("rowCount",rowCount);//总共有多少条记录
		modelAndView.setViewName("manager/index");
		return modelAndView;
	}
	//管理员查看员工列表，跳转到指定页
	@RequestMapping("/admEmployeeListSearchPageF")
	public ModelAndView admloyeeListSearchPageF(@RequestParam(value="pageNumber",required=false)int pageNumber,
			@RequestParam(value="pagenow",required=false)int pagenow,
			HttpSession httpSession
			) throws Exception{
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		//定义分页所需变量
		int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
		int pageSize=8;//指定每页显示多少条数据
		int pageCount=0;//共多少页，计算得出
		int rowCount=0;//表示共有多少条数据，数据库查询的出
		//总共多少条记录，从数据查询
		rowCount=managerServiceImp.admGetEmployeeCount();
		//System.out.println("================================"+);
		//总共多少页，通过计算得到
		pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
		//跳转到指定页
		if(pageNumber>0&&pageNumber<=pageCount){
			pageNow=pageNumber;
		}
		else{
			pageNow=pagenow;
		}
		//System.out.println(page+"==============================================="+pageNow);
		//从数据库取出指定页数据
		//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
		List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeList((pageNow-1)*pageSize, pageSize);
		//System.out.println(managerList.size()+"===============================================");
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.getModel().put("employeeList",employeeList);//员工列表
		modelAndView.getModel().put("pageCount",pageCount);//总共多少页
		modelAndView.getModel().put("pageNow",pageNow);//当前多少页
		modelAndView.getModel().put("rowCount",rowCount);//总共有多少条记录
		modelAndView.setViewName("manager/index");
		return modelAndView;
	}
	//管理员通过id查询指定员工
	@RequestMapping("/admEmployeeById")
	public ModelAndView admEmployeeById(
			@RequestParam(value="es_employee_id")int es_employee_id
			) throws Exception{
		ManagerServiceImp managerServiceImp=new ManagerServiceImp();
		//定义分页所需变量
		int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
		int pageSize=8;//指定每页显示多少条数据
		int pageCount=0;//共多少页，计算得出
		int rowCount=0;//表示共有多少条数据，数据库查询的出
		
		//总共多少条记录，从数据查询
		rowCount=managerServiceImp.admGetEmployeeCountById(es_employee_id);
		//System.out.println("================================"+managerServiceImp.getManagerCount());
		//总共多少页，通过计算得到
		pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
		//从数据库取出指定页数据
		//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
		List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeById(es_employee_id,(pageNow-1)*pageSize, pageSize);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.getModel().put("employeeList",employeeList);//员工列表
		modelAndView.getModel().put("pageCount",pageCount);//总共多少页
		modelAndView.getModel().put("pageNow",pageNow);//当前多少页
		modelAndView.getModel().put("rowCount",rowCount);//总共多少条记录
		modelAndView.setViewName("manager/indexId");
		return modelAndView;
	}
	//管理员通过名字查询指定员工（模糊查询）
		@RequestMapping("/admEmployeeByName")
		public ModelAndView admEmployeeByName(
				@RequestParam(value="es_employee_name")String es_employee_name
				) throws Exception{
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			//定义分页所需变量
			int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=8;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			
			//总共多少条记录，从数据查询
			rowCount=managerServiceImp.admGetEmployeeCountByName(es_employee_name);
			//System.out.println("================================"+managerServiceImp.getManagerCount());
			//总共多少页，通过计算得到
			if(rowCount<=8){
				pageCount=1;
			}
			else{
				pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			}
			//从数据库取出指定页数据
			//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
			List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeByName(es_employee_name,(pageNow-1)*pageSize, pageSize);
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共多少条记录
			modelAndView.getModel().put("es_employee_name",es_employee_name);//带上名字参数
			modelAndView.setViewName("manager/indexName");
			return modelAndView;
		}
		//管理员查看员工列表上一页，下一页,通过名字，模糊查询
		@RequestMapping("/admEmployeeListSearchPageName")
		public ModelAndView admEmployeeListSearchPageName(@RequestParam(value="page",required=false)String page,
				@RequestParam(value="pagenow",required=false)int pagenow,
				@RequestParam(value="es_employee_name")String es_employee_name
				) throws Exception{
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			//定义分页所需变量
			int pageNow=pagenow;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=8;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			//总共多少条记录，从数据查询
			rowCount=managerServiceImp.admGetEmployeeCountByName(es_employee_name);
			//System.out.println("================================"+);
			//总共多少页，通过计算得到
			if(rowCount<=8){
				pageCount=1;
			}
			else{
				pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			}
			//跳转到指定页
			//上一页，下一页点击事件
			if(page.equals("upOne")&&pageNow>1){
				pageNow-=1;
			}
			if(page.equals("nextOne")&&pageNow<pageCount){
				pageNow+=1;	
			}
			if(page.equals("firstPage")&&pageNow>1){
				pageNow=1;
			}
			if(page.equals("endPage")&&pageNow<pageCount){
				pageNow=pageCount;
			}
			//System.out.println(page+"==============================================="+pageNow);
			//从数据库取出指定页数据
			//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
			List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeByName(es_employee_name,(pageNow-1)*pageSize, pageSize);
			//System.out.println(managerList.size()+"===============================================");
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共有多少条记录
			modelAndView.getModel().put("es_employee_name",es_employee_name);//带上名字参数
			modelAndView.setViewName("manager/indexName");
			return modelAndView;
		}
		//管理员查看员工列表，跳转到指定页，,通过名字，模糊查询
		@RequestMapping("/admEmployeeListSearchPageNameE")
		public ModelAndView admEmployeeListSearchPageNameE(@RequestParam(value="pageNumber",required=false)int pageNumber,
				@RequestParam(value="pagenow",required=false)int pagenow,
				@RequestParam(value="es_employee_name")String es_employee_name,
				HttpSession httpSession
				) throws Exception{
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			//定义分页所需变量
			int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=8;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			//总共多少条记录，从数据查询
			rowCount=managerServiceImp.admGetEmployeeCountByName(es_employee_name);
			//System.out.println("================================"+);
			//总共多少页，通过计算得到
			if(rowCount<=8){
				pageCount=1;
			}
			else{
				pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			}
			//跳转到指定页
			if(pageNumber>0&&pageNumber<=pageCount){
				pageNow=pageNumber;
			}
			else{
				pageNow=pagenow;
			}
			//System.out.println(page+"==============================================="+pageNow);
			//从数据库取出指定页数据
			//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
			List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeByName(es_employee_name,(pageNow-1)*pageSize, pageSize);
			//System.out.println(managerList.size()+"===============================================");
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共有多少条记录
			modelAndView.getModel().put("es_employee_name",es_employee_name);//带上名字参数
			modelAndView.setViewName("manager/indexName");
			return modelAndView;
		}
		//管理员通过查找指定部门查询指定员工（模糊查询）
		@RequestMapping("/admEmployeeByDepartment")
		public ModelAndView admEmployeeByDepartment(
				@RequestParam(value="es_employee_department")String es_employee_department
				) throws Exception{
			//这里通过浏览器URL传递参数乱码，通过下面将参数编码为UTF8
			es_employee_department=Utf8.utf8(es_employee_department);
			//System.out.println("==========================="+Utf8.utf8(es_employee_department));
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			//定义分页所需变量
			int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=8;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			
			//总共多少条记录，从数据查询
			rowCount=managerServiceImp.admGetEmployeeCountByDepartment(es_employee_department);
			//总共多少页，通过计算得到
			if(rowCount<=8){
				pageCount=1;
			}
			else{
				pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			}
			//从数据库取出指定页数据
			//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
			List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeByDepartment(es_employee_department,(pageNow-1)*pageSize, pageSize);
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共多少条记录
			modelAndView.getModel().put("es_employee_department",es_employee_department);//带上部门名称参数
			modelAndView.setViewName("manager/indexDepartment");
			return modelAndView;
		}
		//管理员查看员工列表上一页，下一页,通过查找指定部门
		@RequestMapping("/admEmployeeListSearchPageDepartment")
		public ModelAndView admEmployeeListSearchPageDepartment(
				@RequestParam(value="page",required=false)String page,
				@RequestParam(value="pagenow",required=false)int pagenow,
				@RequestParam(value="es_employee_department")String es_employee_department
				) throws Exception{
			//这里通过浏览器URL传递参数乱码，通过下面将参数编码为UTF8
			//System.out.println(es_employee_department+"==========================="+Utf8.utf8(es_employee_department));
			es_employee_department=Utf8.utf8(es_employee_department);
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			//定义分页所需变量
			int pageNow=pagenow;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=8;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			//总共多少条记录，从数据查询
			rowCount=managerServiceImp.admGetEmployeeCountByDepartment(es_employee_department);
			//System.out.println("================================"+);
			//总共多少页，通过计算得到
			if(rowCount<=8){
				pageCount=1;
			}
			else{
				pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			}
			//跳转到指定页
			//上一页，下一页点击事件
			if(page.equals("upOne")&&pageNow>1){
				pageNow-=1;
			}
			if(page.equals("nextOne")&&pageNow<pageCount){
				pageNow+=1;	
			}
			if(page.equals("firstPage")&&pageNow>1){
				pageNow=1;
			}
			if(page.equals("endPage")&&pageNow<pageCount){
				pageNow=pageCount;
			}
			//System.out.println(page+"==============================================="+pageNow);
			//从数据库取出指定页数据
			//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
			List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeByDepartment(es_employee_department,(pageNow-1)*pageSize, pageSize);
			//System.out.println(managerList.size()+"===============================================");
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共有多少条记录
			modelAndView.getModel().put("es_employee_department",es_employee_department);//带上部门名称参数
			modelAndView.setViewName("manager/indexDepartment");
			return modelAndView;
		}
		//管理员查看员工列表，跳转到指定页，,通过查找指定部门
		@RequestMapping("/admEmployeeListSearchPageDepartmentE")
		public ModelAndView admEmployeeListSearchPageDepartmentE(
				@RequestParam(value="pageNumber",required=false)int pageNumber,
				@RequestParam(value="pagenow",required=false)int pagenow,
				@RequestParam(value="es_employee_department")String es_employee_department,
				HttpSession httpSession
				) throws Exception{
			//这里通过浏览器URL传递参数乱码，通过下面将参数编码为UTF8
			//System.out.println(es_employee_department+"==========================="+Utf8.utf8(es_employee_department));
			es_employee_department=Utf8.utf8(es_employee_department);
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			//定义分页所需变量
			int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=8;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			//总共多少条记录，从数据查询
			rowCount=managerServiceImp.admGetEmployeeCountByDepartment(es_employee_department);
			//System.out.println("================================"+);
			//总共多少页，通过计算得到
			if(rowCount<=8){
				pageCount=1;
			}
			else{
				pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			}
			//跳转到指定页
			if(pageNumber>0&&pageNumber<=pageCount){
				pageNow=pageNumber;
			}
			else{
				pageNow=pagenow;
			}
			//System.out.println(page+"==============================================="+pageNow);
			//从数据库取出指定页数据
			//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
			List<EmployeePo> employeeList=managerServiceImp.admGetEmployeeByDepartment(es_employee_department,(pageNow-1)*pageSize, pageSize);
			//System.out.println(managerList.size()+"===============================================");
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共有多少条记录
			modelAndView.getModel().put("es_employee_department",es_employee_department);//带上部门名称参数
			modelAndView.setViewName("manager/indexDepartment");
			return modelAndView;
		}
		//更具id删除指定的员工，由于外键是联级关系，所以删除员工时候该员工的相关信息也会被删除
		@RequestMapping("/deleteEmployeeById")
		ModelAndView deleteEmployeeById(
				@RequestParam(value="es_employee_id")int es_employee_id
				)throws Exception{
			ModelAndView modelAndView=new ModelAndView();
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			managerServiceImp.deleteEmployeeById(es_employee_id);
			modelAndView.setViewName("changeToIndex");
			return modelAndView;
		}
		//更具id查询指定员工的信息
		@RequestMapping("/selectEmployeeById")
		ModelAndView selectEmployeeById(
				@RequestParam(value="es_employee_id")int es_employee_id
				)throws Exception{
			ModelAndView modelAndView=new ModelAndView();
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			EmployeePo employeePo=managerServiceImp.selectEmployeeById(es_employee_id);
			modelAndView.getModel().put("employeePo",employeePo);
			modelAndView.setViewName("manager/employee");
			return modelAndView;
		}
		//更具id查询指定员工的信息,-------修改
		@RequestMapping("/updateEmployeeById")
		ModelAndView updateEmployeeById(
				@RequestParam(value="es_employee_id")int es_employee_id
				)throws Exception{
			ModelAndView modelAndView=new ModelAndView();
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			EmployeePo employeePo=managerServiceImp.selectEmployeeById(es_employee_id);
			System.out.println(employeePo.getEs_employee_password()+"========================================="+employeePo.getEs_employee_count());
			modelAndView.getModel().put("employeePo",employeePo);
			modelAndView.setViewName("manager/updateEmployee");
			return modelAndView;
		}
		//管理员更具id，更新指定员工的信息
		@RequestMapping("/admUpdateEmployee")
		public String admUpdateEmployee( 
				@RequestParam(value="es_employee_id")int es_employee_id,
				@RequestParam(value="es_employee_name")String es_employee_name,
				@RequestParam(value="es_employee_age")Integer es_employee_age,
				@RequestParam(value="es_employee_sex")String es_employee_sex,
				@RequestParam(value="es_employee_marriage")String es_employee_marriage,
				@RequestParam(value="es_employee_idcard")String es_employee_idcard,
				@RequestParam(value="es_employee_qualification")String es_employee_qualification,
				@RequestParam(value="es_employee_major")String es_employee_major,
				@RequestParam(value="es_employee_school")String es_employee_school,
				@RequestParam(value="es_employee_department")String es_employee_department,
				@RequestParam(value="es_employee_post")String es_employee_post,
				@RequestParam(value="es_employee_entry")Date es_employee_entry,
				@RequestParam(value="es_employee_call")String es_employee_call,
				@RequestParam(value="es_employee_mail")String es_employee_mail,
				@RequestParam(value="es_employee_qq")String es_employee_qq,
				@RequestParam(value="es_employee_wechat")String es_employee_wechat,
				@RequestParam(value="es_employee_bankcard")String es_employee_bankcard,
				@RequestParam(value="es_employee_labourcard")String es_employee_labourcard,
				@RequestParam(value="es_employee_pafcard")String es_employee_pafcard,
				@RequestParam(value="es_employee_contacts_relation")String es_employee_contacts_relation,
				@RequestParam(value="es_employee_contacts_name")String es_employee_contacts_name,
				@RequestParam(value="es_employee_contacts_call")String es_employee_contacts_call,
				@RequestParam(value="es_employee_contacts_relationT")String es_employee_contacts_relationT,
				@RequestParam(value="es_employee_contacts_nameT")String es_employee_contacts_nameT,
				@RequestParam(value="es_employee_contacts_callT")String es_employee_contacts_callT,
				@RequestParam(value="es_employee_pod")String es_employee_pod,
				@RequestParam(value="es_employee_domicile")String es_employee_domicile,
				@RequestParam(value="es_employee_motto")String es_employee_motto,
				@RequestParam(value="es_employee_aim")String es_employee_aim,
				@RequestParam(value="es_employee_hobby")String es_employee_hobby,
				@RequestParam(value="es_employee_count")String es_employee_count,
				@RequestParam(value="es_employee_password")String es_employee_password,
				HttpServletRequest request,
				HttpServletResponse response,
				ModelMap model)throws Exception{
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			//ModelAndView modelAndView=new ModelAndView();
			//将上面接受到的参数封装到两个对象里面传递到Mapper里面去
			//接受参数，上传保存员工头像图片
			//员工对象数据封装
			EmployeePo employeePo=new EmployeePo();
			employeePo.setEs_employee_id(es_employee_id);
			employeePo.setEs_employee_name(es_employee_name);
			employeePo.setEs_employee_age(es_employee_age);
			employeePo.setEs_employee_sex(es_employee_sex);
			employeePo.setEs_employee_img("");
			employeePo.setEs_employee_idcard(es_employee_idcard);
			employeePo.setEs_employee_marriage(es_employee_marriage);
			employeePo.setEs_employee_qualification(es_employee_qualification);
			employeePo.setEs_employee_school(es_employee_school);
			employeePo.setEs_employee_major(es_employee_major);
			employeePo.setEs_employee_department(es_employee_department);
			employeePo.setEs_employee_post(es_employee_post);
			employeePo.setEs_employee_entry(es_employee_entry);
			employeePo.setEs_employee_call(es_employee_call);
			employeePo.setEs_employee_mail(es_employee_mail);
			employeePo.setEs_employee_bankcard(es_employee_bankcard);
			employeePo.setEs_employee_labourcard(es_employee_labourcard);
			employeePo.setEs_employee_pafcard(es_employee_pafcard);
			employeePo.setEs_employee_pod(es_employee_pod);
			employeePo.setEs_employee_domicile(es_employee_domicile);
			employeePo.setEs_employee_motto(es_employee_motto);
			employeePo.setEs_employee_aim(es_employee_aim);
			employeePo.setEs_employee_qq(es_employee_qq);
			employeePo.setEs_employee_wechat(es_employee_wechat);
			employeePo.setEs_employee_hobby(es_employee_hobby);
			employeePo.setEs_employee_count(es_employee_count);
			employeePo.setEs_employee_password(es_employee_password);
			//封装好数据后更新员工信息
			managerServiceImp.updateEmployeeById(employeePo);
			//================批量更新员工紧急联系人有点问题，用方法（先删除，在插入）
			//删除更新之前员工的紧急联系人的信息，然后再下面进行重新插入
			managerServiceImp.deleteContactsByEmployeeId(es_employee_id);
			//封装员工紧急联系人信息
			List<ContactsPo> contactsPoList=new ArrayList<>();
			ContactsPo contactsPoO=new ContactsPo();
			ContactsPo contactsPoT=new ContactsPo();
			//contactsPoO.setEs_employee_contacts_id(managerServiceImp.selectIdByConCall(es_employee_contacts_call));
			contactsPoO.setEs_employee_contacts_name(es_employee_contacts_name);
			contactsPoO.setEs_employee_contacts_relation(es_employee_contacts_relation);
			contactsPoO.setEs_employee_contacts_call(es_employee_contacts_call);
			contactsPoO.setEs_employee_id(es_employee_id);
			//contactsPoT.setEs_employee_contacts_id(managerServiceImp.selectIdByConCall(es_employee_contacts_callT));
			contactsPoT.setEs_employee_contacts_name(es_employee_contacts_nameT);
			contactsPoT.setEs_employee_contacts_relation(es_employee_contacts_relationT);
			contactsPoT.setEs_employee_contacts_call(es_employee_contacts_callT);
			contactsPoT.setEs_employee_id(es_employee_id);
			contactsPoList.add(contactsPoO);
			contactsPoList.add(contactsPoT);
			//传递封装好了的集合数据进行插入
			managerServiceImp.managerAddContacts(contactsPoList);
			//managerServiceImp.updateContacts(contactsPoList);
			//修改完成后跳转到首页
			return "changeToIndex";
		}
		//更具id查询指定员工的信息
		@RequestMapping("/employeeData")
		ModelAndView employeeData()throws Exception{
			ModelAndView modelAndView=new ModelAndView();
			ManagerServiceImp managerServiceImp=new ManagerServiceImp();
			modelAndView.getModel().put("getEmployeeAllCount",managerServiceImp.getEmployeeAllCount());//获取员工总人数
			modelAndView.getModel().put("getManCount",managerServiceImp.getManCount());//获取男性数量
			modelAndView.getModel().put("getWomanCount",managerServiceImp.getWomanCount());//获取女性数量
			modelAndView.getModel().put("getAgeOne",managerServiceImp.getAgeOne());//获取年龄在18-22岁的人
			modelAndView.getModel().put("getAgeTwo",managerServiceImp.getAgeTwo());//获取年龄在23-27岁的人
			modelAndView.getModel().put("getAgeThree",managerServiceImp.getAgeThree());//获取年龄在28-32岁的人
			modelAndView.getModel().put("getAgeFour",managerServiceImp.getAgeFour());//获取年龄在33-37岁的人
			modelAndView.getModel().put("getAgeFive",managerServiceImp.getAgeFive());//获取年龄在38-42岁的人
			modelAndView.getModel().put("getAgeSix",managerServiceImp.getAgeSix());//获取年龄在大于42岁的人
			modelAndView.getModel().put("getQualificationOne",managerServiceImp.getQualificationOne());//获取学历为初中的员工数量
			modelAndView.getModel().put("getQualificationTwo",managerServiceImp.getQualificationTwo());//获取学历为高中的员工数量
			modelAndView.getModel().put("getQualificationThree",managerServiceImp.getQualificationThree());//获取学历为专科的员工数量
			modelAndView.getModel().put("getQualificationFour",managerServiceImp.getQualificationFour());//获取学历为本科的员工数量
			modelAndView.getModel().put("getQualificationFive",managerServiceImp.getQualificationFive());//获取学历为硕士的员工数量
			modelAndView.getModel().put("getQualificationSix",managerServiceImp.getQualificationSix());//获取学历为博士的员工数量
			modelAndView.setViewName("manager/employeeData");
			return modelAndView;
		}
}
