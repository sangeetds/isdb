import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    id("java-library")
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.dorongold.task-tree") version "1.5"

    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
    kotlin("kapt") version "1.4.10"

    java
***REMOVED***

group = "com.isdb"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") ***REMOVED***
    maven { url = uri("https://repo.spring.io/snapshot") ***REMOVED***
***REMOVED***

subprojects {
    repositories {
        mavenCentral()
        jcenter()
  ***REMOVED***

    apply {
        plugin("io.spring.dependency-management")

  ***REMOVED***
***REMOVED***

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(project(":login"))
    implementation(project(":spotify"))
    implementation(project(":application"))

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
***REMOVED***

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
  ***REMOVED***
***REMOVED***

tasks.test {
    useJUnit()
    useJUnitPlatform()
***REMOVED***

tasks.withType<BootJar> {
	mainClassName = "com.isdb.ApplicationKt"
	enabled = true
***REMOVED***

tasks.withType<Jar> {
	archiveBaseName.set("app-module")
	archiveVersion.set("0.0.1")
***REMOVED***