/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm")
    kotlin("plugin.spring")

    // Apply the application plugin to add support for building a CLI application.
    application
***REMOVED***

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
***REMOVED***

dependencies {
    implementation(project(":login"))

    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot:2.4.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.4.0")
    implementation("org.springframework.boot:spring-boot-starter-web:2.4.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.4.0")

    implementation("com.adamratzman:spotify-api-kotlin-core:3.5.06")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
***REMOVED***

application {
    // Define the main class for the application.
    mainClassName = "application.AppKt"
***REMOVED***
