// ==== GENESIS PROTOCOL - ORACLE DRIVE INTEGRATION ====
// AI storage module using convention plugins

plugins {
    id("genesis.android.library")
    alias(libs.plugins.ksp)

}

android {
    namespace = "dev.aurakai.auraframefx.oracledriveintegration"
    
    defaultConfig {
        minSdk = 34
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(project(":secure-comm"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.bundles.coroutines)
    // Add other module-specific dependencies here
    implementation(kotlin("stdlib-jdk8"))
}

kotlin {
    jvmToolchain(24)
}
