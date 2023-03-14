package com.zeroninedev.navigation.actions

import com.zeroninedev.navigation.destination.Screen

/**
 * Interface for access in navigation in application
 *
 */
interface Navigator {

    /**
     * Navigate to the next screen by screen class
     *
     * @param screen screen class destination
     */
    fun navigate(screen: Screen)


    /**
     * Navigate to the next screen by screen destination path
     *
     * @param path screen destination path
     */
    fun navigate(path: String)

    /**
     * Navigate to the next root screen with replacing current
     *
     * @param newHomeRoute screen path to new root
     */
    fun navigateAndReplaceStartRoute(newHomeRoute: String)

    /**
     * Navigate to the next screen with replacing current by screen class
     *
     * @param current current screen class destination
     * @param next next screen class destination
     */
    fun navigateToNextWithClearCurrentScreen(current: Screen, next: Screen)

    /**
     * Navigate to the next screen with replacing current by screen destination path
     *
     * @param route current screen destination path
     * @param popUpRoute next screen destination path
     */
    fun navigateToNextWithClearCurrentScreen(route: String, popUpRoute: String)

    /**
     * Navigate to the previous screen
     *
     */
    fun goBackStack()
}
