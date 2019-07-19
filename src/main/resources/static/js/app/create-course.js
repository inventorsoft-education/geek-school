$(document).ready(function () {
    var idLections =[];
    function loadLectionThisCourse(id) {
        $.ajax({
            type: 'GET',
            url: '/api/courses-template/' + id,
            dataType: 'json',
            cache: false,
            success: function (response) {
                var source = document.getElementById("entry").innerHTML;
                var template = Handlebars.compile(source);
                var sortResponse = response.sort(function (a, b) {
                    return a.id - b.id;
                });
                $('#lection_list').empty();
                for (let i = 0; i < sortResponse.length; i++) {
                    $('#lection_list').append(template(sortResponse[i]));
                    idLections.push(sortResponse[i].id);
                }
            }
        });
    };
    function createCourseOnTheTemplate(courseLectionDto) {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: 'POST',
            url: '/api/courses',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            dataType : "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(courseLectionDto),
            success: function () {
                window.location = "http://localhost:8080/courses";
            }, error: function (e) {
                alert(e);
            }
        })
    }
    var url = window.location.pathname;
    var idCourse = url.substring(url.lastIndexOf('/') + 1);
    loadLectionThisCourse(idCourse);

    $('#add_course_btn').on('click', function () {
        var lectionListDto = [];
        for (let i = 0; i < idLections.length; i++) {
            var start_time = $('#start_time' + idLections[i]).val();
            var end_time = $('#end_time' + idLections[i]).val();
            var lectionDateDto = {
                'idLection': idLections[i],
                'startDate': start_time,
                'endDate': end_time
            };
            lectionListDto.push(lectionDateDto);
        }
        var courseLectionDto = {
            'id': idCourse,
            'lectionDateDtoList':lectionListDto
        };
        createCourseOnTheTemplate(courseLectionDto);
    });
});