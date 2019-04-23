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

    <title>员工列表</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/employee/emp_emoloyeeList.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/employee/emp_emoloyeeList.js"></script>
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
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                <!--中间个人信息展示部分-->
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <ul class="empListImg">
                            <c:forEach items="${employeeList}" var="item">
                            <li>
                                <div class="col-md-3 col-lg-3">
                                    <img src="${item.es_employee_img}" alt="empHeadImg" />
                                    <ul class="empInfo">
                                        <li>姓名:${item.es_employee_name}</li>
                                        <li>性别:${item.es_employee_sex}</li>
                                        <li>年龄:${item.es_employee_age}</li>
                                        <li>婚否:${item.es_employee_marriage}</li>
                                        <li>部门:${item.es_employee_department}</li>
                                        <li>职位:${item.es_employee_post}</li>
                                        <li>联系方式:${item.es_employee_call}</li>
                                        <li>QQ:${item.es_employee_qq}</li>
                                        <li>微信:${item.es_employee_wechat}</li>
                                        <li>座右铭:${item.es_employee_motto}</li>
                                        <li>爱好:${item.es_employee_hobby}</li>
                                    </ul>
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <!-- 按照部门查看功能暂时不做，除非不做分页，不然涉及的工作量又会增加很多
                    <div class="apartmentChoose col-xs-12 col-sm-12 col-md-2 col-lg-2">
                        <span>按照部门选取</span>
                        <ul>
                            <li><a href="#">总经办</a></li>
                            <li><a href="#">财务部</a></li>
                            <li><a href="#">销售部</a></li>
                            <li><a href="#">研发部</a></li>
                            <li><a href="#">产品部</a></li>
                            <li><a href="#">人事部</a></li>
                            <li><a href="#">设计部</a></li>
                            <li><a href="#">质检部</a></li>
                            <li><a href="#">后勤部</a></li>
                            <li><a href="#">综合管理部</a></li>
                        </ul>
                    </div>
                     -->
                </div>
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
            </div>
        </div>
    </div>
</div>
</body>
</html>