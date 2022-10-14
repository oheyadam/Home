@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  includeBuild("build-logic")
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

rootProject.name = "Home"
include(":app")
include(":core-common")
include(":core-design")
include(":core-model")
include(":core-network")
include(":core-testing")
include(":library-analytics")
include(":library-data")
include(":feature-detail")
include(":feature-list")
