$(document).ready(function () {
    function loadLection() {
        $.ajax({
            type: 'GET',
            url: 'api/lections',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function (response) {
                var source = document.getElementById("entry").innerHTML;
                var template = Handlebars.compile(source);
                var sortResponse = response.sort(function (a, b) {
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
            success: function (response) {
                var source = document.getElementById(elementById).innerHTML;
                var template = Handlebars.compile(source);
                $(putId).empty();
                for (let i = 0; i < response.length; i++) {
                    $(putId).append(template(response[i]));
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
            beforeSend: function (xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            data: {
                'teacher_name': teacher_id
            },
            success: function () {
                loadLection();
            }
        })
    };

    function updateDateCourse(courseLectionDto) {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'PUT',
            url: '/api/lections',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(courseLectionDto),
            success: function () {
                loadLection();
            }, error: function (e) {
                alert(e);
            }
        });
    };

    function deleteLection(id) {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'DELETE',
            url: "/api/lections/" + id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            success: function () {
                loadLection();
            }
        });
    };

    loadLection();

    var isReady = true;
    $('#lection_list').on('click', '#eventer', function () {
        if (isReady) {
            $(this).toggleClass('image-fliper');
        }
    });

    $('#lection_list').on('click', '#btn_delete_lection', function () {
        var id_delete_lection = $(this).attr("rel");
        deleteLection(id_delete_lection);
    });

    $("#lection_list").on('click', "#btn_change_date", function () {
        var id_lection = $(this).attr("rel");
        var id_course = $(this).attr("value");

        var date_course = '#date_course' + id_lection + id_course;

        var start_time_lection = '#start_time_active' + id_lection;
        var end_time_lection = '#end_time_active' + id_lection;

        var p_create = $('#p_create_date' + id_course + id_lection).text();
        var p_end = $('#p_end_date' + id_course + id_lection).text();

        $(start_time_lection).val(p_create);
        $(end_time_lection).val(p_end);

        if ($(end_time_lection).is(":visible")) {
            isReady = true;
            $(date_course).show();
            $(start_time_lection).hide();
            $(end_time_lection).hide();
        } else {
            isReady = false;
            $(date_course).hide();
            $(start_time_lection).show();
            $(end_time_lection).show();
        }

        $('#lection_list').on('click', '#btn_save', function () {

            var start_time_stamp = $(start_time_lection).val();
            var end_time_stamp = $(end_time_lection).val();
            var lectionDateDto = {
                'idLection': id_lection,
                'startDate': start_time_stamp,
                'endDate': end_time_stamp
            };
            var lectionListDto = [];
            lectionListDto.push(lectionDateDto);

            var courseLectionDto = {
                'id': id_course,
                'lectionDateDtoList':lectionListDto
            };
            isReady = true;
            updateDateCourse(courseLectionDto);
        });
    });

    $("#lection_list").on('click', "#btn_change_teacher", function () {
        var id_lection = $(this).attr("rel");

        var description_id = '#descr' + id_lection;
        var input_teacher_id = '#tech' + id_lection;


        if ($(input_teacher_id).is(":visible")) {
            $(input_teacher_id).hide();
            $(description_id).show();
            isReady = true;
        } else {
            $(description_id).hide();
            $(input_teacher_id).show();

            isReady = false;
        }

        $('#lection_list').on('click', '#btn_save', function () {
            isReady = true;
            var teacher_id = $(input_teacher_id).val();

            updateTeacher(id_lection, teacher_id);
        });
    });

    $("#add_lection_btn").on('click', function () {
        $('.modal').modal('show');
        document.getElementById("add_lection_btn").style.visibility = "hidden";
        var elementById = "lection_teacher";
        var putId = '#input_teacher';
        loadTeacher(elementById, putId);

        $('#btn_cancel').on('click', function () {
            document.getElementById("add_lection_btn").style.visibility = "visible";
        });

        $('#btn_ok').on('click', function (e) {
            e.preventDefault();
            var token = $("meta[name='_csrf']").attr("content");
            var headerName = $("meta[name='_csrf_header']").attr("content");

            var name = $('#input_name').val();
            var description = $('#input_description').val();
            var teacher_name = $('#teacher').val();

            var lectionValid = {
                'name': name,
                'description': description,
                'teacherName': teacher_name
            }

            $.ajax({
                type: "POST",
                url: "api/lections",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(headerName, token);
                },
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(lectionValid),
                success: function () {
                    $('.modal').modal('hide');
                    document.getElementById("add_lection_btn").style.visibility = "visible";
                    loadLection();
                }
                ,
                error: function (e) {
                    alert(e);
                }
            })
        })
    })
});