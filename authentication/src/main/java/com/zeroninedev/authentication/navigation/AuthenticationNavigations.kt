package com.zeroninedev.authentication.navigation

import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroninedev.authentication.presentation.baseauth.screen.BaseAuthScreen
import com.zeroninedev.authentication.presentation.baseauth.viewmodel.BaseAuthIntent
import com.zeroninedev.authentication.presentation.baseauth.viewmodel.BaseAuthViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.AuthenticationScreens

@ExperimentalComposeUiApi
@ExperimentalComposeApi
fun NavGraphBuilder.authenticationsScreenNavigations(
    mainNavigator: Navigator,
    outerNavigator: Navigator
) {

    composable(AuthenticationScreens.BaseScreen.ROUTE) {
        val viewModel: BaseAuthViewModel = hiltViewModel()
        viewModel.processIntent(BaseAuthIntent.SetNavigator(mainNavigator))
        BaseAuthScreen(viewModel, outerNavigator)
    }
}