console.log(UnsplashApiKey)
console.log("hello world")
$(document).ready(function() {
    $('#foodSearch').keyup(function() {
        let search = $(this).val();

        $('.foodItem').each( async function () {
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
                $('#addedFood').append(
                    `<p>Fridge or Freezer?</p>
    <input type="radio" name="isInFreezer" value="false" text="Fridge" checked="checked" id="fridgeOption">
    <label for="fridgeOption">Fridge</label>
    <input type="radio" name="isInFreezer" value="true" text="Freezer" id="freezerOption">
    <label for="freezerOption">Freezer</label>
`
                )
                $('#foodResults').html('');
            })


        })
    })
    $(document).on("keydown", "form", function(event) {
        return event.key != "Enter";
    });

    // JOE API CODE


    function imageCode(query) {
        let baseUrl = 'https://api.unsplash.com/search/photos?client_id=';
        let endPoint = '&query=';
        return fetch(baseUrl + UnsplashApiKey + endPoint +  encodeURIComponent(query))
            .then(function(res) {
                return res.json();
            }).then(function(data) {
                console.log(data)
                return data.results[0].urls.thumb;
            });
    }

});