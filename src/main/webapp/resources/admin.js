$(document).ready(function () {
    $("#divtable").dataTable({
        "sAjaxSource": "/datatables/div",
        "bJQueryUI": true,
        "aoColumns": [
            { "mDataProp": "id" },
            { "mDataProp": "name" },
            {   "sDefaultContent": "",
                "bSortable": false,

                "fnRender": function (o) {

                    return '<button class="btn-mini btn-warning" id="editBtn' + o.aData["id"] + '" value="' + o.aData["id"] + '" data-toggle="modal">Edit</button>'
                }
            },

            {   "sDefaultContent": "",
                "bSortable": false,
                "fnRender": function (o) {
                    return '<input type="button" id="delete/' + o.aData["id"] + '" class="btn btn-danger btn-mini"  value="Delete" data-toggle="confirmation" data-href="/div/delete/' + o.aData["id"] + '" />'
                }
            }

        ],
        "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_ records per page"
        },
        "iDisplayLength": 5,
        "aLengthMenu": [
            [5, 10, 25, -1],
            [5, 10, 25, "All"]
        ],
        "bProcessing": true,
        "fnDrawCallback": function () {
            $("[id^=delete]").confirmation({singleton: true, popout: true});


        }
    });

});

jQuery(document).ready(function () {
    jQuery("#editForm").submit(function (e) {
        jQuery(".error").remove();
        jQuery.ajax({
            url: "/div/update",
            context: document.body,
            type: 'post',
            data: jQuery(this).serialize()

        }).done(function (res) {
                if (res.status === "ERROR") {
                    for (var key in res.errorsMap) {
                        var err = "<span class=\"error\" id=\"" + key + "Id\">" + res.errorsMap[key] + "</span>";
                        jQuery("[name^='" + key + "']").after(err);
                    }
                } else {
                    /*jQuery("#msg").html("Form submitted");*/
                    $('#myModal').modal('hide');
                    var $divtable = $("#divtable").dataTable({ bRetrieve: true });
                    $divtable.fnReloadAjax();
                }
            }).fail(function (data) {
                jQuery("#msg").html("<span class=\"error\">Server failed to process request</span>");
            });
        return false;
    });
});

$(document).on("click", "#btnNew", function () {
    document.getElementById("editForm").reset();

    $('#myModal').modal({
        keyboard: false
    })
});

$(document).on("click", "[id^=editBtn]", function () {


    jQuery.getJSON("/div/" + $(this).val(), function (data) {
        $('#myModal').modal({
            keyboard: false
        })
        $('#id').val(data.id);
        $('#name').val(data.name);

    });
})

$('#myModal').on('hidden', function () {
    $(this).removeData('.modal');
})
