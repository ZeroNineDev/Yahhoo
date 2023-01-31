package com.zeroninedev.manga.presentation.screen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.zeroninedev.manga.di.FeatureMangaComponent
import com.zeroninedev.manga.presentation.view.MainMangaView
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.actions.NavigatorImpl
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import kotlinx.coroutines.launch

@Composable
internal fun MainMangaScreen(navigator: Navigator, component: FeatureMangaComponent) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val startScreen = NavigationItemDrawerScreen.LastUpdatedScreen()

    val navigationController = rememberNavController()
    val internalNavigator = remember(navigationController) { NavigatorImpl(navigationController) }

    MainMangaView(
        scaffoldState,
        startScreen,
        navigationController,
        navigator,
        component,
        onMenuPress = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        },
        onNavigationDrawer = {
            internalNavigator.navigateAndReplaceStartRoute(it.route)
            scope.launch {
                scaffoldState.drawerState.close()
            }
        }
    )
}