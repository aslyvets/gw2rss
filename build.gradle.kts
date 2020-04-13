import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.71"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "com.jedicoder"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

dependencies {
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    implementation(group = "com.squareup.okhttp", name = "okhttp", version = "2.7.5")
    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.5")
    implementation(group = "io.ktor", name = "ktor-server-core", version = "1.3.1")
    implementation(group = "io.ktor", name = "ktor-server-netty", version = "1.3.1")
    implementation(group = "io.ktor", name = "ktor-mustache", version = "1.3.1")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}

tasks.register<Task>("stage") {
    group = "build"
    description = "stage"
    dependsOn("build")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
