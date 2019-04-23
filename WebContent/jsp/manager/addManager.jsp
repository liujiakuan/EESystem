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

    <title>添加管理员</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/manager/addManager.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/manager/addManager.js"></script>
</head>
<body>
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
		var name=$('#loginCount').val();
		var password=$('#loginPassword').val();
		var rePassword=$('#loginRePassword').val();
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
            <div class="col-xs-12 col-sm-12 col-md-4 col-md-offset-2 col-lg-4 col-lg-offset-2">
                <form style="margin-top: 100px;margin-left: 100px;" id="addManagerForm" class="addManagerForm" action="/EESystem/addManager.action" method="post">
                    <label>
                        	账&nbsp;&nbsp;&nbsp;&nbsp;号:
                    </label>
                    <input id="loginCount" class="loginCount" placeholder="请输入登录账号" name="es_manager_login_count" type="text" /><br><br>
                    <label>
                        	密&nbsp;&nbsp;&nbsp;&nbsp;码:
                    </label>
                    <input id="loginPassword" class="loginPassword" placeholder="请输入登录密码" name="es_manager_login_password" type="password"><br><br>
                    <label>
                        	密&nbsp;&nbsp;&nbsp;&nbsp;码:
                    </label>
                    <input id="loginRePassword" class="loginRePassword" placeholder="请再次输入登录密码" name="re_es_manager_login_password" type="password"><br><br>
                    <label>
                        	验证码:
                    </label>
                    <input class="checkCode" placeholder="请输入验证码" name="checkCode" type="text" />
                    <img src="http://localhost:8080/EESystem/jsp/checkCode.jsp" alt="验证码" onclick="this.src='http://localhost:8080/EESystem/jsp/checkCode.jsp?d='+Math.random();" /><br><br>
                    <button class="addLoginButton">添加</button>
                </form>
                <hr>
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
			    <hr>
            </div>
        </div>
    </div>
</div>
</body>
</html>