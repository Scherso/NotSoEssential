import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.architectury.pack200.java.Pack200Adapter
import net.fabricmc.loom.task.RemapJarTask

plugins {
    idea
    java
    id("gg.essential.loom") version "0.10.0.+"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    id("com.github.johnrengelman.shadow") version "7.1.+"
    id("net.kyori.blossom") version ("1.3.+")
}

val projectName: String by project
val projectId: String by project
val projectVersion: String by project
val projectGroup: String by project
val mcVersion: String =
    property("minecraft.version")?.toString() ?: throw IllegalStateException("minecraft.version is not set.")

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
            arg("-Dfml.coreMods.load", "com.github.u9g.notsoessential.plugin.FMLPlugin")
        }
    }

    runConfigs {
        getByName("client") {
            isIdeConfigGenerated = true
        }
    }

    forge.pack200Provider.set(Pack200Adapter())
}

repositories.mavenCentral()

val shade by configurations.creating
configurations.implementation.get().extendsFrom(shade)

dependencies {
    minecraft(libs.minecraft)
    mappings(libs.mappings)
    forge(libs.forge)
}

sourceSets.main {
    output.setResourcesDir(file("${buildDir}/classes/java/main"))
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

        dependsOn(compileJava)
    }

    named<Jar>("jar") {
        archiveBaseName.set(projectName)
        manifest.attributes.run {
            this["FMLCorePlugin"] = "com.github.u9g.notsoessential.plugin.FMLPlugin"
            this["ModType"] = "FML"
            this["FMLCorePluginContainsFMLMod"] = "true"
            this["ForceLoadAsMod"] = "true"
            this["TweakOrder"] = "0"
        }

        dependsOn(shadowJar)
        enabled = false
    }

    named<RemapJarTask>("remapJar") {
        archiveBaseName.set(projectName)
        input.set(shadowJar.get().archiveFile)
    }

    named<ShadowJar>("shadowJar") {
        archiveBaseName.set(projectName)
        archiveClassifier.set("all-dev")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        configurations = listOf(shade)

        exclude(
            "**/LICENSE.md",
            "**/LICENSE.txt",
            "**/LICENSE",
            "**/NOTICE",
            "**/NOTICE.txt",
            "pack.mcmeta",
            "dummyThing",
            "**/module-info.class",
            "META-INF/proguard/**",
            "META-INF/maven/**",
            "META-INF/versions/**",
            "META-INF/com.android.tools/**",
            "fabric.mod.json"
        )
        mergeServiceFiles()
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}