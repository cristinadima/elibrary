function addCategory() {
    var name = document.getElementById("name").value;
    var description = document.getElementById("description").value;
    console.log([name, description].join('/'));
    var t = {};
    t["name"] = name;
    t["description"] = description;


    $.ajax({
        type: "POST",
        url: "/insertCategory",
        data: JSON.stringify(t),
        contentType: "application/json",
        beforeSend: function () {
            console.log("before request")
        },
        success: function (response) {
            console.log("success");

             //location.href = "./categories"
            //location.window.href = "categories";
            window.location.assign("./categories")
        }

    });


}

function editCategory() {
    var name = document.getElementById("name").value;
    var description = document.getElementById("description").value;
    console.log([name, description].join('/'));
    var t = {};
    t["name"] = name;
    t["description"] = description;


    $.ajax({
        type: "POST",
        url: "/updateCategory",
        data: JSON.stringify(t),
        contentType: "application/json",
        beforeSend: function () {
            console.log("before request")
        },
        success: function (response) {
            console.log("success");

            //location.href = "./categories"
            //location.window.href = "categories";
            window.location.assign("./categories")
        }

    });
}