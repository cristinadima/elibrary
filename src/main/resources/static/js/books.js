
$(function () {
    console.log("called load");
   $('.button-delete').on('click', function () {
       var $buttonDelete = $(this);
       console.log("called delete");
       var idBook = $(this).val();

       $.ajax({
           type: "DELETE",
           url: "/deleteBook",
           dataType: "json",
           data: {idBook: idBook},
           beforeSend: function () {
               console.log("before request")
           },
           success: function (response) {
               console.log("success");
               //location.href = "/books.html"
               console.log($buttonDelete.val());
               $($buttonDelete).parents("tr").remove();
           }

       });
   })
});

function deleteBook(id) {

}
