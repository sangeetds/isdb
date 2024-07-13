import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.5.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
    kotlin("plugin.jpa") version "1.4.30"
    kotlin("kapt") version "1.4.10"
}

group = "com.isdb"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    val moshiVersion = "1.12.0"
    val retrofitVersion = "2.9.0"

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //retrofit and moshi
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    // kotlin coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

    // spring web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")

    // spring mongo
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // spring API docs
    implementation("org.springdoc:springdoc-openapi-ui:1.5.10")

    // Kotlin Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotlin logging
    implementation("io.github.microutils:kotlin-logging:2.0.10")

    // Spring Rest Client Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")

    // Kotlin Mocking
    testImplementation("io.mockk:mockk:1.12.0")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Truth testing lib
    testImplementation("com.google.truth:truth:1.4.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootJar> {
	mainClass.set("com.isdb.IsdbBackendApplicationKt")
	enabled = true
}