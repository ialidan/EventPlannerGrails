package com.zelifcam.EventPlanner.service

import com.zelifcam.EventPlanner.domain.Attendee
import com.zelifcam.EventPlanner.domain.Event
import com.zelifcam.util.AppUtil
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import groovy.util.logging.Slf4j

/**
 * Service layer for {@link Attendee} domain objects.
 *
 * <p>All public methods run in a single transactional context thanks to
 * {@code @Transactional}.  Any unchecked exception will roll back the DB
 * changes automatically.  The service returns a simple
 * <em>response‑map</em>—see {@link AppUtil#saveResponse(boolean, Object)}—
 * so controllers can treat success and failure uniformly.</p>
 *
 * <h2>Typical calls</h2>
 * <pre>
 * def attendee = attendeeService.get(42)          // fetch
 * def resp     = attendeeService.save(params)     // insert (with validation)
 * def resp     = attendeeService.update(a, params)// update
 * def resp     = attendeeService.delete(a)        // delete
 * </pre>
 */
@Transactional
@Slf4j
class AttendeeService {

    /**
     * Fetches a single {@link Attendee} by its primary key.
     *
     * @param id the database identifier
     * @return the matching attendee, or {@code null} if not found
     */
    Attendee get(Serializable id) {
        return Attendee.get(id)
    }

    /**
     * Permanently removes an attendee.
     *
     * <p>The delete call is wrapped in a {@code try/catch} so any persistence
     * exception (foreign‑key constraints, etc.) is converted into a structured
     * response rather than bubbling out of the service layer.</p>
     *
     * @param attendee the instance to delete
     * @return response map where {@code isSuccess == true} only if the delete
     *         succeeds
     */
    def delete(Attendee attendee) {
        def response = AppUtil.saveResponse(false, attendee)

        try {
            attendee.delete(flush: true)
            response.isSuccess = true
            println("FAKE EMAIL sent to ${attendee.email} for removal from event")
        } catch (Exception e) {
            log.warn("Failed to delete Attendee ${attendee?.id}", e)
        }

        return response
    }

    /**
     * Creates and persists a new attendee for the target event.
     *
     * @param params request parameters; must include {@code event.id},
     *               {@code fullName}, {@code email}, etc.
     * @return response map containing the attendee (on success) or
     *         {@code null} (on failure)
     */
    def save(GrailsParameterMap params) {
        Event event = Event.get(params.long('event.id'))

        if (!event || event.isFull()) {
            return AppUtil.saveResponse(false, null)
        }

        Attendee attendee = new Attendee(params)
        attendee.event = event

        def response = AppUtil.saveResponse(false, attendee)

        if (attendee.validate()) {
            attendee.save(flush: true)
            response.isSuccess = true
            println("FAKE EMAIL sent to ${attendee.email} for event ${event.id}")
        }

        return response
    }

    /**
     * Updates an existing attendee with new field values.
     *
     * @param attendee the instance to modify (may be {@code null})
     * @param params   request parameters containing updated values
     * @return response map; {@code model} holds the (possibly invalid)
     *         attendee, and {@code isSuccess} is {@code true} only after a
     *         successful validation &amp; save
     */
    def update(Attendee attendee, GrailsParameterMap params) {
        if (!attendee) {
            return AppUtil.saveResponse(false, null)
        }

        attendee.properties = params
        def response = AppUtil.saveResponse(false, attendee)

        if (attendee.validate()) {
            attendee.save(flush: true)
            response.isSuccess = true
        }

        return response
    }
}