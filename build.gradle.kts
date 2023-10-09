import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.8.22"

    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

group = "com.plus"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

object Versions{
    const val QUERY_DSL = "5.0.0"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module= "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")

    implementation("com.querydsl:querydsl-jpa:${Versions.QUERY_DSL}:jakarta")
    kapt("com.querydsl:querydsl-apt:${Versions.QUERY_DSL}:jakarta")
    kapt("jakarta.persistence:jakarta.persistence-api")
    testAnnotationProcessor("com.querydsl:querydsl-apt:${Versions.QUERY_DSL}:jakarta")
    testAnnotationProcessor("jakarta.persistence:jakarta.persistence-api")
    testImplementation("com.querydsl:querydsl-jpa:${Versions.QUERY_DSL}:jakarta")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
