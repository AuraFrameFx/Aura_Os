// ==== GENESIS PROTOCOL - ANDROID LIBRARY CONVENTION ====
// Standard Android library configuration for all modules

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    /**
     * Applies Android library and Kotlin Android plugins and configures project-level Android and Kotlin settings.
     *
     * Configures the Android LibraryExtension and KotlinJvmProjectExtension for the target project:
     * - Applies the "com.android.library" plugin.
     * - Sets Android compileSdk to 36 and default minSdk to 34.
     * - Forces Java source and target compatibility to Java 24.
     * - Sets the Kotlin JVM toolchain to Java 24.
     *
     * @param target The Gradle project to which the plugin is applied; this method mutates the project's plugins and extensions.
     */
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 36

                defaultConfig {
                    minSdk = 34
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_24
                    targetCompatibility = JavaVersion.VERSION_24
                }
            }
        }
    }
}
