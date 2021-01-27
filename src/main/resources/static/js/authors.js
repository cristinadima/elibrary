$(function () {
   $('.button-delete').on('click', function () {
       var $buttonDelete = $(this);
       var idAuthor = $(this).val();

       $.ajax({
           type: "DELETE",
           url: "/deleteAuthor",
           dataType: "json",
           data: {idAuthor: idAuthor},
           success: function (response) {
               console.log($buttonDelete.val());
               $($buttonDelete).parents("tr").remove();

               console.log($('#authors-table').find("tr").length.toString());
               if ($('#authors-table').find("tr").length == 1){
                   $('#empty-authors').removeClass('display-none')
               }
           }

       });
   })
});
