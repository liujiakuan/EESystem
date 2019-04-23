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

    <title>员工信息反馈未处理列表</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/employee/emp_FeedbackList.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/employee/emp_FeedbackList.js"></script>
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
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 col-md-offset-1 col-lg-offset-1">
                <!--中间个人信息展示部分-->
                <div class="table-responsive">
                    <table id="table" class="table table-bordered">
                        <thead>
                            <tr>
                                <td colspan="6">需修改信息</td>
                                <td colspan="2">申请修改日期</td>
                            </tr>
                        </thead>
                        <tbody>
                        
                            <!--暂无未处理修改内容-->
                            <c:if test="${feedbackList!=null}">
                            <c:forEach items="${feedbackList}" var="item">
                            <tr>
                            	<td class="value" colspan="6">${item.es_employee_feedback_content}</td>
                            	<td class="value" colspan="2">
                            	<fmt:formatDate value="${item.es_employee_feedback_date}" pattern="yyyy年MM月dd日"/>
                            	</td>
                            </tr>
                            </c:forEach>
                            <tr>
                            	<td colspan="8">管理员正在紧急处理中，请耐心等候。</td>
                            </tr>
                            </c:if>
                            <c:if test="${feedbackList==null}">
                            <tr>
                            	<td colspan="8">暂无需要处理信息。</td>
                            </tr>
                            </c:if>
                            <!--管理员正在慢慢修改，请耐心等候。-->
                        
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>