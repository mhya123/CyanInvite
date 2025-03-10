plugins {
    java
    kotlin("jvm") version "2.0.20"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    maven("https://nexus.cyanbukkit.cn/repository/maven-public/")
    maven("https://maven.elmakers.com/repository")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly(fileTree("libs") { include("*.jar") })
    implementation(project(":core"))
    compileOnly("com.zaxxer:HikariCP:4.0.3")
}


val group = "cn.cyanbukkit.invatespigot" // 先更改这里
version = "0.2"

bukkit {
    name = rootProject.name // 设置插件的名字 已设置跟随项目名
    description = "Spigot/paper 插件" // 设置插件的描述
    authors = listOf("CyanBukkit") // 设置插件作者
    website = "https://cyanbukkit.cn" // 设置插件的网站
    main = "${group}.cyanlib.launcher.CyanPluginLauncher" // 设置插件的主类 修改请到group修改
}


kotlin {
    jvmToolchain(8)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveFileName.set("${rootProject.name}-spigot-${version}.jar")
    }
}