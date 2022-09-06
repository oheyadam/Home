plugins {
  id("home.android.library")
}

android {
  namespace = "com.oheyadam.core.testing"
}

dependencies {
  implementation(projects.coreModel)
  implementation(projects.coreNetwork)

  api(libs.junit4)
  api(libs.truth)
  api(libs.mockito.kotlin)
  api(libs.androidx.test.core)
  api(libs.kotlinx.coroutines.test)
  api(libs.turbine)
}
