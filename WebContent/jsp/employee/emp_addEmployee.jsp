<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta name="keywords" content="企业员工信息管理系统">
    <meta name="description" content="企业员工信息管理系统">
    <meta name="author" content="最帅的刘先生">
    <meta name="soft" content="WebStorm、Eclipse">
    <meta name="copyRight" content="刘先生版权所有，拒绝盗版，盗版必究">

    <title>添加员工</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/manager/adm_addEmployee.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/manager/adm_addEmployee.js"></script>
</head>
<body>
<div id="body">
<script type="text/javascript">
//前端验证管理员登录时候符合某些规定，如果不符合在前端验证的时候就结束，不向后台
//发送请求，为后台减轻压力
$(document).ready(function(){
	//alert("test");
	
	$('#errorLogin').css("display","none");
	$('#errorPassword').css("display","none");
	
	$('#addManagerForm').submit(function(e){
		$('#errorLogin').css("display","none");
		$('#errorPassword').css("display","none");
		var name=$('#es_employee_count').val();
		var password=$('#es_employee_password').val();
		var rePassword=$('#es_employee_rePassword').val();
		//如果用户名、密码验证失败，就阻止表单提交
		if($.check.checkName(name)==true&&$.check.checkPassword(password)==true){
			$('#errorLogin').css("display","none");
		}
		else{
			$('#errorLogin').css("display","block");
			e.preventDefault();
			return false;
		}
		//检查两次输入密码是否一致
		if($.check.checkRePassword(password,rePassword)==true){
			$('#errorPassword').css("display","none");
		}
		else{
			$('#errorPassword').css("display","block");
			e.preventDefault();
			return false;
		}
	});
		//防止页面后退
	    history.pushState(null, null, document.URL);
	    window.addEventListener('popstate', function () {
	        history.pushState(null, null, document.URL);
	    });
});
$.check={
		checkName:function(name){//用户名匹配字符、数字、下划线、中文:6到10位
			var isMatch=name.match("^[a-zA-Z0-9_\u4e00-\u9fa5]{6,12}$");
			if(isMatch!=null){//成功
				
				return true;
			}
			else{//失败
				
              return false;
			}
		},
		checkPassword:function(password){
			var isMatch=password.match("^[a-zA-Z0-9_]{6,12}$");//密码匹配字符、数字、下划线、:6到10位
			if(isMatch!=null){//成功
				
				return true;
			}
			else{//失败
				
				return false;
			}
		},
		checkRePassword:function(password,rePassword){
			if(password==rePassword){
				return true;
			}
			else{
				return false;
			}
		}
}
</script>
    <!-- 头部导航条 -->
    <header>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid backgroundNav">
                <span class="navName">企业人员信息管理系统</span>
            </div>
        </nav>
    </header>
    <div class="changeLine"></div>
    <!--中间员工登录框部分-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
                <!--中间个人信息展示部分-->
                <form class="addEmpForm" action="/EESystem/emp_addEmployee.action" method="post" enctype="multipart/form-data">
                    <div class="table-responsive">
                        <table id="table" class="table table-bordered">
                            <thead></thead>
                            <tbody>
                            <tr>
                                <td class="imgHeaderAppear" colspan="2" rowspan="4">
                                    <img id="headAppear" class="headAppear" alt="HeadAppear">
                                    <input id="es_employee_img" name="es_employee_img" type="file" accept="image/png, image/jpeg, image/gif, image/jpg" />
                                </td>
                                <td colspan="1" class="keyId">ID:</td>
                                <td colspan="1" class="valueId">*自动分配*</td>
                                <td colspan="1" class="keyId">姓名:</td>
                                <td colspan="1" class="valueId">
                                    <input name="es_employee_name" type="text" />
                                </td>
                                <td colspan="1" class="keyId">年龄:</td>
                                <td colspan="2" class="valueId">
                                    <input name="es_employee_age" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="1" class="keyId">性别:</td>
                                <td colspan="1" class="valueId">
                                    <select name="es_employee_sex">
                                        <option value="男" selected="selected">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </td>
                                <td colspan="1" class="keyId">婚否:</td>
                                <td colspan="1" class="valueId">
                                    <select name="es_employee_marriage">
                                        <option value="未婚" selected="selected">未婚</option>
                                        <option value="已婚">已婚</option>
                                    </select>
                                </td>
                                <td colspan="1" class="keyId">身份证:</td>
                                <td colspan="2" class="valueId">
                                    <input name="es_employee_idcard" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="1" class="keyId">学历:</td>
                                <td colspan="1" class="valueId">
                                    <select name="es_employee_qualification">
                                        <option value="初中">初中</option>
                                        <option value="高中">高中</option>
                                        <option value="大专">大专</option>
                                        <option value="本科" selected="selected">本科</option>
                                        <option value="研究生">研究生</option>
                                        <option value="博士">博士</option>
                                    </select>
                                </td>
                                <td colspan="1" class="keyId">专业:</td>
                                <td colspan="1" class="valueId">
                                    <input name="es_employee_major" type="text" />
                                </td>
                                <td colspan="1" class="keyId">毕业院校:</td>
                                <td colspan="2" class="valueId">
                                    <input name="es_employee_school" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="1" class="keyId">部门:</td>
                                <td colspan="1" colspan="2" class="valueId">
                                    <select name="es_employee_department">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发" selected="selected">研发部</option>
                                    </select>
                                </td>
                                <td colspan="1" class="keyId">职位:</td>
                                <td colspan="1" class="valueId">
                                    <input name="es_employee_post" type="text" />
                                </td>
                                <td class="keyId" colspan="1">入职日期:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_entry" type="date" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">联系方式:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_call" type="number" />
                                </td>
                                <td class="keyId" colspan="1">邮箱:</td>
                                <td class="valueId" colspan="4">
                                    <input name="es_employee_mail" type="email" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">qq:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_qq" type="number" />
                                </td>
                                <td class="keyId" colspan="1">微信:</td>
                                <td class="valueId" colspan="4">
                                    <input name="es_employee_wechat" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">工资卡号:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_bankcard" type="number" />
                                </td>
                                <td class="keyId" colspan="1">劳动保障证号:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_labourcard" type="number" />
                                </td>
                                <td class="keyId" colspan="1">公积金账号:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_pafcard" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1" rowspan="2">紧急联系人:</td>
                                <td class="keyId" colspan="1">关系:</td>
                                <td class="valueId" colspan="1">
                                    <select name="es_employee_contacts_relation">
                                        <option value="父子">父子</option>
                                        <option value="母子">母子</option>
                                        <option value="姐弟">姐弟</option>
                                        <option value="姐妹">姐妹</option>
                                    </select>
                                </td>
                                <td class="keyId" colspan="1">姓名:</td>
                                <td class="valueId" colspan="1">
                                    <input name="es_employee_contacts_name" type="text" />
                                </td>
                                <td class="keyId" colspan="1">联系方式:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_contacts_call" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">关系:</td>
                                <td class="valueId" colspan="1">
                                    <select name="es_employee_contacts_relationT">
                                        <option value="父子">父子</option>
                                        <option value="母子">母子</option>
                                        <option value="姐弟">姐弟</option>
                                        <option value="姐妹">姐妹</option>
                                    </select>
                                </td>
                                <td class="keyId" colspan="1">姓名:</td>
                                <td class="valueId" colspan="1">
                                    <input name="es_employee_contacts_nameT" type="text" />
                                </td>
                                <td class="keyId" colspan="1">联系方式:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_contacts_callT" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">户籍地:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_pod" type="text" />
                                </td>
                                <td class="keyId" colspan="1">现居住地:</td>
                                <td class="valueId" colspan="4">
                                    <input name="es_employee_domicile" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">座右铭:</td>
                                <td class="valueId" colspan="8">
                                    <input name="es_employee_motto" type="text"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">目标:</td>
                                <td class="valueId" colspan="8">
                                    <input name="es_employee_aim" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">爱好:</td>
                                <td class="valueId" colspan="8">
                                    <input name="es_employee_hobby" type="text" />
                                </td>
                            </tr>
                            <tr>
                             	<td class="keyId" colspan="1">请输入登录主账号:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_count" type="text" placeholder="6-12数字或者英文字符" />
                                </td>
                                <td class="keyId" colspan="1">请输入登录密码:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_password" type="password" placeholder="6-12数字或者英文字符"  />
                                </td>
                                <td class="keyId" colspan="1">确认登录密码:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_rePassword" type="password" placeholder="6-12数字或者英文字符"  />
                                </td>
                            </tr>
                            <tr>
                            	<td class="valueId" colspan="8">
                                    <input class="saveSubmit" type="submit" value="保存" />
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div id="errorLogin" class="alert alert-danger text-center" role="alert">用户名或者密码格式不正确，请检查后重新填写。</div>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div id="errorPassword" class="alert alert-danger text-center" role="alert">两次输入密码不一致,请检查完善。</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>