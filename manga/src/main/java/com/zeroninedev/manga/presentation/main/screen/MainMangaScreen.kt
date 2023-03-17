package com.zeroninedev.manga.presentation.main.screen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.zeroninedev.manga.presentation.main.view.MainMangaView
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.actions.NavigatorImpl
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.Screen.SettingScreen
import kotlinx.coroutines.launch

/**
 * Main manga screen with drawer
 *
 * @param navigator main navigation
 */
@ExperimentalComposeApi
@Composable
internal fun MainMangaScreen(navigator: Navigator) {
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
        onMenuPress = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        },
        onSettingClick = {
            internalNavigator.navigateAndReplaceStartRoute(SettingScreen.ROUTE)
            scope.launch {
                scaffoldState.drawerState.close()
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