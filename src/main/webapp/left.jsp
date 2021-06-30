<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String xscheme = request.getHeader("X-Forwarded-Scheme");
    if(xscheme==null){
        xscheme = request.getScheme();
    }
    String basePath = xscheme +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>左边导航</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <!--框架高度设置-->
    <script type="text/javascript">
        $(function(){
            $('.level-1').click(function(){
                if($(this).parent().hasClass('now')){
                    $(this).parent().removeClass('now');
                } else {
                    $('.level-1').each(function(){
                        $(this).parent().removeClass('now');
                    })
                    $(this).parent().addClass('now');

                }

            });

            $('.erji li').click(function(){
                $('.erji li').each(function(){
                    $(this).removeClass('now_li');
                })
                $(this).addClass('now_li');
                $(this).find("a")[0].click()
            });

            var main_h = $(window).height();
            $('.sidenav').css('height',main_h+'px');
        })
    </script>
    <!--框架高度设置-->
</head>

<body>
<div id="left_ctn">
    <ul class="sidenav">
        <li class="now" >
            <div class="level-1 nav_m">
                <span><a>人员管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li class="now_li">
                    <span><a href="../system/staff/list" target="main">员工管理</a></span>
                </li>
                <%--<li>--%>
                    <%--<span><a href="../system/department/list" target="main">部门管理</a></span>--%>
                <%--</li>--%>
            </ul>
        </li>
        <li >
            <div class="level-1 nav_m">
                <span><a>日志管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="../system/log/loginLog" target="main">门禁日志查询</a></span>
                </li>
                <li>
                    <span><a href="../system/log/systemLog" target="main">告警日志查询</a></span>
                </li>
                <li>
                    <span><a href="../system/log/operationLog" target="main">系统日志查询</a></span>
                </li>
            </ul>
        </li>
        <li >
            <div class="level-1 nav_m">
                <span><a>个人中心</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="../system/self/info" target="main">个人信息</a></span>
                </li>
                <li>
                    <span><a href="../system/self/toChangePassword" target="main">修改密码</a></span>
                </li>
            </ul>
        </li>
        <%--<li>--%>
            <%--<div class="nav_m">--%>
                <%--<span><a href="../system/self/logout" target="_top">退出系统</a></span>--%>
            <%--</div>--%>
        <%--</li>--%>
    </ul>
</div>
</body>
</html>
