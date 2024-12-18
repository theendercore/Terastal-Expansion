@file:Suppress("PropertyName")

plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

val minecraft_version: String by project
val cobblemon_version: String by project
val yarn_version: String by project
val fabric_loader_version: String by project

dependencies {
    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${fabric_loader_version}")

    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:${yarn_version}:v2")
    modCompileOnly("com.cobblemon:mod:${cobblemon_version}") {
        isTransitive = false
    }
}
