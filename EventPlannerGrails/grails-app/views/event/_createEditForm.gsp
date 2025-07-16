<fieldset class="form">

    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <g:textField
            name="name"
            id="name"
            value="${event?.name}"
            class="form-control"
            maxlength="50"
            required="true"/>
    </div>

    <div class="mb-3">
        <label for="location" class="form-label">Location</label>
        <g:textField
            name="location"
            id="location"
            value="${event?.location}"
            class="form-control"
            maxlength="50"
            required="true"/>
    </div>

    <div class="mb-3">
        <label for="eventDate" class="form-label">Event Date</label>
        <g:datePicker
            name="eventDate"
            id="eventDate"
            value="${event?.eventDate ?: new Date()}"
            class="form-select"/>
    </div>

    <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <g:textArea
            name="description"
            id="description"
            value="${event?.description}"
            class="form-control"
            maxlength="500"
            rows="4"/>
    </div>

    <div class="mb-3">
            <label for="capacity" class="form-label">Capacity</label>
            <g:textField
                name="capacity"
                id="capacity"
                value="${event?.capacity}"
                class="form-control"
                type="number"
                rows="4"/>
    </div>

</fieldset>