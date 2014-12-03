$(document).ready(function(){
    //sign up
    $('button').click(function(){
        var email = $("email").val();
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            url: '/signup',
            type: 'post',
            dataType: 'json',
            data:{'action': 'signin', 'email': email, 'username': username, 'password': password},
            success: function(msg){
                alert(msg);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(errorThrown);}
            });
    });
});