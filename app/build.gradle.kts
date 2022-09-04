plugins {
  id("home.android.application")
  kotlin("kapt")
  id("dagger.hilt.android.plugin")
  alias(libs.plugins.navigation.safe.args)
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
  implementation(projects.coreCommon)
  implementation(projects.libraryData)
  implementation(projects.coreModel)
  implementation(projects.coreNetwork)
  implementation(projects.libraryAnalytics)
  implementation(projects.featureList)
  implementation(projects.featureDetail)

  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.fragment.ktx)
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.navigation.fragment.ktx)
  implementation(libs.androidx.navigation.ui.ktx)
  implementation(libs.material)
  implementation(libs.coil.kt)
  implementation(libs.hilt.android)
  implementation(libs.retrofit.core)
  implementation(libs.okhttp.logging)
  implementation(libs.timber)

  kapt(libs.hilt.compiler)

  kaptAndroidTest(libs.hilt.compiler)
}
