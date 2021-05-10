$(function(){

    const appendUser = function(data){
        var userCode = '<a href="#" class="user-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#user-list')
            .append('<div>' + userCode + '</div>');
    };

    //Loading books on load page
    $.get('/users/', function(response)
    {
        for(i in response) {
            appendUsers(response[i]);
        }
    });

    //Show adding book form
    $('#show-add-user-form').click(function(){
        $('#user-form').css('display', 'flex');
    });

    //Closing adding book form
    $('#user-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting book
    $(document).on('click', '.user-link', function(){
        var link = $(this);
        var userId = $(this).data('id');
        $.ajax({
            method: "GET",
            url: '/users/' + bookId,
            success: function(response)
            {
                var code = '<span>Имя пользователя:' + response.name + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Пользователь не найден!');
                }
            }
        });
        return false;
    });

    //Adding book
    $('#save-user').click(function()
    {
        var data = $('#user-form').serialize();
        $.ajax({
            method: "POST",
            url: '/users/',
            data: data,
            success: function(response)
            {
                $('#user-form').css('display', 'none');
                var user = {};
                user.id = response;
                var dataArray = $('#user-form').serializeArray();
                for(i in dataArray) {
                    user[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendUser(user);
            }
        });
        return false;
    });

});