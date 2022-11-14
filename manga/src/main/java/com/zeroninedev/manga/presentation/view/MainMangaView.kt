package com.zeroninedev.manga.presentation.view

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.zeroninedev.core_compose.components.drawer.NavigationDrawer
import com.zeroninedev.core_compose.components.toolbar.TopToolbar
import com.zeroninedev.manga.presentation.navigation.MainScreenNavigations
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.actions.NavigatorImpl
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.navigationItemDrawerList
import kotlinx.coroutines.launch

@Composable
fun MainMangaView(navigator: Navigator) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val startScreen = NavigationItemDrawerScreen.PopularScreen()
    var text by remember { mutableStateOf(startScreen.title) }

    val navigationController = rememberNavController()
    val internalNavigator = remember(navigationController) { NavigatorImpl(navigationController) }

    Scaffold(
        topBar = {
            TopToolbar(screenTitle = stringResource(text)) {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            NavigationDrawer(itemsList = navigationItemDrawerList()) {
                text = it.title
                internalNavigator.navigateAndReplaceStartRoute(it.route)
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }

        }
    ) {
        MainScreenNavigations(navigationController, internalNavigator, navigator, startScreen.route)
    }
}