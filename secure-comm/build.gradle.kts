// ==== GENESIS PROTOCOL - SECURE COMMUNICATION MODULE ====
// Security module using convention plugins

plugins {
    id("genesis.android.library")
    id("genesis.android.native")
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.securecomm"
    
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.network)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.timber)
    implementation(libs.coil.compose)
    // Security - BouncyCastle for cryptography
    implementation(libs.bcprov.jdk18on)
    // Test dependencies
    testImplementation(libs.junit4)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.mockk.android)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.hilt.android.testing)
}

// Spotless and toolchain are applied globally via root build.gradle.kts and convention plugins
// ProGuard rules for Hilt, Compose, Serialization, and reflection-based libraries should be in proguard-rules.pro
