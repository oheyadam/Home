plugins {
  id("home.android.library")
  kotlin("kapt")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = "com.oheyadam.library.data"
}

dependencies {
  implementation(projects.coreCommon)
  implementation(projects.coreNetwork)
  implementation(projects.coreModel)

  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}
