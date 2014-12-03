<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--这里大概就是判定一下，如果用户未登录（cookie)就访问index，如果已登录就访问用户主页（/home）的页面（当前）--%>
<%
if(session.getAttribute("user") ==null){
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

    <title>TeamWork</title>

    <!-- Bootstrap core CSS -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../css/home.css" rel="stylesheet">
    <script src="../../js/jquery.js" type="text/javascript"></script>
    <script src="../../js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap-dropdown.js" type="text/javascript"></script>
    <script src="../js/home.js" type="text/javascript"></script>
</head>

<body>
    <div class="container">
        <div class="header">
            <p1>TeamWork</p1>
            <div id="account" class="top-profile">
                <span id="user-id" style="display: none"><% userid %></span>
                <ul class="nav nav-pills">
                    <li class="dropdown all-camera-dropdown">
                        <a class="dropdown-toggle username" data-toggle="dropdown" href="#">
                            <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" style="width: 30px; height: 30px;">
                            <% username %>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a data-toggle="tab" href="/profile"><span class="glyphicon glyphicon-user"> Profile</span></a></li>
                            <li><a data-toggle="tab" href="/settings"><span class="glyphicon glyphicon-cog"> Settings</span></a></li>
                            <li><a data-toggle="tab" href="/quit"><span class="glyphicon glyphicon-log-out"> Quit</span></a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div id="desk-container">
            <%--从后台传team name的列表到这里 , href的值需要改 每一个名称对应一个li list-group-item, 格式和下面一样--%>
            <a class="list-group-item" href="/team/teamname"><h4>test1</h4></a>
            <a class="list-group-item" href="/team/teamname"><h4>test2</h4></a>
            <button class="btn btn-primary" data-toggle="modal" data-target="#CreateTeamModal">Create Team</button>
            <button class="btn btn-primary" data-toggle="modal" data-target="#SearchTeamModal">Search Team</button>
        </div>
        <footer>
            <div class="row divide-line">
                <p class="devide-content">Contact Us.</p>
            </div>
            <div class="contact-cases">
                <p>Email: test@gmail.com</p>
            </div>
            <div class="copyright">
                <div class="footer-logo">TeamWork</div>
                <p class="text">Copyright © 2013-2014 TeamWork.All Rights Reserved.</p>
            </div>
        </footer>  
    </div>
    <div class="modal fade" id="CreateTeamModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Create Team</h4>
                </div>
                <div class="modal-body">
                    <form id="create-team-form" action method="post" class="auth-form form-horizontal">
                        <div class="inputbox">
                            <input type="text" class="form-control" placeholder="Team Name" id="teamname" required autofocus>
                        </div>
                        <div class="inputbox">
                            <input type="text" class="form-control" placeholder="Description" id="description" required autofocus>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" id="create">Submit</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="SearchTeamModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Search Team</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-6">
                        <div class="inputbox">
                            <input type="text" class="form-control" placeholder="Please input the name">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button class="btn btn-primary" type="button" id="search">Search</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>