plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp) // For Room
}

android {
    namespace = "com.yourcompany.vpnresellerapp.core_data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        // For Room + SQLCipher (if you choose this path for encryption)
        // javaCompileOptions {
        //     annotationProcessorOptions {
        //         arguments(mapOf("room.schemaLocation" to "$projectDir/schemas".toString()))
        //     }
        // }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // Needed for Room schema location if not using KSP argument
    // sourceSets {
    //     androidTest.assets.srcDirs += files("$projectDir/schemas".toString())
    // }
}

dependencies {
    implementation(project(":core_domain")) // Depends on domain models/interfaces

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.core)

    // Room
    api(libs.androidx.room.runtime) // api so app module can use it for AppDatabase instance
    api(libs.androidx.room.ktx)     // api for coroutine support convenience
    ksp(libs.androidx.room.compiler)

    // SQLCipher for Room encryption (Example - ensure you have the right dependency)
    // api("net.zetetic:android-database-sqlcipher:${Versions.SQLCIPHER}") // Check libs.versions.toml
    // implementation("androidx.sqlite:sqlite-ktx:2.4.0") // For SafeHelperFactory

    // Google Drive API (client library, not auth which is in app module)
    // implementation("com.google.apis:google-api-services-drive:${Versions.DRIVE_API}") // Check libs.versions.toml
    // implementation("com.google.http-client:google-http-client-gson:1.42.3") // Or other http client

    // Apache POI for Excel (if chosen) - be mindful of its size
    // implementation("org.apache.poi:poi:5.2.3")
    // implementation("org.apache.poi:poi-ooxml:5.2.3")


    // Testing
    testImplementation(libs.junit)
    // testImplementation("org.mockito.kotlin:mockito-kotlin:${Versions.MOCKITO_KOTLIN}") // Check libs.versions.toml
    // testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${libs.versions.coroutines.get()}")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
