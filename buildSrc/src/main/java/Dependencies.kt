object Dependencies {

    object Compose {

        const val version = "1.1.1"
        const val material = "androidx.compose.material:material:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val composeJUnit = "androidx.compose.ui:ui-test-junit4:$version"
        const val composeUITooling = "androidx.compose.ui:ui-tooling:$version"
    }

    object Android {

        const val coreKtx = "androidx.core:core-ktx:1.7.0"
    }

    object Lifecycle {

        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
    }

    object Test {

        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Accompanist {

        private const val version = "0.23.1"
        const val systemUi = "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val navigation = "com.google.accompanist:accompanist-navigation-animation:$version"
    }

    object Project {
        const val coreCompose = ":core-compose"
        const val navigation = ":navigation"
        const val manga = ":manga"
    }

    object Retrofit {
        const val version = "2.9.0"
        const val core = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Dagger {
        const val version = "2.41"
        const val core = "com.google.dagger:dagger:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
    }

    object Coil {
        private const val version = "2.2.2"
        const val coil = "io.coil-kt:coil-compose:$version"
    }
}