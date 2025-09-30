plugins {
    // JVM library setup
    id("java-library")
    kotlin("jvm")

    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
}
group = "dev.aurakai.auraframefx.utilities"
version = "1.0.0"
// ... existing code ...

dependencies {
    // Module dependency
    api(project(":list"))

    // Concurrency and serialization
    implementation(libs.bundles.coroutines)
    implementation(libs.kotlinx.serialization.json)

    // File operations and compression
    implementation(libs.commons.io)
    implementation(libs.commons.compress)
    implementation(libs.xz)

    // Logging API only (do not bind implementation at runtime for libraries)
    implementation(libs.slf4j.api)

    // Testing (JUnit 5)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.mockk)
    testRuntimeOnly(libs.slf4j.simple)
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

// Centralized toolchain version to avoid duplication and drift
val jdkVersion = 24

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(jdkVersion)) }
}

// ... existing code ...

kotlin {
    jvmToolchain(jdkVersion)
}

// ... existing code ...
