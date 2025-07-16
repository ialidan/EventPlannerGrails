package com.zelifcam.util

/**
 * Utility class that provides standardized response formatting for service and controller layers.
 *
 * <p>This class simplifies returning consistent response maps from services and building user-facing
 * flash messages for display in the UI.</p>
 *
 * Example usage:
 * <pre>
 * def result = AppUtil.saveResponse(true, event)
 * flash.message = AppUtil.infoMessage("Event created successfully.")
 * </pre>
 *
 * @author Kevin Farias
 * @since 1.0
 */
class AppUtil {

    /**
     * Wraps a success flag and a domain model (or any object) into a common response structure.
     *
     * @param isSuccess whether the operation was successful
     * @param model the object to return (often a domain class like Event or Attendee)
     * @return a map with keys <code>isSuccess</code> and <code>model</code>
     */
    static saveResponse(Boolean isSuccess, def model) {
        return [isSuccess: isSuccess, model: model]
    }

    /**
     * Creates a flash message structure used to communicate success or failure in the UI.
     *
     * @param message the message string to display
     * @param status optional boolean (default: <code>true</code>), indicates if the operation succeeded
     * @return a map with keys <code>info</code> and <code>success</code>
     */
    static infoMessage(String message, boolean status = true) {
        return [info: message, success: status]
    }
}
