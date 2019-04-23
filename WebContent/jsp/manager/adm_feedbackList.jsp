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

    <title>后台员工反馈信息列表</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/manager/adm_feedbackList.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/manager/adm_feedbackList.js"></script>
</head>
<body>
<div id="body">
<c:if test="${sessionScope.es_manager_login_count==null}">
	<c:redirect url="http://localhost:8080/EESystem/jsp/login.jsp"/>
</c:if>
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
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8 col-md-offset-1 col-lg-offset-1">
                <!--中间个人信息展示部分-->
                <div class="table-responsive">
                    <table id="table" class="table table-bordered">
                        <thead>
                        <tr>
                            <td colspan="2">员工Id</td>
                            <td colspan="6">内容</td>
                            <td colspan="2">日期</td>
                            <td colspan="2">状态</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${feedbackList==null}">
                       		<tr>
	                            <td colspan="12">暂无未处理修改内容</td>
                       		</tr>
                        </c:if>
                        <c:if test="${feedbackList!=null}">
                        	<c:forEach items="${feedbackList}" var="item">
                        		<tr>
		                            <td colspan="2">${item.es_employee_id}</td>
		                            <td class="value" colspan="6">${item.es_employee_feedback_content}</td>
		                            <td class="value" colspan="2">
		                            <fmt:formatDate value="${item.es_employee_feedback_date}" pattern="yyyy年MM月dd日"/>
		                            </td>
		                            <td class="value" colspan="2">
		                                <a href="/EESystem/updateFeedbackState.action?es_employee_feedback_id=${item.es_employee_feedback_id}">点击确认修改完成</a>
		                            </td>
                        		</tr>
                        	</c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <!-- 
                <hr>
                <div class="row">
                    <div class="changePage col-xs-12 col-sm-4 col-md-6 col-lg-4 col-md-offset-1 col-lg-offset-1">
                       	 当前页:<c:out value="${pageNow}"></c:out>
                        	总共页:<c:out value="${pageCount}"></c:out>
                        <a href="/EESystem/empEmployeeListSearchPage.action?page=upOne&&pagenow=${pageNow}">上一页</a>
                        <a href="/EESystem/empEmployeeListSearchPage.action?page=nextOne&&pagenow=${pageNow}">下一页</a>
                        <a href="/EESystem/empEmployeeListSearchPage.action?page=firstPage&&pagenow=${pageNow}">首页</a>
                        <a href="/EESystem/empEmployeeListSearchPage.action?page=endPage&&pagenow=${pageNow}">尾页</a>
                    </div>
                    <div class="col-xs-12 col-sm-4 col-md-6 col-lg-4">
                        <form action="/EESystem/empEmployeeListSearchPageF.action" method="get">
                        	<input type="hidden" name="pagenow" value="${pageNow}">
                            <input class="pageNumber" name="pageNumber" type="number">
                            <input class="submit" type="submit" value="跳转到指定页">
                        </form>
                    </div>
                </div>
                 -->
            </div>
        </div>
    </div>
</div>
</body>
</html>