plugins {
    kotlin("jvm")
    id("io.spring.dependency-management") version "1.1.0"
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        // Alinea con la versión de Spring Boot usada en el root
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.2.0")
    }
}

dependencies {
    implementation("org.springframework:spring-context:6.0.14")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    runtimeOnly("org.postgresql:postgresql")
}