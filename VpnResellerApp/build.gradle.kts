// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(libs.plugins.android.application.get().pluginId) version libs.versions.agp.toString() apply false
    id(libs.plugins.android.library.get().pluginId) version libs.versions.agp.toString() apply false
    id(libs.plugins.kotlin.android.get().pluginId) version libs.versions.kotlin.toString() apply false
    id(libs.plugins.hilt.get().pluginId) version libs.versions.hilt.toString() apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
