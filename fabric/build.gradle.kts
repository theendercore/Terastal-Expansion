plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

architectury {
    platformSetupLoomIde()
    fabric()
}

loom {
    enableTransitiveAccessWideners.set(true)
    silentMojangMappingsLicense()

    mixin {
        defaultRefmapName.set("mixins.${project.name}.refmap.json")
    }
}

val minecraft_version: String by project
val cobblemon_version: String by project
val emi_version: String by project

val fabric_loader_version: String by project
val fabric_version: String by project

dependencies {
    minecraft("net.minecraft:minecraft:${minecraft_version}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${fabric_loader_version}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabric_version}")
    modImplementation("com.cobblemon:fabric:${cobblemon_version}")

    implementation(project(":common", configuration = "namedElements"))
    "developmentFabric"(project(":common", configuration = "namedElements"))

    modImplementation("com.terraformersmc:modmenu:7.1.0")
//    modCompileOnly "dev.emi:emi-fabric:${emi_version}:api"
    modLocalRuntime ("dev.emi:emi-fabric:${emi_version}")
}
