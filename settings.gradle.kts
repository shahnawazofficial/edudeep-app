pluginManagement {
    repositories {
        google()  // Add Google repository here
        mavenCentral()  // Add Maven Central repository here
        gradlePluginPortal()  // Add Gradle Plugin Portal repository
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Ensure project repos are not used
    repositories {
        google()  // Add Google repository here
        mavenCentral()  // Add Maven Central repository here
    }
}

rootProject.name = "edudeep"
include(":app")
