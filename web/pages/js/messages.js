/**
 * Created by whd on 2014/12/4.
 */
$(document).ready(function () {
    $('button#sendmessage').click(function () {
        var content = $("#content").val();
        var data = {
            'action': 'sendPublicMessage',
            'teamid': 5,
            'touserid': -1,
            'content': content
        };
        $.post("/message", data, function (data) {
//            alert("!");
        });
    });
});