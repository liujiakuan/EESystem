$(document).ready(function(){
//
//    /**
//     * 公司男女比列，饼图
//     */
//    var manWoman=echarts.init(document.getElementById('manWoman'));
//    manWoman.setOption({
//        title:{
//               text:'男女比例',
//               subtext:'数据纯属虚构',
//               x:'center'
//           },
//        tooltip:{
//            trigger:'item',
//            formatter: "{a} <br/>{b} : {c} ({d}%)"
//        },
//        legend:{
//            orient:'vertical',
//            left:'left',
//            data:['男','女'],
//            padding:[
//                18,
//                0,
//                0,
//                0
//            ]
//        },
//        series:[{
//            name:'男女比例',
//            type:'pie',
//            radius:'55%',
//            center:['50%','60%'],
//            data:[
//                {
//                    value:824,
//                    name:'男'
//                },
//                {
//                    value:200,
//                    name:'女'
//                }
//            ],
//            itemStyle: {
//                emphasis: {
//                    shadowBlur: 10,
//                    shadowOffsetX: 0,
//                    shadowColor: 'rgba(0, 0, 0, 0.5)'
//                }
//            }
//        }]
//    });
////使用ajax异步填充数据
////    $.ajax({
////        //请求方式为get
////        type:"GET",
////        //json文件位置
////        url:"IndexJsonPie.json",
////        //返回数据格式为json
////        dataType: "json",
////        //请求成功完成后要执行的方法
////        success: function(data){
////        	alert("成功");
////        	myChartManWoman.setOption({
////        		series: [{
////                    // 根据名字对应到相应的系列
////                    name: '男女比例',
////                    data: data[0].dataPie
////                }]
////        	});
////        },
////        error:function(){
////            alert("失败");
////         }
////    });
//
//    /**
//     * 公司各个年龄段男女比例，饼图
//     */
//    var manWomanAge=echarts.init(document.getElementById('manWomanAge'));
//    manWomanAge.setOption({
//        title:{
//            text:'年龄段比例(单位:岁)',
//            subtext:'数据纯属虚构',
//            x:'center'
//        },
//        tooltip:{
//            trigger:'item',
//            formatter: "{a} <br/>{b} : {c} ({d}%)"
//        },
//        legend:{
//            orient:'vertical',
//            left:'left',
//            data:['18-22','23-27','28-32','33-37','38-42','大于42'],
//            padding:[
//                20,
//                0,
//                0,
//                0
//            ]
//        },
//        series:[{
//            name:'男女比例',
//            type:'pie',
//            radius:'55%',
//            center:['50%','60%'],
//            data:[
//                {
//                    value:24,
//                    name:'18-22'
//                },
//                {
//                    value:400,
//                    name:'23-27'
//                },
//                {
//                    value:300,
//                    name:'28-32'
//                },
//                {
//                    value:120,
//                    name:'33-37'
//                },
//                {
//                    value:100,
//                    name:'38-42'
//                },
//                {
//                    value:80,
//                    name:'大于42'
//                }
//            ],
//            itemStyle: {
//                emphasis: {
//                    shadowBlur: 10,
//                    shadowOffsetX: 0,
//                    shadowColor: 'rgba(0, 0, 0, 0.5)'
//                }
//            }
//        }]
//    });
//
//    /**
//     * 各部门男女人数柱状图
//
//    var manWomanApartment=echarts.init(document.getElementById('manWomanApartment'));
//    manWomanApartment.setOption({
//
//    });*/
//
//    /**
//     * 各个学历人数比例，饼图
//     */
//    var manWomanQualification=echarts.init(document.getElementById('manWomanQualification'));
//    manWomanQualification.setOption({
//        title:{
//            text:'公司各个学历比例',
//            subtext:'数据纯属虚构',
//            x:'center'
//        },
//        tooltip:{
//            trigger:'item',
//            formatter: "{a} <br/>{b} : {c} ({d}%)"
//        },
//        legend:{
//            orient:'vertical',
//            left:'left',
//            data:['初中','高中','大专','本科','研究生','博士生'],
//            padding:[
//                18,
//                0,
//                0,
//                0
//            ]
//        },
//        series:[{
//            name:'男女比例',
//            type:'pie',
//            radius:'55%',
//            center:['50%','60%'],
//            data:[
//                {
//                    value:4,
//                    name:'初中'
//                },
//                {
//                    value:20,
//                    name:'高中'
//                },
//                {
//                    value:300,
//                    name:'大专'
//                },
//                {
//                    value:520,
//                    name:'本科'
//                },
//                {
//                    value:100,
//                    name:'研究生'
//                },
//                {
//                    value:80,
//                    name:'博士生'
//                }
//            ],
//            itemStyle: {
//                emphasis: {
//                    shadowBlur: 10,
//                    shadowOffsetX: 0,
//                    shadowColor: 'rgba(0, 0, 0, 0.5)'
//                }
//            }
//        }]
//    });
});