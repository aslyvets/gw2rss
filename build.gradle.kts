import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"

    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}

group = "com.jedicoder"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-mustache")
    implementation(group = "com.squareup.okhttp", name = "okhttp", version = "2.7.5")
    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.5")

    testImplementation(group = "org.assertj", name = "assertj-core", version = "3.14.0")
    testImplementation(group = "org.springframework.boot", name = "spring-boot-starter-test")
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
    testRuntime(group = "org.junit.jupiter", name = "junit-jupiter-engine")
}

tasks.register<Task>("stage") {
    group = "build"
    description = "stage"
    dependsOn("build")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
