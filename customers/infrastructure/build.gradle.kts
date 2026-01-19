plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":customers:domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(project(":shared")) // <--- dependencia shared
    runtimeOnly("org.postgresql:postgresql")
}