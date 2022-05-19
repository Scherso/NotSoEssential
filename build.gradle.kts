import dev.architectury.pack200.java.Pack200Adapter

plugins {
    kotlin("jvm") version("1.6.21")
    id("dev.architectury.architectury-pack200") version("0.1.3")
    id("gg.essential.loom") version("0.10.0.+")
    id("net.kyori.blossom") version("1.3.0")
    java
}

val projectName: String by project
val projectId: String by project
val projectVersion: String by project
val projectGroup: String by project
val mcVersion: String =
    property("minecraft.version")?.toString() ?: throw IllegalStateException("minecraft.version is not set...")

version = projectVersion
group = projectGroup

blossom {
    replaceToken("@VER@", projectVersion)
    replaceToken("@NAME@", projectName)
    replaceToken("@ID@", projectId)
}

loom {
    launchConfigs {
        getByName("client") {
            arg("--tweakClass", "gg.essential.loader.stage0.EssentialSetupTweaker")
            arg("--mixin", "mixins.${projectId}.json")
        }
    }

    runConfigs {
        getByName("client") {
            isIdeConfigGenerated = true
        }
    }

    forge {
        pack200Provider.set(Pack200Adapter())
        mixinConfig("mixins.${projectId}.json")
        mixin.defaultRefmapName.set("mixins.${projectId}.refmap.json")
    }
}

repositories {
    mavenCentral()
    maven("https://repo.essential.gg/repository/maven-public/")
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

val embed by configurations.creating
configurations.implementation.get().extendsFrom(embed)

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")
    annotationProcessor("org.spongepowered:mixin:0.8.5-SNAPSHOT:processor")

    embed(libs.essentialwrapper)
    implementation(libs.essential)

    compileOnly(libs.mixin)
}

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        inputs.property("version", projectVersion)
        inputs.property("mcversion", mcVersion)
        inputs.property("name", projectName)
        inputs.property("id", projectId)

        filesMatching("mcmod.info") {
            expand(
                "id" to projectId,
                "name" to projectName,
                "version" to projectVersion,
                "mcversion" to mcVersion
            )
        }

        filesMatching("mixins.${projectId}.json") {
            expand(
                "id" to projectId
            )
        }
    }

    named<Jar>("jar") {
        archiveBaseName.set(projectName)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest.attributes(
            "ModSide" to "CLIENT",
            "ForceloadAsMod" to true,
            "TweakClass" to "gg.essential.loader.stage0.EssentialSetupTweaker",
            "TweakOrder" to "0",
            "MixinConfigs" to "mixins.${projectId}.json"
        )
    }
}

kotlin {
    jvmToolchain {
        check(this is JavaToolchainSpec)
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}
