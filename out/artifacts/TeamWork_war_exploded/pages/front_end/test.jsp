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
    <%=session.getAttribute("UserName")%>
    <title></title>
    <script src="/js/jquery-2.1.1.min.js"></script>
    <script>
        var data = {
            "username": "王瀚达",
            "password": "123456",
            "email": "12321"
        };

        $.post("/signup", data, function () {
            alert(data.innerText);
        }, "html");

    </script>
</head>
<body>
<h1>test signup</h1>
</body>
</html>
