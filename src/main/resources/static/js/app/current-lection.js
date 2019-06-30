$(document).ready(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var headerName = $("meta[name='_csrf_header']").attr("content");
    var id = window.location.pathname;
        $.ajax({
            type: 'GET',
            url: "/api" + id,
            beforeSend:
                        function(xhr) {
                                            xhr.setRequestHeader(headerName, token);
                                        },
            dataType: 'json',
            pathname: id,
            cache: false,
            traditional: true,
            success: function(response) {
                var source = document.getElementById("current-lection-entry").innerHTML;
                var template = Handlebars.compile(source);

                    $('#my_page').append(template(response));

            }, error: function(e) {
            alert(e);
            }
        })
});