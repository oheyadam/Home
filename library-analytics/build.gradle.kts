plugins {
  id("home.android.library")
  kotlin("kapt")
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
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}
