$(function(){

    const appendBook = function(data){
        var bookCode = '<h4>' + data.name + '</h4>';
        $('#book-list')
            .append('<div>' + bookCode + '</div>');
    };

    //Loading books on load page

    $.get('/users/', function(response)
    {
        for(i in response) {
            appendBook(response[i]);
        }
    });

    //Show adding book form
    $('#show-add-book-form').click(function(){
        $('#book-form').css('display', 'flex');
    });

    //Closing adding book form
    $('#book-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting book
    $(document).on('click', '.book-link', function(){
        var link = $(this);
        var bookId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/users/' + bookId,
            success: function(response)
            {
                var code = '<span>Год выпуска:' + response.year + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Книга не найдена!');
                }
            }
        });
        return false;
    });

    //Adding book
    $('#save-book').click(function()
    {
        var data = $('#book-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/users/',
            data: data,
            success: function(response)
            {
                $('#book-form').css('display', 'none');
                var book = {};
                book.id = response.id;
                var dataArray = $('#book-form form').serializeArray();
                for(i in dataArray) {
                    book[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendBook(book);
            }
        });
        return false;
    });

});