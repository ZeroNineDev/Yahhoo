package com.zeroninedev.manga.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeroninedev.manga.di.FeatureMangaComponent
import com.zeroninedev.manga.presentation.screen.DetailMangaScreen
import com.zeroninedev.manga.presentation.screen.LastUpdatedMangaScreen
import com.zeroninedev.manga.presentation.screen.PopularMangaScreen
import com.zeroninedev.manga.presentation.screen.SearchMangaScreen
import com.zeroninedev.manga.presentation.viewmodel.DetailMangaViewModel
import com.zeroninedev.manga.presentation.viewmodel.LastUpdatedMangaViewModel
import com.zeroninedev.manga.presentation.viewmodel.PopularMangaViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.Screen

@Composable
internal fun MainScreenNavigations(
    navigationController: NavHostController,
    internalNavigator: Navigator,
    outerNavigator: Navigator,
    startDestinationRoute: String,
    component: FeatureMangaComponent
) {
    val lastViewModel: LastUpdatedMangaViewModel = viewModel(factory = component.provideLastUpdatedMangaFactory())
    val popularViewModel: PopularMangaViewModel = viewModel(factory = component.providePopularMangaFactory())
    val detailViewModel: DetailMangaViewModel = viewModel(factory = component.provideDetailMangaFactory())

    NavHost(
        navigationController,
        startDestination = startDestinationRoute
    ) {

        composable(NavigationItemDrawerScreen.PopularScreen.ROUTE) {
            PopularMangaScreen(internalNavigator, outerNavigator, popularViewModel)
        }

        composable(NavigationItemDrawerScreen.SearchScreen.ROUTE) {
            SearchMangaScreen(internalNavigator, outerNavigator)
        }

        composable(NavigationItemDrawerScreen.LastUpdatedScreen.ROUTE) {
            LastUpdatedMangaScreen(internalNavigator, outerNavigator, lastViewModel)
        }

        composable("${Screen.MangaDetailScreen.ROUTE}/{mangaId}") {
            val mangaId = it.arguments?.getString("mangaId") ?: ""
            detailViewModel.loadMangaDetails(mangaId)
            DetailMangaScreen(internalNavigator, component, detailViewModel)
        }
    }
}