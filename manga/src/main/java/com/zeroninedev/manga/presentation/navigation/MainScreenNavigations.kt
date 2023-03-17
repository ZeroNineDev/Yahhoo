package com.zeroninedev.manga.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeroninedev.manga.presentation.lastupdated.screen.LastUpdatedMangaScreen
import com.zeroninedev.manga.presentation.popular.screen.PopularMangaScreen
import com.zeroninedev.manga.presentation.search.screen.SearchMangaScreen
import com.zeroninedev.manga.presentation.lastupdated.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.manga.presentation.mangasetting.screen.SettingMangaScreen
import com.zeroninedev.manga.presentation.mangasetting.viewmodel.SettingMangaViewModel
import com.zeroninedev.manga.presentation.popular.viewmodel.PopularMangaViewModel
import com.zeroninedev.manga.presentation.saved.screen.SavedMangaScreen
import com.zeroninedev.manga.presentation.saved.viewmodel.SavedMangaViewModel
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.Screen.SettingScreen

/**
 * Navigation for graph in main screen with toolbar
 *
 * @param navigationController inner navigation controller
 * @param outerNavigator main navigation vontroller
 * @param startDestinationRoute start route
 */
@ExperimentalComposeApi
@Composable
internal fun MainScreenNavigations(
    navigationController: NavHostController,
    outerNavigator: Navigator,
    startDestinationRoute: String,
) {
    val lastViewModel: LastUpdatedMangaViewModel = hiltViewModel()
    val searchViewModel: SearchMangaViewModel = hiltViewModel()
    val popularViewModel: PopularMangaViewModel = hiltViewModel()
    val savedViewModel: SavedMangaViewModel = hiltViewModel()
    val settingViewModel: SettingMangaViewModel = hiltViewModel()

    NavHost(
        navigationController,
        startDestination = startDestinationRoute
    ) {

        composable(NavigationItemDrawerScreen.PopularScreen.ROUTE) {
            PopularMangaScreen(outerNavigator, popularViewModel)
        }

        composable(NavigationItemDrawerScreen.SearchScreen.ROUTE) {
            SearchMangaScreen(outerNavigator, searchViewModel)
        }

        composable(NavigationItemDrawerScreen.LastUpdatedScreen.ROUTE) {
            LastUpdatedMangaScreen(outerNavigator, lastViewModel)
        }

        composable(NavigationItemDrawerScreen.SavedScreen.ROUTE) {
            SavedMangaScreen(outerNavigator, savedViewModel)
        }

        composable(SettingScreen.ROUTE) {
            SettingMangaScreen(settingViewModel)
        }
    }
}