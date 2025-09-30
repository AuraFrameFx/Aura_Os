// GENESIS PROTOCOL - MODULE D
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.module.d"
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

tasks.register("moduleDStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE D - Ready (Java 24)") }
}
