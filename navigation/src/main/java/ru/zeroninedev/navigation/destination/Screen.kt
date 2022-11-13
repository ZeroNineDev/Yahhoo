package ru.zeroninedev.navigation.destination


/**
 * Class with manga destination
 *
 * @property route View route
 */
sealed class Screen(val route: String) {

    /**
     * Main program screen
     */
    class MainScreen : Screen(ROUTE) {
        companion object {
            /**
             * View route
             */
            const val ROUTE = "main_screen"
        }
    }

    /**
     * Screen with detail about manga
     */
    class MangaDetailScreen : Screen(ROUTE) {
        companion object {
            /**
             * View route
             */
            const val ROUTE = "detail_screen"
        }
    }

}