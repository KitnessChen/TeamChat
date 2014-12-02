<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--这里大概就是判定一下，如果用户未登录（cookie)就访问index，如果已登录就访问用户主页（/home）的页面（当前）--%>
<%
if(session.getAttribute("user") ==null){
response.sendRedirect("index.html");
return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</head>

<body>
    <div class="main">
        <div class="header">
            <p1>TeamWork</p1>
            <div id="account" class="top-profile">
                <span id="user-id" style="display: none"><% userid %></span>
                <img class="avatar" src="">
                <span class="username"><% username %></span>
                <ul class="top-dropdown" id="account-menu">
                    <li id="profile"><a>Profile</a></li>
                    <li id="settings"><a>Settings</a></li>
                    <li id="quit"><a>Quit</a></li>
                </ul>
            </div>
        </div>
        <div class="container" style="left: 20%; opacity: 1;">
            <div id="desk-container">
                <%--从后台传team的名称列表到这里 , 每一个名称对应一个div desk-inline, 格式和下面一样--%>
                <%

                %>
                <div class="desk-line">
                    <div class="desk-cell" id="create-cell"><h4 class="desk-cell-name">Create/Search Team</h4></div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <div class="row divide-line">
            <p class="devide-content">Contact Us.</p>
        </div>
        <div class="contact-cases">
            <p>Email:test@gmail.com</p>
        </div>
        <div class="copyright">
            <div class="footer-logo">TeamWork</div>
            <p class="text">Copyright © 2013-2014 TeamWork.All Rights Reserved.</p>
        </div>
    </footer>  
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../js/home.js"></script>
</body>
</html>
