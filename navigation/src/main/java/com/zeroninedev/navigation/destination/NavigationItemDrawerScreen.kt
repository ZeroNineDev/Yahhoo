package com.zeroninedev.navigation.destination

import androidx.annotation.StringRes
import com.zeroninedev.navigation.R

sealed class NavigationItemDrawerScreen(var route: String, var icon: Int, @StringRes var title: Int) {

    class SearchScreen :
        NavigationItemDrawerScreen(ROUTE, R.drawable.search_icon, R.string.search_text) {
        companion object {
            const val ROUTE = "search_screen"
        }
    }

    class PopularScreen :
        NavigationItemDrawerScreen(ROUTE, R.drawable.popular_icon, R.string.popular_text) {
        companion object {
            const val ROUTE = "popular_screen"
        }
    }

    class LastUpdatedScreen :
        NavigationItemDrawerScreen(ROUTE, R.drawable.updated_icon, R.string.updated_text) {
        companion object {
            const val ROUTE = "updated_screen"
        }
    }
}

fun navigationItemDrawerList(): List<NavigationItemDrawerScreen> = listOf(
    NavigationItemDrawerScreen.LastUpdatedScreen(),
    NavigationItemDrawerScreen.SearchScreen(),
    NavigationItemDrawerScreen.PopularScreen()
)