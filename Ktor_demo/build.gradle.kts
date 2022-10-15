import com.google.cloud.tools.gradle.appengine.appyaml.AppEngineAppYamlExtension

val ktor_version = "2.1.2"
val kotlin_version = "1.7.10"
val gce_logback_version = "0.123.6-alpha"

plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("com.google.cloud.tools.appengine") version "2.4.4"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

configure<AppEngineAppYamlExtension> {
    stage {
        setArtifact("build/libs/${project.name}-all.jar")
    }
    deploy {
        version = "GCLOUD_CONFIG"
        projectId = "GCLOUD_CONFIG"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("com.google.cloud:google-cloud-logging-logback:$gce_logback_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}