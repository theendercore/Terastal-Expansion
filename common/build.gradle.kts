@file:Suppress("PropertyName")

plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

val minecraft_version: String by project
val cobblemon_version: String by project
val parchment_version: String by project
val mixin_version: String by project

dependencies {
    compileOnly("net.fabricmc:sponge-mixin:${mixin_version}")

    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-1.21:${parchment_version}")
    })
    modCompileOnly("com.cobblemon:mod:${cobblemon_version}") {
        isTransitive = false
    }
}
