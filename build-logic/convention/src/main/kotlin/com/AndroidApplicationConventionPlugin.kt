import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.utils.KOTLIN_ANDROID_PLUGIN_ID
import com.oheyadam.home.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
        apply(KOTLIN_ANDROID_PLUGIN_ID)
      }

      extensions.configure<ApplicationExtension> {
        configureKotlinAndroid(this)
        defaultConfig.targetSdk = 33
      }
    }
  }
}
