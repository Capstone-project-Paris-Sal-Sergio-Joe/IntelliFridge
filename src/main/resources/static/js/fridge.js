$(document).ready(function () {

    $('#foodTable').DataTable({
        "paging": false,
        "info": false
    });

    // add food image after name in desktop view table
    $('.food-name').each(async function () {
            let fridgeImage = $(this).text()
            let fridgeImageUrl = await imageCode(fridgeImage);
                $(this).prepend(`
                <img src="${fridgeImageUrl}" class="foodResultImage img-thumbnail">
                <br>
            `)
        }
    )

    // add food image after name in mobile view table
    $('.foodNameMobile').each(async function () {
            let fridgeImage = $(this).text()
            let fridgeImageUrl = await imageCode(fridgeImage);
            $(this).prepend(`
                <img src="${fridgeImageUrl}" class="foodResultImage img-thumbnail m-2">
                <br>
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