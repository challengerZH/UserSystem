<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>门禁日志</title>
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
            $('#searchBtn').unbind('click').bind('click',function (){
                let valueInfo = $('#searchInfo').val();
                let url = '/system/log/queryLoginLog';
                let data = {
                    "keyWord":valueInfo,
                    "pageNum":1,
                    "pageSize":3
                }
                console.log(data)
                $.ajax({
                    url:url,
                    data:JSON.stringify(data),
                    type:'POST-',
                    contentType:'application/json',
                    success:function (res){
                        console.log(res)
                         let list = res.result.list
                        console.log(list[0].id)
                         let str='';
                        for(let i=0;i<list.length;i++){
                            str+=
                                '<tr>'
                                +'<td>'+'list[i].userName'+'</td>'
                                +'<td>'+'list[i].userPhone'+'</td>'
                                +'<td>'+'list[i].officeName'+'</td>'
                                +'<td>'+'list[i].post'+'</td>'
                                +'<td>'+'list[i].oprTime'+'</td>'
                                +'<td>'+'list[i].operation'+'</td>'
                                +'</tr>'
                        }
                        console.log(str)
                        $('.activeInfo').html(str)
                    },
                    fail:function (res){
                        alert(res)
                    }
                })
            })
        });
    </script>
    <!--框架高度设置-->
</head>

<body >
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">门禁日志</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <div class="r_foot">
                <div class="r_foot_m">
                    <span style="margin-left: 1%; font-size: 15px;">用户信息：</span><input id="searchInfo" type="text" class="mysearch" placeholder="请输入查询下信息" value="" />
                </div>
                <div class="r_foot_m">
                    <span id="searchBtn" class="btn" style="float:left; margin-top: 13px;margin-bottom: 3px;">搜索</span>
                </div>
            </div>
            <!--列表-->
            <table cellpadding="0" cellspacing="0" class="list_hy" >
                <thead>
                    <th scope="col">姓名</th>
                    <th scope="col">手机号</th>
                    <th scope="col">所属公司</th>
                    <th scope="col">职务</th>
                    <th scope="col">触发时间</th>
                    <th scope="col">动作</th>
                </thead>
                <tbody class="activeInfo">
                </tbody>
            </table>
            <!--列表-->
        </div>
        <!--会议列表-->
    </div>
</div>
</body>
</html>
