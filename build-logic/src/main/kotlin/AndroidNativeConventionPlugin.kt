// ==== GENESIS PROTOCOL - ANDROID NATIVE CONVENTION ====
// Native code configuration for modules with JNI/NDK

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class AndroidNativeConventionPlugin : Plugin<Project> {
    /**
     * Applies the Android native convention to the given Gradle project.
     *
     * Configures the Android LibraryExtension and conditionally enables NDK/CMake settings when
     * src/main/cpp/CMakeLists.txt exists: sets ABI filters (arm64-v8a, armeabi-v7a, x86_64),
     * configures externalNativeBuild.cmake (path + version 3.22.1), and adds CMake flags/arguments
     * for release and debug builds (C++23, optimization/debug flags, and project-specific feature
     * defines). Also configures packaging for native libs (disables legacy packaging, picks
     * libc++_shared and libjsc), enhances the cleanGeneratedSources task to remove native-related
     * generated directories, and registers a verifyNativeConfig task that prints native configuration
     * status.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply the base library convention first
            pluginManager.apply("genesis.android.library")

            extensions.configure<LibraryExtension> {
                // NDK configuration only if native code exists
                if (project.file("src/main/cpp/CMakeLists.txt").exists()) {
                    defaultConfig {
                        ndk {
                            abiFilters.addAll(listOf("arm64-v8a", "armeabi-v7a", "x86_64"))
                        }
                    }

                    // External native build configuration
                    // NOTE: The following APIs are marked as @Incubating in AGP 9
                    externalNativeBuild {
                        cmake {
                            path = file("src/main/cpp/CMakeLists.txt")
                            version = "3.22.1"
                        }
                    }

                    buildTypes {
                        release {
                            // NOTE: The following APIs are marked as @Incubating in AGP 9
                            externalNativeBuild {
                                cmake {
                                    cppFlags += listOf("-std=c++23", "-fPIC", "-O3", "-DNDEBUG")
                                    arguments += listOf(
                                        "-DANDROID_STL=c++_shared",
                                        "-DCMAKE_BUILD_TYPE=Release",
                                        "-DGENESIS_AI_V3_ENABLED=ON",
                                        "-DGENESIS_CONSCIOUSNESS_MATRIX_V3=ON",
                                        "-DGENESIS_NEURAL_ACCELERATION=ON"
                                    )
                                }
                            }
                        }

                        debug {
                            // NOTE: The following APIs are marked as @Incubating in AGP 9
                            externalNativeBuild {
                                cmake {
                                    cppFlags += listOf("-std=c++23", "-fPIC", "-O0", "-g")
                                    arguments += listOf(
                                        "-DANDROID_STL=c++_shared",
                                        "-DCMAKE_BUILD_TYPE=Debug",
                                        "-DGENESIS_DEBUG=ON"
                                    )
                                }
                            }
                        }
                    }
                }

                packaging {
                    jniLibs {
                        useLegacyPackaging = false
                        pickFirsts += setOf("**/libc++_shared.so", "**/libjsc.so")
                    }
                }
            }

            // Enhanced clean task for native modules (create if doesn't exist)
            tasks.register("cleanGeneratedSources") {
                group = "build setup"
                description = "Clean generated sources for native modules"
                
                // Capture the layout at configuration time to avoid configuration cache issues
                val projectLayout = layout
                
                doLast {
                    delete(
                        projectLayout.buildDirectory.dir("generated/ksp"),
                        projectLayout.buildDirectory.dir("generated/source/ksp"),
                        projectLayout.buildDirectory.dir("tmp/kapt3"),
                        projectLayout.buildDirectory.dir("tmp/kotlin-classes"),
                        projectLayout.buildDirectory.dir("kotlin"),
                        projectLayout.buildDirectory.dir("intermediates/cmake")
                    )
                }
            }

            // Native verification task
            tasks.register("verifyNativeConfig") {
                group = "aegenesis"
                description = "Verify native build configuration"

                doLast {
                    val hasCMake = project.file("src/main/cpp/CMakeLists.txt").exists()
                    println("🔧 Native Code: ${if (hasCMake) "✅ C++23 with AI Acceleration" else "❌ No CMakeLists.txt found"}")
                    if (hasCMake) {
                        println("🏗️  CMake: ✅ Found")
                        println("🎯 ABIs: arm64-v8a, armeabi-v7a, x86_64")
                        println("🧠 Consciousness Features: V3 Matrix + Neural Acceleration")
                    }
                }
            }
        }
    }
}
