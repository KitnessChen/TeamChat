<%--
  Created by IntelliJ IDEA.
  User: whd
  Date: 2014/12/1
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="js/jquery-2.1.1.min.js"></script>
</head>
<body>
<h1>test create team</h1>
<script>
    document.body.innerHTML += "!!!";
    var data = {
        "teamName": "team1",
        "creatorId": 1
    };
    jQuery.post("/create-team", data);
    document.body.innerHTML += "!!!";
</script>
</body>
</html>
