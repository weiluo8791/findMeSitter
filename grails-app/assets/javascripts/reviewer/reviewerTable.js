$(function() {
    $('#reviewerModal').off().on('show.bs.modal', function (event) {
        console.log('reviewerModal fired!');
        var button = $(event.relatedTarget), // Button that triggered the modal
            reviewerId = button.data('reviewer-id'), // Extract info from data-* attributes
            reviewerName = button.html();
        console.log(reviewerName);
        $('#reviewerModalLabel').html(reviewerName); // we already have the title, from the page itself, so just use it
        $.fn.stars = function() {
            return this.each(function(i,e){$(e).html($('<span/>').width($(e).text()*16));});
        };
        var request = $.ajax({
            //url:'/reviewerCenter/_show/'+reviewerId, // for HTML response
            url: '/reviewer/show/' + reviewerId + '.json', // for JSON response
            method: 'GET'
        });
        request.done(function (data) {
            console.log("reviewerModal success");
            console.log(data);
            //$('#reviewerModalContent').html(data); //used for handling HTML response
            // the following are used when calling for json and processing the individual data members
            $('#reviewer-name').html(data.name);
            $('#reviewer-gender').html(data.gender);
            $('#reviewer-city').html(data.city);
            $('#reviewer-state').html(data.state);
            $('#reviewer-first').html(data.first);
            $('#reviewer-latest').html(data.latest);
            $('#reviewer-total').html(data.total);

            if (data.reviews) {
                var reviewList = $('<ul id="reviewList" style="list-style-type: none;"/>');
                $.each(data.reviews, function(index, item) {
                    reviewList.append('<li>DayCare  : <a href="/dayCareCenter/show/'+item.reviewDayCareCenterId+'">'+item.reviewDayCareCenterName+'</a>');
                    reviewList.append('<li>Review : <a href="/review/show/'+item.reviewId+'">'+item.reviewTitle+'</a>    ' +
                        '<span class="stars">' + item.reviewStars  + '</span>' +
                        (item.reviewIsRecommended ? '<img src="/assets/thumbsup.png" alt="thumbsup" height="16" width="16">' : '<img src="/assets/thumbsdown.png" alt="thumbsdown" height="16" width="16">') + '</li>   ');
                    reviewList.append('<li>Review Detail : ' +item.reviewDetail + '</li>');
                    reviewList.append('<li>Other Detail :  ' +item.reviewOtherDetail + '</li>');
                    reviewList.append('<br>');
                });
                $('#reviewer-reviews').html(reviewList);
                $('.stars').stars();
            }

            $('#reviewer-delete-form').attr('action','/reviewer/delete/'+data.id);
            $('#reviewer-edit-link').attr('href','/reviewer/edit/'+data.id);
        });
        request.fail(function (jqXHR, textStatus) {
            console.log("reviewerModal fail - jqXHR="+jqXHR + ", textStatus="+textStatus);
            $('#reviewerModalContent').html('<p>Could not retrieve details for ' + reviewerName + ' with id = ' + reviewerId + '<p>');
        });

    });
});