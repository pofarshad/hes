plugins {
    alias(libs.plugins.android.library) // Or just kotlin("jvm") if no Android specific APIs are needed
    alias(libs.plugins.jetbrains.kotlin.android) // Or just kotlin("jvm")
}

android { // Remove this block if core_domain is a pure Kotlin module
    namespace = "com.yourcompany.vpnresellerapp.core_domain"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt() // Not strictly needed for pure Kotlin, but good for consistency
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Usually false for domain, as it's mostly interfaces/data classes
            // Proguard rules might not be necessary if it's pure Kotlin and interfaces
        }
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
    implementation(libs.androidx.core.ktx) // Example, might not be needed for pure domain
    implementation(libs.kotlinx.coroutines.core) // For Flow, suspend functions in use cases/repos

    // Domain module usually has minimal dependencies, mostly Kotlin stdlib and coroutines.
    // It will define interfaces that data module implements, and data classes for models if not already in data.
    // If data models from core_data are used directly, add:
    // api(project(":core_data")) // if domain needs to expose or use models from core_data directly.
                               // However, it's often cleaner for domain to define its own models/interfaces
                               // and have core_data map to/from them. For simplicity now, we might reuse.

    testImplementation(libs.junit)
    // testImplementation("org.mockito.kotlin:mockito-kotlin:${Versions.MOCKITO_KOTLIN}")
    // testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${libs.versions.coroutines.get()}")
}
