$(document).ready(function(){
    //sign in
    $('button').click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            url: '../front_end/login.jsp',
            type: 'post',
            dataType: 'json',
            data:{'action': 'signin', 'username': username, 'password': password},
            success: function(msg){
                alert(msg);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(errorThrown);}
            });
    });
});