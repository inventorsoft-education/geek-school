$(document).ready(function() {

    var table = $('#user_data');

    function loadUsers() {
        $.ajax({
            type: 'GET',
            url: 'api/users/admin',
            dataType: 'json',
            cache: false,
            traditional: true,
            success: function(response) {
                var source = document.getElementById("entry-template").innerHTML;
                var template = Handlebars.compile(source);
                var sortResponse = response.sort(function(a, b) {
                    return a.id - b.id;
                });
                table.empty();
                for (let i = 0; i < sortResponse.length; i++) {

                    table.append(template(sortResponse[i]));
                    var Toggle = document.getElementById(sortResponse[i].id);
                    var ToggleD = document.getElementById(sortResponse[i].username);

                    if (sortResponse[i].status == "ACTIVE") {
                        Toggle.checked = true;
                        ToggleD.checked = false;
                    } else if (sortResponse[i].status == "DELETED") {
                        Toggle.checked = false;
                        Toggle.disabled = true;
                        ToggleD.checked = true;
                    } else {
                        Toggle.checked = false;
                        Toggle.disabled = false;
                        ToggleD.checked = false;
                    }
                }
            }
        });
    }

    function updateUser(idBtn, status) {
        var token = $("meta[name='_csrf']").attr("content");
        var headerName = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: 'PUT',
            url: 'api/users/' + idBtn,
            beforeSend: function(xhr) {
                xhr.setRequestHeader(headerName, token);
            },
            data: {
                'status': status
            },
            success: function() {
                loadUsers();
            }
        })
    }
    loadUsers();
    $(table).on('click', '#checkbox_user', function() {
        var idBtn = $(this).attr("rel");
        var status;
        var Toggle = document.getElementById(idBtn);
        if (!Toggle.disabled) {
            if (Toggle.checked) {
                console.log("on");
                status = "ACTIVE";
            } else {
                console.log("off");
                status = "NOT_ACTIVE";
            }
        }

        updateUser(idBtn, status);
    });
    $(table).on('click', '#checkbox_delete_user', function() {
        var idToggle = $(this).attr("rel");
        var idRow = $(this).attr("resource");
        var status;
        var Toggle = document.getElementById(idToggle);
        if (Toggle.checked) {
        console.log("on");
                    Toggle.checked = true;
                    status = "DELETED";

        } else {
            console.log("off");
                        Toggle.checked = false;
                        status = "NOT_ACTIVE";
        }

        updateUser(idRow, status);

    });

    $.ajax({
        type: 'GET',
        url: 'api/users/admin/status',
        dataType: 'json',
        cache: false,
        traditional: true,
        success: function(response) {
            var source = document.getElementById("status-template").innerHTML;
            var template = Handlebars.compile(source);
            for (let i = 0; i < response.length; i++) {
                $('#status_list').append(template(response[i]));
            }
        }
    });
});