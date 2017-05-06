<ol class="property-list daycare">

    <li class="fieldcontain">
        <span id="name-label" class="property-label">Name:</span>
        <div class="property-value" aria-labelledby="description-label" id="daycare-name"></div>
    </li>

    <li class="fieldcontain">
        <span id="address-label" class="property-label">Address:</span>
        <div class="property-value" aria-labelledby="description-label" id="daycare-address"></div>
    </li>

    <li class="fieldcontain">
        <span id="city-label" class="property-label">City:</span>
        <div class="property-value" aria-labelledby="city-label" id="daycare-city"></div>
    </li>

    <li class="fieldcontain">
        <span id="state-label" class="property-label">State:</span>
        <div class="property-value" aria-labelledby="state-label" id="daycare-state"></div>
    </li>

    <li class="fieldcontain">
        <span id="zip-label" class="property-label">Zip Code:</span>
        <div class="property-value" aria-labelledby="zip-label" id="daycare-zip"></div>
    </li>

    <li class="fieldcontain">
        <span id="email-label" class="property-label">Email:</span>
        <div class="property-value" aria-labelledby="email-label" id="daycare-email"></div>
    </li>

    <li class="fieldcontain">
        <span id="phone-label" class="property-label">Telephone:</span>
        <div class="property-value" aria-labelledby="phone-label" id="daycare-phone"></div>
    </li>

    <li class="fieldcontain">
        <span id="capacity-label" class="property-label">Center Capacity:</span>
        <div class="property-value" aria-labelledby="capacity-label" id="daycare-capacity"></div>
    </li>

    <li class="fieldcontain">
        <span id="rate-label" class="property-label">Daily Rate:</span>
        <div class="property-value" aria-labelledby="rate-label" id="daycare-rate"></div>
    </li>

    <li class="fieldcontain">
        <span id="other-label" class="property-label">Other Details:</span>
        <div class="property-value" aria-labelledby="other-label" id="daycare-other"></div>
    </li>

</ol>

<div class="property-value" aria-labelledby="reviews-label" id="daycare-reviews"></div>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_USER">
    <form id="daycare-delete-form" action="/dayCareCenter/delete/${daycare?.id}" method="post">
        <input type="hidden" name="_method" value="DELETE" id="_method"/>
        <fieldset class="buttons">
            <a id="daycare-edit-link" href="/dayCareCenter/edit/${daycare?.id}" class="edit">Edit</a>
            <input class="delete" type="submit" value="Delete" onclick="return confirm('Are you sure?');"/>
        </fieldset>
    </form>
</sec:ifAnyGranted>
