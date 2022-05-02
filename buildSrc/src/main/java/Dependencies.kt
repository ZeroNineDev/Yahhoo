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
    }

    object Test {

        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }
}