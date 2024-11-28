@file:Suppress("PropertyName")

plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

val minecraft_version: String by project
val cobblemon_version: String by project

dependencies {
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings(loom.officialMojangMappings())
    modCompileOnly("com.cobblemon:mod:${cobblemon_version}") {
        isTransitive = false
    }
}
