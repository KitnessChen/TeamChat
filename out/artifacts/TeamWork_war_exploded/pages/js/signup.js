$(document).ready(function () {
    //sign up
    $('button').click(function () {
        var email = $("#email").val();
        var username = $("#username").val();
        var password = $("#password").val();
        var data = {
            'email': email,
            'username': username,
            'password': password
        };
        $.post('/signup', data, function (msg) {
            document.body.innerHTML = msg;
        });
//        $.ajax({
//            url: '/signup',
//            type: 'post',
//            dataType: 'json',
//            data: {'action': 'signup', 'email': email, 'username': username, 'password': password},
//            success: function (msg) {
//                alert(msg);
//            },
//            error: function (XMLHttpRequest, textStatus, errorThrown) {
//                alert(errorThrown);
//            }
//        });
    });
});