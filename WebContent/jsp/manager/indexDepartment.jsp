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

    <title>后台管理首页</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/manager/indexDepartment.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/manager/indexDepartment.js"></script>
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
                            	企业组织架构图（上传修改）
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
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-9">
                <div class="query row">
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        <form action="/EESystem/admEmployeeById.action" method="post">
                            <input name="es_employee_id" placeholder="请输入员工Id" class="checkByIdNumber" type="number" />
                            <input class="submit" type="submit" value="查询" />
                        </form>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        <form action="/EESystem/admEmployeeByName.action" method="post">
                            <input name="es_employee_name" placeholder="请输入员工姓名" class="checkByName" type="text" />
                            <input class="submit" type="submit" value="查询" />
                        </form>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        <label>按照所属部门查询:</label>
                        <select id="selectDepartment">
                            <option selected="selected">
                                	请点击选择部门
                            </option>
                            <option>
                                	总经办
                            </option>
                            <option>
                                	财务部
                            </option>
                            <option>
                                	研发部
                            </option>
                            <option>
                            		 销售部
                            </option>
                            <option>
                           			 产品部
                            </option>
                            <option>
                            		人事部
                            </option>
                            <option>
                            		设计部
                            </option>
                            <option>
                            		质检部
                            </option>
                            <option>
                            		后勤部
                            </option>
                            <option>        
                            		综合管理部
                            </option>
                        </select>
                    </div>
                </div>
                <hr>
                <!--中间个人信息展示部分-->
                <div class="table-responsive">
                    <table id="table" class="table table-bordered">
                        <thead>
                        <tr>
                            <td colspan="1" class="keyId">Id</td>
                            <td colspan="1" class="keyId">姓名</td>
                            <td colspan="1" class="keyId">性别</td>
                            <td colspan="1" class="keyId">部门</td>
                            <td colspan="1" class="keyId">职位</td>
                            <td colspan="1" class="keyId">删除</td>
                            <td colspan="1" class="keyId">修改</td>
                            <td colspan="1" class="keyId">查看</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${employeeList}" var="item">
                        <tr>
                            <td colspan="1" class="valueId">${item.es_employee_id}</td>
                            <td colspan="1" class="valueId">${item.es_employee_name}</td>
                            <td colspan="1" class="valueId">${item.es_employee_sex}</td>
                            <td colspan="1" class="valueId">${item.es_employee_department}</td>
                            <td colspan="1" class="valueId">${item.es_employee_post}</td>
                            <td colspan="1" class="valueId">
                                <a href="/EESystem/deleteEmployeeById.action?es_employee_id=${item.es_employee_id}">删除</a>
                            </td>
                            <td colspan="1" class="valueId">
                                <a href="/EESystem/updateEmployeeById.action?es_employee_id=${item.es_employee_id}">修改</a>
                            </td>
                            <td colspan="1" class="valueId">
                                <a href="/EESystem/selectEmployeeById.action?es_employee_id=${item.es_employee_id}">查看</a>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <hr>
                <div class="row">
                    <div class="changePage col-xs-12 col-sm-4 col-md-6 col-lg-4 col-md-offset-1 col-lg-offset-1">
                       	 当前页:<c:out value="${pageNow}"></c:out>
                       	 总共页:<c:out value="${pageCount}"></c:out>
                        <a href="/EESystem/admEmployeeListSearchPageDepartment.action?page=upOne&&pagenow=${pageNow}&&es_employee_department=${es_employee_department}">上一页</a>
                        <a href="/EESystem/admEmployeeListSearchPageDepartment.action?page=nextOne&&pagenow=${pageNow}&&es_employee_department=${es_employee_department}">下一页</a>
                        <a href="/EESystem/admEmployeeListSearchPageDepartment.action?page=firstPage&&pagenow=${pageNow}&&es_employee_department=${es_employee_department}">首页</a>
                        <a href="/EESystem/admEmployeeListSearchPageDepartment.action?page=endPage&&pagenow=${pageNow}&&es_employee_department=${es_employee_department}">尾页</a>
                    </div>
                    <div class="col-xs-12 col-sm-4 col-md-6 col-lg-4">
                        <form action="/EESystem/admEmployeeListSearchPageDepartmentE.action" method="get">
                        	<input type="hidden" name="pagenow" value="${pageNow}">
                        	<input type="hidden" name="es_employee_department" value="${es_employee_department}">
                            <input class="pageNumber" name="pageNumber" type="number">
                            <input class="submit" type="submit" value="跳转到指定页">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>