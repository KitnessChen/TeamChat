<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--这里大概就是判定一下，如果用户未登录（cookie)就访问index，如果已登录就访问用户主页（/home）的页面（当前）--%>
<%
if(session.getAttribute("username") ==null){
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
</head>


<body>

  <!-- Header Section -->
  <div id="headerSection">
    <div class="container">
      <div class="span3 logo"><img src="img/logo.png" alt="" /></div>
      <div class="navbar">
        <div class="nav-collapse">
          <ul class="nav nav-pills">
            <li class="dropdown all-camera-dropdown">
              <a class="dropdown-toggle username" data-toggle="dropdown" href="#">
                <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" style="width: 35px; height: 35px;">
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
    </div>
  </div>


  <div class="desk-container">
    <div class="row">
      <div class="col-md-4">
        <h4>Member List</h4>
         <hr/>
        <div class="member-list">
          <div class="list-group-item member">
            <p>test1<a href="#"><span class="glyphicon glyphicon-remove"></span></a></p><!-- 这里还差一个确认删除modal啊！ -->
          </div>
          <div class="list-group-item member">
            <p>test2<a href="#"><span class="glyphicon glyphicon-remove"></span></a></p>
          </div>
          <div class="list-group-item member">
            <p>test3<a href="#"><span class="glyphicon glyphicon-remove"></span></a></p>
        </div>
      </div>
    </div>
      <div class="col-md-7">
        <h2>Team Name</h2>
        <p>description</p>
      </div>
</div>
</div>


<!-- Copyright Section -->
<div id="footerSection">
  <div class="span8 copyright"><p>  Copyright © 2014 | Developed By RUCers</p></div>
</div>

</body>
</html>
