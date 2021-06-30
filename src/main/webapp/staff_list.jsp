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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>员工管理</title>
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

            // 搜索
            $("#searchBtn").bind('click', searchUser)
            $("#addBtn").bind('click',function(){
                console.log("<%=basePath%>staff/toAdd")
                window.location.href= "<%=basePath%>staff/toAdd"
                return false
            })
        });

        function timeStamp2String(time, type){
            var datetime = new Date(time);
            var year = datetime.getFullYear();
            var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
            var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
            var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
            var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
            var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
            if(type=='day'){
                return year + "-" + month + "-" + date
            }
            return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
        }

        function searchUser() {
            var name=document.getElementById('searchName').value;
            var param = {
                'keyWord':name.toString(),
                'pageNum':1,
                'pagSize':10
            }
            $.ajax({
                type: 'POST',
                url: '../staff/query',
                contentType:'application/json',
                data: JSON.stringify(param),
                success: function (data) {
                    var tbody = $("#usertable tbody");
                    tbody.empty()
                    if(data.success){
                        data.result.list.forEach(function (item) {
                            var tr = $("<tr></tr>")
                            tr.append("<td>"+item.name+"</td>")
                            tr.append("<td>"+item.phone+"</td>")
                            tr.append("<td>"+item.officeName+"</td>")
                            tr.append("<td>"+item.post+"</td>")
                            tr.append("<td>"+timeStamp2String(item.startTime, 'day')+"</td>")
                            tr.append("<td>"+timeStamp2String(item.endTime, 'day')+"</td>")
                            tr.append("<td>"+item.status+"</td>")
                            tr.append("<td>"+timeStamp2String(item.createTime)+"</td>")
                            tr.append("<a href='../staff/toEdit?id="+item.id+"' class='btn'>修改</a>")
                            tr.append("<a href='../staff/remove?id="+item.id+"' class='btn'>删除</a>")
                            tbody.append(tr)
                        })
                    }else{
                        alert('查询失败!')
                    }
                }
            })
            return false
        }


    </script>
    <!--框架高度设置-->
</head>

<body>
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">员工列表</span>
            </div>

            <div class="space_hx">&nbsp;</div>

            <div class="r_foot">
                <div class="r_foot_m">
                    <span style="margin-left: 1%; font-size: 15px;">关键字：</span><input id="searchName" type="text" class="mysearch" placeholder=" 请输入手机号或姓名..." value="" />
                    <a href="" id="searchBtn" class="btn">搜索</a>
                </div>
                <div class="r_foot_m">
                    <a href="" id="addBtn" class="btn" style="float:left; margin-top: 13px;margin-bottom: 3px;">添加</a>
                </div>
            </div>
            <!--列表-->
            <table id="usertable" cellpadding="0" cellspacing="0" class="list_hy">
                <thead>
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
                </thead>>
                <tbody>
                <c:forEach items="${LIST}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                    <td>${user.officeName}</td>
                    <td>${user.post}</td>
                    <td><fmt:formatDate value="${user.startTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${user.endTime}" pattern="yyyy-MM-dd"/> </td>
                    <td>${user.status}</td>
                    <td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a href="../staff/toEdit?id=${user.id}" class="btn">修改</a>
                        <a href="../staff/remove?id=${user.id}" class="btn">删除</a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <!--列表-->
        </div>
        <!--会议列表-->
    </div>
</div>
</body>
</html>
