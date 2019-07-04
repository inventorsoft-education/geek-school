$(document).ready(function() {
                $("#load_table").click(function() {
                    $.ajax({
                        type: 'GET',
                        url: 'api/users/admin',
                        dataType: 'json',
                        cache: false,
                        traditional: true,
                        success: function(response) {
                            $("#load_table").prop("disabled", true);
                            var source   = document.getElementById("entry-template").innerHTML;
                            var template = Handlebars.compile(source);
                            for (let i = 0; i < response.length; i++) {
                                $('#user_data').append(template(response[i]));
                            }
                        }
                      })
                      $.ajax({
                        type: 'GET',
                        url: 'api/users/admin/status',
                        dataType: 'json',
                        cache: false,
                        traditional: true,
                        success: function(response) {
                            var source   = document.getElementById("status-template").innerHTML;
                            var template = Handlebars.compile(source);
                            for (let i = 0; i < response.length; i++) {
                                $('#status_list').append(template(response[i]));
                            }
                        }
                      })

                            $('#my_table').on('click', '#btn_change', function() {
                                var idBtn = $(this).attr("data");
                                $('#change_status').show();
                                var token = $("meta[name='_csrf']").attr("content");
                                var headerName = $("meta[name='_csrf_header']").attr("content");

                                $('#change_status').on('click', '#btn_change_status', function() {
                                    var status = $('#state').val();
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
                                        }
                                    })
                                })
                        })
                    });
                });
