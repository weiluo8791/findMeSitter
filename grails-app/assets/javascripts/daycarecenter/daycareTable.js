$(function() {
    $('#daycareModal').on('show.bs.modal', function (event) {
        console.log('daycareModal fired!');
        var button = $(event.relatedTarget), // Button that triggered the modal
            daycareId = button.data('daycare-id'), // Extract info from data-* attributes
            daycareName = button.html();
        console.log(daycareName);
        $('#daycareModalLabel').html(daycareName); // we already have the title, from the page itself, so just use it

        var request = $.ajax({
            //url:'/dayCareCenter/_show/'+daycareId, // for HTML response
            url: '/dayCareCenter/show/' + daycareId + '.json', // for JSON response
            method: 'GET'
        });
        request.done(function (data) {
            console.log("daycareModal success");
            console.log(data);
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

            $('#daycare-delete-form').attr('action','/dayCareCenter/delete/'+data.id);
                $('#daycare-edit-link').attr('href','/dayCareCenter/edit/'+data.id);
        });
        request.fail(function (jqXHR, textStatus) {
            console.log("daycareModal fail - jqXHR="+jqXHR + ", textStatus="+textStatus);
            $('#daycareModalContent').html('<p>Could not retrieve details for ' + daycareName + ' with id = ' + daycareId + '<p>');
        });

    });
});