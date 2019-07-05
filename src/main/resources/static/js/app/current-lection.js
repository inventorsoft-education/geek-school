$(document).ready(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var headerName = $("meta[name='_csrf_header']").attr("content");
    var id = window.location.pathname;
    $.ajax({
        type: 'GET',
        url: "/api" + id,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(headerName, token);
        },
        dataType: 'json',
        cache: false,
        traditional: true,
        success: function(response) {
            var source = document.getElementById("current-lection-entry").innerHTML;
            var template = Handlebars.compile(source);

            $('#my_page').append(template(response));

        },
        error: function(e) {
            alert(e);
        }
    })
    $('#my_page').on('click', '#btn_delete', function() {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'DELETE',
            url: "/api" + id,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            success: function() {
                top.location.href = "/lection";
            },
            error: function(e) {}
        })
    })
    $.ajax({
        type: 'GET',
        url: '/api/users/teacher',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(headerName, token);
        },
        dataType: 'json',
        cache: false,
        traditional: true,
        success: function(response) {
            var source = document.getElementById("teacher-template").innerHTML;
            var template = Handlebars.compile(source);
            for (let i = 0; i < response.length; i++) {
                $('#teacher_list').append(template(response[i]));
            }
        },
        error: function(e) {
            alert(e.text + "sas");
        }
    })
    $('#my_page').on('click', '#btn_change', function() {
        var idBtn = $(this).attr("data");
        $('#change_teacher').show();
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");


        $('#change_teacher').on('click', '#btn_change_teacher', function() {

            var teacher_id = $('#state').val();

            $.ajax({
                type: 'PUT',
                url: '/api/lection/' + idBtn,
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(headerName, token);
                },
                data: {
                    'teacher_name': teacher_id
                },
                success: function() {},
                error: function(e) {
                    alert(e.text);
                }
            })
        })
    })
});