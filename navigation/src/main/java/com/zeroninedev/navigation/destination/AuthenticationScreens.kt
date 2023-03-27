package com.zeroninedev.navigation.destination

sealed class AuthenticationScreens(val route: String) {

    /**
     * Screen for register in app
     */
    class RegisterScreen : AuthenticationScreens(ROUTE) {

        companion object {

            /**
             * View route
             */
            const val ROUTE = "register_screen"
        }
    }

    /**
     * Screen for login in app
     */
    class LoginScreen : AuthenticationScreens(ROUTE) {

        companion object {

            /**
             * View route
             */
            const val ROUTE = "login_screen"
        }
    }

    /**
     * Screen for login in app
     */
    class BaseScreen : AuthenticationScreens(ROUTE) {

        companion object {

            /**
             * View route
             */
            const val ROUTE = "base_auth_screen"
        }
    }
}
