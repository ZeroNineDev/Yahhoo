package com.zeroninedev.manga.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroninedev.manga.presentation.category.screen.CategoryMangaScreen
import com.zeroninedev.manga.presentation.category.viewmodel.CategoryMangaIntent
import com.zeroninedev.manga.presentation.category.viewmodel.CategoryMangaViewModel
import com.zeroninedev.manga.presentation.detail.screen.DetailMangaScreen
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaIntent
import com.zeroninedev.manga.presentation.mangachapter.screen.MangaChapterScreen
import com.zeroninedev.manga.presentation.detail.viewmodel.DetailMangaViewModel
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.MangaChapterIntent
import com.zeroninedev.manga.presentation.mangachapter.viewmodel.NewMangaChapterViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.Screen

/**
 * Main manga navigation destination grph
 *
 * @param navigator main navigator
 */
@ExperimentalComposeUiApi
@ExperimentalComposeApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.mainMangaNavigation(navigator: Navigator) {

    composable(Screen.MangaDetailScreen.ROUTE) {
        val detailViewModel: DetailMangaViewModel = hiltViewModel()
        val mangaId = remember { it.arguments?.getString("mangaId").orEmpty() }

        LaunchedEffect(key1 = mangaId) {
            detailViewModel.processIntent(DetailMangaIntent.LoadMangaInfo(mangaId))
        }

        DetailMangaScreen(navigator, detailViewModel)
    }

    composable(Screen.MangaChapterScreen.ROUTE) {
        val mangaChapterViewModel: NewMangaChapterViewModel = hiltViewModel()

        val mangaId = remember { it.arguments?.getString("mangaId").orEmpty() }
        val chapterId = remember { it.arguments?.getString("chapterId").orEmpty() }
        val chapterName = remember { it.arguments?.getString("chapterName").orEmpty() }

        LaunchedEffect(key1 = mangaId, key2 = chapterId) {
            mangaChapterViewModel.processIntent(MangaChapterIntent.LoadMangaChapters(mangaId, chapterId, chapterName))
            mangaChapterViewModel.processIntent(MangaChapterIntent.SetNavigator(navigator))
        }

        MangaChapterScreen(mangaChapterViewModel)
    }

    composable(Screen.CategoryScreen.ROUTE) {
        val categoryViewModel: CategoryMangaViewModel = hiltViewModel()
        val categoryName = remember { it.arguments?.getString("categoryName").orEmpty() }
        val categoryId = remember { it.arguments?.getString("categoryId").orEmpty() }

        LaunchedEffect(key1 = categoryName, key2 = categoryId) {
            categoryViewModel.processIntent(
                CategoryMangaIntent.LoadManga(categoryName = categoryName, categoryId = categoryId)
            )
        }

        CategoryMangaScreen(navigator, categoryViewModel)
    }
}