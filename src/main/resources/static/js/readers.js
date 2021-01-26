$(function () {
   $('.button-delete').on('click', function () {
       var $buttonDelete = $(this);
       var idReader = $(this).val();

       $.ajax({
           type: "DELETE",
           url: "/deleteReader",
           dataType: "json",
           data: {idReader: idReader},
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
