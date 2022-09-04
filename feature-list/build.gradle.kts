plugins {
  id("home.android.library")
  id("home.android.feature")
  id("dagger.hilt.android.plugin")
  id("org.jetbrains.kotlin.android")
}

android {
  namespace = "com.oheyadam.feature.list"
  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(projects.libraryAnalytics)

  implementation(libs.androidx.fragment.ktx)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.recyclerview)
  implementation(libs.material)
}
