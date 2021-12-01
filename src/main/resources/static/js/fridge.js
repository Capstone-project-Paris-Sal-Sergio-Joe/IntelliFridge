console.log("hey there")


$('.foodName').each(function() {

        console.log($(this).html())
    }
)

// function imageCode(query) {
//     let baseUrl = 'https://api.unsplash.com/search/photos?client_id=';
//     let endPoint = '&query=';
//     return fetch(baseUrl + UnsplashApiKey + endPoint +  encodeURIComponent(query))
//         .then(function(res) {
//             return res.json();
//         }).then(function(data) {
//             console.log(data)
//             return data.results[0].urls.thumb;
//         });
// }