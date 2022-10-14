@file:Suppress("UnstableApiUsage")

plugins {
  id("home.android.library")
  id("home.android.hilt")
}

android {
  namespace = "com.oheyadam.library.analytics"
  buildFeatures {
    buildConfig = true
  }
}

dependencies {
  implementation(projects.coreCommon)

  implementation(libs.timber)
}
