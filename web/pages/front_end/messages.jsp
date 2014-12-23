<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--这里大概就是判定一下，如果用户未登录（cookie)就访问index，如果已登录就访问用户主页（/home）的页面（当前）--%>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.html");
        return;
    }
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Messages - TeamWork</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap-3.1.1.min.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/message.css" rel="stylesheet">

    <!-- Custom scripts for this template -->
    <script src="/js/jquery.js" type="text/javascript"></script>
    <script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap-dropdown.js" type="text/javascript"></script>
</head>

<body>
<!-- Header Section -->
<div id="headerSection">
    <div class="container">
        <div class="span3 logo"><img src="/img/logo1.png" alt=""/></div>
        <div class="navbar">
            <div class="nav-collapse">
                <ul class="nav nav-pills">
                    <li class="dropdown all-camera-dropdown">
                        <a class="dropdown-toggle username" data-toggle="dropdown" href="#">
                            <img class="img-circle"
                                 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                                 alt="Generic placeholder image" style="width: 35px; height: 35px;">
                            <% username %>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a data-toggle="tab" href="/profile"><span
                                    class="glyphicon glyphicon-user"> Profile</span></a></li>
                            <li><a data-toggle="tab" href="/settings"><span
                                    class="glyphicon glyphicon-cog"> Settings</span></a></li>
                            <li><a data-toggle="tab" href="/quit"><span class="glyphicon glyphicon-log-out"> Quit</span></a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<div class="desk-container">
    <div class="row">
        <div class="col-md-3">
            <div class="span2">
                <ul class="nav nav-pills nav-stacked">
                    <li class="nav-header" data-tid="<% team_id %>"><a href="/team/teamname">teamname</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">Channel</li>
                    <!-- 一个channel对应一个li -->
                    <li class="active" data-cid="<% channel_id %>"><a href="/message/teamname#channel">channel name</a>
                    </li>
                    <li data-cid="<% channel_id %>"><a href="/message/teamname#channel">channel name</a></li>
                    <li data-cid="<% channel_id %>"><a href="/message/teamname#channel">channel name</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">Direct Message</li>
                    <!-- 一个member对应一个li -->
                    <li data-mid="<% member_id %>"><a href="/message/teamname@member">member name</a></li>
                    <li data-mid="<% member_id %>"><a href="/message/teamname@member">member name</a></li>
                    <li data-mid="<% member_id %>"><a href="/message/teamname@member">member name</a></li>
                    <li class="divider"></li>
                </ul>
            </div>
        </div>
        <div class="col-md-8">
            <div class="message-send-box">
                <div class="input-group">
                    <input type="text" class="form-control" required autofocus>
           <span class="input-group-btn">
            <button id="sendmessage" class="btn btn-default" type="button">
                Go!
            </button>
         </span>
                </div>
            </div>
            <div class="message-box">
                <div class="panel panel-default message">
                    <div class="panel-body">
                        test
                    </div>
                </div>
                <div class="message-box">
                    <div class="panel panel-default message">
                        <div class="panel-body">
                            这里显示message的内容，简单以 “说话者username： message ” 的格式展示就可以了
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Copyright Section -->
<div id="footerSection">
    <div class="span8 copyright"><p> Copyright © 2014 | Developed By RUCers</p></div>
</div>

</body>
</html>
