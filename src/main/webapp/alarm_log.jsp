<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>告警日志</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/thems.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function(){


            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');

            var search_w = $(window).width()-40;
            $('.search').css('width',search_w+'px');
            //$('.list_hy').css('width',search_w+'px');
        });
    </script>
    <!--框架高度设置-->
</head>

<body >
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">告警日志</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <div class="r_foot">
                <div class="r_foot_m">
                    <span style="margin-left: 1%; font-size: 15px;">姓  名：</span><input id="searchName" type="text" class="mysearch" placeholder="请输入姓名..." value="" />
                    <%--                    <span style="margin-left: 3%; font-size: 15px;">手机号：</span><input  id="searchPhone" type="text" class="mysearch" placeholder="请输入手机号..." value="" />--%>
                </div>
                <div class="r_foot_m">
                    <a href="" id="searchBtn" class="btn" onclick="searchUser();" style="float:left; margin-top: 13px;margin-bottom: 3px;">搜索</a>
                </div>
            </div>
            <!--列表-->
            <table cellpadding="0" cellspacing="0" class="list_hy">
                <tr>
                    <th scope="col">设备编号</th>
                    <th scope="col">告警类型</th>
                    <th scope="col">告警时间</th>
                </tr>
                <c:forEach items="${LIST}" var="log">
                <tr>
                    <td>${log.remark}</td>
                    <td>${log.operation}</td>
                    <td><fmt:formatDate value="${log.oprTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                </c:forEach>
            </table>
            <!--列表-->
        </div>
        <!--会议列表-->
    </div>
</div>
</body>
</html>
