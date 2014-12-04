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
    <script src="/js/jquery-2.1.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#submit').click(function () {
                var teamid = $('#teamid').val();
                var userid = $('#userid').val();
                var parameters = {
                    'type': 'add team member',
                    'teamid': teamid,
                    'userid': userid
                };
                $.post("/team", parameters, function (data) {
                    alert(data);
                });
            });
        });
    </script>
</head>
<body>
<h1>添加成员</h1>

<div>
    <input type="text" id="teamid" value="teamid"/>
</div>
<div>
    <input type="text" id="userid" value="userid"/>
</div>
<button id="submit">添加</button>
</body>
</html>
