package com.zeroninedev.yahhoo.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zeroninedev.manga.presentation.navigation.mainMangaNavigation
import com.zeroninedev.navigation.actions.NavigatorImpl
import com.zeroninedev.yahhoo.main.screen.MainScreen

/**
 * Main navigation graph.
 * The place where to add the module navigation.
 *
 */
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalComposeApi
@ExperimentalFoundationApi
@Composable
fun MainNavigationGraph() {
    val navigationController = rememberNavController()
    val navigator = remember(navigationController) { NavigatorImpl(navigationController) }

    NavHost(
        navigationController,
        startDestination = MainScreenRoute.ROUTE
    ) {

        composable(MainScreenRoute.ROUTE) {
            MainScreen(navigator)
        }


        mainMangaNavigation(navigator)
    }
}