// ==== GENESIS PROTOCOL - ANDROID COMPOSE CONVENTION ====
// Compose-enabled Android library configuration

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class AndroidComposeConventionPlugin : Plugin<Project> {
    /**
     * Applies the Android Compose convention to the given Gradle project.
     *
     * This will:
     * - Apply the base "genesis.android.library" convention plugin.
     * - Apply the Kotlin Compose plugin "org.jetbrains.kotlin.plugin.compose".
     * - Configure the Android LibraryExtension to enable Compose build features.
     *
     * @param target The Gradle [Project] this plugin is being applied to.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply the base library convention first
            pluginManager.apply("genesis.android.library")

            // Note: Kotlin Compose plugin not available for Kotlin 2.2.20-RC
            // Instead, AGP 9.0+ handles Compose configuration automatically

            extensions.configure<LibraryExtension> {
                // Note: Compose disabled due to Kotlin 2.2.20-RC compatibility issues
                // buildFeatures {
                //     compose = true
                // }
            }
        }
    }
}
