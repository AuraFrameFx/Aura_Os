// GENESIS PROTOCOL - MODULES A-F
// Module E
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.module.e"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    
    // Add other module-specific dependencies here
    implementation(kotlin("stdlib-jdk8"))
}

tasks.register("moduleEStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE E - Ready (Java 24)") }
}

kotlin {
    jvmToolchain(24)
}
