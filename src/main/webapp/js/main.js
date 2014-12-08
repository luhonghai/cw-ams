$( document ).ready(function() {
    function parseTableRow(row) {
        var btnCommands = [];
        btnCommands.push('<button type="button" action="edit" target="' + row.id + '" class="btn btn-warning table-action">');
        btnCommands.push('<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>');
        //btnCommands.push('Edit');
        btnCommands.push('</button>');
        btnCommands.push('<button type="button" action="delete" target="' + row.id + '" class="btn btn-danger table-action">');
        btnCommands.push('<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>');
        //btnCommands.push('Delete');
        btnCommands.push('</button>');
        row.command = btnCommands.join("");
        return row;
    }

    var $dataTable = $("#data-table");
    var $rootDataTable;
    var tableTarget;
    var tableData;

    function getFormData() {
        $('#txtId').removeAttr('disabled');
        if (tableTarget == 'qualification') {
            return {
                "id" : $("#txtId").val(),
                "name" : $("#txtName").val(),
                "description" : $("#txtDescription").val()
            }
        } else if (tableTarget == 'job') {
            return {
                "id" : $("#txtId").val(),
                "name": $("#txtName").val(),
                "description" : $("#txtDescription").val(),
                "numberOfEngineer" :$("#txtEngineerNumber").val(),
                "qualification" : {
                    "id" : $("#selQualification").val()
                },
                "status" : {
                    "id" : $("#selStatus").val()
                },
                "priority" : {
                    "id" : $("#selPriority").val()
                }
            }
        } else if (tableTarget == 'user') {
            return {
                "id" : $("#txtId").val(),
                "firstName" : $("#txtFirstname").val(),
                "lastName" : $("#txtLastname").val(),
                "email" : $("#txtEmail").val(),
                "password" : $("#txtPassword").val(),
                "username" : $("#txtUsername").val(),
                "gender" : $("#rdMale").is(":checked")
            }
        }
    }

    function setupFormData(data) {
        if (tableTarget == 'qualification') {
            $("#txtName").val(data.name);
            $("#txtDescription").val(data.description);
        } else if (tableTarget == 'job') {
            $("#txtName").val(data.name);
            $("#txtDescription").val(data.description);
            $("#txtEngineerNumber").val(data.numberOfEngineer);
            if (typeof data.qualification != 'undefined') {
                $("#selQualification").val("" + data.qualification.id);
            }
            if (typeof data.priority != 'undefined') {
                $("#selPriority").val("" + data.priority.id);
            }
            if (typeof data.status != 'undefined') {
                $("#selStatus").val("" + data.status.id);
            }
        } else if (tableTarget == 'user') {
            $("#txtFirstname").val(data.firstName);
            $("#txtLastname").val(data.lastName);
            $("#txtEmail").val(data.email);
            $("#txtUsername").val(data.username);
            $(data.gender ? "#rdMale" : "#rdFemale").attr("checked", "checked");
        }
    }

    function clearFormData() {
        if (tableTarget == 'qualification') {
            $("#txtName").val("");
            $("#txtDescription").val("");
        } else if (tableTarget == 'job') {
            $("#txtName").val("");
            $("#txtDescription").val("");
            $("#txtEngineerNumber").val("");

        } else if (tableTarget == 'user') {
            $("#txtFirstname").val("");
            $("#txtLastname").val("");
            $("#txtEmail").val("");
            $("#txtUsername").val("");
            $("#txtPassword").val("");
            $("#rdMale").attr("checked", "checked");
        }
    }

    function getDataById(id) {
        if (typeof tableData == 'undefined' && typeof tableData.data == 'undefined') return null;
        var i;
        for (i = 0; i < tableData.data.length; i++) {
            if (tableData.data[i].id == id) {
                return tableData.data[i];
            }
        }
    }

    function addData(data) {
        if (typeof tableData == 'undefined') return;
        if (typeof tableData.data == 'undefined') {
            tableData.data = [];
        }
        tableData.data.push(data);

    }

    function deleteData(id) {
        if (typeof tableData == 'undefined' && typeof tableData.data == 'undefined') return;
        var i;
        var index = -1;
        for (i = 0; i < tableData.data.length; i++) {
            if (tableData.data[i].id == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            tableData.data.splice(index, 1);
        }
    }

    function updateData(data) {
        if (typeof tableData == 'undefined' && typeof tableData.data == 'undefined') return;
        var i;
        for (i = 0; i < tableData.data.length; i++) {
            if (tableData.data[i].id == data.id) {
                tableData.data[i] = data;
                break;
            }
        }
    }

    function parseTableData(data) {
        data.columns.push({title:"", data:"command", className: "table-command"});
        if (typeof data.data == 'undefined') return data;
        var i;
        for (i = 0; i < data.data.length; i++) {
            data.data[i] = parseTableRow(data.data[i]);
        }
        return data;
    }


    if ($dataTable.length != 0) {
        tableTarget = $dataTable.attr("table-data");
        $.ajax({
            type: "POST",
            url: app.contextPath + "handler/data",
            data: {
                type: "list",
                target: tableTarget
            }
        }).done(function( resp ) {
            if (typeof resp != 'undefined' && resp.length > 0) {
                var data = JSON.parse(resp);
                if (typeof data.message != 'undefined' && data.message=="Completed") {
                    tableData = parseTableData(data);
                    $rootDataTable = $dataTable.DataTable( tableData );
                }
            }
        });

    }

    $(document).click(function(event) {
        var $target = $(event.target);
        if ($target.hasClass("table-action")) {
            var tAction = $target.attr("action");
            var tTarget = $target.attr("target");
            if (tAction == 'delete') {
                swal({
                        title: "Are you sure?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "Yes, delete it!",
                        cancelButtonText: "Cancel",
                        closeOnConfirm: false,
                        closeOnCancel: true
                    },
                    function (isConfirm) {
                        if (isConfirm) {
                            var requestData = JSON.stringify(getDataById(tTarget));
                            $.ajax({
                                type: "POST",
                                url: app.contextPath + "handler/data",
                                data: {
                                    type: "delete",
                                    target: tableTarget,
                                    data: requestData
                                }
                            }).done(function( resp ) {
                                if (typeof resp != 'undefined' && resp.length > 0) {
                                    var data = JSON.parse(resp);
                                    var message = ""|data.message;
                                    if (typeof data.message != 'undefined' && data.message=="Completed") {
                                        $rootDataTable.row($target.parents('tr')).remove().draw();
                                        deleteData(tTarget);
                                        swal("Deleted successfully!", "", "success");
                                    } else {
                                        swal("Error!", message, "warning");
                                    }
                                } else {
                                    swal("Error!", "", "warning");
                                }
                            });

                        }
                 });
            } else if (tAction == 'edit') {
                var $form = $("#dataModelForm");
                $form.attr("act", "update");
                $("#dataModelTitle").html("Update " + $("#dataModelTitle").attr("target"));
                $('#dataModal').modal('show');
                $('#txtId').removeAttr('disabled');
                $('#txtId').val(tTarget);
                setupFormData(getDataById(tTarget));
                $('#txtId').attr('disabled','disabled');
                $('#dataModelID').show();
            }
        } else if ($target.hasClass("table-add-new")) {
            var $form = $("#dataModelForm");
            $form.attr("act", "create");
            $("#dataModelTitle").html("Add new " + $("#dataModelTitle").attr("target"));
            $('#dataModelID').hide();
            clearFormData();
        } else if ($target.hasClass("dataModelSave")) {
            var $form = $("#dataModelForm");
            var formData = getFormData();
            if (typeof formData.id == 'undefined' || formData.id == "") {
                formData.id = 0;
            }
            var requestData = JSON.stringify(formData);
            var tType = $form.attr("act");
            $.ajax({
                type: "POST",
                url: app.contextPath + "handler/data",
                data: {
                    type: tType,
                    target: tableTarget,
                    data: requestData
                }
            }).done(function( resp ) {
                $('#dataModal').modal('hide');
                if (typeof resp != 'undefined' && resp.length > 0) {

                    var data = JSON.parse(resp);
                    var message = ""|data.message;
                    if (typeof data.message != 'undefined' && data.message=="Completed" && typeof data.object != 'undefined') {
                        var obj = data.object;
                        if (tType == 'update') {
                            var $btnTarget = $('button[target=' + obj.id + ']');
                            $rootDataTable.row($btnTarget.parents('tr')).remove();
                            updateData(obj);
                            swal("Updated successfully!", "", "success");
                        } else if (tType == 'create') {
                            addData(obj);
                            swal("Created successfully!", "", "success");
                        } else {
                            swal("Completed!", "", "success");
                        }
                        $rootDataTable.row.add(parseTableRow(obj)).draw();
                    } else {
                        swal("Error!", message, "warning");
                    }

                } else {
                    swal("Error!", "", "warning");
                }
            });
        }

    });

    $("#user-profile").click(function() {
        $.ajax({
            type: "POST",
            url: app.contextPath + "handler/data",
            data: {
                target: 'current_user',
                type: 'get',
                option: 'profile'
            }
        }).done(function( resp ) {
            if (typeof resp != 'undefined' && resp.length > 0) {
                $("#modalProfile").modal("show");
                var data = JSON.parse(resp);
                $("#pTxtFirstname").val(data.firstName);
                $("#pTxtLastname").val(data.lastName);
                $("#pTxtUsername").val(data.username);
                $("#pTxtId").val(data.id);
                $("#pTxtEmail").val(data.email);
                if (data.gender) {
                    $("#pRdMale").attr("checked", "checked");
                } else {
                    $("#pRdFemale").attr("checked", "checked");
                }
            } else {
                swal("Error!", "Could not load profile", "warning");
            }
        });
    });

    $(".modalProfileSave").click(function() {
        var requestData = JSON.stringify({
            "id" : $("#pTxtId").val(),
            "firstName" : $("#pTxtFirstname").val(),
            "lastName" : $("#pTxtLastname").val(),
            "email" : $("#pTxtEmail").val(),
            "password" : $("#pTxtPassword").val(),
            "username" : $("#pTxtUsername").val(),
            "gender" : $("#rdMale").is(":checked")
        });
        $.ajax({
            type: "POST",
            url: app.contextPath + "handler/data",
            data: {
                type: "update",
                target: "user",
                data: requestData
            }
        }).done(function( resp ) {
            $("#modalProfile").modal("hide");
            if (typeof resp != 'undefined' && resp.length > 0) {
                var data = JSON.parse(resp);
                var message = ""|data.message;
                if (typeof data.message != 'undefined' && data.message=="Completed" && typeof data.object != 'undefined') {
                    swal("Updated successfully!", "", "success");
                } else {
                    swal("Error!", message, "warning");
                }
            } else {
                swal("Error!", "", "warning");
            }
        });
    });
});