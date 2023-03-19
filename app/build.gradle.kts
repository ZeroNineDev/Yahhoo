plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        applicationVariants.all {
            val variant = this
            outputs
                .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                .forEach { output ->
                    val outputFileName = "Yahhoo - ${variant.baseName} - ${variant.versionName} ${variant.versionCode}.apk"
                    println("OutputFileName: $outputFileName")
                    output.outputFileName = outputFileName
                }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Dependencies.Project.coreCompose))
    implementation(project(Dependencies.Project.navigation))
    implementation(project(Dependencies.Project.manga))
    implementation(project(Dependencies.Project.common))

    implementation(Dependencies.Android.coreKtx)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.preview)

    implementation(Dependencies.Lifecycle.lifecycleKtx)
    implementation(Dependencies.Lifecycle.activityCompose)

    implementation(Dependencies.Accompanist.navigation)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Compose.composeJUnit)
    debugImplementation(Dependencies.Compose.composeUITooling)

    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)
}