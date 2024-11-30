plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

architectury {
    platformSetupLoomIde()
    forge()
}

val mod_id: String by project

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

val minecraft_version: String by project
val cobblemon_version: String by project
val emi_version: String by project
val yarn_version: String by project

val forge_version: String by project
val forge_kotlin_version: String by project

dependencies {
    minecraft("net.minecraft:minecraft:${minecraft_version}")
    mappings("net.fabricmc:yarn:${yarn_version}:v2")
    forge("net.minecraftforge:forge:${minecraft_version}-${forge_version}")


    implementation(project(":common", configuration = "namedElements"))
    "developmentForge"(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }

    modImplementation("com.cobblemon:forge:${cobblemon_version}")
    //Needed for cobblemon
    runtimeOnly("thedarkcolour:kotlinforforge:${forge_kotlin_version}")
    implementation("thedarkcolour:kotlinforforge:${forge_kotlin_version}")

    //    modCompileOnly "dev.emi:emi-fabric:${emi_version}:api"
    modLocalRuntime("dev.emi:emi-forge:${emi_version}")
}
