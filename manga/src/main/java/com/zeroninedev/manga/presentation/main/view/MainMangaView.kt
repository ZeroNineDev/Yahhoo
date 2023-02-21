package com.zeroninedev.manga.presentation.main.view

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.zeroninedev.core_compose.components.drawer.NavigationDrawer
import com.zeroninedev.core_compose.components.toolbar.TopToolbar
import com.zeroninedev.manga.di.FeatureMangaComponent
import com.zeroninedev.manga.presentation.navigation.MainScreenNavigations
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.navigationItemDrawerList

@ExperimentalComposeApi
@Composable
internal fun MainMangaView(
    scaffoldState: ScaffoldState,
    startScreen: NavigationItemDrawerScreen,
    navigationController: NavHostController,
    navigator: Navigator,
    component: FeatureMangaComponent,
    onMenuPress: () -> Unit,
    onNavigationDrawer: (NavigationItemDrawerScreen) -> Unit
) {
    var text by remember { mutableStateOf(startScreen.title) }

    Scaffold(
        topBar = {
            TopToolbar(screenTitle = stringResource(text)) {
                onMenuPress()
            }
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            NavigationDrawer(itemsList = navigationItemDrawerList()) {
                text = it.title
                onNavigationDrawer(it)
            }
        }
    ) {
        MainScreenNavigations(
            navigationController = navigationController,
            outerNavigator = navigator,
            startDestinationRoute = startScreen.route,
            component = component
        )
    }
}