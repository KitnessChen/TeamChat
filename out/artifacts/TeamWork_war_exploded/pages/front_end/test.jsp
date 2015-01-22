<%--
  Created by IntelliJ IDEA.
  User: whd
  Date: 2014/12/1
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute("username", "whd");
    session.setAttribute("userid", 4);
%>
<html>
<head>
    <title></title>
    <script src="/js/jquery-2.1.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#publish").click(function () {
                var teamid = parseInt($("#teamid").val());
                var touserid = parseInt($("#touserid").val());
                var content = $("#content").val();
                var data = {
                    "teamid": teamid,
                    "touserid": touserid,
                    "content": content,
                    "action": "sendMessage"
                };
                $.post("/message", data, function (res) {
                    console.log("ok");
                });
            });
            $("#getmessage").click(function () {
                var teamid = parseInt($("#teamid").val());
                var touserid = parseInt($("#touserid").val());
                var lastmessageid = $("#lastmessageid").val();
                var data = {
                    "teamid": teamid,
                    "touserid": touserid,
                    "lastmessageid": lastmessageid,
                    "action": "getMessageList"
                };
                $.get("/message", data, function (res) {
                    console.log(res);
                });
            });
            $("#searchteam").click(function () {
                var teamname = $("#searchteamtext").val();
                var data = {
                    "teamname": teamname,
                    "action": "searchTeam"
                };
                $.get("/team", data, function (res) {
                    console.log(res);
                });
            });
        });
    </script>
</head>
<body>
<div id="container">
    <label for="teamid">team id</label> <input id="teamid" type="text">
    <label for="touserid">to user id</label> <input id="touserid" type="text" value="-1">
    <label for="content">public message</label> <input id="content" type="text">
    <br>
    <button type="button" id="publish">publish</button>
    <br>
    <label for="lastmessageid">last message id</label> <input id="lastmessageid" type="text">
    <button type="button" id="getmessage">get messagelist</button>
    <br>
    <label for="searchteamtext">search team</label> <input type="text" id="searchteamtext">
    <button type="button" id="searchteam">search team</button>
</div>

</body>
</html>
