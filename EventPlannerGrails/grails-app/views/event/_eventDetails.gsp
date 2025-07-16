<g:set var="ev" value="${event}"/>

<g:set var="remaining"
       value="${ev.capacity != null ? ev.capacity - (ev.attendees?.size() ?: 0) : null}"/>

<div class="card shadow-sm mb-3 w-100">

    <div class="card-body">
        <h3 class="card-title mb-2">${ev.name?.encodeAsHTML()}</h3>

        <p class="card-text">
            ${ev.description ? ev.description.encodeAsHTML() : 'no description provided'}
        </p>

        <ul class="list-unstyled small mb-2">
            <li>
                <i class="bi bi-geo-alt-fill me-1"></i>
                ${ev.location?.encodeAsHTML()}
            </li>
            <li>
                <i class="bi bi-calendar3 me-1"></i>
                <g:formatDate date="${ev.eventDate}" format="MMM d, yyyy • HH:mm"/>
            </li>
        </ul>

        <hr/>

        <p class="mb-1">
            <strong>
                Attendees
                <g:if test="${ev.capacity != null}">
                    (${ev.attendees?.size() ?: 0} / ${ev.capacity})
                </g:if>
                <g:else>
                    (${ev.attendees?.size() ?: 0})
                </g:else>
                <g:if test="${remaining != null}">
                    &nbsp;—&nbsp;<em>${remaining} seat${remaining == 1 ? '' : 's'} left</em>
                </g:if>
            </strong>
            <g:if test="${ev.attendees && !ev.attendees.isEmpty()}">
                <g:each in="${ev.attendees}" var="att" status="idx">
                    <span class="badge bg-light text-dark me-1">
                        <g:link controller="attendee"
                                action="show"
                                id="${att.id}"
                                class="text-decoration-none">
                            ${att.fullName?.encodeAsHTML()}
                        </g:link>
                    </span>
                    <g:if test="${idx < ev.attendees.size() - 1}">, </g:if>
                </g:each>
            </g:if>
            <g:else>
                No attendees yet
            </g:else>
        </p>
    </div>

    <g:if test="${actions}">
        <div class="card-footer text-center bg-white border-0">
            <g:link class="btn btn-outline-primary btn-sm me-2"
                    action="edit" id="${ev.id}">Edit</g:link>

            <g:form resource="${ev}" method="DELETE" class="d-inline">
                <g:actionSubmit
                        class="btn btn-outline-danger btn-sm"
                        action="delete"
                        value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </g:form>
        </div>
    </g:if>
</div>