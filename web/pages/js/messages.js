/**
 * Created by whd on 2014/12/4.
 */
$(document).ready(function () {
    $('button#sendmessage').click(function () {
        var content = $("#content").val();
        var data = {
            'type': 'public message',
            'teamid': 1,
            'touserid': -1,
            'content': content
        };
        $.post("/message", data);
    });
});