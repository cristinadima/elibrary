$(function () {
    $('.button-delete').on('click', function () {
        var $buttonDelete = $(this);
        var idCat = $(this).val();

        $.ajax({
            type: "DELETE",
            url: "/deleteCategory",
            dataType: "json",
            data: {idCat: idCat},
            success: function (response) {
                console.log($buttonDelete.val());
                $($buttonDelete).parents("tr").remove();

                console.log($('#readers-table').find("tr").length.toString());
                if ($('#readers-table').find("tr").length == 1){
                    console.log("if");
                    $('#empty-readers').removeClass('display-none')
                }
            }

        });
    })
});
