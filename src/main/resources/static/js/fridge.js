$(document).ready(function() {
$('.food-name').each(async function() {
        let fridgeImage = $(this).text()
            console.log(fridgeImage)
            let fridgeImageUrl = await imageCode(fridgeImage);
            $(this).next().html(`
            <img src="${fridgeImageUrl}">
        `)

    }
)

function imageCode(query) {
    let baseUrl = 'https://api.unsplash.com/search/photos?client_id=';
    let endPoint = '&query=';
    return fetch(baseUrl + UnsplashApiKey + endPoint +  encodeURIComponent(query))
        .then(function(res) {
            return res.json();
        }).then(function(data) {
            return data.results[0].urls.thumb;
        });
}
});