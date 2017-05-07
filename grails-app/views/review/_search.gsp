<%@ page import="org.springframework.util.ClassUtils" %>
<g:if test="${searchResults}">
    <g:each var="result" in="${searchResults}">
        <g:set var="className" value="${org.springframework.util.ClassUtils.getShortName(result.class)}" />
        <g:set var="link" value="${createLink(controller: className[0].toLowerCase() + className[1..-1], action: 'show', id: result.id)}" />
        <li><a href="${link}">${className}: ${result.id}</a></li>
    </g:each>
</g:if>