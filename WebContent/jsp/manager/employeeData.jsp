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

    <title>后台数据可视信息</title>

    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/common/bootstrapCode/css/bootstrap-theme.css.map">
    <link rel="stylesheet" href="http://localhost:8080/EESystem/css/manager/employeeData.css">

    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/bootstrapCode/js/npm.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/common/echarts.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/EESystem/js/manager/employeeData.js" charset="utf-8"></script>
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
            <div class="col-xs-12 col-sm-12 col-md-10  col-lg-10">
                <div class="row dataShow">
                    <div id="totalCount" class="totalCount col-xs-12 col-sm-12 col-md-3 col-lg-3">
                        <div></div>
                        <span>
                        	员工总人数
                        </span>
                        <p>
                            <span>${getEmployeeAllCount}</span>
                        </p>
                    </div>
                    <div id="manWoman" class="manWoman col-xs-12 col-sm-12 col-md-3 col-lg-3 col-md-offset-1 col-lg-offset-1">
                        <!--男女比例，饼图-->
                    </div>
                    <script type="text/javascript">
                    var manWoman=echarts.init(document.getElementById('manWoman'));
                    manWoman.setOption({
                        title:{
                               text:'男女比例',
                               subtext:'数据纯属虚构',
                               x:'center'
                           },
                        tooltip:{
                            trigger:'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend:{
                            orient:'vertical',
                            left:'left',
                            data:['男','女'],
                            padding:[
                                18,
                                0,
                                0,
                                0
                            ]
                        },
                        series:[{
                            name:'男女比例',
                            type:'pie',
                            radius:'55%',
                            center:['50%','60%'],
                            data:[
                                {
                                    value:<%=request.getAttribute("getManCount") %>,
                                    name:'男'
                                },
                                {
                                    value:<%=request.getAttribute("getWomanCount") %>,
                                    name:'女'
                                }
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }]
                    });
                //使用ajax异步填充数据
//                    $.ajax({
//                        //请求方式为get
//                        type:"GET",
//                        //json文件位置
//                        url:"IndexJsonPie.json",
//                        //返回数据格式为json
//                        dataType: "json",
//                        //请求成功完成后要执行的方法
//                        success: function(data){
//                        	alert("成功");
//                        	myChartManWoman.setOption({
//                        		series: [{
//                                    // 根据名字对应到相应的系列
//                                    name: '男女比例',
//                                    data: data[0].dataPie
//                                }]
//                        	});
//                        },
//                        error:function(){
//                            alert("失败");
//                         }
//                    });
                    </script>
                    <div id="manWomanAge" class="manWomanAge col-xs-12 col-sm-12 col-md-4 col-lg-4 col-md-offset-1 col-lg-offset-1">
                        <!--各个年龄段男女比例，饼图-->
                    </div>
                    <script type="text/javascript">
                    var manWomanAge=echarts.init(document.getElementById('manWomanAge'));
                    manWomanAge.setOption({
                        title:{
                            text:'年龄段比例(单位:岁)',
                            subtext:'数据纯属虚构',
                            x:'center'
                        },
                        tooltip:{
                            trigger:'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend:{
                            orient:'vertical',
                            left:'left',
                            data:['18-22','23-27','28-32','33-37','38-42','大于42'],
                            padding:[
                                18,
                                0,
                                0,
                                0
                            ]
                        },
                        series:[{
                            name:'年龄段比例(单位:岁)',
                            type:'pie',
                            radius:'55%',
                            center:['50%','60%'],
                            data:[
                                {
                                    value:<%=request.getAttribute("getAgeOne") %>,
                                    name:'18-22'
                                },
                                {
                                    value:<%=request.getAttribute("getAgeTwo") %>,
                                    name:'23-27'
                                },
                                {
                                    value:<%=request.getAttribute("getAgeThree") %>,
                                    name:'28-32'
                                },
                                {
                                    value:<%=request.getAttribute("getAgeFour") %>,
                                    name:'33-37'
                                },
                                {
                                    value:<%=request.getAttribute("getAgeFive") %>,
                                    name:'38-42'
                                },
                                {
                                    value:<%=request.getAttribute("getAgeSix") %>,
                                    name:'大于42'
                                }
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }]
                    });
                    </script>
                </div>
                <div class="row">
                    <div id="numberData" class="numberData col-xs-12 col-sm-12 col-md-5 col-lg-5">
                        <!--各个部门男女人数，柱状图-->
                        <ul class="numberDataUl">
                        	<li>男:<span>${getManCount}</span>人</li>
                        	<li>女:<span>${getWomanCount}</span>人</li>
                        </ul>
                        <ul class="numberDataUl">
                        	<li>18-22岁:<span>${getAgeOne}</span>人</li>
                        	<li>23-27岁:<span>${getAgeTwo}</span>人</li>
                        	<li>28-32岁:<span>${getAgeThree}</span>人</li>
                        	<li>33-37岁:<span>${getAgeFour}</span>人</li>
                        	<li>38-42岁:<span>${getAgeFive}</span>人</li>
                        	<li>大于42岁:<span>${getAgeSix}</span>人</li>
                        </ul>
                        <ul class="numberDataUl">
                        	<li>初中生:<span>${getQualificationOne}</span>人</li>
                        	<li>高中生:<span>${getQualificationTwo}</span>人</li>
                        	<li>大专生:<span>${getQualificationThree}</span>人</li>
                        	<li>本科生:<span>${getQualificationFour}</span>人</li>
                        	<li>研究生:<span>${getQualificationFive}</span>人</li>
                        	<li>博士生:<span>${getQualificationSix}</span>人</li>
                        </ul>
                    </div>
                    <div id="manWomanQualification" class="manWomanQualification col-xs-12 col-sm-12 col-md-5 col-lg-5 col-md-offset-1 col-lg-offset-1">
                        <!--各个学历人数比例，饼图-->
                    </div>
                    <script type="text/javascript">
                    var manWomanQualification=echarts.init(document.getElementById('manWomanQualification'));
                    manWomanQualification.setOption({
                        title:{
                            text:'公司各个学历比例',
                            subtext:'数据纯属虚构',
                            x:'center'
                        },
                        tooltip:{
                            trigger:'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend:{
                            orient:'vertical',
                            left:'left',
                            data:['初中','高中','大专','本科','研究生','博士生'],
                            padding:[
                                18,
                                0,
                                0,
                                0
                            ]
                        },
                        series:[{
                            name:'公司各个学历比例',
                            type:'pie',
                            radius:'55%',
                            center:['50%','60%'],
                            data:[
                                {
                                    value:<%=request.getAttribute("getQualificationOne") %>,
                                    name:'初中'
                                },
                                {
                                    value:<%=request.getAttribute("getQualificationTwo") %>,
                                    name:'高中'
                                },
                                {
                                    value:<%=request.getAttribute("getQualificationThree") %>,
                                    name:'大专'
                                },
                                {
                                    value:<%=request.getAttribute("getQualificationFour") %>,
                                    name:'本科'
                                },
                                {
                                    value:<%=request.getAttribute("getQualificationFive") %>,
                                    name:'研究生'
                                },
                                {
                                    value:<%=request.getAttribute("getQualificationSix") %>,
                                    name:'博士生'
                                }
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }]
                    });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>