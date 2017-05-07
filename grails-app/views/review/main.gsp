<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'review.label', default: 'Review')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div>
    <strong>Quick search</strong>
    <g:form url='[controller: "review", action: "search"]' id="searchForm" name="searchForm" method="get">
        <input type="text" name="query" value="${query ?: 'Keywords'}" onClick="javascript:if (this.value=='Keywords') { this.value='' }">
        <input type="image" src="${resource(dir:'images',file:'go_quick_search.png')}">
    </g:form>
</div>
</body>
</html>