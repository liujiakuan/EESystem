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
import ees.po.FeedbackPo;
import ees.service.imp.EmployeeServiceImp;
import ees.service.imp.ManagerServiceImp;
import util.Utf8;

@Controller
public class EmployeeController {
	//跳转到员工首页
	@RequestMapping("/employeeIndex")
	ModelAndView employeeIndex(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			HttpSession httpSession
			)throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		EmployeeServiceImp employeeServiceImp=new EmployeeServiceImp();
		String es_employee_count=(String)httpSession.getAttribute("es_employee_count");
		modelAndView.getModel().put("employee",employeeServiceImp.employeeByCount(es_employee_count));
		modelAndView.setViewName("employee/emp_index");
		return modelAndView;

	}
	//员工添加自己信息
		@RequestMapping("/emp_addEmployee")
		public String emp_addEmployee(
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
				ModelMap model,
				HttpSession httpSession)throws Exception{
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
					targetFile= new File(path+fileName+"."+ext);
				}
		      //保存  ，并且判断是否是指定格式的图片
				if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)){
					es_employee_img.transferTo(targetFile);
				}
				else{
					//上传不能保存
				}
				//fileName = new Date().getTime() + fileName;
				String epHeadImgPath="http://localhost:8080/headImg/headImg/"+fileName+"."+ext;
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
				return "employee/emp_login";//添加员工信息完成后跳转到登录界面
		      } catch (Exception e) {
		          e.printStackTrace();
		      }
			
			return null;
		}
		
		//员工登录
		@RequestMapping("/employeeLogin")
		ModelAndView employeeLogin(
				@RequestParam(value="es_employee_count")String es_employee_count,
				@RequestParam(value="es_employee_password")String es_employee_password,
				@RequestParam(value="checkCode")String checkCode,
				HttpServletRequest request,
				HttpServletResponse response,
				ModelMap model,
				HttpSession httpSession
				)throws Exception{
			ModelAndView modelAndView=new ModelAndView();
			EmployeeServiceImp employeeServiceImp=new EmployeeServiceImp();
			//System.out.println(checkCode+"============"+httpSession.getAttribute("checkCode").toString());
			//System.out.println(employeeServiceImp.employeeLogin(es_employee_count, es_employee_password)+es_employee_count+"==============="+es_employee_password+checkCode.equalsIgnoreCase(httpSession.getAttribute("checkCode").toString()));
			if(employeeServiceImp.employeeLogin(es_employee_count, es_employee_password)==true&&checkCode.equalsIgnoreCase(httpSession.getAttribute("checkCode").toString())){
				httpSession.setAttribute("es_employee_count", es_employee_count);
				httpSession.setAttribute("es_employee_name", employeeServiceImp.employeeName(es_employee_count));
				//httpSession.setAttribute("es_employee_password", es_employee_password);
				//System.out.println("============"+employeeServiceImp.employeeIndex(es_employee_count, es_employee_password).get(0).getContactsPoList().get(1).getEs_employee_contacts_call());
				modelAndView.getModel().put("employee",employeeServiceImp.employeeIndex(es_employee_count, es_employee_password));
				modelAndView.setViewName("employee/emp_index");
				return modelAndView;
			}
			else{
				modelAndView.setViewName("error");
				return modelAndView;
			}
		}
		
		//员工信息修改反馈
		@RequestMapping("/employeeFeedback")
		ModelAndView employeeFeedback(
				@RequestParam(value="es_employee_feedback_content")String es_employee_feedback_content,
				HttpSession httpSession
				)throws Exception{
			ModelAndView modelAndView=new ModelAndView();
			EmployeeServiceImp employeeServiceImp=new EmployeeServiceImp();
			String es_employee_count=(String)httpSession.getAttribute("es_employee_count");
			FeedbackPo feedbackPo=new FeedbackPo();
			feedbackPo.setEs_employee_feedback_content(es_employee_feedback_content);
			feedbackPo.setEs_employee_feedback_date(new Date());
			feedbackPo.setEs_employee_feedback_state(0);
			feedbackPo.setEs_employee_id(employeeServiceImp.employeeId(es_employee_count));
			employeeServiceImp.insertEs_employee_feedback(feedbackPo);
			modelAndView.setViewName("employee/emp_alter");
			return modelAndView;
		}
		//员工查询自己的错误信息修改反馈信息
		@RequestMapping("/empSelectFeedback")
		ModelAndView empSelectFeedback(
				HttpSession httpSession
				)throws Exception{
			ModelAndView modelAndView=new ModelAndView();
			EmployeeServiceImp employeeServiceImp=new EmployeeServiceImp();
			String es_employee_count= (String)httpSession.getAttribute("es_employee_count");
			int es_employee_id=employeeServiceImp.employeeId(es_employee_count);
			List<FeedbackPo> feedbackList=employeeServiceImp.empSelectFeedback(es_employee_id);
			modelAndView.getModel().put("feedbackList", feedbackList);
			modelAndView.setViewName("employee/emp_FeedbackList");
			return modelAndView;
		}
		//员工查看员工列表
		@RequestMapping("/empEmployeeList")
		public ModelAndView empEmployeeList() throws Exception{
			EmployeeServiceImp employeeServiceImp = new EmployeeServiceImp();
			//定义分页所需变量
			int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=4;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			
			//总共多少条记录，从数据查询
			rowCount=employeeServiceImp.empGetEmployeeCount();
			//System.out.println("================================"+managerServiceImp.getManagerCount());
			//总共多少页，通过计算得到
			pageCount=rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			//从数据库取出指定页数据
			//pageNow=(pageNow-1)*pageSize;//pageNow:从第几条开始，pageSize:显示多少条
			List<EmployeePo> employeeList=employeeServiceImp.empGetEmployeeList((pageNow-1)*pageSize, pageSize);
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共多少条记录
			modelAndView.setViewName("employee/emp_emoloyeeList");
			return modelAndView;
		}
		//员工查看员工列表上一页，下一页
		@RequestMapping("/empEmployeeListSearchPage")
		public ModelAndView employeeListSearchPage(@RequestParam(value="page",required=false)String page,
				@RequestParam(value="pagenow",required=false)int pagenow,
				HttpSession httpSession
				) throws Exception{
			EmployeeServiceImp employeeServiceImp = new EmployeeServiceImp();
			//定义分页所需变量
			int pageNow=pagenow;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=4;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			//总共多少条记录，从数据查询
			rowCount=employeeServiceImp.empGetEmployeeCount();
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
			List<EmployeePo> employeeList=employeeServiceImp.empGetEmployeeList((pageNow-1)*pageSize, pageSize);
			//System.out.println(managerList.size()+"===============================================");
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共有多少条记录
			modelAndView.setViewName("employee/emp_emoloyeeList");
			return modelAndView;
		}
		//员工查看员工列表，跳转到指定页
		@RequestMapping("/empEmployeeListSearchPageF")
		public ModelAndView employeeListSearchPageF(@RequestParam(value="pageNumber",required=false)int pageNumber,
				@RequestParam(value="pagenow",required=false)int pagenow,
				HttpSession httpSession
				) throws Exception{
			EmployeeServiceImp employeeServiceImp = new EmployeeServiceImp();
			//定义分页所需变量
			int pageNow=1;//当前页,默认为1--定义在上面为全局变量,这样保证每次pageNow都是更新后的值
			int pageSize=4;//指定每页显示多少条数据
			int pageCount=0;//共多少页，计算得出
			int rowCount=0;//表示共有多少条数据，数据库查询的出
			//总共多少条记录，从数据查询
			rowCount=employeeServiceImp.empGetEmployeeCount();
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
			List<EmployeePo> employeeList=employeeServiceImp.empGetEmployeeList((pageNow-1)*pageSize, pageSize);
			//System.out.println(managerList.size()+"===============================================");
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.getModel().put("employeeList",employeeList);//员工列表
			modelAndView.getModel().put("pageCount",pageCount);//总共多少页
			modelAndView.getModel().put("pageNow",pageNow);//当前多少页
			modelAndView.getModel().put("rowCount",rowCount);//总共有多少条记录
			modelAndView.setViewName("employee/emp_emoloyeeList");
			return modelAndView;
		}
		//员工退出登录处理，清空登录时候保存的session数据
		@RequestMapping("/empQuit")
		ModelAndView empQuit(
				HttpSession httpSession
				)throws Exception{
			ModelAndView modelAndView=new ModelAndView();
			//退出后清空session数据，也可以指定清除部分数据HTTPSession.removeAttribute("");
			httpSession.invalidate();
			modelAndView.setViewName("quit");
			return modelAndView;
		}
}