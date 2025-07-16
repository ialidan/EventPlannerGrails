<section class="row">
    <div id="list-event" class="col-12 content scaffold-list" role="main">

        <g:set var="filter" value="${params.filter ?: 'upcoming'}"/>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <g:form url="[controller: 'event', action: 'index']" method="GET" role="search">
            <div class="form-row">

                    <!-- Sort radios -->
                    <div class="col">
                        <span class="mr-2 font-weight-bold">Sort by:</span>

                        <label class="form-check form-check-inline mr-3">
                            <g:radio name="filter"
                                     value="upcoming"
                                     checked="${!params.filter || params.filter == 'upcoming'}"/>
                            <g:message code="label.event.upcoming" default="Upcoming Events"/>
                        </label>

                        <label class="form-check form-check-inline mr-3">
                            <g:radio name="filter"
                                     value="past"
                                     checked="${params.filter == 'past'}"/>
                            <g:message code="label.event.past" default="Past Events"/>
                        </label>
                    </div>

                    <!-- Search box -->
                    <div class="col">
                        <input type="text"
                               class="form-control"
                               placeholder="Search by name or location"
                               id="searchByText"
                               name="value"
                               value="${params.value ?: ''}"/>
                    </div>

                    <!-- Submit -->
                    <div class="col">
                        <button type="submit" class="btn btn-primary float-right">
                            <g:message code="button.applyParameters" default="Apply parameters"/>
                        </button>
                    </div>
                </div>
        </g:form>

        <g:if test="${eventList && eventList.size() > 0}">
            <div class="row row-cols-md-3">
                <g:each in="${eventList}" var="event">
                    <div class="col mb-4">
                        <g:render template="eventCard" model="[event: event]"/>
                    </div>
                </g:each>
            </div>
        </g:if>

        <g:else>
            <p>No events available</p>
        </g:else>


        <div class="pagination">
            <g:paginate total="${eventCount ?: 0}" />
        </div>


    </div>
</section>