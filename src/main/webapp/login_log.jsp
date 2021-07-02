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
    <title>门禁日志</title>
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
                <span class="name">门禁日志</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <div class="r_foot" style="display: flex">
                <div class="r_foot_m" style="margin-top: 20px">
                    <span style="margin-left: 1%; font-size: 15px;"></span><input id="searchInfo" type="text" class="mysearch" placeholder="请输入用户名或手机号" value="" />
                    <span id="searchBtn" class="btn" onclick="searchBtn()">搜索</span>
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
            <div class="pageSelect"></div>
        </div>
        <!--会议列表-->
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/page.js"></script>
<script type="text/javascript">
    let pages = '';
    // 页面初始化
    (function(){
        var main_h = $(window).height();
        $('.hy_list').css('height',main_h-45+'px');
        var search_w = $(window).width()-40;
        $('.search').css('width',search_w+'px');
        //$('.list_hy').css('width',search_w+'px');
        searchBtn().then((res)=>{
            ZUI.paging({
                prev_next_text: '上一页|下一页',
                count: res,
                selector: '.pageSelect',
                current: 1,
                page_len: res>7?7:res,
                callBack: function (page) {
                    console.log(page);
                    searchBtn(page)
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
    function searchBtn (page){
        return new Promise((resolve,rec)=>{
            let valueInfo = $('#searchInfo').val();
            let url = '/system/log/queryLoginLog';
            let data = {
                "keyWord": valueInfo,
                "pageNum": !page?1:page,
                "pageSize": 5
            }
            console.log(data)
            $.ajax({
                url: url,
                data: JSON.stringify(data),
                type: 'POST-',
                contentType: 'application/json',
                success: function (res) {
                    let list = res.result.list
                    pages = res.result.pages;
                    // console.log(pages)
                    let str = '';
                    if(Object.keys(list).length==0){
                        str='<tr>'
                            +'<td colspan="6" style="text-align: center">'+'暂未查询到数据'+'</td>'
                            +'</tr>'
                    }else {
                        for (let i = 0; i < list.length; i++) {
                            str += '<tr>'
                                if(list[i].userName==null){str+='<td>' + "-" + '</td>'}else{str+='<td>' + list[i].userName + '</td>'}
                                if(list[i].userPhone==null){str+='<td>' + "-" + '</td>'}else{str+='<td>' + list[i].userPhone + '</td>'}
                                if(list[i].officeName==null){str+='<td>' + "-" + '</td>'}else{str+='<td>' + list[i].officeName + '</td>'}
                                if(list[i].post==null){str+='<td>' + "-" + '</td>'}else{str+='<td>' + list[i].post + '</td>'}
                                str+='<td>' + timeStamp2String(list[i].oprTime) + '</td>'
                                + '<td>' + list[i].operation + '</td>'
                                + '</tr>'
                        }
                    }
                    $('.activeInfo').empty().html(str)
                    resolve(pages);
                },
                fail: function (res) {
                    alert(res)
                }
            })
        })

    }
</script>
</body>
</html>
