<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Find Me a Sitter</title>
</head>
<body>

<div class="png" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="family-1976162_1280.png" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Find Me a Sitter</h1>
        <g:form url='[controller: "review", action: "search"]' id="searchForm" name="searchForm" method="get">
            <div class="input-group">
                <input type="text" name="query" size="64" placeholder="${query ?: 'Find Review, Day Care Provider, Location'}" onClick="javascript:if (this.value=='Find Review, Day Care Provider, Location') { this.value='' }" class="form-control">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit">Go!</button>
                </span>
            </div>
        </g:form>
        <ul>
            <li>If you are are Day Care Provider <g:link controller="dayCareCenter">Enter Here</g:link></li>
            <li><g:link controller="reviewer">Log in as Reviewer</g:link></li>
            <li><g:link view="review/main">Search Review</g:link></li>
        </ul>
    </section>
</div>

</body>
</html>