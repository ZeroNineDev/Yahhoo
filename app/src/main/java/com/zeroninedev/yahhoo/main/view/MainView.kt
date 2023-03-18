package com.zeroninedev.yahhoo.main.view

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
import com.zeroninedev.manga.presentation.navigation.MainMangaScreenNavigations
import com.zeroninedev.navigation.R.string
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.NavigationItemDrawerScreen
import com.zeroninedev.navigation.destination.navigationItemDrawerList

/**
 * Main manga view
 *
 * @param scaffoldState remember scaffold state
 * @param startScreen started screen
 * @param navigationController internal navigation controller
 * @param navigator main navigation controller
 * @param onMenuPress callback on menu press
 * @param onSettingClick callback on setting click
 * @param onNavigationDrawer callback on select item in drawer
 */
@ExperimentalComposeApi
@Composable
internal fun MainView(
    scaffoldState: ScaffoldState,
    startScreen: NavigationItemDrawerScreen,
    navigationController: NavHostController,
    navigator: Navigator,
    onMenuPress: () -> Unit,
    onSettingClick: () -> Unit,
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
            NavigationDrawer(
                itemsList = navigationItemDrawerList(),
                onSettingClick = {
                    text = string.setting_text
                    onSettingClick()
                },
                onDestinationClicked = {
                    text = it.title
                    onNavigationDrawer(it)
                }
            )
        }
    ) {
        MainMangaScreenNavigations(
            navigationController = navigationController,
            outerNavigator = navigator,
            startDestinationRoute = startScreen.route
        )
    }
}