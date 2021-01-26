$(function () {
   $('.button-delete').on('click', function () {
       var $buttonDelete = $(this);
       var idBorrow = $(this).val();

       $.ajax({
           type: "DELETE",
           url: "/deleteBorrow",
           dataType: "json",
           data: {idBorrow: idBorrow},
           success: function (response) {
               console.log($buttonDelete.val());
               $($buttonDelete).parents("tr").remove();

               console.log($('#readers-table').find("tr").length.toString());
               if ($('#borrows-table').find("tr").length == 1){
                   console.log("if");
                   $('#empty-borrows').removeClass('display-none')
               }
           }

       });
   })
});
