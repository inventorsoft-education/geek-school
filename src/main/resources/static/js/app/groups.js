$(document).ready(function() {
                $.ajax({
                      type: 'GET',
                      url: 'api/groups/admin',
                      dataType: 'json',
                      cache: false,
                      traditional: true,
                      success: function(response) {
                      var source   = document.getElementById("group-template").innerHTML;
                      var template = Handlebars.compile(source);
                      for (let i = 0; i < response.length; i++) {
                            $('#input_group').append(template(response[i]));
                        }
                 }
                 })
                 var token = $("meta[name='_csrf']").attr("content");
                 var headerName = $("meta[name='_csrf_header']").attr("content");
                 $("#add_student").on('click', function() {
                  var user_name = $('#name_user').val();
                  var group_id = $('#group_id').attr("name");
                        $.ajax({
                            type: 'PUT',
                            url: 'api/groups/admin/add',
                            beforeSend: function(xhr) {
                                  xhr.setRequestHeader(headerName, token);
                            },
                            data: {
                            'group_id': group_id,
                            'username': user_name
                            },
                            success: function() {
                            }
                        })
                    })
                  $("#delete_student").on('click', function() {
                  var user_name = $('#name_user').val();
                  var group_id = $('#group_id').attr("name");
                        $.ajax({
                            type: 'DELETE',
                            url: 'api/groups/admin/' + group_id,
                            beforeSend: function(xhr) {
                                xhr.setRequestHeader(headerName, token);
                            },
                            data: {
                            'username': user_name
                            },
                            success: function() {
                            }
                        })
                })
})