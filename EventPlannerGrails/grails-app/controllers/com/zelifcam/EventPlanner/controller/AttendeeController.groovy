package com.zelifcam.EventPlanner.controller

import com.zelifcam.EventPlanner.domain.Attendee
import com.zelifcam.EventPlanner.service.AttendeeService
import com.zelifcam.util.AppUtil

/**
 * REST-style MVC controller that exposes CRUD endpoints for {@link Attendee} instances.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Accept user input (via params) and delegate all persistence logic to {@link AttendeeService}.</li>
 *   <li>Translate the service’s response wrapper (<code>isSuccess</code>, <code>model</code>) into
 *       redirects and flash messages so the UI can react.</li>
 *   <li>Provide graceful handling for “not-found” entities.</li>
 * </ul>
 *
 * <p>The controller purposefully <b>does not</b> contain business logic;
 * all validation, filtering, and persistence are handled in the service layer.</p>
 *
 * @author Kevin Farias
 * @since 1.0
 */

class AttendeeController {

    AttendeeService attendeeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Show details of an attendee.
     *
     * @param id ID of the attendee to display
     */
    def show(Long id) {
        Attendee attendee = getAttendeeOrNotFound(id)
        if (!attendee) return

        respond attendee, model: [event: attendee.event]
    }

    /**
     * Render the edit form for an existing attendee.
     *
     * @param id ID of the attendee to edit
     * @return A model containing the attendee instance
     */
    def edit(Long id) {
        Attendee attendee = getAttendeeOrNotFound(id)
        if (!attendee) return

        [attendee: attendee]
    }

    /**
     * Save a new attendee.
     *
     * Delegates to the service layer and handles redirect and flash messaging.
     */

    def save() {
        def response = attendeeService.save(params)

        Long eventId = response.model?.event?.id ?:
                params.long('event.id')

        handleResponse(response,
                [controller: 'event', action: 'show', id: eventId],
                'attendee.registered.success',
                [controller: 'event', action: 'show', id: eventId],
                'attendee.registered.failed'
        )
    }

    /**
     * Update an existing attendee.
     *
     * Delegates update logic to service layer and handles redirects and flash messages.
     */
    def update() {
        Attendee attendee = getAttendeeOrNotFound(params.id)
        if (!attendee) return

        def response = attendeeService.update(attendee, params)

        handleResponse(response,
                [controller: 'event', action: 'show', id: attendee.event?.id],
                'attendee.updated.success',
                [action: 'edit', id: params.id],
                'attendee.updated.failed'
        )
    }

    /**
     * Delete an attendee.
     *
     * Delegates deletion to service layer and handles redirect and flash messaging.
     *
     * @param id ID of the attendee to delete
     */
    def delete(Long id) {
        Attendee attendee = getAttendeeOrNotFound(id)
        if (!attendee) return

        def response = attendeeService.delete(attendee)

        handleResponse(response,
                [controller: 'event', action: 'show', id: attendee.event?.id],
                'attendee.deleted.success',
                [action: 'show', id: id],
                'attendee.deleted.failed'
        )
    }

    // --------------------------------------------------------------------
    // Helper methods
    // --------------------------------------------------------------------

    /**
     * Retrieve an attendee by ID or redirect with a not-found message.
     *
     * @param id the attendee's primary key
     * @return the Attendee instance or <code>null</code> if not found
     */
    protected Attendee getAttendeeOrNotFound(Serializable id) {
        Attendee attendee = attendeeService.get(id)
        if (!attendee) {
            notFound()
            return null
        }
        return attendee
    }

    /**
     * Handle success/failure responses and perform redirects with appropriate flash messages.
     *
     * @param serviceResponse response from the service layer containing isSuccess flag and model
     * @param successRedirect redirect map for successful operations
     * @param successMessageCode i18n code for success flash message
     * @param failureRedirect redirect map for failed operations
     * @param failureMessageCode i18n code for failure flash message
     */
    protected void handleResponse(def serviceResponse,
                                  def successRedirect, String successMessageCode,
                                  def failureRedirect, String failureMessageCode) {

        if (serviceResponse?.isSuccess) {
            flash.message = AppUtil.infoMessage(g.message(code: successMessageCode))
            redirect successRedirect
            return
        }

        flash.message = AppUtil.infoMessage(g.message(code: failureMessageCode), false)
        redirect failureRedirect
    }

    /**
     * Flash a not-found message and redirect as fallback.
     */
    protected void notFound() {
        flash.message = AppUtil.infoMessage(g.message(code: 'attendee.not.found'), false)
        redirect controller: 'event', action: 'index'
    }
}