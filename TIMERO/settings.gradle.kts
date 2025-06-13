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
        // This line is essential for the PDF viewer and other libraries
        maven { url = uri("https://jitpack.io") }
    }
}
rootProject.name = "TIMERO"
include(":app")
