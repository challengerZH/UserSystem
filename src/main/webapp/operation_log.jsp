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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统日志</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/thems.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/page.css">

    <!--框架高度设置-->
</head>

<body style="background-color: #FFFFFF">
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">系统日志</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <div class="r_foot" style="display: flex">
                <div class="r_foot_m" style="margin-top: 20px">
                    <span style="margin-left: 1%; font-size: 15px;"></span><input id="searchName" type="text" class="mysearch" placeholder="请输入用户名或手机号" value="" />
                    <div id="searchBtn" class="btn" onclick="searchOperationLog();">搜索</div>
                </div>
            </div>
            <!--列表-->
            <table cellpadding="0" cellspacing="0" class="list_hy">
                <thead>
                    <th scope="col">姓名</th>
                    <th scope="col">手机号</th>
                    <th scope="col">操作菜单</th>
                    <th scope="col">操作类型</th>
                    <th scope="col">操作时间</th>
                </thead>
                <tbody class="logInfo">
                </tbody>
            </table>
            <div class="pageSelect"></div>
            <!--列表-->
        </div>
        <!--会议列表-->
    </div>
</div>

<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/page.js"></script>
<script type="text/javascript">
    let pages = '';
    (function(){
        var main_h = $(window).height();
        $('.hy_list').css('height',main_h-45+'px');

        var search_w = $(window).width()-40;
        $('.search').css('width',search_w+'px');
        //$('.list_hy').css('width',search_w+'px');
        searchOperationLog().then((res)=>{
            ZUI.paging({
                prev_next_text: '上一页|下一页',
                count: res,
                selector: '.pageSelect',
                current: 1,
                page_len: res>7?7:res,
                callBack: function (page) {
                    searchOperationLog(page);
                }
            })
        });
    })();
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
    function searchOperationLog(page){
        return new Promise((resolve,rec) => {
            let valueInfo = $('#searchName').val();
        let url = '/system/log/queryOperationLog';
        let data = {
            "keyWord": valueInfo,
            "pageNum": !page?1:page,
            "pageSize": 5
        }
        console.log(data);
        $.ajax({
            url: url,
            data: JSON.stringify(data),
            type: 'POST',
            contentType: 'application/json',
            success: function (res) {
                if (res.success) {
                    pages = res.result.pages;
                    let str = '';
                    let item = res.result.list
                    if (Object.keys(item).length == 0) {
                        str = '<tr>'
                            + '<td colspan="5" style="text-align: center">' + '暂未查询到数据' + '</td>'
                            + '</tr>'
                    } else {
                        for (let i = 0; i < item.length; i++) {
                            str += '<tr>'
                                + '<td>' + item[i].userName + '</td>'
                                + '<td>' + item[i].userPhone + '</td>'
                                + '<td>' + item[i].moudle + '</td>'
                                + '<td>' + item[i].operation + '</td>'
                                + '<td>' + timeStamp2String(item[i].oprTime) + '</td>'
                                + '</tr>'
                        }
                    }

                    $('.logInfo').empty().html(str)
                    resolve(pages);
                } else {
                    alert(res.result)
                }
            },
            fail: function (res) {
                alert(res.result)
            }
        })
    })
    }
</script>
</body>
</html>
