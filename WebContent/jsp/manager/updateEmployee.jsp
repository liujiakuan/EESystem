<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Date"%>
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

    <title>修改员工信息</title>

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
<c:if test="${sessionScope.es_manager_login_count==null}">
	<c:redirect url="http://localhost:8080/EESystem/jsp/login.jsp"/>
</c:if>
<script type="text/javascript">
//前端验证管理员登录时候符合某些规定，如果不符合在前端验证的时候就结束，不向后台
//发送请求，为后台减轻压力
$(document).ready(function(){
	//alert("test");
	//$('#headAppear').click(function(){
	//	alert($('#es_employee_img').values());
	//});
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
                <ul>
                    <li><a href="http://localhost:8080/EESystem/managerLogOut.action">退出</a></li>
                    <li><time><fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd "/></time></li>
                    <li><%=session.getAttribute("es_manager_login_count").toString() %></li>
                </ul>
            </div>
        </nav>
    </header>
    <div class="changeLine"></div>
    <!--中间员工登录框部分-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
                <!--这里是侧边栏下拉折叠列表导航-->
                <div class="panel-group" id="accordion">
                    <div id="collapseOne" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/admEmployeeList.action" class="list-group-item leftItem">
                            	管理员后台首页
                        </a>
                    </div>
                    <div id="collapseTwo" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/jsp/manager/adm_addEmployee.jsp" class="list-group-item leftItem">
                            	添加员工
                        </a>
                    </div>
                    <div id="collapseThree" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/employeeData.action" class="list-group-item leftItem">
                            	后台数据中心
                        </a>
                    </div>
                    <div id="collapseFour" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/jsp/manager/epOrganize.jsp" class="list-group-item leftItem">
                            	企业组织架构图
                        </a>
                    </div>
                    <div id="collapseFive" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/admFeedbackList.action" class="list-group-item leftItem">
                            	个人信息修改申请反馈列表
                        </a>
                    </div>
                    <div id="collapseSix" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/jsp/manager/addManager.jsp" class="list-group-item leftItem">
                            	添加管理员
                        </a>
                    </div>
                    <div id="collapseSeven" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/managerList.action" class="list-group-item leftItem">
                            	管理员列表
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                <!--中间个人信息展示部分-->
                <form class="addEmpForm" action="/EESystem/admUpdateEmployee.action" method="post">
                    <div class="table-responsive">
                        <table id="table" class="table table-bordered">
                            <thead></thead>
                            <tbody>
                            <tr>
                                <td class="imgHeaderAppear" colspan="2" rowspan="4">
                                    <img src="${employeePo.es_employee_img}" id="headAppear" class="headAppear" alt="HeadAppear">
                                	<!--
                                	不允许修改头像
                                	用户修改信息时候，可以修改头像，也可以不修改头像导致后台逻辑代码实现部门很繁琐
                                	（暂时不知道什么很合理的方法来处理这个问题，所以就设定为不允许修改头像）
                                	 <input value="${employeePo.es_employee_img}" id="es_employee_img" name="es_employee_img" type="file" accept="image/png, image/jpeg, image/gif, image/jpg" /> 
                                	 -->
                                </td>
                                <td colspan="1" class="keyId">ID:</td>
                                <td colspan="1" class="valueId">
                                ${employeePo.es_employee_id}
                                <input type="hidden" name="es_employee_id" value="${employeePo.es_employee_id}" />
                                </td>
                                <td colspan="1" class="keyId">姓名:</td>
                                <td colspan="1" class="valueId">
                                    <input name="es_employee_name" value="${employeePo.es_employee_name}" type="text" />
                                </td>
                                <td colspan="1" class="keyId">年龄:</td>
                                <td colspan="2" class="valueId">
                                    <input name="es_employee_age" value="${employeePo.es_employee_age}" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="1" class="keyId">性别:</td>
                                <td colspan="1" class="valueId">
                                    <select name="es_employee_sex">
                                        <c:if test="${employeePo.es_employee_sex=='男'}">
                                        	<option value="男" selected="selected">男</option>
                                        	<option value="男">女</option>	
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_sex=='女'}">
                                        	<option value="男">男</option>
                                        	<option value="女"  selected="selected">女</option>	
                                        </c:if>
                                    </select>
                                </td>
                                <td colspan="1" class="keyId">婚否:</td>
                                <td colspan="1" class="valueId">
                                    <select name="es_employee_marriage">
                                        <c:if test="${employeePo.es_employee_marriage=='未婚'}">
                                        	<option value="未婚" selected="selected">未婚</option>
                                        	<option value="已婚">已婚</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_marriage=='已婚'}">
                                        	<option value="未婚">未婚</option>
                                        	<option value="已婚" selected="selected">已婚</option>
                                        </c:if>
                                    </select>
                                </td>
                                <td colspan="1" class="keyId">身份证:</td>
                                <td colspan="2" class="valueId">
                                    <input name="es_employee_idcard" value="${employeePo.es_employee_idcard}" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="1" class="keyId">学历:</td>
                                <td colspan="1" class="valueId">
                                    <select name="es_employee_qualification">
                                        <c:if test="${employeePo.es_employee_qualification=='初中生'}">
                                        <option value="初中生" selected="selected">初中生</option>
                                        <option value="高中生">高中生</option>
                                        <option value="专科生">专科生</option>
                                        <option value="本科生">本科生</option>
                                        <option value="研究生">研究生</option>
                                        <option value="博士生">博士生</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_qualification=='高中生'}">
                                        <option value="初中生">初中生</option>
                                        <option value="高中生" selected="selected">高中生</option>
                                        <option value="专科生">专科生</option>
                                        <option value="本科生">本科生</option>
                                        <option value="研究生">研究生</option>
                                        <option value="博士生">博士生</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_qualification=='专科生'}">
                                        <option value="初中生">初中生</option>
                                        <option value="高中生">高中生</option>
                                        <option value="专科生" selected="selected">专科生</option>
                                        <option value="本科生">本科生</option>
                                        <option value="研究生">研究生</option>
                                        <option value="博士生">博士生</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_qualification=='本科生'}">
                                        <option value="初中生">初中生</option>
                                        <option value="高中生">高中生</option>
                                        <option value="专科生">专科生</option>
                                        <option value="本科生" selected="selected">本科生</option>
                                        <option value="研究生">研究生</option>
                                        <option value="博士生">博士生</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_qualification=='研究生'}">
                                        <option value="初中生">初中生</option>
                                        <option value="高中生">高中生</option>
                                        <option value="专科生">专科生</option>
                                        <option value="本科生">本科生</option>
                                        <option value="研究生" selected="selected">研究生</option>
                                        <option value="博士生">博士生</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_qualification=='博士生'}">
                                        <option value="初中生">初中生</option>
                                        <option value="高中生">高中生</option>
                                        <option value="专科生">专科生</option>
                                        <option value="本科生">本科生</option>
                                        <option value="研究生">研究生</option>
                                        <option value="博士生" selected="selected">博士生</option>
                                        </c:if>
                                    </select>
                                </td>
                                <td colspan="1" class="keyId">专业:</td>
                                <td colspan="1" class="valueId">
                                    <input name="es_employee_major" value="${employeePo.es_employee_major}" type="text" />
                                </td>
                                <td colspan="1" class="keyId">毕业院校:</td>
                                <td colspan="2" class="valueId">
                                    <input name="es_employee_school" value="${employeePo.es_employee_school}" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="1" class="keyId">部门:</td>
                                <td colspan="1" colspan="2" class="valueId">
                                    <select name="es_employee_department">
                                        <c:if test="${employeePo.es_employee_department=='总经办'}">
                                        <option value="总经办" selected="selected">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='财务部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部" selected="selected">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='销售部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部" selected="selected">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='产品部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部" selected="selected">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='人事部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部" selected="selected">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='设计部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部" selected="selected">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='质检部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部" selected="selected">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='后勤部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部" selected="selected">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='综合管理部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部" selected="selected">综合管理部</option>
                                        <option value="研发部">研发部</option>
                                        </c:if>
                                        <c:if test="${employeePo.es_employee_department=='研发部'}">
                                        <option value="总经办">总经办</option>
                                        <option value="财务部">财务部</option>
                                        <option value="销售部">销售部</option>
                                        <option value="产品部">产品部</option>
                                        <option value="人事部">人事部</option>
                                        <option value="设计部">设计部</option>
                                        <option value="质检部">质检部</option>
                                        <option value="后勤部">后勤部</option>
                                        <option value="综合管理部">综合管理部</option>
                                        <option value="研发部" selected="selected">研发部</option>
                                        </c:if>
                                    </select>
                                </td>
                                <td colspan="1" class="keyId">职位:</td>
                                <td colspan="1" class="valueId">
                                    <input name="es_employee_post" value="${employeePo.es_employee_post}" type="text" />
                                </td>
                                <td class="keyId" colspan="1">入职日期:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_entry" type="date" value="<fmt:formatDate value='${employeePo.es_employee_entry}' pattern='yyyy-MM-dd'/>" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">联系方式:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_call" value="${employeePo.es_employee_call}" type="number" />
                                </td>
                                <td class="keyId" colspan="1">邮箱:</td>
                                <td class="valueId" colspan="4">
                                    <input name="es_employee_mail" value="${employeePo.es_employee_mail}" type="email" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">qq:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_qq" value="${employeePo.es_employee_qq}" type="number" />
                                </td>
                                <td class="keyId" colspan="1">微信:</td>
                                <td class="valueId" colspan="4">
                                    <input name="es_employee_wechat" value="${employeePo.es_employee_wechat}" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">工资卡号:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_bankcard" value="${employeePo.es_employee_bankcard}" type="number" />
                                </td>
                                <td class="keyId" colspan="1">劳动保障证号:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_labourcard" value="${employeePo.es_employee_labourcard}" type="number" />
                                </td>
                                <td class="keyId" colspan="1">公积金账号:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_pafcard" value="${employeePo.es_employee_pafcard}" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1" rowspan="2">紧急联系人:</td>
                                <td class="keyId" colspan="1">关系:</td>
                                <td class="valueId" colspan="1">
                                    <select name="es_employee_contacts_relation">
                                        <c:if test="${employeePo.contactsPoList.get(0).es_employee_contacts_relation=='父子'}">
                                        <option value="父子" selected="selected">父子</option>
                                        <option value="母子">母子</option>
                                        <option value="姐弟">姐弟</option>
                                        <option value="姐妹">姐妹</option>
                                        </c:if>
                                        <c:if test="${employeePo.contactsPoList.get(0).es_employee_contacts_relation=='母子'}">
                                        <option value="父子">父子</option>
                                        <option value="母子" selected="selected">母子</option>
                                        <option value="姐弟">姐弟</option>
                                        <option value="姐妹">姐妹</option>
                                        </c:if>
                                        <c:if test="${employeePo.contactsPoList.get(0).es_employee_contacts_relation=='姐弟'}">
                                        <option value="父子">父子</option>
                                        <option value="母子">母子</option>
                                        <option value="姐弟" selected="selected">姐弟</option>
                                        <option value="姐妹">姐妹</option>
                                        </c:if>
                                        <c:if test="${employeePo.contactsPoList.get(0).es_employee_contacts_relation=='姐妹'}">
                                        <option value="父子">父子</option>
                                        <option value="母子">母子</option>
                                        <option value="姐弟">姐弟</option>
                                        <option value="姐妹" selected="selected">姐妹</option>
                                        </c:if>
                                    </select>
                                </td>
                                <td class="keyId" colspan="1">姓名:</td>
                                <td class="valueId" colspan="1">
                                    <input name="es_employee_contacts_name" value="${employeePo.contactsPoList.get(0).es_employee_contacts_name}" type="text" />
                                </td>
                                <td class="keyId" colspan="1">联系方式:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_contacts_call" value="${employeePo.contactsPoList.get(0).es_employee_contacts_call}" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">关系:</td>
                                <td class="valueId" colspan="1">
                                    <select name="es_employee_contacts_relationT">
                                        <c:if test="${employeePo.contactsPoList.get(1).es_employee_contacts_relation=='父子'}">
                                        <option value="父子" selected="selected">父子</option>
                                        <option value="母子">母子</option>
                                        <option value="姐弟">姐弟</option>
                                        <option value="姐妹">姐妹</option>
                                        </c:if>
                                        <c:if test="${employeePo.contactsPoList.get(1).es_employee_contacts_relation=='母子'}">
                                        <option value="父子">父子</option>
                                        <option value="母子" selected="selected">母子</option>
                                        <option value="姐弟">姐弟</option>
                                        <option value="姐妹">姐妹</option>
                                        </c:if>
                                        <c:if test="${employeePo.contactsPoList.get(1).es_employee_contacts_relation=='姐弟'}">
                                        <option value="父子">父子</option>
                                        <option value="母子">母子</option>
                                        <option value="姐弟" selected="selected">姐弟</option>
                                        <option value="姐妹">姐妹</option>
                                        </c:if>
                                        <c:if test="${employeePo.contactsPoList.get(1).es_employee_contacts_relation=='姐妹'}">
                                        <option value="父子">父子</option>
                                        <option value="母子">母子</option>
                                        <option value="姐弟">姐弟</option>
                                        <option value="姐妹" selected="selected">姐妹</option>
                                        </c:if>
                                    </select>
                                </td>
                                <td class="keyId" colspan="1">姓名:</td>
                                <td class="valueId" colspan="1">
                                    <input name="es_employee_contacts_nameT" value="${employeePo.contactsPoList.get(1).es_employee_contacts_name}" type="text" />
                                </td>
                                <td class="keyId" colspan="1">联系方式:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_contacts_callT" value="${employeePo.contactsPoList.get(1).es_employee_contacts_call}" type="number" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">户籍地:</td>
                                <td class="valueId" colspan="3">
                                    <input name="es_employee_pod" value="${employeePo.es_employee_pod}" type="text" />
                                </td>
                                <td class="keyId" colspan="1">现居住地:</td>
                                <td class="valueId" colspan="4">
                                    <input name="es_employee_domicile" value="${employeePo.es_employee_domicile}" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">座右铭:</td>
                                <td class="valueId" colspan="8">
                                    <input name="es_employee_motto" value="${employeePo.es_employee_motto}" type="text"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">目标:</td>
                                <td class="valueId" colspan="8">
                                    <input name="es_employee_aim" value="${employeePo.es_employee_aim}" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td class="keyId" colspan="1">爱好:</td>
                                <td class="valueId" colspan="8">
                                    <input name="es_employee_hobby" value="${employeePo.es_employee_hobby}" type="text" />
                                </td>
                            </tr>
                            <tr>
                             	<td class="keyId" colspan="1">登录账号:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_count" value="${employeePo.es_employee_count}" type="text" placeholder="6-12数字或者英文字符" />
                                </td>
                                <td class="keyId" colspan="1">登录密码:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_password" value="${employeePo.es_employee_password}" type="text" placeholder="6-12数字或者英文字符" />
                                </td>
                                <td class="keyId" colspan="1">确认登录密码:</td>
                                <td class="valueId" colspan="2">
                                    <input name="es_employee_rePassword" value="${employeePo.es_employee_password}" type="text" placeholder="6-12数字或者英文字符" />
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