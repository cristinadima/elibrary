function addBook() {
    var authorsList = document.getElementById("author");
    var selectedAuthor = authorsList.options[authorsList.selectedIndex].value;
    var categoriesList = document.getElementById("category");
    var selectedCategory = categoriesList.options[categoriesList.selectedIndex].value;
    var title = document.getElementById("title").value;
    var publisher = document.getElementById("publisher").value;
    console.log([selectedAuthor, selectedCategory, title, publisher].join('/'));
    // alert([selectedAuthor, selectedCategory, title,publisher].join('/'));
    var t = {};
    t["title"] = title;
    t["idAuthor"] = selectedAuthor;
    t["idCategory"] = selectedCategory;
    t["publisher"] = publisher;

    $.ajax({
        type: "POST",
        url: "/insertBook",
        data: JSON.stringify(t),
        contentType: "application/json",
        beforeSend: function () {
            console.log("before request")
        },
        success: function (response) {
            console.log("success");
           // location.href = "./books"
        }

    });
}

/*function editBook() {
    var authorsList = document.getElementById("author");
    var selectedAuthor = authorsList.options[authorsList.selectedIndex].value;
    var categoriesList = document.getElementById("category");
    var selectedCategory = categoriesList.options[categoriesList.selectedIndex].value;
    var title = document.getElementById("title").value;
    var publisher = document.getElementById("publisher").value;
    var id = document.getElementById("idBook").value;
   // console.log([selectedAuthor, selectedCategory, title, publisher].join('/'));
    // alert([selectedAuthor, selectedCategory, title,publisher].join('/'));
    var t = {};
    t["idBook"] = id;
    t["title"] = title;
    t["idAuthor"] = selectedAuthor;
    t["idCategory"] = selectedCategory;
    t["publisher"] = publisher;

    $.ajax({
        type: "POST",
        url: "/updateBook",
        data: JSON.stringify(t),
        contentType: "application/json",
        beforeSend: function () {
            console.log("before request")
        },
        success: function (response) {
            console.log("success");
            location.href = "/books.html"
        }

    });
}*/
