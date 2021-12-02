$(document).ready(function () {

    // initialize datatable
    $('#foodTable').DataTable();

    $('.food-name').each(async function () {
            let fridgeImage = $(this).text()
            let fridgeImageUrl = await imageCode(fridgeImage);
            $(this).append(`
            <img src="${fridgeImageUrl}">
        `)

        }
    )

    function imageCode(query) {
        let baseUrl = 'https://api.unsplash.com/search/photos?client_id=';
        let endPoint = '&query=';
        return fetch(baseUrl + UnsplashApiKey + endPoint + encodeURIComponent(query))
            .then(function (res) {
                return res.json();
            }).then(function (data) {
                return data.results[0].urls.thumb;
            });
    }
});