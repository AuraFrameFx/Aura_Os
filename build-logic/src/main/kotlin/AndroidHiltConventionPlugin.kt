// ==== GENESIS PROTOCOL - ANDROID HILT CONVENTION ====
// Hilt dependency injection configuration for Android modules

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    /**
     * Applies the Android Hilt convention to the given Gradle project.
     *
     * This will:
     * - Apply the base "genesis.android.library" convention plugin.
     * - Apply the Dagger Hilt Android plugin.
     * - Apply the KSP plugin for annotation processing.
     * - Add necessary Hilt dependencies.
     *
     * target The Gradle [Project] this plugin is being applied to.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply the base Android plugin if not present
            val hasApp = plugins.hasPlugin("com.android.application")
            val hasLib = plugins.hasPlugin("com.android.library")
            if (!hasApp && !hasLib) {
                pluginManager.apply("genesis.android.library")
            }

            // Apply Hilt-specific plugins
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
            }

            // Configure dependencies through version catalog
            dependencies {
                add("implementation", project.dependencies.create("com.google.dagger:hilt-android:2.51.1"))
                add("annotationProcessor", project.dependencies.create("com.google.dagger:hilt-compiler:2.51.1"))
            }
        }
    }
}
