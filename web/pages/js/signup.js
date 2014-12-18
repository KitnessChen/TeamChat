$(document).ready(function () {
    //sign up
    $('button').click(function () {
        var email = $("#email").val();
        var username = $("#username").val();
        var password = $("#password").val();
        var data = {
            'action': 'signUp',
            'email': email,
            'username': username,
            'password': password
        };
        //注意如果是打算了要用ajax post的话就不要给button的type设置为submit，
        // 如果type是submit的话就会发两次（submit自带提交form效果）
        //只是为了测试一下是否返回结果成功
        $.post('/signup', data, function (msg) {
            alert(msg);
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