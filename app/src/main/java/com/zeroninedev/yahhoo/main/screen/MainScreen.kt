package com.zeroninedev.yahhoo.main.screen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.actions.NavigatorImpl
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.Screen.SettingScreen
import com.zeroninedev.yahhoo.main.view.MainView
import kotlinx.coroutines.launch

@ExperimentalComposeApi
@Composable
internal fun MainScreen(navigator: Navigator) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val startScreen = NavigationItemDrawerScreen.LastUpdatedScreen()

    val navigationController = rememberNavController()
    val mainNavigator = remember(navigationController) { NavigatorImpl(navigationController) }

    MainView(
        scaffoldState,
        startScreen,
        navigationController,
        navigator,
        onMenuPress = { scope.launch { scaffoldState.drawerState.open() } },
        onSettingClick = {
            mainNavigator.navigateAndReplaceStartRoute(SettingScreen.ROUTE)
            scope.launch { scaffoldState.drawerState.close() }
        },
        onNavigationDrawer = {
            mainNavigator.navigateAndReplaceStartRoute(it.route)
            scope.launch { scaffoldState.drawerState.close() }
        }
    )
}