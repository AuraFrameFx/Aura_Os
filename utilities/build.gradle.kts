plugins {
    // JVM library setup
    id("java-library")
    kotlin("jvm")

    // Additional tooling
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
}

group = "dev.aurakai.auraframefx.utilities"
version = "1.0.0"

// Centralized toolchain version
val jdkVersion = 24

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(jdkVersion)) }
}

kotlin {
    jvmToolchain(jdkVersion)
}

dependencies {
    // Module dependency (utilities depends on list)
    api(project(":list"))

    // Concurrency and serialization
    implementation(libs.bundles.coroutines)
    implementation(libs.kotlinx.serialization.json)

    // File operations and compression
    implementation(libs.commons.io)
    implementation(libs.commons.compress)
    implementation(libs.xz)

    // Logging API only (no binding at library runtime)
    implementation(libs.slf4j.api)

    // Testing (JUnit 5)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(libs.mockk)
    testImplementation(kotlin("stdlib"))
    // Bind a simple logger only during tests
    testRuntimeOnly(libs.slf4j.simple)
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
