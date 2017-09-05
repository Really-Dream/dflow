<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Theme Template for Bootstrap</title>

    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>

<body>
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Bootstrap theme</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li id="home"><a href="#" onclick="initIndex(this)">文件</a></li>
                <li><a href="#" onclick="initDeployed(this)">已部署</a></li>
                <li><a href="#" onclick="started(this)">已启动</a></li>
                <li><a href="#" onclick="task(this)">任务</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="#">Separated link</a></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container theme-showcase" role="main">

    <div class="page-header">
    </div>
    <div class="row">
        <div class="col-md-6">
            <table class="table table-striped">
                <thead>
                    <tr id="title">
                        <th>编号</th>
                        <th>名称</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="list">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>

<script type="text/javascript">
    $(function() {
        initIndex();
        $("#home").addClass("active");
    })

    /**
     * 展示所有文件
     */
    function initIndex(obj){
        $("#title").html("<th>编号</th><th>名称</th><th>操作</th>");
        $.getJSON("${pageContext.request.contextPath}/processes",function(data){
            var html = "";
            for (var i = 0; i<data.length ; i++){
                html += "<tr><td>"+(i+1)+"</td><td>"+data[i]+"</td><td><a href='#' onclick=\"deploy('"+data[i]+"')\">部署</a></td></tr>";
            }
            $("#list").html(html);
        })
        addStyle(obj);
    }

    /**
     * 展示已部署的流程
     */
    function initDeployed(obj){
        $("#title").html("<th>流程ID</th><th>流程名称</th><th>操作</th>");
        $.getJSON("${pageContext.request.contextPath}/deployed",function(data){
            var html = "";
            for(var i = 0; i < data.length; i++){
                html += "<tr><td>"+data[i][0]+"</td><td>"+data[i][1]+"</td>" +
                    "<td><a href='#' onclick=\"deleteDeployed('"+data[i][2]+"')\">删除</a>&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/bpm/startProcess?id="+data[i][2]+"')>启动</a></td>" +
                    "</tr>";
            }
            $("#list").html(html);
        })
        addStyle(obj);
    }

    /**
     * 展示已启动的流程
     */
    function started(obj){
        $("#title").html("<th>流程实例ID</th><th>流程定义ID</th><th>操作</th>");
        $.getJSON("${pageContext.request.contextPath}/started",function(data){
            var html = "";
            for(var i = 0; i < data.length; i++){
                html += "<tr><td>"+data[i][0]+"</td><td>"+data[i][1]+"</td>" +
                    "<td><a href='#' onclick=\"deleteInstance('"+data[i][0]+"')\">删除</a></td>" +
                    "</tr>";
            }
            $("#list").html(html);
        })
        addStyle(obj);
    }

    /**
     * 任务状态
     */
    function task(obj){
        $("#title").html("<th>流程定义ID</th><th>任务ID</th><th>任务名称</th><th>任务执行人</th><th>操作</th>");
        $.getJSON("${pageContext.request.contextPath}/task",function(data){
            var html = "";
            for(var i = 0; i < data.length; i++){
                html += "<tr><td>"+data[i][3]+"</td><td>"+data[i][0]+"</td><td>"+data[i][1]+"</td><td>"+data[i][2]+"</td>" +
                    "<td><a href='#' onclick=\"doTask('"+data[i][0]+"')\">执行</a></td>" +
                    "</tr>";
            }
            $("#list").html(html);
        })
        addStyle(obj);
    }

    /**
     * 部署流程
     * @param 流程文件名称
     */
    function deploy(fileName){
        $.getJSON("${pageContext.request.contextPath}/deploy?fileName="+fileName,function(data) {
            if(data == "SUCCESS"){
                alert("部署成功");
            }else{
                alert("部署失败");
            }
            initDeployed();
        })
    }

    /**
     * 删除已部署流程
     * @param id 部署流程ID
     */
    function deleteDeployed(id){
        $.getJSON("${pageContext.request.contextPath}/deleteDeployed?id="+id,function(data) {
            if(data == "SUCCESS"){
                alert("删除成功");
            }else{
                alert("删除失败");
            }
            initDeployed();
        })
    }

    /**
     * 启动流程
     */
    function startProcess(id){
        $.getJSON("${pageContext.request.contextPath}/startProcess?id="+id,function (data) {
            if(data == "SUCCESS"){
                alert("启动成功");
            }else{
                alert("删启动失败");
            }
            started();
        })
    }

    /**
     * 删除流程实例
     */
    function deleteInstance(id) {
        $.getJSON("${pageContext.request.contextPath}/deleteInstance?id="+id+"&reason=happy",function (data) {
            if(data == "SUCCESS"){
                alert("删除成功");
            }else{
                alert("删除失败");
            }
            started();
        })
    }

    /**
     * 删除流程实例
     */
    function doTask(id) {
        $.getJSON("${pageContext.request.contextPath}/doTask?id="+id,function (data) {
            if(data == "SUCCESS"){
                alert("删除成功");
            }else{
                alert("删除失败");
            }
            started();
        })
    }

    /**
     * 给所点击的按键添加样式
     */
    function addStyle(obj) {
        $("#navbar").find("li").each(function(){
            $(this).removeClass("active");
        })
        $(obj).parent().addClass("active");
    }

</script>

</body>
</html>
