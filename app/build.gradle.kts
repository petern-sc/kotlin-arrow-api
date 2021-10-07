val mainClassFile = "arrow.api.AppKt"

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.30"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.30"
    kotlin("kapt") version "1.5.30"

    application
}

repositories {
    mavenCentral()
}

val arrowVersion = "0.13.2"
val http4kVersion = "4.12.0.1"
val kotlinxVersion = "1.2.2"
val kotlinLoggingVersion = "2.0.10"
val logbackVersion = "1.2.6"
val slf4jVersion = "2.0.0-alpha5"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    implementation("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("io.arrow-kt:arrow-meta:$arrowVersion")
    implementation("io.arrow-kt:arrow-fx-coroutines:$arrowVersion")
    kapt("io.arrow-kt:arrow-meta:$arrowVersion")

    implementation("org.http4k:http4k-core:$http4kVersion")
    implementation("org.http4k:http4k-format-kotlinx-serialization:$http4kVersion")
    implementation("org.http4k:http4k-server-jetty:$http4kVersion")
    implementation("org.http4k:http4k-client-okhttp:$http4kVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxVersion")

    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")


    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

application {
    // Define the main class for the application.
    mainClass.set(mainClassFile)
}
