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
        const val google = "com.google.android.gms:play-services-auth:20.4.0"
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
        const val common = ":common"
        const val manga = ":manga"
        const val auth = ":authentication"
    }

    object Retrofit {
        const val version = "2.9.0"
        const val core = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
        const val httpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.10.0"
    }

    object Hilt {
        const val version = "2.40.5"
        const val versionNavigation = "1.0.0"

        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:$versionNavigation"
    }

    object Coil {
        private const val version = "2.2.2"
        const val coil = "io.coil-kt:coil-compose:$version"
        const val gif = "io.coil-kt:coil-gif:$version"
    }

    object Firebase {
        private const val version = "4.3.15"
        const val classpath = "com.google.gms:google-services:$version"
        const val bom = "com.google.firebase:firebase-bom:31.2.3"
        const val analytic = "com.google.firebase:firebase-analytics-ktx"
        const val authentication = "com.google.firebase:firebase-auth-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val crashlyticsClassPath = "com.google.firebase:firebase-crashlytics-gradle:2.9.4"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime:3.1.0"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha13"
    }

    object Room {
        private const val room_version = "2.3.0"
        const val ktx = "androidx.room:room-ktx:$room_version"
        const val compiler = "androidx.room:room-compiler:$room_version"
        const val api = "androidx.room:room-runtime:$room_version"
    }
}