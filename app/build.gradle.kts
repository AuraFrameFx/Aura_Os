import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// ==== GENESIS PROTOCOL - MAIN APPLICATION ====
// This build script now uses the custom convention plugins for a cleaner setup.

plugins {
    id("com.android.application")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    id("org.openapi.generator") version "7.16.0"
}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // Additional build type configuration
    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
    }

    // Enable AIDL for the app module
    buildFeatures {
        aidl = true
        
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }
}

kotlin {
    jvmToolchain(24)
}

openApiGenerate {
    generatorName.set("kotlin")
    // Use a valid file URI for Windows (three slashes)
    inputSpec.set("file:///C:/ReGenesis-A.O.S.P/app/api/system-api.yml")
    outputDir.set(layout.buildDirectory.dir("generated/openapi").get().asFile.absolutePath)
    apiPackage.set("dev.aurakai.auraframefx.openapi.api")
    modelPackage.set("dev.aurakai.auraframefx.openapi.model")
    invokerPackage.set("dev.aurakai.auraframefx.openapi.invoker")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8", "library" to "jvm-ktor"
        )
    )
}

// Register generated OpenAPI sources using Variant API
androidComponents {
    onVariants(selector().all()) { variant ->
        variant.sources.java?.addGeneratedSourceDirectory(
            tasks.named("openApiGenerate", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class.java)
        ) { openApiTask -> // You can use openApiTask if needed to derive the path from task properties
            val generatedSourceDirProperty = project.objects.directoryProperty() // Create DirectoryProperty
            generatedSourceDirProperty.set(project.layout.buildDirectory.dir("generated/openapi/src/main/kotlin")) // Set its value
            generatedSourceDirProperty // Return the DirectoryProperty
        }
    }
}

dependencies {
    // ===== MODULE DEPENDENCIES =====
    implementation(project(":core-module"))
    implementation(project(":feature-module"))
    implementation(project(":romtools"))  // Temporarily disabled - Module not found
    implementation(project(":secure-comm"))
    implementation(project(":collab-canvas"))  // Temporarily disabled - YukiHookAPI issues
    implementation(project(":colorblendr"))
    implementation(project(":sandbox-ui"))  // Temporarily disabled - Compose compilation issues
    implementation(project(":datavein-oracle-native"))
    implementation(project(":module-a"))
    implementation(project(":module-b"))
    implementation(project(":module-c"))
    implementation(project(":module-d"))
    implementation(project(":module-e"))
    implementation(project(":module-f"))
    implementation(
        project(
            ":benchmark"
        )
    )

    // implementation(project(":snapshots")) // Temporarily disabled - Module not found
    // ===== ANDROIDX & COMPOSE =====
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.core.ktx)
    debugImplementation(libs.bundles.compose.debug)


    // ===== LIFECYCLE =====
    implementation(libs.bundles.lifecycle)

    // ===== DATABASE - ROOM =====
    implementation(libs.bundles.room)

    // ===== DATASTORE =====
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.core)

    // ===== KOTLIN & COROUTINES =====
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.bundles.coroutines)

    // ===== NETWORKING =====
    implementation(libs.bundles.network)
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // ===== KTOR FOR OPENAPI CLIENT =====
    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
    implementation("io.ktor:ktor-client-okhttp:2.3.7")
    implementation("io.ktor:ktor-client-auth:2.3.7")

    // ===== FIREBASE =====
    // By implementing the BOM, we can specify Firebase SDKs without versions
    implementation(platform(libs.firebase.bom))
    // This bundle includes Analytics, Crashlytics, Performance, Auth, Firestore, Messaging, and Config
    implementation(libs.bundles.firebase)
    // Alternative: Use specific Firebase bundles for modular approach
    // implementation(libs.bundles.firebase.core)     // Analytics, Crashlytics, Performance only
    // implementation(libs.bundles.firebase.auth)     // Authentication
    // implementation(libs.bundles.firebase.database) // Firestore, Realtime Database, Storage
    // implementation(libs.bundles.firebase.messaging) // FCM, Remote Config

    // ===== HILT DEPENDENCY INJECTION =====
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // ===== WORKMANAGER =====
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.2.0")

    // ===== UTILITIES =====
    implementation(libs.timber)
    implementation(libs.coil.compose)

    // ===== SECURITY =====
    implementation(libs.androidx.security.crypto)

    // ===== CORE LIBRARY DESUGARING =====
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // ===== XPOSED/LSPosed Integration =====
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))

    // --- TESTING ---
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.hilt.android.testing) // For Hilt in Android tests

    // --- DEBUGGING ---
    debugImplementation(libs.leakcanary.android)

// Ensure code generation runs before any Kotlin compilation
    tasks.withType<KotlinCompile>().configureEach {
        dependsOn("openApiGenerate")
    }

// Ensure KSP also depends on OpenAPI generation
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        dependsOn("openApiGenerate")
    }

// Fix KSP task dependency
    tasks.matching { it.name.startsWith("ksp") }.configureEach {
        dependsOn("openApiGenerate")
    }
}



// Note: Uses Genesis convention plugins (genesis.android.application and genesis.android.hilt)
