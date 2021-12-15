$(document).ready(function () {

    //search for mobile view
    $('#searchMobileView').keyup(function() {
        $('#noFoodMessage').html('');
        let search = $(this).val().toLowerCase();
        let foodCount = $('.mobileViewFood').length;
        $('.mobileViewFood').each(function() {
            let foodName = $(this).attr('data-id');
            let regExp = new RegExp(`${search}`);
            if (search != '') {
                if (regExp.test(foodName) == true) {
                    $(this).css('display','block');
                    // foodCount += 1;
                } else {
                    $(this).css('display','none');
                    foodCount -= 1;
                }
            } else {
                $(this).css('display','block');
            }
        })

        if (foodCount === 0) {
            $('#noFoodMessage').html(`<h2 class="alert-danger">No matches...</h2>`)
        }


    })

    // sort by for mobile view
    $('#sortByMobileView').change(function(){
        var value = $(this).val();
        window.location.href = window.location.href.replace( /[\?#].*|\/name|\/dateAdded|\/expirationDate|$/, `/${value}`);
    });


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