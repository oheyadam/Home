package com.oheyadam.home

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *>) {
  val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
  commonExtension.apply {
    buildFeatures {
      compose = true
    }

    composeOptions {
      kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
    }

    kotlinOptions {
      freeCompilerArgs = freeCompilerArgs + listOf(
        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
      )
    }
  }
}
