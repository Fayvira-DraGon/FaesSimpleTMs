plugins {
    id("java")
    id("dev.architectury.loom") version("1.7-SNAPSHOT")
    id("architectury-plugin") version("3.4-SNAPSHOT")
    kotlin("jvm") version "2.0.21"
}

group = "git.dragomordor.simpletms"
version = "1.2.0-1.21.1"

architectury {
    platformSetupLoomIde()
    fabric()
}

loom {
    silentMojangMappingsLicense()

    mixin {
        defaultRefmapName.set("mixins.${project.name}.refmap.json")
    }
}

repositories {
    mavenCentral()
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://maven.impactdev.net/repository/development/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    minecraft("net.minecraft:minecraft:${project.property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}")
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")

    modRuntimeOnly("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")
    modImplementation(fabricApi.module("fabric-command-api-v2", "${project.property("fabric_version")}"))

    modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("kotlin_version")}")
    modImplementation("com.cobblemon:fabric:${project.property("cobblemon_version")}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${project.property("junit-jupiter-api_version")}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${project.property("junit-jupiter-engine_version")}")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand(project.properties)
    }
}