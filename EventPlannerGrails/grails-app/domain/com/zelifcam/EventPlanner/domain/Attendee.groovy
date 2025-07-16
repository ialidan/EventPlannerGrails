package com.zelifcam.EventPlanner.domain

/**
 * Domain class representing an attendee registered for an {@link Event}.
 *
 * <p>Properties:</p>
 * <ul>
 *   <li><code>fullName</code>: The attendee’s full name (required, max 50 chars).</li>
 *   <li><code>email</code>: The attendee’s email address (required, valid email, max 50 chars).</li>
 *   <li><code>event</code>: The event this attendee is associated with (required).</li>
 * </ul>
 *
 * <p>Associations:</p>
 * <ul>
 *   <li>Belongs to an {@link Event} (many attendees can belong to one event).</li>
 * </ul>
 *
 * @author Kevin Farias
 * @since 1.0
 */

class Attendee {
    String fullName
    String email
    Event event

    static belongsTo = [event: Event]

    static constraints = {
        fullName(nullable: false, blank: false, maxSize: 50)
        email(nullable: false, blank: false, maxSize: 50, email: true)
        event(nullable: false)
    }

    /**
     * Returns the attendee’s full name as its string representation.
     */
    String toString() {
        return fullName
    }
}
