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
    register("androidTest") {
      id = "home.android.test"
      implementationClass = "AndroidTestConventionPlugin"
    }
    register("androidLibrary") {
      id = "home.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidFeature") {
      id = "home.android.feature"
      implementationClass = "AndroidFeatureConventionPlugin"
    }
  }
}
