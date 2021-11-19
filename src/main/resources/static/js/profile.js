console.log("hello");
const body = $('body');

body.on("click", "#edit", function (){
    $(`#editForm`).toggleClass("hidden")
});