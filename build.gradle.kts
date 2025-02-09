plugins {
    id("java")
    id("dev.architectury.loom") version("1.9-SNAPSHOT")
    id("architectury-plugin") version("3.4-SNAPSHOT")
    kotlin("jvm") version "2.0.21"
}

group = "${project.property("mod_group_id")}"
version = "${project.property("mod_version")}"

architectury {
    platformSetupLoomIde()
    fabric()
}

loom {
    mixin {
        defaultRefmapName.set("${project.name}.refmap.json")
    }
    runs {
        named("client") {
            property("devauth.enabled", "true") // enable devauth, & on first run generate config & login link in log
            property("devauth.account", "main") // account type: minecraft
        }
    }
}

repositories {
    mavenCentral()
    maven("https://maven.impactdev.net/repository/development/") // cobblemon
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1") // devauth
}

dependencies {
    minecraft("net.minecraft:minecraft:${project.property("minecraft_version")}") // minecraft
    mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}") // mappings
    modImplementation("net.fabricmc:fabric-loader:${project.property("fabric_loader_version")}") // mod loader
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}") // fabric api
    modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("kotlin_version")}") // kotlin
    modImplementation("com.cobblemon:fabric:${project.property("cobblemon_version")}") // cobblemon
    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:1.2.1") // devauth
}

tasks.processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") { expand(project.properties) }
}
