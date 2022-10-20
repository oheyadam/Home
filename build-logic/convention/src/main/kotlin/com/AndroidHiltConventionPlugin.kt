import com.android.build.gradle.internal.utils.KOTLIN_KAPT_PLUGIN_ID
import com.oheyadam.home.implementation
import com.oheyadam.home.kapt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidHiltConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply(KOTLIN_KAPT_PLUGIN_ID)
        apply("dagger.hilt.android.plugin")
      }

      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
      dependencies {
        implementation(libs.findLibrary("hilt.android").get())
        kapt(libs.findLibrary("hilt.compiler").get())
      }
    }
  }
}
