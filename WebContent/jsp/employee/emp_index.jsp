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

    <title>员工首页</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/employee/emp_index.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/employee/emp_index.js"></script>
</head>
<body>
<div id="body">
<c:if test="${sessionScope.es_employee_count==null}">
	<c:redirect url="http://localhost:8080/EESystem/jsp/employee/emp_login.jsp"/>
</c:if>
    <!-- 头部导航条 -->
    <header>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid backgroundNav">
                <span class="navName">企业人员信息管理系统</span>
                <ul>
                    <li><a href="http://localhost:8080/EESystem/empQuit.action">退出</a></li>
                    <li><time><fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd "/></time></li>
                    <li>欢迎你:${employee.get(0).es_employee_name}</li>
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
                        <a href="http://localhost:8080/EESystem/employeeIndex.action" class="list-group-item leftItem">
                            	个人信息
                        </a>
                    </div>
                    <div id="collapseTwo" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/jsp/employee/emp_epOrganize.jsp" class="list-group-item leftItem">
                            	企业组织架构图
                        </a>
                    </div>
                    <div id="collapseThree" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/empEmployeeList.action" class="list-group-item leftItem">
                            	企业员工列表
                        </a>
                    </div>
                    <div id="collapseFour" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/jsp/employee/emp_alter.jsp" class="list-group-item leftItem">
                            	个人信息修改申请
                        </a>
                    </div>
                    <div id="collapseFive" class="panel panel-danger marginTop">
                        <a href="http://localhost:8080/EESystem/empSelectFeedback.action" class="list-group-item leftItem">
                            	个人信息修改申请反馈
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-9">
                <!--中间个人信息展示部分-->
               <div class="table-responsive">
                   <table id="table" class="table table-bordered">
                       <thead></thead>
                       <tbody>
                       <tr>
                           <td class="imgHeaderAppear" colspan="2" rowspan="4">
                               <img class="headAppear" src="${employee.get(0).es_employee_img}" alt="HeadAppear">
                           </td>
                           <td colspan="1" class="keyId">ID:</td>
                           <td colspan="1" class="valueId">${employee.get(0).es_employee_id}</td>
                           <td colspan="1" class="keyId">姓名:</td>
                           <td colspan="1" class="valueId">${employee.get(0).es_employee_name}</td>
                           <td colspan="1" class="keyId">年龄:</td>
                           <td colspan="2" class="valueId">${employee.get(0).es_employee_age}</td>
                       </tr>
                       <tr>
                           <td colspan="1" class="keyId">性别:</td>
                           <td colspan="1" class="valueId">${employee.get(0).es_employee_sex}</td>
                           <td colspan="1" class="keyId">婚否:</td>
                           <td colspan="1" class="valueId">${employee.get(0).es_employee_marriage}</td>
                           <td colspan="1" class="keyId">身份证:</td>
                           <td colspan="2" class="valueId">${employee.get(0).es_employee_idcard}</td>
                       </tr>
                       <tr>
                           <td colspan="1" class="keyId">学历:</td>
                           <td colspan="1" class="valueId">${employee.get(0).es_employee_qualification}</td>
                           <td colspan="1" class="keyId">专业:</td>
                           <td colspan="1" class="valueId">${employee.get(0).es_employee_major}</td>
                           <td colspan="1" class="keyId">毕业院校:</td>
                           <td colspan="2" class="valueId">${employee.get(0).es_employee_school}</td>
                       </tr>
                       <tr>
                           <td colspan="1" class="keyId">部门:</td>
                           <td colspan="1" class="valueId">${employee.get(0).es_employee_department}</td>
                           <td colspan="1" class="keyId">职位:</td>
                           <td colspan="1" class="valueId">${employee.get(0).es_employee_post}</td>
                           <td class="keyId" colspan="1">入职日期:</td>
                           <td class="valueId" colspan="2">
                           <fmt:formatDate value="${employee.get(0).es_employee_entry}" pattern="yyyy年MM月dd日"/>
                           </td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1">联系方式:</td>
                           <td class="valueId" colspan="3">${employee.get(0).es_employee_call}</td>
                           <td class="keyId" colspan="1">邮箱:</td>
                           <td class="valueId" colspan="4">${employee.get(0).es_employee_mail}</td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1">qq:</td>
                           <td class="valueId" colspan="3">${employee.get(0).es_employee_qq}</td>
                           <td class="keyId" colspan="1">微信:</td>
                           <td class="valueId" colspan="4">${employee.get(0).es_employee_wechat}</td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1">工资卡号:</td>
                           <td class="valueId" colspan="2">${employee.get(0).es_employee_bankcard}</td>
                           <td class="keyId" colspan="1">劳动保障证号:</td>
                           <td class="valueId" colspan="2">${employee.get(0).es_employee_labourcard}</td>
                           <td class="keyId" colspan="1">公积金账号:</td>
                           <td class="valueId" colspan="2">${employee.get(0).es_employee_pafcard}</td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1" rowspan="2">紧急联系人:</td>
                           <td class="keyId" colspan="1">关系:</td>
                           <td class="valueId" colspan="1">${employee.get(0).contactsPoList.get(0).es_employee_contacts_relation}</td>
                           <td class="keyId" colspan="1">姓名:</td>
                           <td class="valueId" colspan="1">${employee.get(0).contactsPoList.get(0).es_employee_contacts_name}</td>
                           <td class="keyId" colspan="1">联系方式:</td>
                           <td class="valueId" colspan="3">${employee.get(0).contactsPoList.get(0).es_employee_contacts_call}</td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1">关系:</td>
                           <td class="valueId" colspan="1">${employee.get(0).contactsPoList.get(1).es_employee_contacts_relation}</td>
                           <td class="keyId" colspan="1">姓名:</td>
                           <td class="valueId" colspan="1">${employee.get(0).contactsPoList.get(1).es_employee_contacts_name}</td>
                           <td class="keyId" colspan="1">联系方式:</td>
                           <td class="valueId" colspan="3">${employee.get(0).contactsPoList.get(1).es_employee_contacts_call}</td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1">户籍地:</td>
                           <td class="valueId" colspan="3">${employee.get(0).es_employee_pod}</td>
                           <td class="keyId" colspan="1">现居住地:</td>
                           <td class="valueId" colspan="4">${employee.get(0).es_employee_domicile}</td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1">座右铭:</td>
                           <td class="valueId" colspan="8">${employee.get(0).es_employee_motto}
                           </td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1">目标:</td>
                           <td class="valueId" colspan="8">${employee.get(0).es_employee_aim}</td>
                       </tr>
                       <tr>
                           <td class="keyId" colspan="1">爱好:</td>
                           <td class="valueId" colspan="8">${employee.get(0).es_employee_hobby}</td>
                       </tr>
                       </tbody>
                   </table>
               </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>