$(document).ready(function() {
    function loadLection() {
        $.ajax({
            type: 'GET',
            url: 'api/lections',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function(response) {
                var source = document.getElementById("entry").innerHTML;
                var template = Handlebars.compile(source);
                var sortResponse = response.sort(function(a, b) {
                                    return a.id - b.id;
                });
                $('#lection_list').empty();
                for (let i = 0; i < sortResponse.length; i++) {
                    $('#lection_list').append(template(sortResponse[i]));
                }
                var elementTechId = "teacher-template";
                var techId = '#teacher_list';
                loadTeacher(elementTechId, techId);
            }
        });
    };

        function loadTeacher(elementById, putId) {
        $.ajax({
            type: 'GET',
            url: 'api/users/teacher',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function(response) {
                var source = document.getElementById(elementById).innerHTML;
                var template = Handlebars.compile(source);
                $(putId).empty();
                for (let i = 0; i < response.length; i++) {
                    $(putId).append(template(response[i]));
                }
            }
        });
    };

    function loadCourse() {
        $.ajax({
            type: 'GET',
            url: 'api/courses',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function(response) {
                var source = document.getElementById("lection_subject").innerHTML;
                var template = Handlebars.compile(source);
                $('#input_subject').empty();
                for (let i = 0; i < response.length; i++) {
                    $('#input_subject').append(template(response[i]));
                }
            }
        });
    };

    function updateTeacher(id_lection, teacher_id) {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'PUT',
            url: '/api/lections/' + id_lection,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            data: {
                'teacher_name': teacher_id
            },
            success: function() {
                loadLection();
            }
        })
    };

    function deleteLection(id) {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'DELETE',
            url: "/api/lections/" + id,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            success: function() {
                loadLection();
            }
        });
    };

    loadLection();

    $('#lection_list').on('click', '#btn_delete_lection', function() {
        var id_delete_lection = $(this).attr("rel");
        deleteLection(id_delete_lection);
    });

    $("#lection_list").on('click', "#btn_change_teacher", function() {
        var id_lection = $(this).attr("rel");
        var input_teacher_id = '#tech' + id_lection;
        if ($(input_teacher_id).is(":visible")) {
            $(input_teacher_id).hide();
        } else {
            $(input_teacher_id).show();
        }

        $('#lection_list').on('click', '#btn_change_teacher_ok', function() {
            var teacher_id = $(input_teacher_id).val();
            updateTeacher(id_lection, teacher_id);
        });
    });

    $("#add_lection_btn").on('click', function() {
        loadCourse();
        document.getElementById("add_lection_btn").style.visibility="hidden";
        var elementById = "lection_teacher";
        var putId = '#input_teacher';
        loadTeacher(elementById, putId);

        $('#btn_cancel').on('click', function() {
            $('#new_lection').hide();
            document.getElementById("add_lection_btn").style.visibility="visible";
        })

        $('#new_lection').show();
        $('#btn_ok').on('click', function(e) {
            e.preventDefault();
            var token = $("meta[name='_csrf']").attr("content");
            var headerName = $("meta[name='_csrf_header']").attr("content");
            var name = $('#input_name').val();
            var description = $('#input_description').val();
            var teacher_name = $('#teacher').val();
            var subject_name = $('#subject').val();

            $.ajax({
                type: "POST",
                url: "api/lections",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(headerName, token);
                },
                data: {
                    "name": name,
                    "description": description,
                    "teacher_name": teacher_name,
                    "subject_name": subject_name
                },
                success: function() {
                    location.reload();
                },
                error: function(e) {
                    alert(e);
                }
            })
        })
    })
});