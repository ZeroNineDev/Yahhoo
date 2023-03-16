package com.zeroninedev.navigation.destination


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

    /**
     * Screen with detail about manga
     */
    class MangaChapterScreen : Screen(ROUTE) {
        companion object {
            /**
             * View route
             */
            const val ROUTE = "chapter_screen"
        }
    }

    /**
     * Screen with manga by category
     */
    class CategoryScreen : Screen(ROUTE) {
        companion object {
            /**
             * View route
             */
            const val ROUTE = "category_screen"
        }
    }

    /**
     * Screen with manga settings
     */
    class SettingScreen : Screen(ROUTE) {
        companion object {
            /**
             * View route
             */
            const val ROUTE = "setting_screen"
        }
    }
}