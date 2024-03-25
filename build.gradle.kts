plugins {
    kotlin("jvm") version "1.9.22"
}

group = "dev.sakkke"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    from({
        configurations.runtimeClasspath.get().filter { it.isDirectory }.map { it }
        configurations.runtimeClasspath.get().filter { !it.isDirectory }.map { zipTree(it) }
    })
}
kotlin {
    jvmToolchain(17)
}