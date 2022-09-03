import org.gradle.api.JavaVersion.VERSION_11

plugins {
  `kotlin-dsl`
}

group = "com.oheyadam.home.buildlogic"

java {
  sourceCompatibility = VERSION_11
  targetCompatibility = VERSION_11
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidApplication") {
      id = "home.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    register("androidApplicationCompose") {
      id = "home.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }
    register("androidLibrary") {
      id = "home.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidLibraryCompose") {
      id = "home.android.library.compose"
      implementationClass = "AndroidLibraryComposeConventionPlugin"
    }
    register("androidFeature") {
      id = "home.android.feature"
      implementationClass = "AndroidFeatureConventionPlugin"
    }
    register("androidTest") {
      id = "home.android.test"
      implementationClass = "AndroidTestConventionPlugin"
    }
  }
}
