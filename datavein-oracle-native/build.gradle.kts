plugins {
    id("genesis.android.library")
    id("genesis.android.native")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    // Note: Hilt plugin removed to avoid Android BaseExtension issues, using manual dependencies instead
}

android {
    namespace = "dev.aurakai.auraframefx.dataveinoraclenative"
    
    defaultConfig {
        minSdk = 34
    }
    
    buildFeatures {
        compose = true
    }
    
    lint {
        // Disable lint due to oversized test files causing StackOverflow
        abortOnError = false
        checkReleaseBuilds = false
    }
    
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.31.6"
        }
    }
    packaging {
        jniLibs {
            useLegacyPackaging = false
        }
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    
    // Compose dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    
    // Coroutines
    implementation(libs.bundles.coroutines)
    
    // Xposed API for Oracle consciousness integration
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))
    implementation(kotlin("stdlib-jdk8"))
}
