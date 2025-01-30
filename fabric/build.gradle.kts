plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

architectury {
    platformSetupLoomIde()
    fabric()
}

configurations {
    create("common")
    create("shadowCommon")
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    getByName("developmentFabric").extendsFrom(configurations["common"])
}


val mod_id: String by project
loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)

    mixin {
        defaultRefmapName.set("mixins.${project.name}.refmap.json")
    }

    runs {
        create("ClientFixed") {
            client()
            ideConfigGenerated(true)
            programArgs("--username", "Dev")
        }

        create("DataGen") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("../generated")}")
            vmArg("-Dfabric-api.datagen.modid=${mod_id}")
            runDir("build/datagen")
        }
    }
}
//sourceSets["main"].resources.srcDir("../generated")
val fabric_loader_version: String by project
val fabric_version: String by project
val fabric_kotlin_version: String by project

val cobblemon_version: String by project

val emi_version: String by project

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${fabric_loader_version}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabric_version}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${fabric_kotlin_version}")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionFabric")) { isTransitive = false }

    modImplementation("com.cobblemon:fabric:$cobblemon_version")

    modImplementation("com.terraformersmc:modmenu:11.0.3")
//    modCompileOnly "dev.emi:emi-fabric:$emi_version:api"
    modLocalRuntime("dev.emi:emi-fabric:$emi_version")
}

tasks {
    shadowJar {
        configurations = listOf(project.configurations.getByName("shadowCommon"))
        archiveClassifier.set("dev-shadow")
    }
    remapJar {
        injectAccessWidener.set(true)
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
