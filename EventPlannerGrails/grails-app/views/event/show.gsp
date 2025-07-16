<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <g:render template="eventNav"/>

            <section class="row">
                <div id="show-event" class="col-12 content scaffold-show" role="main">

                    <h1><g:message code="default.show.label" args="[entityName]" /></h1>

                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>

                    <g:render template="eventDetails" model="[event: event, actions: true]"/>

                    <g:render template="rsvpForm" model="[eventId: event.id]"/>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
