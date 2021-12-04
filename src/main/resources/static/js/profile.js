console.log("hello");
const body = $('body');

body.on("click", "#edit", function (){
    $(`#editForm`).toggleClass("hidden")
});


body.on("click", ".addFormButton", function (){
    let id = $(this).attr("data-id");
    $(`#addUserForm${id}`).toggleClass("hidden");
});