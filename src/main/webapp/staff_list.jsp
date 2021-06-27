<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>员工列表</title>
    <link rel="stylesheet" type="text/css" href="../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/thems.css">
    <script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //自适应屏幕宽度
            window.onresize=function(){ location=location };

            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');

            var search_w = $(window).width()-40;
            $('.search').css('width',search_w+'px');
        });

        function searchUser() {
            var name=document.getElementById('searchName').value;
            var phone=document.getElementById('searchPhone').value;
            console.log("name:" +name + ", phone:" + phone);
            // var form = new FormData();
            // form.append('name',name);
            // form.append('phone',phone);
            $.ajax({
                type: 'POST',
                url: '../staff/query',
                data: {"name":name,"phone":phone},
                success: function (data) {

                }
            })
        }





    </script>
    <!--框架高度设置-->
</head>

<body onLoad="Resize();">
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">员工列表</span>
            </div>

            <div class="space_hx">&nbsp;</div>

            <div class="r_foot">
                <div class="r_foot_m">
                    <span style="margin-left: 1%; font-size: 15px;">姓  名：</span><input id="searchName" type="text" class="mysearch" placeholder="请输入姓名..." value="" />
                    <span style="margin-left: 3%; font-size: 15px;">手机号：</span><input  id="searchPhone" type="text" class="mysearch" placeholder="请输入手机号..." value="" />
                </div>
                <div class="r_foot_m">
                    <a href="" id="searchBtn" class="btn" onclick="searchUser();" style="float:left; margin-top: 13px;margin-bottom: 3px;">搜索</a>
                    <a href="../staff/toAdd" class="btn" style="margin-left: 2%;margin-top: 13px;">添加</a>

                </div>
            </div>
            <!--列表-->
            <table cellpadding="0" cellspacing="0" class="list_hy">
                <tr>
                    <th scope="col">姓名</th>
                    <th scope="col">手机号</th>
                    <th scope="col">所属公司</th>
                    <th scope="col">职务</th>
                    <th scope="col">开始期限</th>
                    <th scope="col">结束期限</th>
                    <th scope="col">状态</th>
                    <th scope="col">创建时间</th>
                    <th scope="col">操作</th>
                </tr>
                <c:forEach items="${LIST}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                    <td>${user.officeName}</td>
                    <td>${user.post}</td>
                    <td><fmt:formatDate value="${user.startTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${user.endTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
                    <td>${user.status}</td>
                    <td>${user.createTime}</td>
                    <td>
                        <a href="../staff/toEdit?id=${user.id}" class="btn">修改</a>
                        <a href="../staff/remove?id=${user.id}" class="btn">删除</a>
                    </td>
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
