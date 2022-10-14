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
        add("implementation", project(":core-design"))
        add("implementation", project(":core-common"))
        add("implementation", project(":core-model"))
        add("implementation", project(":library-data"))
        add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
        add("implementation", libs.findLibrary("androidx.viewmodel.ktx").get())
        add("implementation", libs.findLibrary("coil.kt").get())
      }
    }
  }
}
