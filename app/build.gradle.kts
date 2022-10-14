@file:Suppress("UnstableApiUsage")

plugins {
  id("home.android.application")
  id("home.android.application.compose")
  id("home.android.hilt")
}

android {
  namespace = "com.oheyadam.home"

  defaultConfig {
    applicationId = "com.oheyadam.home"
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    val debug by getting {
      applicationIdSuffix = ".debug"
    }
    val release by getting {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  packagingOptions {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
  }
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}

dependencies {
  implementation(projects.coreDesign)
  implementation(projects.coreCommon)
  implementation(projects.coreModel)
  implementation(projects.coreNetwork)
  implementation(projects.libraryAnalytics)
  implementation(projects.libraryData)
  implementation(projects.featureDetail)
  implementation(projects.featureList)

  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3.window.size)
  implementation(libs.androidx.compose.ui)
}
