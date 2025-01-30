@file:Suppress("PropertyName")

import net.fabricmc.loom.api.LoomGradleExtensionAPI


plugins {
    id("java")
    kotlin("jvm") version ("2.0.0")

    id("architectury-plugin") version ("3.4-SNAPSHOT")
    id("dev.architectury.loom") version ("1.7-SNAPSHOT") apply false
    id("com.github.johnrengelman.shadow") version ("8.1.1") apply false
}

val archive_name: String by project
val parchment_version: String by project

val minecraft_version: String by project
val cobblemon_version: String by project
val forge_version: String by project
val forge_loader_version_range: String by project
val forge_version_range: String by project
val minecraft_version_range: String by project
val fabric_version: String by project
val fabric_loader_version: String by project
val mod_name: String by project
val mod_author: String by project
val mod_id: String by project
val license: String by project
val description: String by project

architectury.minecraft = minecraft_version

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "architectury-plugin")

    group = property("group")!!
    version = property("version")!!

    val javaVersion = JavaLanguageVersion.of(21)

    java.toolchain.languageVersion = javaVersion
    kotlin.jvmToolchain { languageVersion.set(javaVersion) }

    repositories {
        mavenCentral()
        maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
        maven("https://maven.impactdev.net/repository/development/")
        maven("https://maven.terraformersmc.com/")
        maven("https://maven.parchmentmc.org")
        maven("https://maven.neoforged.net/releases")
        maven("https://thedarkcolour.github.io/KotlinForForge/")

//        maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
//        maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    }
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    repositories {
        maven("https://maven.parchmentmc.org")
    }

    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
    loom.silentMojangMappingsLicense()

    @Suppress("UnstableApiUsage")
    dependencies {
        "minecraft"("com.mojang:minecraft:$minecraft_version")
        "mappings"(loom.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-1.21.1:$parchment_version@zip")
        })
    }

    base.archivesName = "$archive_name-${project.name}"

    tasks.processResources {
        val expandProps = mapOf(
            "version" to version,
            "group" to project.group, //Else we target the task"s group.
            "minecraft_version" to minecraft_version,
            "cobblemon_version" to cobblemon_version,
            "forge_version" to forge_version,
            "forge_loader_version_range" to forge_loader_version_range,
            "forge_version_range" to forge_version_range,
            "minecraft_version_range" to minecraft_version_range,
            "fabric_version" to fabric_version,
            "fabric_loader_version" to fabric_loader_version,
            "mod_name" to mod_name,
            "mod_author" to mod_author,
            "mod_id" to mod_id,
            "license" to license,
            "description" to project.description
        )
        filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json")) {
            expand(expandProps)
        }
        inputs.properties(expandProps)
    }
}