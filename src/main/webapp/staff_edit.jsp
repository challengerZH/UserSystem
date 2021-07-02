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
    <title>编辑员工</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/thems.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            var main_h = $(window).height();
            $('.hy_list').css('height', main_h - 45 + 'px');

            var main_w = $(window).width();
            $('.xjhy').css('width', main_w - 40 + 'px');

        });

        // 选择图片显示
        function imgChange(obj) {
            //获取点击的文本框
            var file = document.getElementById("file");
            var imgUrl = window.URL.createObjectURL(file.files[0]);
            var img =document.getElementById('imghead');
            img.setAttribute('src',imgUrl); // 修改img标签src属性值
            return setImg(file.files[0]);
        };

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
                <span class="name">编辑员工</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <form action="../staff/edit" method="post" name="addForm">
                <input type="hidden" name="id" value="${OBJ.id}"/>
                <div class="xjhy">
                    <!--高级配置-->
                    <ul class="hypz gjpz clearfix">
                        <li class="clearfix">
                            <span class="title">姓名：</span>
                            <div class="li_r">
                                <input class="chang" name="name" type="text" value="${OBJ.name}"/>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">手机号：</span>
                            <div class="li_r">
                                <input class="chang" name="phone" type="text" value="${OBJ.phone}"/>
                                <i>*</i>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">所属公司：</span>
                            <div class="li_r">
                                <select name="officeId">
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
                                <input class="chang" name="post" type="text" value="${OBJ.post}"/>
                                <i>*</i>
                            </div>
                        </li>

                        <li class="clearfix">
                            <span class="title">有效期开始时间：</span>
                            <div class="li_r">
                                <input id="d11" class="chang" type="text" name="startTime" onclick="WdatePicker()"
                                       value="${OBJ.startTimeStr}"/>
                                <i>*</i>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="title">有效期结束时间：</span>
                            <div class="li_r">
                                <input id="d12" class="chang" type="text" name="endTime" onclick="WdatePicker()" value="${OBJ.endTimeStr}"/>
                                <i>*</i>
                            </div>
                        </li>

                        <li class="clearfix" style="height:88px;">
                            <span class="title">头像图片：</span>
                            <div class="li_r">
                                <c:if test="${OBJ.info==null || OBJ.info==''}">
                                    <img id="pImg" class="chang" src="../images/blank.gif" style="width:78px; height: 78px;left: 20px; ">

                                </c:if>
                                <c:if test="${OBJ.info!=null && OBJ.info!=''}">
                                    <img id="pImg" class="chang" src="${OBJ.info}" style="width:78px; height: 78px;left: 20px; ">
                                </c:if>
                                <input id="Agreement_file" type="file" style="display:none" onchange="uplodeFile();" />
                                <%--用来接收图片地址传到后台--%>
                                <input id="info" type="text" name="info" value="value="${OBJ.info}"" style="display:none"/>
                            </div>

                        </li>
                        <li class="tj_btn">
                            <a href="javascript:history.go(-1);" class="back">返回</a>
                            <a href="javascript:addForm.submit();">保存</a>
                        </li>
                    </ul>
                    <!--高级配置-->
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
