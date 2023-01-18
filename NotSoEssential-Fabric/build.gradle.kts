plugins {
	idea
	java
	id("fabric-loom") version ("1.0-SNAPSHOT")
	id("net.kyori.blossom") version ("1.3.1")
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

val projectName: String by project
val projectId: String by project
val projectVersion: String by project
val projectGroup: String by project
val yarnMappings: String = property("yarn_mappings").toString()
val loaderVersion: String = property("loader_version").toString()
val fabricVersion: String = property("fabric_version").toString()
val mcVersion: String = property("minecraft.version").toString()

version = projectVersion
group = projectGroup

blossom {
	replaceToken("@VER@", projectVersion)
	replaceToken("@NAME@", projectName)
	replaceToken("@ID@", projectId)
}

repositories.mavenCentral()

dependencies {
	minecraft("com.mojang:minecraft:${mcVersion}")
	mappings("net.fabricmc:yarn:${yarnMappings}:v2")
	modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${fabricVersion}")
}

tasks.processResources {
	inputs.property("version", projectVersion)
	inputs.property("mcversion", mcVersion)
	inputs.property("id", projectId)

	filesMatching("fabric.mod.json") {
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

	from("LICENSE") {
		rename { "\"${it}_${archiveBaseName}\"" }
	}
}
