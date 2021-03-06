<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--这里大概就是判定一下，如果用户未登录（cookie)就访问index，如果已登录就访问用户主页（/home）的页面（当前）--%>
<%
    String userName = null;
    if (session.getAttribute("username") == null) {
        response.sendRedirect("/");
    } else {
        userName = session.getAttribute("username").toString();
    }
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>TeamName - TeamWork</title> <!-- 这里要的teamname是传过来的呀-->

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap-3.1.1.min.css" rel="stylesheet">
    <link href="/css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/team.css" rel="stylesheet">

    <!-- Custom scripts for this template -->
    <script src="/js/jquery.js" type="text/javascript"></script>
    <script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap-dropdown.js" type="text/javascript"></script>
    <script src="/pages/js/bootstrap-dropdown.js" type="text/javascript"></script>
</head>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign up - TeamWork</title> <!-- 这里要的teamname是传过来的呀-->

    <!-- Bootstrap core CSS -->
    <link href="../../css/bootstrap-3.1.1.min.css" rel="stylesheet">
    <link href="../../css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="../../css/navbar-static-top.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../css/team.css" rel="stylesheet">

    <!-- Custom scripts for this template -->
    <script src="../../js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../../js/jquery.scrollTo-1.4.3.1-min.js" type="text/javascript"></script>
    <script src="../../js/jquery.easing-1.3.min.js" type="text/javascript"></script>
    <script src="../../js/default.js" type="text/javascript"></script>
    <script src="../js/team.js" type="text/javascript"></script>
</head>


<body>

<!-- Header Section -->
<div id="headerSection">
    <div class="container">
        <div class="span3 logo"><img src="../../img/logo1.png" alt=""/></div>
        <div class="navbar">
            <div class="nav-collapse">
                <ul class="nav nav-pills">
                    <li class="dropdown all-camera-dropdown">
                        <a class="dropdown-toggle username" data-toggle="dropdown" href="#">
                            <img class="img-circle"
                                 src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                                 alt="Generic placeholder image" style="width: 35px; height: 35px;">
                            <%= userName %>
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
        <div class="col-md-4">
            <h4>Member List<a class="add-member" data-toggle="modal" data-target="#AddMemberModal"><span
                    class="glyphicon glyphicon-plus-sign"></span></a></h4>
            <hr/>
            <div class="member-list">
                <div class="list-group-item member">
                    <p class="member">test1<a class="remove-member" href="#"><span
                            class="glyphicon glyphicon-remove"></span></a></p>
                </div>
                <div class="list-group-item member">
                    <p class="member">test2<a class="remove-member" href="#"><span
                            class="glyphicon glyphicon-remove"></span></a></p>
                </div>
                <div class="list-group-item member">
                    <p class="member">test3<a class="remove-member" href="#"><span
                            class="glyphicon glyphicon-remove"></span></a></p>
                </div>
            </div>
        </div>
        <div class="col-md-7">
            <div class="team-name">
                <h2 class="name">Team Name <a class="edit-name" href="#"><span
                        class="glyphicon glyphicon-pencil"></span></a></h2>
            </div>
            <div class="team-description">
                <h4 class="description">description <a class="edit-description" href="#"><span
                        class="glyphicon glyphicon-pencil"></span></a></h4>
            </div>
        </div>
    </div>
</div>


<!-- Modal Section -->
<div class="modal fade" id="AddMemberModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Add member</h4>
            </div>
            <div class="modal-body">
                <p>Please input member's name:</p>

                <form id="create-team-form" action method="post" class="auth-form form-horizontal">
                    <div class="inputbox">
                        <input type="text" class="form-control" placeholder="Member Name" id="teamname" required
                               autofocus>
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

<!-- Copyright Section -->
<div id="footerSection">
    <div class="span8 copyright"><p> Copyright © 2014 | Developed By RUCers</p></div>
</div>

</body>
</html>
