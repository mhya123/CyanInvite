plugins {
    kotlin("jvm") version "2.0.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "cn.cyanbukkit.invatebungee"
version = "0.2-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/public/") {
        name = "aliyun"
    }
}

dependencies {
    implementation(project(":core"))
    compileOnly(fileTree("libs") { include("*.jar") })
    implementation("com.zaxxer:HikariCP:4.0.3")
}

val targetJavaVersion = 8
    kotlin {
        jvmToolchain(targetJavaVersion)
    }

tasks.build {
    dependsOn("shadowJar")
}

tasks {
    shadowJar {
        archiveFileName.set("${rootProject.name}-bungee-${version}.jar")
    }
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("bungee.yml") {
        expand(props)
    }
}
