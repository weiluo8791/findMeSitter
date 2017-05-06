$(function () {
    $('#daycareModal').off().on('show.bs.modal', function (event) {
        console.log('daycareModal fired!');
        var button = $(event.relatedTarget), // Button that triggered the modal
            daycareId = button.data('daycare-id'), // Extract info from data-* attributes
            daycareName = button.html();
        $('#daycareModalLabel').html(daycareName); // we already have the title, from the page itself, so just use it
        $.fn.stars = function() {
            return this.each(function(i,e){$(e).html($('<span/>').width($(e).text()*16));});
        };

        var request = $.ajax({
            //url:'/dayCareCenter/_show/'+daycareId, // for HTML response
            url: '/dayCareCenter/show/' + daycareId + '.json', // for JSON response
            method: 'GET'
        });
        request.done(function (data) {
            console.log("daycareModal ajax success");
            //$('#daycareModalContent').html(data); //used for handling HTML response
            // the following are used when calling for json and processing the individual data members
            $('#daycare-name').html(data.name);
            $('#daycare-address').html(data.address);
            $('#daycare-city').html(data.city);
            $('#daycare-state').html(data.state);
            $('#daycare-zip').html(data.zip);
            $('#daycare-email').html(data.email);
            $('#daycare-phone').html(data.phone);
            $('#daycare-capacity').html(data.capacity);
            $('#daycare-rate').html(data.rate);
            $('#daycare-other').html(data.other);

            if (data.reviews) {
                var reviewList = $('<ul id="reviewList" style="list-style-type: none;"/>');
                $.each(data.reviews, function(index, item) {
                    reviewList.append('<li><a href="/review/show/'+item.reviewId+'">'+item.reviewTitle+'</a>    ' +
                        '<span class="stars">' + item.reviewStars  + '</span>' +
                        (item.reviewIsRecommended ? '<img src="/assets/thumbsup.png" alt="thumbsup" height="16" width="16">' : '<img src="/assets/thumbsdown.png" alt="thumbsdown" height="16" width="16">') + '</li>   ');
                        //' Recommended: '+ item.reviewIsRecommended + '</li>');
                    reviewList.append('<li>Detail: ' +item.reviewDetail + '</li>');
                    reviewList.append('<li>Other: ' +item.reviewOtherDetail + '</li>');
                    reviewList.append('<br>');
                });
                $('#daycare-reviews').html(reviewList);
                $('.stars').stars();
            }

            $('#daycare-delete-form').attr('action', '/dayCareCenter/delete/' + data.id);
            $('#daycare-edit-link').attr('href', '/dayCareCenter/edit/' + data.id);
        });
        request.fail(function (jqXHR, textStatus) {
            console.log("daycareModal fail - jqXHR=" + jqXHR + ", textStatus=" + textStatus);
            $('#daycareModalContent').html('<p>Could not retrieve details for ' + daycareName + ' with id = ' + daycareId + '<p>');
        });

    });
    $('#daycareModal').on('hidden.bs.modal', function () {
        window.location.reload();
    });

});