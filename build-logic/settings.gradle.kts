// ==== GENESIS PROTOCOL - BUILD LOGIC SETTINGS ====

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://repo.gradle.org/gradle/libs-releases")
        }
    }
    plugins {
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
        plugins {
        }
        dependencyResolutionManagement {
            repositories {
                google()
                mavenCentral()
                gradlePluginPortal()
                maven {
                    url = uri("https://repo.gradle.org/gradle/libs-releases")
                }
            }
            versionCatalogs {
            }
            pluginManagement {
            }
        }
    }
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            gradlePluginPortal()
            maven {
                url = uri("https://repo.gradle.org/gradle/libs-releases")
            }
        }
        versionCatalogs {
            create("libs") {
                from(files("../gradle/libs.versions.toml"))
            }
        }
    }
    // Note: Only include builds that actually exist
    // includeBuild("../build-logic") // Self-reference not needed
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://repo.gradle.org/gradle/libs-releases")
        }
    }
}

// Build-logic only contains convention plugins, no app modules

rootProject.name = "build-logic"
