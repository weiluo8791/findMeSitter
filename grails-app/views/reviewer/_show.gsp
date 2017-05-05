<ol class="property-list reviewer">

    <li class="fieldcontain">
        <span id="name-label" class="property-label">Name:</span>
        <div class="property-value" aria-labelledby="description-label" id="reviewer-name"></div>
    </li>

    <li class="fieldcontain">
        <span id="gender-label" class="property-label">Gender:</span>
        <div class="property-value" aria-labelledby="gender-label" id="reviewer-gender"></div>
    </li>

    <li class="fieldcontain">
        <span id="city-label" class="property-label">City:</span>
        <div class="property-value" aria-labelledby="city-label" id="reviewer-city"></div>
    </li>

    <li class="fieldcontain">
        <span id="state-label" class="property-label">State:</span>
        <div class="property-value" aria-labelledby="state-label" id="reviewer-state"></div>
    </li>

    <li class="fieldcontain">
        <span id="first-label" class="property-label">First Review:</span>
        <div class="property-value" aria-labelledby="first-label" id="reviewer-first"></div>
    </li>

    <li class="fieldcontain">
        <span id="latest-label" class="property-label">Latest Review:</span>
        <div class="property-value" aria-labelledby="latest-label" id="reviewer-latest"></div>
    </li>

    <li class="fieldcontain">
        <span id="total-label" class="property-label">Total Review:</span>
        <div class="property-value" aria-labelledby="total-label" id="reviewer-total"></div>
    </li>

</ol>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_USER">
    <form id="reviewer-delete-form" action="/reviewer/delete/${reviewer?.id}" method="post" >
        <input type="hidden" name="_method" value="DELETE" id="_method" />
        <fieldset class="buttons">
            <a id="reviewer-edit-link" href="/reviewer/edit/${reviewer?.id}" class="edit">Edit</a>
            <input class="delete" type="submit" value="Delete" onclick="return confirm('Are you sure?');" />
        </fieldset>
    </form>
</sec:ifAnyGranted>
