plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

architectury {
    platformSetupLoomIde()
    neoForge()
}

val mod_id: String by project

loom {
//    enableTransitiveAccessWideners.set(true)
//    silentMojangMappingsLicense()
    neoForge {
//        mixinConfig("${mod_id}.common.mixins.json")
    }
}

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases")
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://maven.impactdev.net/repository/development/")
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

val minecraft_version: String by project
val cobblemon_version: String by project
val emi_version: String by project
val parchment_version: String by project

val forge_version: String by project
val neoforge_version: String by project
val forge_kotlin_version: String by project

sourceSets["main"].resources.srcDir("../generated")

dependencies {
    minecraft("net.minecraft:minecraft:${minecraft_version}")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-1.21:${parchment_version}")
    })

    neoForge("net.neoforged:neoforge:${neoforge_version}")


    implementation(project(":common", configuration = "namedElements"))
    "developmentNeoForge"(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }

    modImplementation("com.cobblemon:neoforge:${cobblemon_version}")
    //Needed for cobblemon
//    runtimeOnly("thedarkcolour:kotlinforforge:${forge_kotlin_version}")
    implementation("thedarkcolour:kotlinforforge-neoforge:${forge_kotlin_version}"){
        exclude("net.neoforged.fancymodloader", "loader")
    }


    //    modCompileOnly "dev.emi:emi-fabric:${emi_version}:api"
    modLocalRuntime("dev.emi:emi-neoforge:${emi_version}")
}
