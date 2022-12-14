plugins {
  id("home.android.library")
  id("home.android.library.compose")
  id("home.android.feature")
}

android {
  namespace = "com.oheyadam.feature.list"
}

dependencies {
  implementation(projects.libraryAnalytics)

  testImplementation(projects.coreTesting)
}
