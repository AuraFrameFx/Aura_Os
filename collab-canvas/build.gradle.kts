// Apply plugins (versions via version catalog)
plugins {
    id("genesis.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    // Note: Hilt plugin removed to avoid Android BaseExtension issues, using manual dependencies instead

}

android {
    namespace = "dev.aurakai.auraframefx.collabcanvas"
    
    defaultConfig {
        minSdk = 34
    }
    
    buildFeatures {
        buildConfig = true
        compose = true
    }
}



kotlin {
    jvmToolchain(24)
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Network
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.okhttp.logging.interceptor)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)

    // UI / Utils
    implementation(libs.coil.compose)
    implementation(libs.timber)
    implementation(fileTree("../Libs") { include("*.jar") })
    implementation(libs.gson)

    // Testing
    testImplementation(libs.junit4)
    testImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.tooling.preview)

    // YukiHook API (temporarily disabled due to configuration issues)
    // implementation(libs.yukihook.api)
    // ksp(libs.yukihook.ksp)
    
    // Xposed API (using the correct path)
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.register("collabStatus") {
    group = "aegenesis"
    doLast { println("COLLAB CANVAS - Ready (Java 24 toolchain, unified).") }
}
