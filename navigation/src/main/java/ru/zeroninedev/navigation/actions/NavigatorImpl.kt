package ru.zeroninedev.navigation.actions

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import ru.zeroninedev.navigation.destination.Screen

class NavigatorImpl(private val navController: NavController) : Navigator {

    override fun navigate(screen: Screen) {
        navController.navigate(screen.route)
    }

    override fun navigate(path: String) {
        navController.navigate(path)
    }

    override fun navigateToNextWithClearCurrentScreen(current: Screen, next: Screen) {
        navController.navigate(
            next.route,
            navOptions = NavOptions.Builder().setPopUpTo(current.route, true).build()
        )
    }

    override fun navigateToNextWithClearCurrentScreen(route: String, popUpRoute: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUpRoute)
        }
    }

    override fun goBackStack() {
        navController.navigateUp()
    }
}