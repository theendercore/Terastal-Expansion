@file:Suppress("PropertyName")

architectury {
    common("fabric", "neoforge")
    platformSetupLoomIde()
}

val minecraft_version: String by project
val cobblemon_version: String by project
val parchment_version: String by project
val mixin_version: String by project

//loom.accessWidenerPath.set(file("src/main/resources/examplemod.accesswidener"))
sourceSets["main"].resources.srcDir("../generated")

dependencies {
    compileOnly("net.fabricmc:sponge-mixin:${mixin_version}")
    compileOnly("io.github.llamalad7:mixinextras-neoforge:0.4.1")
    modCompileOnly("com.cobblemon:mod:${cobblemon_version}") { isTransitive = false }
}
