plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":orders:domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.flywaydb:flyway-core")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(project(":shared")) // <--- usar el módulo shared
    runtimeOnly("org.postgresql:postgresql")
}