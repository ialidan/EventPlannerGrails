<div class="card w-100 h-100" style="width: 18rem;">
    <asset:image src="placeholder-1-1.png" alt="Placeholder"/>
    <div class="card-body">
        <h2 class="card-title">${event.name}</h2>
        <g:if test="${event.description}">
             <p class="card-text">${event.description}</p>
        </g:if>
        <g:else>
             <p class="card-text">No description provided</p>
        </g:else>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item text-center">Location: ${event.location}</li>
        <li class="list-group-item text-center">Date: <g:formatDate date="${event.eventDate}" format="MM-dd-yyyy HH:mm" /></li>
    </ul>
    <div class="card-body text-center">
         <g:link action="show" id="${event.id}">View details</g:link>
    </div>
</div>