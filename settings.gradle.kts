pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Receipe"
include(":app")
include(":common")
include(":feature:search:data")
include(":feature:search:domain")
include(":feature:search:presentation")
include(":feature:profile")
include(":feature:settings")
include(":feature:profile:data")
include(":feature:profile:presentation")
include(":feature:profile:domain")
