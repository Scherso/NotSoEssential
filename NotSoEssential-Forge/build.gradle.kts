import dev.architectury.pack200.java.Pack200Adapter

plugins {
    idea
    java
    id("gg.essential.loom") version ("0.10.0.+")
    id("dev.architectury.architectury-pack200") version ("0.1.3")
    id("net.kyori.blossom") version ("1.3.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

val projectName:    String by project
val projectId:      String by project
val projectVersion: String by project
val projectGroup:   String by project
val mcVersion:      String = property("minecraft.version").toString()

version = projectVersion
group   = projectGroup

blossom {
    replaceToken("@VER@", projectVersion)
    replaceToken("@NAME@", projectName)
    replaceToken("@ID@", projectId)
}

repositories.mavenCentral()

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")
}

loom {
    silentMojangMappingsLicense()
    launchConfigs {
        getByName("client") {
            property("NSE.debugBytecode", "true")
            /* Ensuring that the forge mod is loaded as a core-mod.
             * @see com.github.u9g.notsoessential.FMLPlugin */
            arg("-Dfml.coreMods.load", "com.github.u9g.notsoessential.FMLPlugin")
        }
    }

    forge.pack200Provider.set(Pack200Adapter())
    runConfigs.getByName("client").isIdeConfigGenerated = true
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    inputs.property("version", projectVersion)
    inputs.property("mcversion", mcVersion)
    inputs.property("id", projectId)

    filesMatching("mcmod.info") {
        expand(
            "id" to projectId,
            "version" to projectVersion,
            "mcversion" to mcVersion
        )
    }
}

tasks.named<Jar>("jar") {
    archiveBaseName.set(projectName)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest.attributes.run {
        this["Manifest-Version"] = "1.0"
        this["FMLCorePlugin"] = "com.github.u9g.notsoessential.FMLPlugin"
        this["ModType"] = "FML"
        this["FMLCorePluginContainsFMLMod"] = "true"
        this["ForceLoadAsMod"] = "true"
        this["TweakOrder"] = "0"
    }
}
