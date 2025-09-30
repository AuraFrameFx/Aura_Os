// ==== GENESIS PROTOCOL - COLORBLENDR MODULE ====
// Color utility and theming module

plugins {
    id("genesis.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    // Note: Hilt plugin removed to avoid Android BaseExtension issues, using manual dependencies instead
}

android {
    namespace = "dev.aurakai.auraframefx.colorblendr"
    
    defaultConfig {
        minSdk = 34
    }
    
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    // Core
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    
    // Lifecycle
    implementation(libs.bundles.lifecycle)
    
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    
    // Utilities
    implementation(libs.timber)
    
    // Testing
    testImplementation(libs.junit4)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.bundles.testing.android)
    implementation(kotlin("stdlib-jdk8"))
}
