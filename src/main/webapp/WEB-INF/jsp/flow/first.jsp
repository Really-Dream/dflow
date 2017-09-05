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
            <input>
            <%--<table class="table table-striped">--%>
                <%--<thead>--%>
                <%--<tr id="title">--%>
                    <%--<th>编号</th>--%>
                    <%--<th>名称</th>--%>
                    <%--<th>操作</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody id="list">--%>
                <%--<tr>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                <%--</tr>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        </div>
    </div>
</div>

<script type="text/javascript">

</script>

</body>
</html>
