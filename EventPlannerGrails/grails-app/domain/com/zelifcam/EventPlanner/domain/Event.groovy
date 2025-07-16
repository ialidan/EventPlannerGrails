package com.zelifcam.EventPlanner.domain

/**
 * Domain class representing an event that attendees can register for.
 *
 * <p>Properties:</p>
 * <ul>
 *   <li><code>name</code>: Name of the event (required, max 50 characters).</li>
 *   <li><code>location</code>: Location of the event (required).</li>
 *   <li><code>eventDate</code>: Date and time of the event (required, must be in the future).</li>
 *   <li><code>description</code>: Optional description of the event (max 500 characters).</li>
 *   <li><code>capacity</code>: Optional maximum number of attendees allowed (nullable, min 1 if set).</li>
 * </ul>
 *
 * <p>Associations:</p>
 * <ul>
 *   <li>Has many {@link Attendee} instances.</li>
 * </ul>
 *
 * <p>Validation:</p>
 * <ul>
 *   <li><code>eventDate</code> must be a future date.</li>
 *   <li><code>capacity</code> must be either null (unlimited) or at least the number of current attendees.</li>
 * </ul>
 *
 * @author Kevin Farias
 * @since 1.0
 */
class Event {
    String name
    String location
    Date eventDate
    String description
    Integer capacity

    static hasMany = [attendees: Attendee]

    static constraints = {
        name(nullable: false, blank: false, maxSize: 50)
        location(nullable: false, blank: false)
        eventDate(nullable: false, blank: false, validator: { date ->
            if (date <= new Date()) {
                return ['event.invalid.date.message']
            }
            return true
        })
        description(nullable: true, maxSize: 500)
        capacity nullable: true, min: 1, validator: { Integer cap, Event ev ->
            if (cap == null) return true // unlimited capacity

            int attendeeCount = ev.attendees?.size() ?: 0
            if (cap < attendeeCount) {
                return ['event.capacity.too.small', attendeeCount]
            }
            return true
        }
    }

    /**
     * Checks if the event is full based on capacity and current number of attendees.
     *
     * @return true if capacity is set and number of attendees is greater or equal; false otherwise
     */
    boolean isFull() {
        capacity != null && attendees?.size() >= capacity
    }

    /**
     * Returns the event’s name as its string representation.
     */
    String toString() {
        return name
    }
}