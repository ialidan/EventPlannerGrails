package com.zelifcam.util

/**
 * GlobalConfig is a utility class used to store application-wide configuration settings.
 *
 * This class provides static configuration values that can be reused throughout the application
 * to ensure consistency and centralized control over certain parameters.
 *
 * For example, pagination limits, feature toggles, or default settings can be placed here.
 */
class GlobalConfig {

    /**
     * Returns the default number of items to display per page in paginated views.
     *
     * This is typically used for pagination in list views.
     * By centralizing this value, it's easy to adjust pagination behavior across the entire app.
     *
     * @return the number of items to display per page (default is 6)
     */
    public static Integer itemsPerPage() {
        return 6
    }
}
