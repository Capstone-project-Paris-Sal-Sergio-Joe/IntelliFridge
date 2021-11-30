$(document).ready(function() {
    $('#foodSearch').keyup(function() {
        let search = $(this).val();

        $('.foodItem').each(async function () {
            let foodName = $(this).find('.foodName').text();
            let regExp = new RegExp(`^${search}`);
            if (search != '') {
                if (regExp.test(foodName) == true) {
                    let foodImageURL = await imageCode(foodName);
                    $(this).find('.foodImageURL').html(`
                            <img src="${foodImageURL}">
                        `)
                    $(this).css('display', 'block');
                } else {
                    $(this).css('display', 'none');
                }
            } else {
                $(this).css('display', 'none');
            }

            $(this).click(function () {
                $('#foodSearch').val(foodName);
                $('#addedFood').html($(this).html());
                $('#foodResults').html('');
            })


        })
    })
    $(document).on("keydown", "form", function(event) {
        return event.key != "Enter";
    });

    // JOE API CODE

    let UNSPLASH_ACCESS_KEY = "0vWQD0zIliLfVCQmhxLcCFmdUg12C4A0-0JUgmc4qNg";

    function imageCode(query) {
        let baseUrl = 'https://api.unsplash.com/search/photos?client_id=';
        let endPoint = '&query=';
        return fetch(baseUrl + UNSPLASH_ACCESS_KEY + endPoint +  encodeURIComponent(query))
            .then(function(res) {
                return res.json();
            }).then(function(data) {
                return data.results[0].urls.thumb;
            });
    }

});