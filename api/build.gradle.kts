dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.logback.classic)
    implementation(libs.slf4j.api)
    implementation(libs.jetbrains.annotations)
    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.serialization.kotlinx.core.jvm)
    implementation(libs.serialization.kotlinx.json)
    implementation(libs.serialization.kotlinx.hocon)
    implementation(libs.typesafe.config)

    implementation(libs.aws.dynamodb)
    implementation(libs.aws.secretsmanager)
    implementation(libs.aws.regions)
    implementation(libs.aws.http.client.spi)
    implementation(libs.aws.core)
    implementation(libs.aws.sdk.core)

    implementation(libs.vertx.core)
    implementation(libs.vertx.coroutines)
    implementation(libs.vertx.kafka)

    implementation(libs.aws.regions)
    implementation(libs.aws.lambda.java.core)
    implementation(libs.aws.lambda.java.events)
    implementation(libs.aws.secretsmanager)
    implementation(libs.aws.dynamodb)
    implementation(libs.aws.url.connection.client)

    implementation(libs.avro4k.core)
    implementation(libs.avro)

    implementation(libs.aws.msk.iam.auth)
    permitUnusedDeclared(libs.aws.msk.iam.auth)
}

tasks {
    val buildZip by creating(Zip::class) {
        from(compileKotlin)
        from(processResources)
        from(compileJava)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        into("lib") {
            from(configurations.runtimeClasspath)
        }
    }
    build {
        dependsOn(buildZip)
    }
}

kotlin {
    jvmToolchain(21)
}