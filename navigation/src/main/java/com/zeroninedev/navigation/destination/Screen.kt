package com.zeroninedev.navigation.destination

/**
 * Class with manga destination
 *
 * @property route View route
 */
sealed class Screen(val route: String) {

    /**
     * Screen with detail about manga
     */
    class MangaDetailScreen : Screen(ROUTE) {

        companion object {

            /**
             * View route
             */
            const val ROUTE = "detail_screen/{mangaId}"

            fun getRoute(mangaId: String): String = "detail_screen/$mangaId"
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
            const val ROUTE = "chapter_screen/{mangaId}/{chapterId}/{chapterName}"

            fun getRoute(mangaId: String, chapterId: String, chapterName: String): String =
                "chapter_screen/$mangaId/$chapterId/$chapterName"
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
            const val ROUTE = "category_screen/{categoryName}/{categoryId}"

            fun getRoute(categoryName: String, categoryId: String): String =
                "category_screen/$categoryName/$categoryId"
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