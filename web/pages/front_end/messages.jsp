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
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->

    <!-- Custom scripts for this template -->
    <script src="/js/jquery.js" type="text/javascript"></script>
    <script src="/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap-dropdown.js" type="text/javascript"></script>
</head>
<body>
    <div class="container">
      <div class="row">
        <div class="col-md-2">
          <div class="span2">
              <ul class="nav nav-pills nav-stacked">
                  <li class="nav-header" data-tid="<% team_id %>"><a href="/team/teamname">teamname</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Channel</li>
                  <!-- 一个channel对应一个li -->
                  <li class="active" data-cid="<% channel_id %>"><a href="/message/teamname#channel">channel name</a></li>
                  <li data-cid="<% channel_id %>"><a href="/message/teamname#channel">channel name</a></li>
                  <li data-cid="<% channel_id %>"><a href="/message/teamname#channel">channel name</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Direct Message</li>
                  <!-- 一个member对应一个li -->
                  <li data-mid="<% member_id %>"><a href="/message/teamname@member">member name</a></li>
                  <li data-mid="<% member_id %>"><a href="/message/teamname@member">member name</a></li>
                  <li data-mid="<% member_id %>"><a href="/message/teamname@member">member name</a></li>
                  <li class="divider"></li>
                  <li class="nav-header" data-uid="<% user_id %>"><a href="/account/profile">username</a></li>
              </ul>
          </div>
      </div>
      <div class="col-md-8">
        <div class="message-send-box">
            <div class="input-group">
             <input type="text" class="form-control" required autofocus>
             <span class="input-group-btn">
                <button class="btn btn-default" type="button">
                   Go!
               </button>
           </span>
       </div>
   </div>
   <div class="message-box">
    <div class="panel panel-default message">
       <div class="panel-body">
          test <br>
          test
      </div>
  </div>
  <div class="message-box">
    <div class="panel panel-default message">
       <div class="panel-body">
        这里输入message的内容，简单以  “说话者username： message ” 的格式展示就可以了
    </div>
</div>
</div>
</div>
</div>
</div>
<footer>
    <div class="container">
        <h1>Contact Us.</h1>
        <p>Email:test@gmail.com</p>
    </div>
    <div class="copyright">
        <div class="footer-logo">TeamWork</div>
        <p class="text">Copyright © 2013-2014 TeamWork.All Rights Reserved.</p>
    </div>
</footer>  
</div>

</body>
</html>
