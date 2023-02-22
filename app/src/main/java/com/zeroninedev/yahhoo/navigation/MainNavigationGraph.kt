package com.zeroninedev.yahhoo.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.zeroninedev.manga.presentation.navigation.mainMangaNavigation
import com.zeroninedev.navigation.actions.NavigatorImpl
import com.zeroninedev.navigation.destination.Screen

/**
 * Main navigation graph.
 * The place where to add the module navigation.
 *
 */
@ExperimentalAnimationApi
@ExperimentalComposeApi
@Composable
fun MainNavigationGraph() {
    val navigationController = rememberNavController()
    val navigator = remember(navigationController) { NavigatorImpl(navigationController) }

    NavHost(
        navigationController,
        startDestination = Screen.MainScreen.ROUTE
    ) {

        mainMangaNavigation(navigator)
    }
}