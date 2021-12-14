$(document).ready(function() {

    $('#addFoodButton').click(() => {
        $('#addFoodForm').css('display','block')
    })

    $('#foodSearch').keyup(function() {
        let search = $(this).val().toLowerCase();
        $('.foodItem').each( async function () {
            let foodName = $(this).find('.foodName').text().toLowerCase();
            let regExp = new RegExp(`${search}`);
            if (search != '') {
                if (regExp.test(foodName) == true) {
                    let foodImageURL = await imageCode(foodName);
                    $(this).find('.foodImageURL').html(`
                            <div>
                                <img src="${foodImageURL}" class="img-thumbnail foodResultImage">
                            </div>
                           
                        `)
                    $(this).css('display', 'block');
                } else {
                    $(this).css('display', 'none');
                }
            } else {
                $(this).css('display', 'none');
            }

            $(this).click(function () {
                $(window).scrollTop(0);
                $('#foodSearch').val(foodName);
                $('#foodSearch').prop('readonly',true);
                $('#addedFood').html($(this).html() + `
    <p class="text text-center">Fridge or Freezer?</p>
    <div class="d-flex flex-row justify-content-around">
    <input type="radio" name="isInFreezer" value="false" text="Fridge" checked="checked" id="fridgeOption">
    <label for="fridgeOption">Fridge</label>
    <input type="radio" name="isInFreezer" value="true" text="Freezer" id="freezerOption">
    <label for="freezerOption">Freezer</label>
    </div>
    <button type="submit" class="btn btn-primary">Add Food</button>
    <button type="button" id="cancelAddFood" class="btn btn-danger">Cancel</button>
                `);
                $('#foodResults').html('');

                $('#cancelAddFood').click(() => location.reload());

            })
        })
    })







    // Prevents user from submitting form by pressing enter
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
                return data.results[0].urls.thumb;
            });
    }

});