pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        // Architectury
        maven("https://maven.architectury.dev/")
        // Fabric
        maven("https://maven.fabricmc.net/")
        // Essential
        maven("https://repo.essential.gg/repository/maven-public/")
        // Forge
        maven("https://maven.minecraftforge.net/")
        // Jitpack
        maven("https://jitpack.io/")
    }
}

val projectName: String by settings
rootProject.name = projectName
