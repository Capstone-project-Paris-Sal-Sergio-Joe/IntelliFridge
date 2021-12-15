const body = $('body');

body.on("click", "#edit", function (){
    $(`#editForm`).toggleClass("hidden")
});


body.on("click", ".addFormButton", function (){
    let id = $(this).attr("data-id");
    $(`#addUserForm${id}`).toggleClass("hidden");
});

body.on("click", "#addFridgeBtn", function () {
   $(`#addFridgeBtn`).toggleClass("hidden");
});

body.on("click", "#addFridgeBtn", function (){
    $(`#addFridgeForm`).toggleClass("hidden");
});

$('#cancel').click(() => location.reload());

setTimeout(()=> {
    $('#backendErrorProfileMessage').fadeOut('slow');
}, 5000 );

setTimeout(()=> {
    $('#errorM').fadeOut('slow');
}, 5000 );

setTimeout(()=> {
    $('#add-success').fadeOut('slow');
}, 5000 );
