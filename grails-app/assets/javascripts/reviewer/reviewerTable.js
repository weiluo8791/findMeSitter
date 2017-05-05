$(function() {
    $('#reviewerModal').off().on('show.bs.modal', function (event) {
        console.log('reviewerModal fired!');
        var button = $(event.relatedTarget), // Button that triggered the modal
            reviewerId = button.data('reviewer-id'), // Extract info from data-* attributes
            reviewerName = button.html();
        console.log(reviewerName);
        $('#reviewerModalLabel').html(reviewerName); // we already have the title, from the page itself, so just use it

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
            $('#reviewer-delete-form').attr('action','/reviewer/delete/'+data.id);
            $('#reviewer-edit-link').attr('href','/reviewer/edit/'+data.id);
        });
        request.fail(function (jqXHR, textStatus) {
            console.log("reviewerModal fail - jqXHR="+jqXHR + ", textStatus="+textStatus);
            $('#reviewerModalContent').html('<p>Could not retrieve details for ' + reviewerName + ' with id = ' + reviewerId + '<p>');
        });

    });
});