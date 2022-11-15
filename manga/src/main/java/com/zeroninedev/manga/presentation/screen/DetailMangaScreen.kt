package com.zeroninedev.manga.presentation.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.zeroninedev.core_compose.components.text.ScreenTitleTextView
import com.zeroninedev.manga.di.FeatureMangaComponent
import com.zeroninedev.manga.presentation.viewmodel.DetailMangaViewModel
import com.zeroninedev.navigation.actions.Navigator

@Composable
internal fun DetailMangaScreen(navigator: Navigator, component: FeatureMangaComponent, viewModel: DetailMangaViewModel) {
    val screen = viewModel.screenState.collectAsState().value
    ScreenTitleTextView(text = "Manga")
}