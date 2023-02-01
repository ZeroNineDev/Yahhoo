package com.zeroninedev.manga.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroninedev.manga.di.DaggerFeatureMangaComponent
import com.zeroninedev.manga.presentation.screen.DetailMangaScreen
import com.zeroninedev.manga.presentation.screen.MainMangaScreen
import com.zeroninedev.manga.presentation.screen.MangaChapterScreen
import com.zeroninedev.manga.presentation.viewmodel.DetailMangaViewModel
import com.zeroninedev.manga.presentation.viewmodel.MangaChapterViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen

fun NavGraphBuilder.mainMangaNavigation(navigator: Navigator) {

    val component = DaggerFeatureMangaComponent.builder().build()

    composable(Screen.MainScreen.ROUTE) {
        MainMangaScreen(navigator, component)
    }

    composable("${Screen.MangaDetailScreen.ROUTE}/{mangaId}") {
        val detailViewModel: DetailMangaViewModel = viewModel(factory = component.provideDetailMangaFactory())
        val mangaId = remember { it.arguments?.getString("mangaId").orEmpty() }

        LaunchedEffect(key1 = mangaId) {
            detailViewModel.loadMangaDetails(mangaId)
        }


        DetailMangaScreen(navigator, detailViewModel)
    }

    composable("${Screen.MangaChapterScreen.ROUTE}/{mangaId}/{chapterId}") {
        val mangaChapterViewModel: MangaChapterViewModel = viewModel(factory = component.provideMangaChapterFactory())
        val mangaId = remember { it.arguments?.getString("mangaId").orEmpty() }
        val chapterId  = remember { it.arguments?.getString("chapterId").orEmpty() }
        LaunchedEffect(key1 = mangaId, key2 = chapterId) {
            mangaChapterViewModel.loadMangaChapter(mangaId, chapterId)
        }

        MangaChapterScreen(navigator, mangaChapterViewModel)
    }

}