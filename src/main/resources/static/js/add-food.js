console.log("hello world")
fetch('https://api.unsplash.com/search/photos?client_id=' + "0vWQD0zIliLfVCQmhxLcCFmdUg12C4A0-0JUgmc4qNg&query=cats")
    .then(res => res.json())
    .then(console.log)
    .catch(console.log)


function renderAddFoodImage(object){
    var html = '<div class="add-food-image">';
    html += `<img src="${object.results.url.small}" />`;
    html += `<small> +${object.results.user.name} </small>`;
    html += '</div>';

    return html;
}

let imageSelector = document.getElementById("foodName");

$(`#foodName`).on("keydown","",function (){
    console.log("a key was pressed");
});