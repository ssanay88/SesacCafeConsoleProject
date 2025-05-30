plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("kapt") version "2.1.10"
}

group = "project.sesaccafe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("com.squareup.moshi:moshi:1.15.1")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}