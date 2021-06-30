<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String xscheme = request.getHeader("X-Forwarded-Scheme");
    if(xscheme==null){
        xscheme = request.getScheme();
    }
    String basePath = xscheme +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        body, h1, h2, h3, h4, h5, h6, p, dl, dd{
            margin: 0;
        }
        ul, ol{
            margin: 0;
            padding: 0;
            list-style: none;
        }
        img{
            border: none;
            vertical-align: top;
        }
        a {
            text-decoration: none;
        }
        .outermost{
            width: 80%;
            height: 780px;
            margin: 0 auto;
            border: 1px solid #ffdddd;
        }
        .top{
            width: 95%;
            height: 20px;
            margin: 0 auto;
            border: 1px solid #000;
            text-align: center;
            margin-top: 15px;
            font-size: 12px;
        }
        .center{
            width: 95%;
            height: 200px;
            border: 1px solid #36d;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            flex-wrap: nowrap;
            text-align: center;
        }
        .center_left,.center_right{
            width: 20%;
            height: 100%;
            border: 1px solid #ccccdd;
        }
        .center_middle{
            width: 55%;
            height: 100%;
            border: 1px solid #F2F2F2;
        }
        .center_child_header,.footer_header{
            height: 30px;
            width: 100%;
            font-size: 12px;
            background-color: #F2F2F2;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .center_left_top,.center_left_bottom{
            width: 100%;
            height: 85px;
        }
        .center_left_top{
            display: flex;
            flex-direction: row;
        }
        .center_left_top_img{
            width: 50%;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
        .center_left_top_img>img{
            width: 50px;
            height: 50px;
        }
        .center_left_bottom{
            border-top: 1px solid #F2F2F2;
        }
        .center_right_bottom{
            width: 100%;
            height: 170px;
        }
        .center_left_bottom_img{
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
        .center_left_bottom_img>img{
            width: 50px;
        }
        .center_middle_img{
            width: 100%;
            height: 170px;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
        .center_middle_img>img{
            width: 60px;
        }
        ul{
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        li{
            line-height: 24px;
            text-align: center;
            font-size: 12px;
        }
        .footer{
            width: 95%;
            height: 320px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            flex-wrap: nowrap;
            margin: 0 auto;
            margin-top: 15px;
        }
        .footer_left,.footer_right{
            width: 49%;
            height: 100%;
            border: 1px solid red;
        }
        .footer_left_content{
            width: 100%;
            height: 200px;
            display: flex;
            flex-direction: row;
            justify-content: space-around;
        }
        .footer_left_content>li{
            width: 30%;
            height: 150px;
            border: 1px solid #3366dd;
        }
        .footer_left_content_top_left,.footer_left_content_top_mid,.footer_left_content_top_right{
            line-height: 75px;
            font-size: 30px;
            font-weight: 600;
            color: #02A7F0;
        }
       .footer_left_content_top_right{
           color: #D9001B;
       }
       .footer_left_content_bottom{
           line-height: 75px;
           font-size: 20px;
           font-weight: 600;
       }
       .footer_bottom{
           width: 85%;
           height: 90px;
           margin: 0 auto;
           line-height: 90px;
           font-weight: 600;
           color: #7F7F7F;
       }
       .footer_right_bottom{
           width: 100%;
           height: 290px;
           display: flex;
           justify-content: space-between;
           flex-direction: row;
           flex-wrap: nowrap;
       }
        .footer_right_bottom_content{
            height: 290px;
        }
        .footer_right_bottom_content>li{
            line-height: 40px;
        }
    </style>
    <script rel="stylesheet" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
</head>
<body onLoad="Resize();">
    <div class="outermost">
        <div class="top">
            告警信息:设备SN0000001  &nbsp&nbsp  频繁进行人脸识别 &nbsp&nbsp  2021-05-07 18:59:03
        </div>
        <div class="center">
            <div class="center_left">
                <div class="center_child_header">
                    远程呼叫应答
                </div>
                <div class="center_left_top">
                    <div class="center_left_top_img"><img src="<%=basePath%>/images/u36.png" alt=""></div>
                    <div class="center_left_top_img"><img src="<%=basePath%>/images/u37.png" alt=""></div>
                </div>
                <div class="center_left_bottom">
                    <div class="center_left_bottom_img">
                        <img src="<%=basePath%>/images/u38.png" alt="">
                    </div>
                </div>
            </div>
            <div class="center_middle">
                <div class="center_child_header">
                    站点视频监控
                </div>
                <div class="center_middle_img">
                    <img src="<%=basePath%>/images/u30.png" alt="">
                </div>
            </div>
            <div class="center_right">
                <div class="center_child_header">
                    近期进入人员信息
                </div>
                <div class="center_right_bottom">
                    <ul>
                        <li>张三  2021-05-08 18:28:16</li>
                        <li>李四  2021-05-08 18:20:10</li>
                        <li>王五  2021-05-08 08:21:32</li>
                        <li>刘二  2021-05-07 14:28:19</li>
                        <li>陈六  2021-05-07 12:40:01</li>
                        <li>张三  2021-05-07 10:36:27</li>
                        <li>李四  2021-05-06 18:21:16</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="footer_left">
                <div class="footer_header">
                    当日人员进出数据统计
                </div>
                <ul class="footer_left_content">
                    <li>
                        <div class="footer_left_content_top_left">
                            8人
                        </div>
                        <div class="footer_left_content_bottom">
                            到访人员
                        </div>
                    </li>
                    <li>
                        <div class="footer_left_content_top_mid">
                           6人
                        </div>
                        <div class="footer_left_content_bottom">
                            离访人员
                        </div>
                    </li>
                    <li>
                        <div class="footer_left_content_top_right">
                           2人
                        </div>
                        <div class="footer_left_content_bottom">
                            驻留人员
                        </div>
                    </li>
                </ul>
                <div class="footer_bottom">
                    数据统计：截止至2021-05-08 18:58
                </div>
            </div>
            <div class="footer_right">
                <div class="footer_header">
                    异常告警历史记录
                </div>
                <div class="footer_right_bottom">
                    <ul class="footer_right_bottom_content">
                        <li>SN0000001 </li>
                        <li>SN0000001</li>
                        <li>SN0000001</li>
                        <li>SN0000001</li>
                        <li>SN0000001</li>
                    </ul>
                    <ul class="footer_right_bottom_content">
                        <li>未在规定时间离开</li>
                        <li>频繁进行人脸识别</li>
                        <li>门锁未正常闭锁</li>
                        <li>门锁未正常开锁</li>
                        <li>未在规定时间离开</li>
                    </ul>
                    <ul class="footer_right_bottom_content">
                        <li>2021-05-07 18:56:20 </li>
                        <li>2021-05-07 18:56:20</li>
                        <li>2021-05-07 18:56:20</li>
                        <li>2021-05-07 18:56:20</li>
                        <li>2021-05-07 18:56:20</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(function(){
            //自适应屏幕宽度
            window.onresize=function(){ location=location };
            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');
            var search_w = $(window).width()-40;
            $('.search').css('width',search_w+'px');
            //$('.list_hy').css('width',search_w+'px');
        });
        $('.center_left_bottom_img').bind('click',function (){
            let data={
                "keyWord":'',
                "pageNum":1,
                "pageSize":3
            }
            let url = '/system/api/othersystem/v1.0/open';
            $.ajax({
                url:url,
                data:JSON.stringify(data),
                type:'post',
                contentType:'application/json',
                success:function (res){
                    console.log(res)
                }
            })
        })
    </script>
</body>
</html>
