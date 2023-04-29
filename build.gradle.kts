import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
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

application {
    mainClass.set("MainKt")
}