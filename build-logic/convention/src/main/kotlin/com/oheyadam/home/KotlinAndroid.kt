package com.oheyadam.home

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion.VERSION_11
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *>) {
  commonExtension.apply {
    compileSdk = 33

    defaultConfig {
      minSdk = 21
    }

    compileOptions {
      sourceCompatibility = VERSION_11
      targetCompatibility = VERSION_11
      isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
      // Treat all Kotlin warnings as errors (disabled by default)
      // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
      val warningsAsErrors: String? by project
      allWarningsAsErrors = warningsAsErrors.toBoolean()
      freeCompilerArgs = freeCompilerArgs + listOf(
        "-opt-in=kotlin.RequiresOptIn",
        "-opt-in=kotlin.Experimental",
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-opt-in=kotlinx.coroutines.FlowPreview"
      )
      jvmTarget = VERSION_11.toString()
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
      coreLibraryDesugaring(libs.findLibrary("android.core.library.desugaring").get())
    }
  }
}

private fun DependencyHandlerScope.coreLibraryDesugaring(dependencyNotation: Any): Dependency? {
  return add("coreLibraryDesugaring", dependencyNotation)
}
