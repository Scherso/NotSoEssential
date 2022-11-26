import dev.architectury.pack200.java.Pack200Adapter

plugins {
    idea
    java
    id("gg.essential.loom") version "0.10.0.+"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    id("net.kyori.blossom") version ("1.3.1")
}

val projectName:    String by project
val projectId:      String by project
val projectVersion: String by project
val projectGroup:   String by project
val mcVersion:      String = property("minecraft.version")?.toString() ?: throw IllegalStateException("minecraft.version is not set.")

version = projectVersion
group = projectGroup

blossom {
    replaceToken("@VER@", projectVersion)
    replaceToken("@NAME@", projectName)
    replaceToken("@ID@", projectId)
}

loom {
    silentMojangMappingsLicense()
    launchConfigs {
        getByName("client") {
            property("debugBytecode", "true")
            property("asmhelper.verbose", "true")
            property("fml.coreMods.load", "com.github.u9g.notsoessential.FMLPlugin")
            arg("-Dfml.coreMods.load", "com.github.u9g.notsoessential.FMLPlugin")
        }
    }

    forge.pack200Provider.set(Pack200Adapter())
    runConfigs.getByName("client").isIdeConfigGenerated = true
}

repositories.mavenCentral()

dependencies {
    minecraft(libs.minecraft)
    mappings(libs.mappings)
    forge(libs.forge)
}

tasks.processResources {
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

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}