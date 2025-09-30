/*
 * Android Library conventions for AeGenesis project
 * Applies common conventions and configures Android library specific settings
 */

plugins {
    id("com.android.library")
}

android {
    namespace = "com.aegenesis.${project.name.replace("-", "")}"

    compileSdk = 36 // Unified to 36

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Java toolchain replaces manual source/target compatibility
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(24))
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Configure test tasks
tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// Publishing configuration removed to avoid dependency issues
