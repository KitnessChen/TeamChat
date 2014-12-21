$(document).ready(function(){
    // remove member
    $('a.remove-member').click(function(){
        var member = $('p.member').text();
        var res = confirm("Please confirm your choice...");
        if(res == true)
        {
            $.ajax({
                url: 'team/remove',
                type: 'post',
                dataType: 'json',
                data: {'member': member}
            });
        }
    });

    // edit team name
    $('a.edit-name').click(function(){
        var name = $('h2.name').text();
        var text = $('h2.name');
        var input = $("<div class='input-group name' data-origin='" + name + "''>" +
            "<input class='form-control' id='teamname' type='text' value='" + name + "'/>" +
            "<span class='input-group-btn'><button id='savename' class='btn btn-default' type='button'>Save</button>" +
            "<button id='cancelname' class='btn btn-default' type='button'>Cancel</button></span></div>");
        text.html(input);
        
        //save team name
        $('button#savename').click(function(){
            var input = $('div.name');
            var name = $('input#teamname').value();
            var text = $('<h2 class="name">' + name +
               '  <a class="edit-name" href="#"><span class="glyphicon glyphicon-pencil"></span><a></h2>');
            input.html(text);
            $.ajax({
                url: '/team/save',
                type: 'post',
                dataType: 'json',
                data: {'team_name': name}
            });
        });

        //cancel name
        $('button#cancelname').click(function(){
            var input = $('div.name');
            var name = $('div.name').attr('data-origin');
            var text = $('<h2 class="name">' + name +
            '  <a class="edit-name" href="#"><span class="glyphicon glyphicon-pencil"></span><a></h2>');
            input.html(text);
        });
    });


    // edit team description
    $('a.edit-description').click(function(){
        var description = $('h4.description').text();
        var text = $('h4.description');
        var input = $("<div class='input-group description' data-origin='" + description + "''>" +
            "<input class='form-control' id='teamdescription' type='text' value='" + description + "'/>" +
            "<span class='input-group-btn'><button id='savedescription' class='btn btn-default' type='button'>Save</button>" +
            "<button id='canceldescription' class='btn btn-default' type='button'>Cancel</button></span></div>");
        text.html(input);

        //save team description
        $('button#savedescription').click(function(){
            var input = $('div.description');
            var description = $('input#teamdescription').value();
            var text = $('<h4 class="description">' + description +
               '  <a class="edit-description" href="#"><span class="glyphicon glyphicon-pencil"></span><a></h4>');
            input.html(text);
            $.ajax({
                url: '/team/save',
                type: 'post',
                dataType: 'json',
                data: {'team_description': description}
            });
        });

        //cancel description
        $('button#canceldescription').click(function(){
            var input = $('div.description');
            var description = $('div.description').attr('data-origin');
            var text = $('<h4 class="description">' + description +
                '  <a class="edit-description" href="#"><span class="glyphicon glyphicon-pencil"></span><a></h4>');
            input.html(text);
        });
    });

});
