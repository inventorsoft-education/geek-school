$(document).ready(function() {

    $.ajax({
        type: 'GET',
        url: 'api/lection',
        dataType: 'json',
        cache: false,
        traditional: true,
        success: function(response) {
            var source = document.getElementById("entry").innerHTML;
            var template = Handlebars.compile(source);
            for (let i = 0; i < response.length; i++) {
                $('#lection_list').append(template(response[i]));
            }
        }
    })
    $("#add_lection_btn").on('click', function() {
        $.ajax({
            type: 'GET',
            url: 'api/subjects',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function(response) {
                var source = document.getElementById("lection_subject").innerHTML;
                var template = Handlebars.compile(source);
                for (let i = 0; i < response.length; i++) {
                    $('#input_subject').append(template(response[i]));
                }
            }
        })
        $.ajax({
            type: 'GET',
            url: 'api/users/teacher',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function(response) {
                var source = document.getElementById("lection_teacher").innerHTML;
                var template = Handlebars.compile(source);
                for (let i = 0; i < response.length; i++) {
                    $('#input_teacher').append(template(response[i]));
                }
            }
        })

        $('#new_lection').show();
        $(document).on('submit', 'form#contact-form', function(e) {
            e.preventDefault();
            var token = $("meta[name='_csrf']").attr("content");
            var headerName = $("meta[name='_csrf_header']").attr("content");
            var name = $('#input_name').val();
            var description = $('#input_description').val();
            var teacher_name = $('#teacher').val();
            var subject_name = $('#subjects').val();

            $.ajax({
                type: "POST",
                url: "api/lection",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(headerName, token);
                },
                data: {   "name": name,
                          "description": description,
                          "teacher_name": teacher_name,
                          "subject_name": subject_name },
                success: function() {
                    location.reload();
                }, error: function(e) {
                alert(e);
                }
            })
        })
    })
})
$(function(){
    $(".table").on("click", "tr[role=\"button\"]", function(e) {
    window.location = $(this).data("href");
    });
});

