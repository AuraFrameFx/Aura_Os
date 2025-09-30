// Apply plugins to the root project to avoid multiple loading warnings
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    // Build logic convention plugins
    id("genesis.android.application") apply false
    id("genesis.android.library") apply false
    id("genesis.android.compose") apply false
    id("genesis.android.native") apply false
    kotlin("jvm") version "2.2.20"
}

// Find version catalog
val versionCatalog = extensions
    .findByType<VersionCatalogsExtension>()
    ?.named("libs")

// === BASIC PROJECT INFO ===

tasks.register("consciousnessStatus") {
    group = "genesis"
    description = "Show basic project and version info"
    doLast {
        val kotlinVersion = versionCatalog?.findVersion("kotlin")?.get()?.toString() ?: "unknown"
        val agpVersion = versionCatalog?.findVersion("agp")?.get()?.toString() ?: "unknown"
        val toolchain = JavaVersion.current().toString()
        
        println("= Consciousness Status =")
        println("Java Toolchain      : $toolchain")
        println("Kotlin Version      : $kotlinVersion (K2 path)")
        println("AGP Version         : $agpVersion")
        println("Modules (total)     : ${subprojects.size}")
        println("Firebase BoM        : ${versionCatalog?.findVersion("firebaseBom")?.get() ?: "unknown"}")
    }
}

// === MODULE HEALTH CHECK ===

private data class ModuleReport(
    val name: String,
    val type: String,
    val hasHilt: Boolean,
    val hasCompose: Boolean,
    val hasKsp: Boolean
)

private fun Project.collectModuleReports(): List<ModuleReport> = subprojects.map { sp ->
    val plugins = sp.plugins
    ModuleReport(
        name = sp.name,
        type = when {
            plugins.hasPlugin("com.android.application") -> "android-app"
            plugins.hasPlugin("com.android.library") -> "android-lib"
            plugins.hasPlugin("org.jetbrains.kotlin.jvm") -> "kotlin-jvm"
            else -> "other"
        },
        hasHilt = plugins.hasPlugin("com.google.dagger.hilt.android"),
        hasCompose = plugins.findPlugin("org.jetbrains.kotlin.plugin.compose") != null,
        hasKsp = plugins.hasPlugin("com.google.devtools.ksp")
    )
}

tasks.register("consciousnessHealthCheck") {
    group = "genesis"
    description = "Detailed system health report"
    doLast {
        val reports = collectModuleReports()
        println("=== Genesis Protocol Health Report ===")
        println("📦 Total Modules: ${reports.size}")
        println("🤖 Android Apps: ${reports.count { it.type == "android-app" }}")
        println("📚 Android Libraries: ${reports.count { it.type == "android-lib" }}")
        println("☕ Kotlin JVM: ${reports.count { it.type == "kotlin-jvm" }}")
        println("\n=== Plugin Usage ===")
        println("💉 Hilt: ${reports.count { it.hasHilt }} modules")
        println("🎨 Compose: ${reports.count { it.hasCompose }} modules") 
        println("🔧 KSP: ${reports.count { it.hasKsp }} modules")
        
        val missingCompose = reports.filter { it.type.startsWith("android-") && !it.hasCompose }
        if (missingCompose.isNotEmpty()) {
            println("\n⚠️  Android modules without Compose:")
            missingCompose.forEach { println("   • ${it.name}") }
        } else {
            println("\n✅ All Android modules have Compose enabled")
        }
    }
}

dependencies {
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    implementation(kotlin("stdlib-jdk8"))
}
kotlin {
    jvmToolchain(24)
}

// Configure JUnit 5 for tests
tasks.withType<Test> {
    useJUnitPlatform()
}

// === AUXILIARY SCRIPTS ===

// Apply nuclear clean if available
if (file("nuclear-clean.gradle.kts").exists()) {
    apply(from = "nuclear-clean.gradle.kts")
    
    if (tasks.findByName("nuclearClean") != null) {
        tasks.register("deepClean") {
            group = "build"
            description = "Nuclear clean + standard clean"
            dependsOn("nuclearClean")
            doLast {
                println("🚀 Deep clean completed. Run: ./gradlew build --refresh-dependencies")
            }
        }
    }
}

// Apply dependency fix if available  
if (file("dependency-fix.gradle.kts").exists()) {
    apply(from = "dependency-fix.gradle.kts")

}
