package com.zelifcam.EventPlanner.service

import com.zelifcam.EventPlanner.domain.Event
import com.zelifcam.util.AppUtil
import com.zelifcam.util.GlobalConfig
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import groovy.util.logging.Slf4j

/**
 * Service layer for {@link Event} domain objects.
 *
 * <p>This class sits between controllers and the GORM‑managed domain model,
 * keeping data‑access logic in one place and making it easier to unit‑test the
 * web layer.  Because the class is marked {@code @Transactional}, every public
 * method executes inside a single transaction boundary—write operations will
 * automatically roll back if an unhandled exception occurs.</p>
 *
 * <h2>Usage</h2>
 * <pre>
 * def results = eventService.list(params)   // paginated query
 * def event   = eventService.get(42)        // single record
 * def resp    = eventService.save(params)   // insert
 * def resp    = eventService.update(event, params) // update
 * def resp    = eventService.delete(event)  // delete
 * </pre>
 */
@Transactional
@Slf4j
class EventService {

    /**
     * Fetches a single {@link Event} by its primary key.
     *
     * @param id the database identifier (usually supplied by the controller)
     * @return the matching {@code Event}, or {@code null} if none exists
     */
    Event get(Serializable id) {
        return Event.get(id)
    }

    /**
     * Retrieves a paginated, optionally filtered list of events.
     *
     * <p>The following request parameters are recognised:</p>
     *
     * <table>
     *   <thead>
     *     <tr><th>Parameter</th><th>Description</th></tr>
     *   </thead>
     *   <tbody>
     *     <tr><td>{@code max}</td>
     *         <td>Maximum rows per page (defaults to {@link GlobalConfig#itemsPerPage()}).</td></tr>
     *     <tr><td>{@code offset}</td>
     *         <td>Row offset for pagination (automatically supplied by Grails taglibs).</td></tr>
     *     <tr><td>{@code value}</td>
     *         <td>Free‑text search applied (case‑insensitive) to {@code name} and {@code location}.</td></tr>
     *     <tr><td>{@code filter}</td>
     *         <td>{@code "past"}&nbsp;⇒ events whose {@code eventDate}&nbsp;&lt;&nbsp;now;<br>
     *             {@code "upcoming"}&nbsp;⇒ events whose {@code eventDate}&nbsp;≥&nbsp;now.<br>
     *             Anything else (or omitted) disables date filtering.</td></tr>
     *   </tbody>
     * </table>
     *
     * @param params Grails parameter map forwarded from the controller
     * @return a map containing:
     *   <ul>
     *     <li>{@code list}  – the current page of {@code Event} instances</li>
     *     <li>{@code count} – total row count for the criteria (for pagination controls)</li>
     *   </ul>
     */
    def list(GrailsParameterMap params) {
        params.max = params.int('max') ?: GlobalConfig.itemsPerPage()
        List<Event> eventList = Event.createCriteria().list(params) {
            if (params.value) {
                or {
                    ilike 'name',     "%${params.value}%"
                    ilike 'location', "%${params.value}%"
                }
            }

            def now = new Date()
            if (params.filter) {
                if (params.filter == 'past') {
                    lt('eventDate', now)  // eventDate < now
                } else if (params.filter == 'upcoming') {
                    ge('eventDate', now)  // eventDate >= now
                }
            }

            order('eventDate', 'asc')
        }
        return [list: eventList, count: eventList.totalCount]
    }

    /**
     * Permanently removes an {@link Event} from the database.
     *
     * <p>The delete is wrapped in a {@code try/catch} so constraint‑violation
     * exceptions (e.g. foreign‑key dependencies) do not propagate beyond the
     * service layer.  A structured response is returned instead.</p>
     *
     * @param event the domain instance to delete
     * @return a response map in the format produced by
     *         {@link AppUtil#saveResponse(boolean, Object)}, where
     *         {@code isSuccess} is {@code true} only if the delete succeeds
     */
    def delete(Event event) {
        def response = AppUtil.saveResponse(false, event)

        try {
            event.delete(flush: true)
            response.isSuccess = true
        } catch (Exception e) {
            log.warn("Failed to delete Event ${event.id}", e)
        }

        return response
    }

    /**
     * Creates and persists a new {@link Event}.
     *
     * @param params request parameters (typically from an HTML form)
     * @return a response map indicating validation/insert success and
     *         containing the {@code Event} instance
     */
    def save(GrailsParameterMap params) {
        Event event = new Event(params)
        def response = AppUtil.saveResponse(false, event)

        if (event.validate()) {
            event.save(flush: true)
            response.isSuccess = true
        }

        return response
    }

    /**
     * Updates an existing {@link Event} with data from the request.
     *
     * @param event  the existing domain instance (usually loaded by the controller)
     * @param params request parameters containing updated field values
     * @return a response map indicating validation/update success and
     *         containing the modified {@code Event} instance
     */
    def update(Event event, GrailsParameterMap params) {
        if (!event) {
            return AppUtil.saveResponse(false, null)
        }

        event.properties = params
        def response = AppUtil.saveResponse(false, event)

        if (event.validate()) {
            event.save(flush: true)
            response.isSuccess = true
        }

        return response
    }
}