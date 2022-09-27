plugins {
  id("home.android.library")
  id("home.android.library.compose")
  id("home.android.feature")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = "com.oheyadam.feature.detail"
}

dependencies {
  implementation(projects.libraryAnalytics)
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.fragment.ktx)

  testImplementation(projects.coreTesting)
}
