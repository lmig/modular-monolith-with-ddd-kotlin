plugins {
    kotlin("jvm")
    id("org.springframework.boot") version "3.2.0" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-context:6.0.14")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    runtimeOnly("org.postgresql:postgresql")
}