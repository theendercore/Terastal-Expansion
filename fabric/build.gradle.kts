plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

architectury {
    platformSetupLoomIde()
    fabric()
}

val mod_id: String by project
loom {
    enableTransitiveAccessWideners.set(true)
    silentMojangMappingsLicense()

    mixin {
        defaultRefmapName.set("mixins.${project.name}.refmap.json")
    }

    runs {
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

val minecraft_version: String by project
val cobblemon_version: String by project
val emi_version: String by project
val parchment_version: String by project

val fabric_loader_version: String by project
val fabric_version: String by project
val fabric_kotlin_version: String by project


sourceSets["main"].resources.srcDir("../generated")

dependencies {
    minecraft("net.minecraft:minecraft:${minecraft_version}")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-1.21:${parchment_version}")
    })

    modImplementation("net.fabricmc:fabric-loader:${fabric_loader_version}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabric_version}")
    modImplementation("com.cobblemon:fabric:${cobblemon_version}")

    implementation(project(":common", configuration = "namedElements"))
    "developmentFabric"(project(":common", configuration = "namedElements"))

    modImplementation("net.fabricmc:fabric-language-kotlin:${fabric_kotlin_version}")

    modImplementation("com.terraformersmc:modmenu:11.0.3")
//    modCompileOnly "dev.emi:emi-fabric:${emi_version}:api"
    modLocalRuntime("dev.emi:emi-fabric:${emi_version}")
}
