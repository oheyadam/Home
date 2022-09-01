plugins {
  id("home.android.library")
  kotlin("kapt")
  id("dagger.hilt.android.plugin")
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.oheyadam.core.network"
}
dependencies {
  implementation(projects.coreCommon)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.okhttp.logging)
  implementation(libs.retrofit.core)
  implementation(libs.retrofit.converter.moshi)
  implementation(libs.moshi)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
  ksp(libs.moshi.compiler)
}
