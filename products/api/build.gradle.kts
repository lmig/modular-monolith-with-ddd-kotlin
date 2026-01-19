plugins {
    id("org.springframework.boot") version "3.2.0"
    kotlin("jvm")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":products:application"))
    implementation(project(":products:infrastructure"))
    implementation(project(":shared")) // <-- shared
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}