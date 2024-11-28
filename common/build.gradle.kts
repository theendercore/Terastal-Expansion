plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1")//${minecraft_version}")
    mappings(loom.officialMojangMappings())
//    modCompileOnly("com.cobblemon:mod:${cobblemon_version}") {
//        isTransitive = false
//    }

//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

//tasks.getByName<Test>("test") {
//    useJUnitPlatform()
//}