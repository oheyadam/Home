plugins {
  id("home.android.library")
  kotlin("kapt")
}

android {
  namespace = "com.oheyadam.core.common"
}
dependencies {
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
}
