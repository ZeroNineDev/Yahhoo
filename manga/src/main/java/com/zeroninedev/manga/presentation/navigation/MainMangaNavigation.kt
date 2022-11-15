package com.zeroninedev.manga.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroninedev.manga.di.DaggerFeatureMangaComponent
import com.zeroninedev.manga.presentation.screen.DetailMangaScreen
import com.zeroninedev.manga.presentation.screen.MainMangaScreen
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen

fun NavGraphBuilder.mainMangaNavigation(navigator: Navigator) {

    val component = DaggerFeatureMangaComponent.builder().build()
    composable(Screen.MainScreen.ROUTE) {
        MainMangaScreen(navigator, component)
    }

}