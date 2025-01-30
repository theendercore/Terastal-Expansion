plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

configurations {
    create("common")
    create("shadowCommon")
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    getByName("developmentNeoForge").extendsFrom(configurations["common"])
}

val neoforge_version: String by project
val forge_kotlin_version: String by project

val cobblemon_version: String by project

val emi_version: String by project
//loom.accessWidenerPath.set(project(":common").loom.accessWidenerPath)
//sourceSets["main"].resources.srcDir("../generated")
dependencies {
    neoForge("net.neoforged:neoforge:$neoforge_version")
    implementation("thedarkcolour:kotlinforforge-neoforge:$forge_kotlin_version") {
        exclude("net.neoforged.fancymodloader", "loader")
    }
    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionNeoForge")) { isTransitive = false }

    modImplementation("com.cobblemon:neoforge:$cobblemon_version")

    modLocalRuntime("dev.emi:emi-neoforge:$emi_version")
}

tasks {
    shadowJar {
        exclude("fabric.mod.json")
        configurations = listOf(project.configurations.getByName("shadowCommon"))
        archiveClassifier.set("dev-shadow")
    }
    remapJar {
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
    }
    jar.get().archiveClassifier.set("dev")
}

components {
    java.run {
        if (this is AdhocComponentWithVariants)
            withVariantsFromConfiguration(project.configurations.shadowRuntimeElements.get()) { skip() }
    }
}
