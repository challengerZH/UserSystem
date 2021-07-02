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
    <link rel="stylesheet" href="../css/duDialog.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/thems.css">
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
            border: 1px solid #F0F0F0;
            background-color: #FFFFFF;
            margin-top: 10px;
        }
        .top{
            width: 95%;
            height: 20px;
            margin: 0 auto;
            border: 1px solid #F0F0F0;
            text-align: center;
            margin-top: 15px;
            font-size: 12px;
        }
        .center{
            width: 95%;
            height: 200px;
            border: 1px solid #F0F0F0;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            flex-wrap: nowrap;
            text-align: center;
        }
        .center_left,.center_right{
            width: 20%;
            height: 100%;
            border: 1px solid #F0F0F0;
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
        .center_left_bottom{
            width: 100%;
            height: 180px;
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
            border: 1px solid #F0F0F0;
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

</head>
<body style="background-color: #FFFFFF">
<div class="box_t">
    <span class="name">远程开门</span>
</div>
    <div class="outermost">
        <div class="center">
            <div class="center_left">
                <div class="center_child_header">
                    远程呼叫应答
                </div>
                <div class="center_left_bottom">
                    <div class="center_left_bottom_img">
                        <img src="<%=basePath%>/images/u38.png" alt="">
                    </div>
                </div>
            </div>
            <div class="center_middle">
                <div class="center_child_header">
                    最近一次人脸识别
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
                    <ul class="loginLog">
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
                        </div>
                        <div class="footer_left_content_bottom">
                            到访人员
                        </div>
                    </li>
                </ul>
                <div class="footer_bottom">
                </div>
            </div>
            <div class="footer_right">
                <div class="footer_header">
                    异常告警历史记录
                </div>
                <div class="footer_right_bottom">
                    <ul class="footer_right_bottom_content" id="footer_right_bottom_content1">
                    </ul>
                    <ul class="footer_right_bottom_content" id="footer_right_bottom_content2">
                    </ul>
                    <ul class="footer_right_bottom_content" id="footer_right_bottom_content3">
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script rel="stylesheet" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script rel="stylesheet" src="<%=basePath%>js/duDialog.js"></script>
    <script>
        //页面初始化调用接口
        (function(){
            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');
            var search_w = $(window).width()-40;
            $('.search').css('width',search_w+'px');
            //$('.list_hy').css('width',search_w+'px');
            searchVisit();//到访人员
            nearestFace();//人脸识别
            loginLog();//到访人员
            getNowFormatDate();
            alarmLog();
        })();
        //获取当前日期时间
        function getNowFormatDate() {
            var date = new Date();
            $('.footer_bottom').html('数据统计：截止至'+timeStamp2String(date));
        }
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
        function searchVisit(){
            let url = '/system/log/countVisitors';
            let data = '';
            $.ajax({
                url:url,
                data:data,
                type: 'POST',
                contentType:'application/json',
                success:function (res){
                    console.log(res)
                    if(res.success){
                        $('.footer_left_content_top_left').html(res.result+'人')
                    }else {
                        alert('获取信息失败')
                    }
                },
                fail:function (res){
                    alert('获取信息失败')
                }
            })

        }
        function nearestFace(){
            let url = '/system/staff/getLastImg';
            let data = '';
            $.ajax({
                url:url,
                data:data,
                type: 'POST',
                contentType:'application/json',
                success:function (res){
                    console.log(res)
                    if(res.success){
                        let imgPath = '<%=basePath%>' + res.result.replace(/\/data\/webuser01\/grad-project\//,'');
                        $('.center_middle_img img').attr('src', imgPath)
                    }else {
                        alert('获取人脸识别失败')
                    }
                },
                fail:function (res){
                    alert('获取人脸识别失败')
                }
            })
        }
        function loginLog(){
            let url = '/system/log/queryLoginLog';
            let data = {
                "keyWord": '',
                "pageNum": 1,
                "pageSize": 6
            }
            console.log(data)
            $.ajax({
                url: url,
                data: JSON.stringify(data),
                type: 'POST',
                contentType: 'application/json',
                success: function (res) {
                    let list = res.result.list
                    // console.log(pages)
                    let str = '';
                    if(Object.keys(list).length==0){
                        str= '<li style="text-align: center">'+'暂未查询到数据'+'</li>'
                    }else {
                        for (let i = 0; i < list.length; i++) {
                            str += '<li>' + list[i].userName +'\xa0\xa0\xa0'+ timeStamp2String(list[i].oprTime) + '</li>'
                        }
                    }
                    $('.loginLog').empty().html(str)
                },
                fail: function (res) {
                    alert(res)
                }
            })
        }
        function alarmLog(){
            let url = '/system/log/querySystemLog';
            let data = {
                "keyWord": '',
                "pageNum": 1,
                "pageSize": 5
            }
            console.log(data)
            $.ajax({
                url: url,
                data: JSON.stringify(data),
                type: 'POST',
                contentType: 'application/json',
                success: function (res) {
                    let list = res.result.list
                    // console.log(pages)
                    let str = '';
                    let str1 = '';
                    let str2 = '';
                    if(Object.keys(list).length==0){
                        str= '<li style="text-align: center">'+'暂未查询到数据'+'</li>'
                        str1= '<li style="text-align: center">'+'暂未查询到数据'+'</li>'
                        str2= '<li style="text-align: center">'+'暂未查询到数据'+'</li>'
                    }else {
                        for (let i = 0; i < list.length; i++) {
                            str += '<li>' + list[i].remark + '</li>'
                            str1 += '<li>' + list[i].operation + '</li>'
                            str2 += '<li>' + timeStamp2String(list[i].oprTime)+ '</li>'
                        }
                    }
                    $('#footer_right_bottom_content1').empty().html(str)
                    $('#footer_right_bottom_content2').empty().html(str1)
                    $('#footer_right_bottom_content3').empty().html(str2)
                },
                fail: function (res) {
                    alert(res)
                }
            })
        }
        $('.center_left_bottom_img').on('click', function () {
            new duDialog('是否确认执行此操作', '你确定吗?', duDialog.OK_CANCEL, {
                okText: '确定',
                callbacks: {
                    okClick: function () {
                        // do something
                        this.hide();  // hides the dialog
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
                    }
                }
            });
        })
    </script>
</body>
</html>
