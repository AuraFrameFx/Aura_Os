import org.openapitools.generator.gradle.plugin.tasks.OpenApiGenerateTask

plugins {
    id("org.openapi.generator")
    id("org.jetbrains.kotlin.jvm")
}

tasks.register<OpenApiGenerateTask>("openApiGenerate") {
    generatorName.set("kotlin")
    inputSpec.set(file("${projectDir}/api/system-api.yml"))
    outputDir.set(layout.buildDirectory.dir("generated/openapi").get().asFile)
    apiPackage.set("dev.aurakai.auraframefx.api")
    modelPackage.set("dev.aurakai.auraframefx.model")
    configOptions.set(
        mapOf(
            "library" to "jvm-ktor",
            "serializationLibrary" to "kotlinx-serialization"
        )
    )
}

sourceSets["main"].java.srcDir(tasks.named("openApiGenerate").map { it.outputs.files })

dependencies {
    implementation(kotlin("stdlib"))
    // Add Ktor, kotlinx-serialization, or other dependencies as needed
}

