$(document).ready(function(){
    //create team
    $('button#create').click(function(){
        var teamname = $("#teamname").val();
        var description = $("#description").val();
        $.ajax({
            url: '/createteam',
            type: 'post',
            dataType: 'json',
            data:{'action': 'signin', 'teamname': teamname, 'description': description},
            success: function(msg){
                alert(msg);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(errorThrown);
            }
        });
    });

    //search team
    $('button#search').click(function(){
        var teamname = $("#teamname").val();
        $.ajax({
            url: '/searchteam',
            type: 'post',
            dataType: 'json',
            data:{'action': 'signin', 'teamname': teamname},
            success: function(msg){
                alert(msg);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
                alert(errorThrown);
            }
        });
    });
});