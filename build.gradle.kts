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
