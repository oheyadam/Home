import com.oheyadam.home.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("com.android.library")
        apply("home.android.hilt")
      }

      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
      dependencies {
        implementation(project(":core-design"))
        implementation(project(":core-common"))
        implementation(project(":core-model"))
        implementation(project(":library-data"))
        implementation(libs.findLibrary("kotlinx.coroutines.android").get())
        implementation(libs.findLibrary("androidx.viewmodel.ktx").get())
        implementation(libs.findLibrary("coil.kt").get())
      }
    }
  }
}
