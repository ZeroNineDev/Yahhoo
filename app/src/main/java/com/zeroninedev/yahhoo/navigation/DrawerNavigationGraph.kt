package com.zeroninedev.yahhoo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zeroninedev.authentication.navigation.authenticationsScreenNavigations
import com.zeroninedev.manga.presentation.navigation.mangaDrawerScreenNavigations
import com.zeroninedev.navigation.actions.Navigator

@ExperimentalComposeUiApi
@ExperimentalComposeApi
@Composable
internal fun DrawerNavigationGraph(
    navigationController: NavHostController,
    outerNavigator: Navigator,
    mainNavigator: Navigator,
    startDestinationRoute: String,
) {
    NavHost(
        navController = navigationController,
        startDestination = startDestinationRoute
    ) {

        mangaDrawerScreenNavigations(outerNavigator)

        authenticationsScreenNavigations(mainNavigator, outerNavigator)
    }
}