import com.android.build.gradle.LibraryExtension
import com.oheyadam.home.configureAndroidCompose
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
        add("implementation", project(":core-design"))
        add("implementation", libs.findLibrary("coil.kt.compose").get())
        add("implementation", libs.findLibrary("androidx.compose.foundation").get())
        add("implementation", libs.findLibrary("androidx.compose.ui").get())
        add("implementation", libs.findLibrary("androidx.compose.ui.tooling").get())
        add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())
        add("implementation", libs.findLibrary("androidx.compose.material").get())
        add("implementation", libs.findLibrary("androidx.compose.material3").get())
        add("implementation", libs.findLibrary("androidx.compose.icons.core").get())
        add("implementation", libs.findLibrary("androidx.compose.icons.extended").get())
        add("implementation", libs.findLibrary("androidx.compose.runtime").get())
        add("implementation", libs.findLibrary("androidx.compose.runtime.livedata").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
      }
    }
  }
}
