<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String xscheme = request.getHeader("X-Forwarded-Scheme");
    if(xscheme==null){
        xscheme = request.getScheme();
    }
    String basePath = xscheme +"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <title>员工信息</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/thems.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var main_h = $(window).height();
            $('.hy_list').css('height', main_h - 45 + 'px');

            var main_w = $(window).width();
            $('.xjhy').css('width', main_w - 40 + 'px');

        });
    </script>
</head>

<body >
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">员工信息</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <div class="xjhy">
                <!--高级配置-->
                <ul class="hypz gjpz clearfix">
                    <li class="clearfix">
                        <span class="title">用户名：</span>
                        <div class="li_r">${OBJ.phone}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">状态：</span>
                        <div class="li_r">${OBJ.status}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">部门：</span>
                        <div class="li_r">${OBJ.officeName}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">姓名：</span>
                        <div class="li_r">${OBJ.name}</div>
                    </li>
                    </li>
                    <li class="clearfix">
                        <span class="title">开始期限：</span>
                        <div class="li_r">
                            <fmt:formatDate value="${OBJ.startTime}" pattern="yyyy-MM-dd hh:mm"/>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="title">结束期限：</span>
                        <div class="li_r">
                            <fmt:formatDate value="${OBJ.endTime}" pattern="yyyy-MM-dd hh:mm"/>
                        </div>
                    </li>
                    <li class="clearfix" style="height:88px;">
                        <span class="title">头像图片：</span>
                        <div class="li_r">
                            <img id="pImg" class="chang" src="${OBJ.info}"
                                 style="width:78px; height: 78px;left: 20px; ">
                        </div>
                    </li>
                    <li class="tj_btn">
                        <a href="javascript:history.go(-1);" class="back">返回</a>
                    </li>
                </ul>
                <!--高级配置-->
            </div>
        </div>
    </div>
</div>
</body>
</html>
