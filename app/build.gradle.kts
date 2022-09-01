plugins {
  id("home.android.application")
  kotlin("kapt")
  id("dagger.hilt.android.plugin")
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
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.material)
  implementation(libs.coil.kt)
  implementation(libs.hilt.android)
  implementation(libs.retrofit.core)
  implementation(libs.okhttp.logging)

  kapt(libs.hilt.compiler)

  kaptAndroidTest(libs.hilt.compiler)
}
