console.log("hello");
const body = $('body');

body.on("click", "#edit", function (){
    $(`#editForm`).toggleClass("hidden")
});

// Nice to have unsubscribe user

// $(`#unsubscribe`).click(function(){
//     if(window.confirm('are you sure you want to unsubscribe?'))
//     {
//         console.log("they clicked yes")
//     } else{
//         console.log("they clicked no")
//     }
// });