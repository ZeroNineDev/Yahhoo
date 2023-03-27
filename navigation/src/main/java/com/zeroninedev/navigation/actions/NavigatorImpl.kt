package com.zeroninedev.navigation.actions

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.zeroninedev.navigation.destination.Screen

/**
 * Implementation of [Navigator]
 *
 * @property navController navigation manager
 */
class NavigatorImpl(private val navController: NavController) : Navigator {

    override fun navigate(screen: Screen) {
        navController.navigate(screen.route)
    }

    override fun navigate(path: String) {
        navController.navigate(path)
    }

    override fun navigateAndReplaceStartRoute(newHomeRoute: String) {
        navController.navigate(newHomeRoute) {
            popUpTo(0)
        }
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
        Log.d("HETE", navController.currentDestination.toString())
        navController.navigateUp()
    }
}