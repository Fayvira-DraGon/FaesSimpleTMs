plugins {
    id("java")
    id("dev.architectury.loom") version("1.7-SNAPSHOT")
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
    modImplementation("net.fabricmc:fabric-loader:${project.property("fabric_loader_version")}")

    modRuntimeOnly("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}")
    modImplementation(fabricApi.module("${project.property("fabric_command_api_version")}", "${project.property("fabric_api_version")}"))

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