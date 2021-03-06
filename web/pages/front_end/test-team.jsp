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
                    'action': 'addTeamMember',
                    'teamid': teamid,
                    'userid': userid
                };
                $.post("/team", parameters, function (data) {
                    alert(data);
                });
            });

            $("#get").click(function () {
                var parameters = {
                    'action': 'getTeamMemberList',
                    'teamid': $("#getteamid").val()
                };
                $.get("/team", parameters, function (data) {
                    for (var i in data.memberlist) {
                        console.log(data.memberlist[i]);
                    }
                }, "json");
            });
            $("#getteams").click(function () {
                var parameters = {
                    'action': 'getTeamList',
                    'userid': $("#getuserid").val()
                };
                $.get("/team", parameters, function (data) {
                    for (var i in data.teamlist) {
                        console.log(data.teamlist[i]);
                    }
                }, "json");
            });

            $('#create_team').click(function () {
                $.post('/team', {
                    'action': 'createTeam',
                    'teamname': $('#teamname').val()
                }, function (msg) {
                    alert(msg);
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

<div>
    <input type="text" id="getteamid" value="getteamid"/>
    <button id="get">获得所有成员</button>
</div>
<div>
    <input type="text" id="teamname" value="团队名称"/>
    <button id="create_team">创建团队</button>
</div>
<div>
    <input type="text" id="getuserid" value="userid"/>
    <button id="getteams">获得所有团队</button>
</div>
<div id="container">

</div>

</body>
</html>
