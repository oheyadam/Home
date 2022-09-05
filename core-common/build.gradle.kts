plugins {
  id("home.android.library")
  kotlin("kapt")
}

android {
  namespace = "com.oheyadam.core.common"
  buildFeatures {
    viewBinding = true
  }
}
dependencies {
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.androidx.fragment.ktx)
  implementation(libs.hilt.android)
  implementation(libs.androidx.recyclerview)
  implementation(libs.material)
  kapt(libs.hilt.compiler)
}
