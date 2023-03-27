package com.zeroninedev.yahhoo.main.screen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.AuthenticationScreens
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.Screen.SettingScreen
import com.zeroninedev.yahhoo.main.view.MainView
import kotlinx.coroutines.launch

@ExperimentalComposeApi
@ExperimentalComposeUiApi
@Composable
internal fun MainScreen(
    navigator: Navigator,
    navigationControllerWithoutToolbar: NavHostController,
    navigatorWithoutToolbar: Navigator,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val startScreen = NavigationItemDrawerScreen.LastUpdatedScreen()


    MainView(
        scaffoldState = scaffoldState,
        startScreen = startScreen,
        navigationController = navigationControllerWithoutToolbar,
        navigator = navigator,
        mainNavigator = navigatorWithoutToolbar,
        onMenuPress = { scope.launch { scaffoldState.drawerState.open() } },
        onSettingClick = {
            navigatorWithoutToolbar.navigateAndReplaceStartRoute(SettingScreen.ROUTE)
            scope.launch { scaffoldState.drawerState.close() }
        },
        onNavigationDrawer = {
            navigatorWithoutToolbar.navigateAndReplaceStartRoute(it.route)
            scope.launch { scaffoldState.drawerState.close() }
        },
        onUserClick = {
            navigatorWithoutToolbar.navigate(AuthenticationScreens.BaseScreen.ROUTE)
            scope.launch { scaffoldState.drawerState.close() }
        }
    )
}