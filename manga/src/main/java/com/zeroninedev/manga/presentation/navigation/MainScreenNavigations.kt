package com.zeroninedev.manga.presentation.navigation

import androidx.compose.runtime.ExperimentalComposeApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroninedev.manga.presentation.lastupdated.screen.LastUpdatedMangaScreen
import com.zeroninedev.manga.presentation.lastupdated.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.manga.presentation.mangasetting.screen.SettingMangaScreen
import com.zeroninedev.manga.presentation.mangasetting.viewmodel.SettingMangaViewModel
import com.zeroninedev.manga.presentation.popular.screen.PopularMangaScreen
import com.zeroninedev.manga.presentation.popular.viewmodel.PopularMangaViewModel
import com.zeroninedev.manga.presentation.saved.screen.SavedMangaScreen
import com.zeroninedev.manga.presentation.saved.viewmodel.SavedMangaViewModel
import com.zeroninedev.manga.presentation.search.screen.SearchMangaScreen
import com.zeroninedev.manga.presentation.search.viewmodel.SearchMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.Screen.SettingScreen

/**
 * Navigation for graph in main screen with toolbar
 *
 * @param outerNavigator main navigation vontroller
 */
@ExperimentalComposeApi
fun NavGraphBuilder.mangaDrawerScreenNavigations(outerNavigator: Navigator) {

    composable(NavigationItemDrawerScreen.PopularScreen.ROUTE) {
        val popularViewModel: PopularMangaViewModel = hiltViewModel()
        PopularMangaScreen(outerNavigator, popularViewModel)
    }

    composable(NavigationItemDrawerScreen.SearchScreen.ROUTE) {
        val searchViewModel: SearchMangaViewModel = hiltViewModel()
        SearchMangaScreen(outerNavigator, searchViewModel)
    }

    composable(NavigationItemDrawerScreen.LastUpdatedScreen.ROUTE) {
        val lastViewModel: LastUpdatedMangaViewModel = hiltViewModel()
        LastUpdatedMangaScreen(outerNavigator, lastViewModel)
    }

    composable(NavigationItemDrawerScreen.SavedScreen.ROUTE) {
        val savedViewModel: SavedMangaViewModel = hiltViewModel()
        SavedMangaScreen(outerNavigator, savedViewModel)
    }

    composable(SettingScreen.ROUTE) {
        val settingViewModel: SettingMangaViewModel = hiltViewModel()
        SettingMangaScreen(settingViewModel)
    }
}