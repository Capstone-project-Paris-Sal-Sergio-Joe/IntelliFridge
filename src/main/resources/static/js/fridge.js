$(document).ready(function () {

    $('#foodTable').DataTable({
        "paging": false,
        "info": false
    });

    // add food image after name in table
    $('.food-name').each(async function () {
            let fridgeImage = $(this).text()
            let fridgeImageUrl = await imageCode(fridgeImage);
            $(this).append(`
            <img src="${fridgeImageUrl}">
        `)


        }
    )

    $('.expirationDate').each(() => {

    })

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