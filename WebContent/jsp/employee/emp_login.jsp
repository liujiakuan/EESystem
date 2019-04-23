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

    <title>登录</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/employee/emp_login.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/employee/emp_login.js"></script>
</head>
<body>
<div id="body">
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
            <div class="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">
                <form class="employeeLoginForm" action="/EESystem/employeeLogin.action" method="post">
                    <label>
                        	账&nbsp;&nbsp;&nbsp;&nbsp;号:
                    </label>
                    <input class="loginCount" name="es_employee_count" type="text" /><br><br>
                    <label>
                        	密&nbsp;&nbsp;&nbsp;&nbsp;码:
                    </label>
                    <input class="loginPassword" name="es_employee_password" type="password"><br><br>
                    <label>
                        	验证码:
                    </label>
                    <input class="identifyCode" name="checkCode" type="text" />
                    <img src="http://localhost:8080/EESystem/jsp/checkCode.jsp" alt="验证码" onclick="this.src='http://localhost:8080/EESystem/jsp/checkCode.jsp?d='+Math.random();" /><br><br>
                    <button class="loginButton">登录</button>
                    <a class="newEmployeeAdd" href="http://localhost:8080/EESystem/jsp/employee/emp_addEmployee.jsp">
                        	新员工录入
                    </a>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>