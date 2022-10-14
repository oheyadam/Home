plugins {
  id("home.android.library")
  kotlin("kapt")
}

android {
  namespace = "com.oheyadam.core.common"
}

dependencies {
  implementation(libs.kotlinx.coroutines.android)
}
