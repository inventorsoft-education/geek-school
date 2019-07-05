$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'api/groups/admin',
        dataType: 'json',
        cache: false,
        traditional: true,
        success: function(response) {
            var source = document.getElementById("group-handlebars").innerHTML;
            var template = Handlebars.compile(source);
            for (let i = 0; i < response.length; i++) {
                $('#input_group').append(template(response[i]));
            }
        }
    })
    var token = $("meta[name='_csrf']").attr("content");
    var headerName = $("meta[name='_csrf_header']").attr("content");


    $("#delete_student").on('click', function() {
        var user_name = $('#name_user').val();
        var group_id = $('#group_id').attr("rev");
        $.ajax({
            type: 'DELETE',
            url: 'api/groups/admin/' + group_id,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            data: {
                'username': user_name
            },
            success: function() {}
        })
    })
    $("#add_student").on('click', function() {
        var user_name = $('#name_user').val();
        var group_id = $('#tagPlaces').attr("value");
        $.ajax({
            type: 'PUT',
            url: 'api/groups/admin',
            beforeSend: function(xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            data: {
                'group_id': group_id,
                'username': user_name
            },
            success: function() {}
        })
    })
    $('#input_group').on('click', '#group_id', function() {
        var group_id = $(this).attr('rev');
        var group_name = $(this).attr('name');
        $('#tagPlaces').attr("value", group_id);
        $('#tagPlaces').text(group_name);
    });

})