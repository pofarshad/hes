pluginManagement {
    repositories {
        google()  // This must be first for Android plugins
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()  // This must be first for Android dependencies
        mavenCentral()
    }
    // Enable version catalog
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "VpnResellerApp"
include(":app")
include(":core_ui")
include(":core_data")
include(":core_domain")
