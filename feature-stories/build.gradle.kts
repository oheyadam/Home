plugins {
  id("home.android.library")
  id("home.android.feature")
  id("dagger.hilt.android.plugin")
}

android {
  namespace = "com.oheyadam.feature.stories"
}

dependencies {
  implementation(projects.coreModel)
  implementation(projects.coreData)
  implementation(projects.coreCommon)
}
