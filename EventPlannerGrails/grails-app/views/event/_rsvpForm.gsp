<g:set var="eid" value="${eventId}"/>

<div class="card shadow-sm mb-3 w-100">
    <div class="card-body">
        <h3 class="acard-title mb-3">RSVP to this Event</h3>

        <g:form controller="attendee" action="save">
            <g:hiddenField name="event.id" value="${eid}"/>

            <div class="mb-3">
                <label for="fullName" class="form-label">Your Name</label>
                <g:textField name="fullName" id="fullName" class="form-control" placeholder="Enter your full name" required="true"/>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <g:textField name="email" id="email" type="email" class="form-control" placeholder="name@example.com" required="true"/>
            </div>

            <button type="submit" class="btn btn-primary btn-sm">RSVP</button>
        </g:form>
    </div>
</div>