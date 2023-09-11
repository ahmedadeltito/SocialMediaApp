@file:Suppress("UnstableApiUsage")

include(":ui-home")
include(":feature-comment")
include(":feature-post")
include(":datasource")
include(":core")


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

rootProject.name = "SocialMediaApp"
include(":app")
 