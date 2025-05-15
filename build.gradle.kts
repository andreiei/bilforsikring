import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `version-catalog`
    alias(libs.plugins.kotlin.jvm) apply true
    alias(libs.plugins.spotless) apply true
    alias(libs.plugins.cutterslade.analyze) apply true
    alias(libs.plugins.kotlin.serialization) apply true
    alias(libs.plugins.dotenv) apply true
}

repositories {
    mavenCentral()
}

dependencies {
    permitUnusedDeclared(libs.kotlin.stdlib)
    permitUnusedDeclared(libs.kotlin.stdlib.jdk8)
}

allprojects {
    group = "com.airthings.location"

    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "ca.cutterslade.analyze")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    repositories {
        mavenCentral()
        airthings()
    }

    tasks.withType<KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
            showStackTraces = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }

    /*tasks.register("runSpotlessApply", org.gradle.api.DefaultTask::class.java) {
        dependsOn("spotlessApply")
        doLast {
            exec {
                workingDir(project.rootDir)
                commandLine("./gradlew", "spotlessApply")
            }
        }
    }
    tasks.named("build") {
        dependsOn("runSpotlessApply")
    }*/

    spotless {
        kotlin {
            ktlint(libs.versions.ktlint.get())
        }
        kotlinGradle {
            ktlint(libs.versions.ktlint.get())
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
    }
}

fun RepositoryHandler.airthings() =
    maven {
        name = "com.airthings.github.packages"
        url = uri("https://maven.pkg.github.com/Airthings/*")
        credentials {
            username = env.fetchOrNull("GITHUB_USERNAME")
                ?: System.getenv("GITHUB_USERNAME")
                ?: System.getenv("GITHUB_ACTOR")
            password = env.fetchOrNull("GITHUB_ACCESS_TOKEN_READ_PACKAGES")
                ?: System.getenv("GITHUB_ACCESS_TOKEN_READ_PACKAGES")
        }
    }
