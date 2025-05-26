// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(libs.plugins.android.application.get().pluginId) version libs.versions.agp.get() apply false
    id(libs.plugins.android.library.get().pluginId) version libs.versions.agp.get() apply false
    id(libs.plugins.kotlin.android.get().pluginId) version libs.versions.kotlin.get() apply false
    id(libs.plugins.hilt.get().pluginId) version libs.versions.hilt.get() apply false
    // google-services plugin is applied in app/build.gradle.kts, so it's not needed here with apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
