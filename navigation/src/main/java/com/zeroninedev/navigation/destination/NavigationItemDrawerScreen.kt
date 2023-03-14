package com.zeroninedev.navigation.destination

import androidx.annotation.StringRes
import com.zeroninedev.navigation.R

/**
 * Screens in navigation drawer
 *
 * @property route screen route text
 * @property icon icon to drawer
 * @property title title to drawer
 */
sealed class NavigationItemDrawerScreen(var route: String, var icon: Int, @StringRes var title: Int) {

    /**
     * Search manga screen
     *
     */
    class SearchScreen :
        NavigationItemDrawerScreen(ROUTE, R.drawable.search_icon, R.string.search_text) {
        companion object {
            const val ROUTE = "search_screen"
        }
    }

    /**
     * Popular manga screen
     *
     */
    class PopularScreen :
        NavigationItemDrawerScreen(ROUTE, R.drawable.popular_icon, R.string.popular_text) {
        companion object {
            const val ROUTE = "popular_screen"
        }
    }

    /**
     * Last updated manga screen
     *
     */
    class LastUpdatedScreen :
        NavigationItemDrawerScreen(ROUTE, R.drawable.updated_icon, R.string.updated_text) {
        companion object {
            const val ROUTE = "updated_screen"
        }
    }

    /**
     * Saved manga screen
     *
     */
    class SavedScreen :
        NavigationItemDrawerScreen(ROUTE, R.drawable.saved_icon, R.string.saved_text) {
        companion object {
            const val ROUTE = "saved_screen"
        }
    }
}

/**
 * @return List of items for navigation drawer
 */
fun navigationItemDrawerList(): List<NavigationItemDrawerScreen> = listOf(
    NavigationItemDrawerScreen.LastUpdatedScreen(),
    NavigationItemDrawerScreen.SearchScreen(),
    NavigationItemDrawerScreen.PopularScreen(),
    NavigationItemDrawerScreen.SavedScreen()
)