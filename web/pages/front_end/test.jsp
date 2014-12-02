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
    <script>
        var data = {
            "teamName": "team1",
            "creatorId": 1
        };
        $.post("/create-team", data);

    </script>
</head>
<body>
    <h1>test create team</h1>
</body>
</html>
