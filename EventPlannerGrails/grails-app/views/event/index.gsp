<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">

            <g:render template="eventNav"/>

            <h1><g:message code="default.list.label" args="[entityName]" /></h1>

            <g:render template="eventList"/>

        </div>
    </div>
    </body>
</html>