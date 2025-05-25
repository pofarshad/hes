plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp) // For Room or other annotation processors
    // Potentially add Hilt plugin here if chosen for DI: id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.yourcompany.vpnresellerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yourcompany.vpnresellerapp"
        minSdk = 26 // Android 8.0 Oreo - Consider based on feature requirements like encryption
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true // Enable Proguard/R8 for release builds
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // Or higher if needed, e.g., 17 for newer AGP
        targetCompatibility = JavaVersion.VERSION_1_8 // Or higher
    }
    kotlinOptions {
        jvmTarget = "1.8" // Or higher
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtension.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core_ui"))
    implementation(project(":core_data"))
    implementation(project(":core_domain"))
    implementation(project(":feature_representatives"))
    implementation(project(":feature_invoices"))
    implementation(project(":feature_accounting"))
    implementation(project(":feature_settings"))
    // implementation(project(":feature_analytics")) // To be added later

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Navigation Compose
    implementation(libs.androidx.navigation.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Room (core_data will have the main implementation, app might need runtime)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Google Sign-In (for Drive API)
    implementation(libs.play.services.auth)

    // WorkManager for background tasks (e.g., daily backups)
    implementation(libs.androidx.work.runtime.ktx)


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt or Koin for Dependency Injection (Example for Hilt)
    // implementation("com.google.dagger:hilt-android:${Versions.HILT}")
    // ksp("com.google.dagger:hilt-compiler:${Versions.HILT}")
    // implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
}

// If using Hilt
// apply(plugin = "com.google.dagger.hilt.android")
