plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":billing:domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(project(":shared")) // <--- shared
    runtimeOnly("org.postgresql:postgresql")
}