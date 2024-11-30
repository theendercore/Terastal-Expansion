@file:Suppress("PropertyName")

plugins {
    id("java")
    id("java-library")
    kotlin("jvm") version ("2.0.0")

    id("dev.architectury.loom") version ("1.7-SNAPSHOT") apply false
    id("architectury-plugin") version ("3.4-SNAPSHOT") apply false
}

group = property("group")!!
version = property("version")!!

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
        maven(url = "https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
        maven("https://maven.impactdev.net/repository/development/")
        maven("https://maven.terraformersmc.com/")

    }
}

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

subprojects {
    apply(plugin = "java")

    val javaVersion = JavaLanguageVersion.of(17)

    java.toolchain.languageVersion = javaVersion
    kotlin {
        jvmToolchain {
            languageVersion.set(javaVersion)
        }
    }

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
        filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "META-INF/mods.toml", "*.mixins.json")) {
            expand(expandProps)
        }
        inputs.properties(expandProps)
    }
}