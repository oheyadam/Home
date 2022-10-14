plugins {
  id("home.android.library")
  id("home.android.library.compose")
  id("home.android.feature")
}

android {
  namespace = "com.oheyadam.feature.detail"
}

dependencies {
  implementation(projects.libraryAnalytics)

  testImplementation(projects.coreTesting)
}
