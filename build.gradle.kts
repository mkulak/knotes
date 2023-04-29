import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jib)
    application
}

group = "me.mkulak"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.vertx)
    implementation(libs.bundles.jackson)
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

val mainClazz by extra("me.mkulak.knotes.MainKt")

application {
    mainClass.set(mainClazz)
    applicationDefaultJvmArgs = listOf(
        "-server",
        "-Djava.awt.headless=true",
        "-XX:+UseZGC",
        "-Xms128m",
        "-Xmx256m",
    )
}

jib {
    from {
        image = "eclipse-temurin:17.0.2_8-jre"
    }
    to {
        image = "knotes"
    }
    container {
        ports = listOf("8080")
        mainClass = mainClazz
        creationTime = "USE_CURRENT_TIMESTAMP"

        jvmFlags = listOf(
            "-server",
            "-Djava.awt.headless=true",
            "-XX:+UseZGC",
            "-XX:MaxRAMPercentage=90",
            "-XX:+ExitOnOutOfMemoryError"
        )
    }
}

tasks.processResources {
    from("openapi.yml")
}
