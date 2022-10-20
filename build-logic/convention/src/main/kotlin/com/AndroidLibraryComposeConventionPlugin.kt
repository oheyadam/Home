import com.android.build.gradle.LibraryExtension
import com.oheyadam.home.configureAndroidCompose
import com.oheyadam.home.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply("com.android.library")
      val extension = extensions.getByType<LibraryExtension>()
      configureAndroidCompose(extension)

      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
      dependencies {
        implementation(libs.findLibrary("coil.kt.compose").get())
        implementation(libs.findLibrary("androidx.compose.foundation").get())
        implementation(libs.findLibrary("androidx.compose.ui").get())
        implementation(libs.findLibrary("androidx.compose.ui.tooling").get())
        implementation(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
        implementation(libs.findLibrary("androidx.compose.material3").get())
        implementation(libs.findLibrary("androidx.compose.icons.core").get())
        implementation(libs.findLibrary("androidx.compose.icons.extended").get())
        implementation(libs.findLibrary("androidx.compose.runtime").get())
        implementation(libs.findLibrary("androidx.compose.runtime.livedata").get())
        implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
      }
    }
  }
}
