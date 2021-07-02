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
    <title>头部</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
</head>
<body>
<div class="head clearfix" style="background: linear-gradient(90deg,#5996e2 0,#83c0ec);">
        <div style="width: 170px;height: 50px;position: absolute;left: 50px;top: 15px;">
            <img src="https://b2c.market.chinamobile.com/member/asset/image/f4d1e3d4.png" alt="" style="width: 100%;height: 100%">
        </div>
        <div style="position: absolute;left: 235px;height: 85px;display: flex;align-items: center;font-size:25px;color: #fff;">
            和守护智慧门禁管理平台
        </div>
    <div class="curr" style="color: #FFFFFF;font-size: 15px"><span>欢迎您，${USER.name}[ <a href="<%=basePath%>self/logout" target="_top" style="color:#FFFFFF ">退出</a> ]</span></div>
</div>
</body>
</html>
