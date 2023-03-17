plugins {
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.gsonConverter)
    implementation(Dependencies.Retrofit.httpInterceptor)

    implementation(Dependencies.Paging.runtime)

    implementation(Dependencies.Lifecycle.viewModelCompose)

    implementation(Dependencies.Room.ktx)
    api(Dependencies.Room.api)
    kapt(Dependencies.Room.compiler)

    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)
}