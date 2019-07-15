$(document).ready(function () {
    function loadCourses() {
        $.ajax({
            type: 'GET',
            url: 'api/courses-template',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function (response) {
                var source = document.getElementById("entry").innerHTML;
                var template = Handlebars.compile(source);
                var sortResponse = response.sort(function (a, b) {
                    return a.id - b.id;
                });
                $('#courses_list').empty();
                for (let i = 0; i < sortResponse.length; i++) {
                    $('#courses_list').append(template(sortResponse[i]));
                }
            }
        });
    };

    function loadActiveCourses() {
        $.ajax({
            type: 'GET',
            url: 'api/courses',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function (response) {
                var source = document.getElementById("script_active_course").innerHTML;
                var template = Handlebars.compile(source);
                var sortResponse = response.sort(function (a, b) {
                    return a.id - b.id;
                });
                $('#active_courses_list').empty();
                for (let i = 0; i < sortResponse.length; i++) {
                    $('#active_courses_list').append(template(sortResponse[i]));
                }
            }
        });
    };

    function createCourseOnTheTemplate(id_course_template, start_time_stamp, end_time_stamp) {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'POST',
            url: '/api/courses',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            data: {
                'id_course_template': id_course_template,
                'creation_date': start_time_stamp,
                'end_date': end_time_stamp
            },
            success: function () {
                loadActiveCourses();
            }
        })
    }

    function deleteCourse(id) {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'DELETE',
            url: "/api/courses/" + id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            success: function () {
                loadActiveCourses();
            }
        });
    }

    loadCourses();
    loadActiveCourses()

    $('#courses_list').on('click', '#eventer', function () {
        $(this).toggleClass('image-fliper');
    });

    $('#courses_list').on('click', '#btn_create_course', function () {
        var id_course_template = $(this).attr("rel");

        $('.modal').modal('show');

        $('#btn_ok').on('click', function(e) {
            var start_time_stamp = $('#start_time').val();
            var end_time_stamp = $('#end_time').val();
            createCourseOnTheTemplate(id_course_template, start_time_stamp, end_time_stamp);
            $('.modal').modal('hide');
        });
    });

    //active
    $('#active_courses_list').on('click', '#eventer_active', function () {
        $(this).toggleClass('image-fliper');
    });

    $('#active_courses_list').on('click', '#btn_delete_course', function () {
        var id_delete_course = $(this).attr("rel");
        deleteCourse(id_delete_course);
    });
});
