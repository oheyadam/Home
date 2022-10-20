package com.oheyadam.home

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.Dependency
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
  (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any): Dependency? {
  return add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, dependencyNotation)
}

internal fun DependencyHandlerScope.kapt(dependencyNotation: Any): Dependency? {
  return add("kapt", dependencyNotation)
}
