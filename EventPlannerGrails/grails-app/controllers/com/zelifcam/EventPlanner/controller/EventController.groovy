package com.zelifcam.EventPlanner.controller

import com.zelifcam.EventPlanner.domain.Event
import com.zelifcam.EventPlanner.service.EventService
import com.zelifcam.util.AppUtil

/**
 * REST‑style MVC controller that exposes CRUD endpoints for {@link Event} instances.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Accept user input (via params) and delegate all persistence logic to {@link EventService}.</li>
 *   <li>Translate the service’s response wrapper (<code>isSuccess</code>, <code>model</code>) into
 *       redirects and flash messages so the UI can react.</li>
 *   <li>Provide graceful handling for “not‑found” entities.</li>
 * </ul>
 *
 * <p>The controller purposefully <b>does not</b> contain business logic;
 * all validation, filtering, and persistence are handled in the service layer.</p>
 *
 * @author  Kevin Farias
 * @since   1.0
 */
class EventController {

    EventService eventService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * List and optionally filter events.
     *
     * @param max maximum rows to return (passed in by Grails paging tag)
     * @return a model map with <code>eventList</code> and <code>eventCount</code>
     */
    def index(Integer max) {
        def response = eventService.list(params)
        [ eventList: response.list, eventCount: response.count ]
    }

    /**
     * Show a single event with attendee count.
     *
     * @param id ID of the event to display
     */
    def show(Long id) {
        Event event = getEventOrNotFound(id)

        if (!event) return

        respond event, model: [ attendeeCount: event.attendees.size() ]
    }

    /**
     * Render “new event” form, primed with any request params.
     */
    def create() {
        respond new Event(params)
    }

    /**
     * Persist a new event then redirect to its “show” page, or back to “create” on failure.
     */
    def save() {
        def response = eventService.save(params)

        handleResponse(response,
                [action: 'show', id: response.model.id], 'event.created.success',
                [action: 'create'], 'event.created.failed'
        )
    }

    /**
     * Render “edit” form for an existing event.
     *
     * @param id ID of the event to edit
     */
    def edit(Long id) {
        Event event = getEventOrNotFound(id)

        if (!event) return

        return [event: event]
    }

    /**
     * Update an existing event.
     *
     * Redirects to “show” on success or back to “edit” on failure.
     */
    def update() {
        Event event = getEventOrNotFound(params.id)

        if (!event) return

        def response = eventService.update(event, params)

        handleResponse(response,
                [action: 'show', id: params.id], 'event.updated.success',
                [action: 'edit', id: params.id], 'event.updated.failed'
        )
    }

    /**
     * Delete an event and redirect to the index list.
     */
    def delete() {
        Event event = getEventOrNotFound(params.id)

        if (!event) return

        def response = eventService.delete(event)

        handleResponse(response,
                [action: 'index'], 'event.deleted.success',
                [action: 'show', id: params.id], 'event.deleted.failed'
        )

    }

    // --------------------------------------------------------------------
    // Helper methods
    // --------------------------------------------------------------------

    /**
     * Push a “not found” flash message and redirect to the list view.
     * Called when {@link #getEventOrNotFound} fails.
     */
    protected void notFound() {
        flash.message = AppUtil.infoMessage(g.message(code: 'event.not.found'), false)
        redirect action: 'index'
    }

    /**
     * Retrieve an event or issue a 404 redirect.
     *
     * @param id the event’s primary key
     * @return the Event instance or <code>null</code> if absent
     */
    protected Event getEventOrNotFound(Serializable id) {
        Event event = eventService.get(id)

        if (!event) {
            notFound()
            return null
        }

        return event
    }

    /**
     * Centralised success/error handling to avoid duplicated redirect logic.
     *
     * @param serviceResponse response wrapper from the service layer
     * @param successRedirect map passed to {@code redirect} on success
     * @param successMessageCode i18n code for flash message on success
     * @param failureRedirect map passed to {@code redirect} on failure
     * @param failureMessageCode i18n code for flash message on failure
     */
    protected void handleResponse(def serviceResponse,
                                  def successRedirect, String successMessageCode,
                                  def failureRedirect, String failureMessageCode) {

        if (serviceResponse.isSuccess) {
            flash.message = AppUtil.infoMessage(g.message(code: successMessageCode))
            redirect successRedirect
            return
        }

        flash.message = AppUtil.infoMessage(g.message(code: failureMessageCode), false)
        redirect failureRedirect
    }
}
