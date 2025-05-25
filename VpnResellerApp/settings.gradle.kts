pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "VpnResellerApp"
include(":app")
include(":core_ui")
include(":core_data")
include(":core_domain")
include(":feature_representatives")
include(":feature_invoices")
include(":feature_accounting")
include(":feature_settings")
