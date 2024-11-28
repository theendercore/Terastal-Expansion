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

dependencies {
    minecraft("net.minecraft:minecraft:${cobblemon_version}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${fabric_loader_version}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabric_version}")
//    modImplementation(fabricApi.module("fabric-command-api-v2", "0.100.7+1.21"))
    modImplementation("com.cobblemon:fabric:${cobblemon_version}")

    implementation(project(":common", configuration = "namedElements"))
    "developmentFabric"(project(":common", configuration = "namedElements"))

//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

//tasks.getByName<Test>("test") {
//    useJUnitPlatform()
//}