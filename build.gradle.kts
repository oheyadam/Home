buildscript {
  repositories {
    google()
    mavenCentral()
  }
}

// https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.hilt) apply false
  id("com.android.library") version "7.2.2" apply false
  id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}
