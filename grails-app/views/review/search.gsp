<%@ page import="org.springframework.util.ClassUtils" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <g:set var="entityName" value="${message(code: 'review.label', default: 'Review')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-searchResult" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="list-searchResult" class="content scaffold-list" role="main">
    <g:render template="search"/>

        %{--<g:each in="${searchResults}" var="result">--}%
            %{--<g:set var="className" value="${org.springframework.util.ClassUtils.getShortName(result.class)}" />--}%
            %{--<g:set var="link" value="${createLink(controller: className[0].toLowerCase() + className[1..-1], action: 'show', id: result.id)}" />--}%
            %{--<li><a href="${link}">${className}: ${result.id}</a></li>--}%
        %{--</g:each>--}%
</div>
</body>
</html>