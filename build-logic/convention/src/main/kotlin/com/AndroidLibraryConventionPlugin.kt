import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.utils.KOTLIN_ANDROID_PLUGIN_ID
import com.oheyadam.home.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply(KOTLIN_ANDROID_PLUGIN_ID)
      }

      extensions.configure<LibraryExtension> {
        configureKotlinAndroid(this)
      }
    }
  }
}
