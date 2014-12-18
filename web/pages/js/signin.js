$(document).ready(function () {

    //sign in
    $('button').click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var data = {
            'action' : 'signIn',
            'username': username,
            'password': password
        };
        $.post('/signin', data, function (msg) {
            alert(msg);
        });
//        $.ajax({
//            url: '/signin',
//            type: 'post',
//            dataType: 'json',
//            data: {
//                'username': username,
//                'password': password
//            },
//            success: function (msg) {
////                alert(msg);
//            },
//            error: function (XMLHttpRequest, textStatus, errorThrown) {
//                alert(errorThrown);
//            }
//        });
    });
});