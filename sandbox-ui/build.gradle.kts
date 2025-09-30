// ==== GENESIS PROTOCOL - SANDBOX UI ====
plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)

    // Note: Hilt plugin removed to avoid Android BaseExtension issues, using manual dependencies instead
}

android {
    namespace = "dev.aurakai.auraframefx.sandboxui"
    compileSdk = 36
    defaultConfig { minSdk = 34 }
    buildFeatures { compose = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }
}

kotlin {
    jvmToolchain(24)
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
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.hilt.android); ksp(libs.hilt.compiler)
    implementation(libs.bundles.coroutines)
    implementation(libs.timber); implementation(libs.coil.compose)
    testImplementation(libs.bundles.testing.unit); testImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.hilt.android.testing); kspTest(libs.hilt.compiler)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.hilt.android.testing); kspAndroidTest(libs.hilt.compiler)
    implementation(kotlin("stdlib-jdk8"))
}

tasks.register("sandboxStatus") {
    group = "aegenesis"; doLast { println("🧪 SANDBOX UI - Ready (Java 24)") }
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
