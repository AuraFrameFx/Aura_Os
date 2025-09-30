// ==== GENESIS PROTOCOL - FEATURE MODULE ====
// Primary feature module using convention plugins

plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dokka)
    // Note: Hilt plugin removed to avoid Android BaseExtension issues, using manual dependencies instead
    alias(libs.plugins.ksp)  // Required for Hilt annotation processing

}


android {
    namespace = "dev.aurakai.auraframefx.featuremodule"
    compileSdk = 36

    defaultConfig {
        minSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }

    kotlin {
        jvmToolchain(24)
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(project(":core-module"))
    implementation(libs.bundles.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.network)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.timber)
    implementation(libs.coil.compose)
    implementation(fileTree("../Libs") { include("*.jar") })
    testImplementation(libs.bundles.testing.unit)
    testImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.hilt.android.testing)
    debugImplementation(libs.leakcanary.android)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(kotlin("stdlib-jdk8"))
}

tasks.register("featureStatus") {
    group = "aegenesis"
    doLast { println("🚀 FEATURE MODULE - ${android.namespace} - Ready (Java 17)!") }
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            sourceRoots.from(file("src/main/java"))
            sourceRoots.from(file("src/main/kotlin"))
            sourceRoots.from(file("src/main/res"))
        }
    }
}
