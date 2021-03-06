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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>添加员工</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/thems.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function(){
            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');
            var main_w = $(window).width();
            $('.xjhy').css('width',main_w-40+'px');
        });
        function uplodeFile() {
            var file=$('#Agreement_file')[0].files[0];
            var form = new FormData();
            form.append('img',file);
            $.ajax({
                type: 'POST',
                url: '../staff/uploadFile',
                data: form,
                processData: false, //告诉jquery要传输data对象
                contentType: false,   //告诉jquery不需要增加请求头对于contentType的设置
                success: function (data) {
                    console.log(data);
                    var imageURL=data;
                    var paths;
                    if(imageURL.indexOf('/') != -1) {
                        paths  = imageURL.split('/');
                    } else {
                        paths  = imageURL.split('\\');
                    }
                    imageURL  = "../upload/" + paths[paths.length-1];
                    $('#pImg').attr('src',imageURL);
                    $('#info').attr('value',imageURL);
                }
            })
        }
        function saveUserInfo(){
            // alert('sdfgdgdgs')
            let name = $('.hypz .name').val();
            let phone = $('.hypz .phone').val();
            let officeId = $('.hypz .officeId').find("option:selected").val();
            let post = $('.hypz .post').val();
            let startTime = $('.hypz .startTime').val();
            let endTime = $('.hypz .endTime').val();
            let info = $('.hypz .info').val();
            let data={
                name,
                phone,
                officeId,
                post,
                startTime,
                endTime,
                info
            }
            console.log(data)
            $.ajax({
                url: '/system/staff/add',
                data:data,
                type:'POST',
                success:function (res){
                    if(res.success){
                        alert('添加成功')
                        history.go(-1);
                        self.location=document.referrer;
                    }else {
                        alert(res.result)
                    }
                },
                fail:function (res){
                    alert(res.result)
                }
            })
        }
        // 选择文件
        $(document).ready(function(data){
            $("#pImg").click(function(){
                $("#Agreement_file").trigger("click");
            });
        });

    </script>
</head>

<body >
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">添加员工</span>
            </div>
            <div class="space_hx">&nbsp;</div>
                <div class="xjhy">
                    <!--高级配置-->
                    <ul class="hypz gjpz clearfix">
                        <li class="clearfix">
                            <span class="title">姓名：</span>
                            <div class="li_r">
                                <input class="name" name="name" type="text"/>
                                <i>*</i>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">手机号：</span>
                            <div class="li_r">
                                <input class="phone" name="phone" type="text"/>
                                <i>*</i>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">所属公司：</span>
                            <div class="li_r">
                                <select class="officeId" name="officeId">
                                    <c:forEach items="${DLIST}" var="dep">
                                        <c:if test="${OBJ.officeId==dep.id}">
                                            <option value="${dep.id}" selected="selected">${dep.name}</option>
                                        </c:if>
                                        <c:if test="${OBJ.officeId!=dep.id}">
                                            <option value="${dep.id}">${dep.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <i>*</i>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">职务：</span>
                            <div class="li_r">
                                <input class="post" name="post" type="text" value="${OBJ.post}"/>
                                <i>*</i>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">有效期开始时间：</span>
                            <div class="li_r">
                                <input id="d11" class="startTime" type="text" name="startTime" onclick="WdatePicker()"
                                       value="${OBJ.startTimeStr}"/>
                                <i>*</i>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">有效期结束时间：</span>
                            <div class="li_r">
                                <input id="d12" class="endTime" type="text" name="endTime" onclick="WdatePicker()" value="${OBJ.endTimeStr}"/>
                                <i>*</i>
                            </div>
                        </li>
                        <li class="clearfix" style="height:88px;">
                            <span class="title">头像图片：</span>
                            <div class="li_r">
                                <img id="pImg" class="chang" src="../images/blank.gif" style="width:78px; height: 78px;left: 20px; ">
                                <input id="Agreement_file" type="file" style="display:none" onchange="uplodeFile();" />
                                <%--用来接收图片地址传到后台--%>
                                <input id="info" class="info" type="text" name="info" value="value="${OBJ.info}"" style="display:none"/>
                            </div>

                        </li>
                        <li class="tj_btn">
                            <a href="javascript:history.go(-1);" class="back">返回</a>
                            <a href="javascript:;" onclick="saveUserInfo()">保存</a>
                        </li>
                    </ul>
                    <!--高级配置-->
                </div>
        </div>
    </div>
</div>
</body>
</html>
