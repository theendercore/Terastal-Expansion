plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

architectury {
    platformSetupLoomIde()
    forge()
}

loom {
    enableTransitiveAccessWideners.set(true)
    silentMojangMappingsLicense()
}

repositories {
    mavenCentral()
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://maven.impactdev.net/repository/development/")
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

dependencies {
    minecraft("net.minecraft:minecraft:${minecraft_version}")
    mappings(loom.officialMojangMappings())
    forge("net.minecraftforge:forge:${minecraft_version}-${forge_version}")


    implementation(project(":common", configuration = "namedElements"))
    "developmentNeoForge"(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }



    modImplementation("com.cobblemon:neoforge:${cobblemon_version}")
    //Needed for cobblemon
    modImplementation("thedarkcolour:kotlinforforge:${forge_kotlin_version}")

//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

/*
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
*/
