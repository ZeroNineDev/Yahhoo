package com.zeroninedev.manga.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeroninedev.manga.di.FeatureMangaComponent
import com.zeroninedev.manga.presentation.screen.LastUpdatedMangaScreen
import com.zeroninedev.manga.presentation.screen.PopularMangaScreen
import com.zeroninedev.manga.presentation.screen.SearchMangaScreen
import com.zeroninedev.manga.presentation.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.manga.presentation.viewmodel.PopularMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen

@Composable
internal fun MainScreenNavigations(
    navigationController: NavHostController,
    outerNavigator: Navigator,
    startDestinationRoute: String,
    component: FeatureMangaComponent,
) {
    val lastViewModel: LastUpdatedMangaViewModel = viewModel(factory = component.provideLastUpdatedMangaFactory())
    val popularViewModel: PopularMangaViewModel = viewModel(factory = component.providePopularMangaFactory())

    NavHost(
        navigationController,
        startDestination = startDestinationRoute
    ) {

        composable(NavigationItemDrawerScreen.PopularScreen.ROUTE) {
            PopularMangaScreen(outerNavigator, popularViewModel)
        }

        composable(NavigationItemDrawerScreen.SearchScreen.ROUTE) {
            SearchMangaScreen(outerNavigator)
        }

        composable(NavigationItemDrawerScreen.LastUpdatedScreen.ROUTE) {
            LastUpdatedMangaScreen(outerNavigator, lastViewModel)
        }

    }
}