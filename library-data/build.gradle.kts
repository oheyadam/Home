plugins {
  id("home.android.library")
  id("home.android.hilt")
}

android {
  namespace = "com.oheyadam.library.data"
}

dependencies {
  implementation(projects.coreCommon)
  implementation(projects.coreNetwork)
  implementation(projects.coreModel)

  implementation(libs.kotlinx.coroutines.android)

  testImplementation(projects.coreTesting)

  testImplementation(libs.kotlinx.datetime)
}
