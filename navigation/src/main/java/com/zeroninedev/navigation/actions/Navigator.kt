package com.zeroninedev.navigation.actions

import com.zeroninedev.navigation.destination.Screen

interface Navigator {

    fun navigate(screen: Screen)
    fun navigate(path: String)
    fun navigateToNextWithClearCurrentScreen(current: Screen, next: Screen)
    fun navigateToNextWithClearCurrentScreen(route: String, popUpRoute: String)
    fun goBackStack()
}
