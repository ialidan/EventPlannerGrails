<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">

            <g:render template="eventNav"/>

            <section class="row">
                <div id="create-event" class="col-12 content scaffold-create" role="main">
                    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>

                    <g:hasErrors bean="${this.event}">
                        <ul class="errors" role="alert">
                            <g:eachError bean="${this.event}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                            </g:eachError>
                        </ul>
                    </g:hasErrors>
                    <g:form resource="${this.event}" method="POST">
                        <fieldset class="form">
                            <f:field bean="event" property="name"/>
                            <f:field bean="event" property="location"/>
                            <div class="fieldcontain">
                              <label for="eventDate">Event Date</label>
                              <g:datePicker name="eventDate" value="${new Date()}"/>
                            </div>

                            <f:field bean="event" property="description"/>
                            <f:field bean="event" property="capacity"/>
                        </fieldset>
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
