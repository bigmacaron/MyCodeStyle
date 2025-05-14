pluginManagement {
    includeBuild("build-logic")
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
        maven {
            url = uri("https://raw.githubusercontent.com/alexgreench/google-webrtc/master")
        }
    }
}
// If the project has build-logic module with convention module. (for example: https://github.com/android/nowinandroid/tree/main/build-logic)
// https://stackoverflow.com/questions/77279080/unable-to-make-progress-running-work-android-studio
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "MyCodeStyle"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:room")
include(":core:design-system")
include(":core:ui")
include(":core:common")
include(":feature:main")
include(":feature:home")
include(":feature:favorite")
include(":adhelper")
