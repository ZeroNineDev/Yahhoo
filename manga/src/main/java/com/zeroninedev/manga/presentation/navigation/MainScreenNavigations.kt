package com.zeroninedev.manga.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeroninedev.manga.di.DaggerFeatureMangaComponent
import com.zeroninedev.manga.presentation.screen.LastUpdatedMangaScreen
import com.zeroninedev.manga.presentation.screen.PopularMangaScreen
import com.zeroninedev.manga.presentation.screen.SearchMangaScreen
import com.zeroninedev.manga.presentation.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen

@Composable
internal fun MainScreenNavigations(
    navigationController: NavHostController,
    internalNavigator: Navigator,
    outerNavigator: Navigator,
    startDestinationRoute: String
) {
    val component = DaggerFeatureMangaComponent.builder().build()
    val lastViewModel = LastUpdatedMangaViewModel(component.provideNetworkRepository())

    NavHost(
        navigationController,
        startDestination = startDestinationRoute
    ) {

        composable(NavigationItemDrawerScreen.PopularScreen.ROUTE) {
            PopularMangaScreen(internalNavigator, outerNavigator)
        }

        composable(NavigationItemDrawerScreen.SearchScreen.ROUTE) {
            SearchMangaScreen(internalNavigator, outerNavigator)
        }

        composable(NavigationItemDrawerScreen.LastUpdatedScreen.ROUTE) {
            LastUpdatedMangaScreen(internalNavigator, outerNavigator, lastViewModel)
        }
    }
}