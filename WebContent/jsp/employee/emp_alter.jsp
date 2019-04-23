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

    <title>员工信息修改申请</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/employee/emp_alter.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/employee/emp_alter.js"></script>
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
                    <li>欢迎你:${sessionScope.es_employee_name }</li>
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
            <div class="col-xs-12 col-sm-12 col-md-6 col-md-offset-2 col-lg-4 col-lg-offset-3">
                <!--中间个人信息展示部分-->
                <form action="/EESystem/employeeFeedback.action" method="post">
                    <textarea name="es_employee_feedback_content" id="textarea" class="textarea">请说明需要修改的地方，以及修改之后的内容。例如:身份证错误，需要修改为:511522199602548745</textarea>
                    <input id="submit" class="submit" type="submit" value="提交" />
                </form>
                <script type="text/javascript">
                    var t = document.getElementById('textarea');
                    t.onfocus = function(){
                        if(this.innerHTML != ''){this.value = ''}
                    };
                </script>
                <div>
                    <a style="display: block;" id="submitSuccess" class="submitSuccess" href="#">提交成功。</a>
                </div>
                <script type="text/javascript">
                	var submitSuccess=document.getElementById("submitSuccess");
                	submitSuccess.style.display="none";
                	var submit=document.getElementById("submit");
                	submit.onclick=function(){
                		submitSuccess.style.display="block";
                		//提交成功后暂停3秒
                		//sleep(3000);
                		//setTimeout("window.location.reload()",3000);
                	}
                </script>
            </div>
        </div>
    </div>
</div>
</body>
</html>