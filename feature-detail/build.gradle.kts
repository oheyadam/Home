plugins {
  id("home.android.library")
  id("home.android.library.compose")
  id("home.android.feature")
  id("dagger.hilt.android.plugin")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "com.oheyadam.feature.detail"
}

dependencies {
  implementation(libs.androidx.fragment.ktx)
  implementation(libs.timber)
}
