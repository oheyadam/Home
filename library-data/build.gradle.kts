plugins {
  id("home.android.library")
  id("dagger.hilt.android.plugin")
  kotlin("kapt")
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

  testImplementation(projects.coreTesting)
  testImplementation(libs.kotlinx.datetime)
}
