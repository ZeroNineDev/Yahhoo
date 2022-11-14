package com.zeroninedev.yahhoo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.zeroninedev.manga.presentation.navigation.mainMangaNavigation
import com.zeroninedev.navigation.actions.NavigatorImpl
import com.zeroninedev.navigation.destination.Screen

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