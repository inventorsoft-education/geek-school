$(document).ready(function() {
                $("#load_table").click(function() {
                    $.ajax({
                        type: 'GET',
                        url: 'api/users',
                        dataType: 'json',
                        cache: false,
                        traditional: true,
                        success: function(response) {
                            for (let i = 0; i < response.length; i++) {
                                $('#my_table').append(
                                    "<tr><td>" + response[i].username +
                                    "</td><td>" + response[i].email +
                                    "</td><td>" + response[i].role +
                                    "</td><td>" + response[i].status +
                                    "</td><td><button id='btn_change' data= '" + response[i].id + "' >Change</button></td></tr>");
                            }
                            $("#load_table").prop("disabled", true);

                            $('#my_table').on('click', '#btn_change', function() {
                                var idBtn = $(this).attr("data");

                                $('#change_status').show();
                                $('#change_status').on('click', '#btn_change_status', function() {
                                    var status = $('#state').val();
                                    $.ajax({
                                        type: 'PUT',
                                        url: 'api/users/' + idBtn,
                                        data: {
                                            'status': status
                                        },
                                        success: function() {
                                        }
                                    })
                                })
                            })
                        }
                    });
                });
            });